package de.htwg.se.monopoly.model.boardComponent
import de.htwg.se.monopoly.model.playerComponent.Player

case class Building(name: String, price: Int) extends Buyable(name, price) {
    override def action(player: Player): Unit = ???
}