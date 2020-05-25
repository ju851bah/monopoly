package model.fieldComponent.fieldBaseImpl

import model.gamestate.GameStatus
import model.gamestate.GameStatus.GameStatus
import play.api.libs.json.{JsObject, Json}
import model.fieldComponent.IBuilding
import model.playerComponent.IPlayer

import scala.xml.Elem

case class Building(name: String, price: Int, isBought: Boolean = false) extends IBuilding {
    def setBought(): IBuilding = this.copy(isBought = true)

    def copy(name: String, price: Int, isBought: Boolean): IBuilding =
        Building(name, price, isBought)

    def getName: String = name

    def getIsBought: Boolean = isBought

    override def action(player: IPlayer): GameStatus = {
        if (isBought) {
            if (player.getBought.contains(this)) GameStatus.ALREADY_BOUGHT
            else GameStatus.BOUGHT_BY_OTHER
        }
        else GameStatus.CAN_BUY
    }

    def getPrice: Int = price

    override def toXml(): Elem = {
        <building>
            <name>name</name>
            <price>
                {price}
            </price>
            <is-bought>
                {isBought}
            </is-bought>
        </building>

    }

    override def nameToXml(): Elem = {
        <building>
            <name>name</name>
        </building>
    }

    override def toJson(): JsObject = {
        Json.obj(
            "building" -> Json.obj(
                "name" -> name,
                "price" -> price,
                "is-bought" -> isBought
            )
        )
    }

    override def nameToJson(): JsObject = {
        Json.obj(
            "field" -> Json.obj(
                "type" -> "building",
                "name" -> name
            )
        )
    }
}

object Building {
    def fromJson(json : JsObject) : Building = {
        Building(
            name = (json \ "name").get.as[String],
            price = (json \ "price").get.as[Int],
            isBought = (json \ "is-bought").get.as[Boolean]
        )
    }
}