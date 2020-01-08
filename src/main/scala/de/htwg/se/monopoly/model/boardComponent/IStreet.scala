package de.htwg.se.monopoly.model.boardComponent

import play.api.libs.json.JsObject

trait IStreet extends IBuyable {

    def buyHouses(amount: Int): IStreet

    def setBought(): IStreet

    def getJSON: JsObject

    def copy(name: String = IStreet.this.getName,
             price: Int = IStreet.this.getPrice,
             rentCosts: Array[Int] = IStreet.this.getRentCosts,
             houseCost: Int = IStreet.this.getHouseCost,
             numHouses: Int = IStreet.this.getNumHouses,
             isBought: Boolean = IStreet.this.getIsBought): IStreet

    def getName: String

    def getPrice: Int

    def getRentCosts: Array[Int]

    def getHouseCost: Int

    def getNumHouses: Int

    def getIsBought: Boolean
}
