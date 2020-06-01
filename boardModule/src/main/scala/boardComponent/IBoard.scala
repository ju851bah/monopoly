package boardComponent

import play.api.libs.json.JsObject
import model.fieldComponent.{Field, IBuyable}
import model.playerComponent.IPlayer
import model.util.PlayerIterator

import scala.xml.Elem

trait IBoard {

    def nextPlayer(): IPlayer

    def nextPlayerTurn(): IBoard

    def replacePlayer(player: IPlayer, newPlayer: IPlayer): IBoard

    def replaceField(field: IBuyable, newField: IBuyable): IBoard

    def getPlayerIt: PlayerIterator

    def getPlayerByName(name: String): IPlayer

    def givePlayerMoney(player: IPlayer, money: Int): IBoard

    def buildHouses(streetName: String, amount: Int): IBoard

    def getBuyer(buyable: IBuyable): Option[IPlayer]

    def getFieldByName(name: String): Option[Field]

    def getFields: List[Field]

    def getCurrentPlayer: IPlayer

    def copy(fields: List[Field], currentPlayer: IPlayer, playerIt: PlayerIterator): IBoard

    def toXml(): Elem

    def toJson(): JsObject
}
