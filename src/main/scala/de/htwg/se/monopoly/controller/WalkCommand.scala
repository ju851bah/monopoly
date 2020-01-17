package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.controller.GameStatus._
import de.htwg.se.monopoly.controller.controllerBaseImpl.UpdateInfo
import de.htwg.se.monopoly.model.boardComponent.{IBoard, IBuyable}
import de.htwg.se.monopoly.util.{Command, GeneralUtil}

case class WalkCommand(dice: (Int, Int), controller: IController) extends Command {
    private val backupBoard: IBoard = controller.getBoard.copy(controller.getBoard.getFields,
        controller.getBoard.getCurrentPlayer,
        controller.getBoard.getPlayerIt)
    private val backupGameString: String = controller.getCurrentGameMessage

    override def doStep(): Unit = {
        controller.controllerState = ROLLED
        controller.catCurrentGameMessage
        println("DICE: " + dice)
        val player = controller.getBoard.getCurrentPlayer
        val (newPlayer, passedGo) = player.walk(dice._1 + dice._2)

        if (passedGo) {
            controller.controllerState = PASSED_GO
            controller.catCurrentGameMessage
        }

        controller.setBoard(controller.getBoard.replacePlayer(player, newPlayer))
        controller.controllerState = NEW_FIELD
        controller.catCurrentGameMessage

        val newField = controller.getCurrentField
        // Action return ALREADY_BOUGHT, CAN_BUY or BOUGHT_BY_OTHER
        controller.controllerState = newField.action(newPlayer)
        controller.catCurrentGameMessage

        controller.controllerState match {
            case BOUGHT_BY_OTHER =>
                controller.payRent(controller.getCurrentPlayer.get, controller.getCurrentField.asInstanceOf[IBuyable],
                    controller.getBuyer(controller.getCurrentField.asInstanceOf[IBuyable]).get)
            case _ =>
        }

        if (GeneralUtil.getWholeGroups(newPlayer) != Nil) {
            controller.controllerState = CAN_BUILD
            controller.buildStatus = BuildStatus.DEFAULT
        }
    }

    override def undoStep(): Unit = {
        controller.setBoard(backupBoard)
        controller.controllerState = START_OF_TURN
        controller.currentGameMessage = backupGameString
        controller.updateCurrentPlayerInfo
        controller.publish(new UpdateInfo)
    }

    override def redoStep(): Unit = {
        doStep()
        controller.publish(new UpdateInfo)
    }
}