package playerModule.fieldComponent

import gamestate.GameStatus
import org.scalatest.{Matchers, WordSpec}
import playerModule.fieldComponent.fieldBaseImpl.Building
import playerModule.playerComponent.playerBaseImpl.Player
import playerModule.util.FieldIterator

class BuildingSpec extends WordSpec with Matchers {
    "A Building" when {
        "new" should {
            val building = Building("buildingName", 100)
            "have a name" in {
                building.getName should be("buildingName")
            }
            "have a price" in {
                building.getPrice should be(100)
            }
            "not be bought" in {
                building.isBought should be(false)
            }
            "be buyable" in {
                val boughtBuilding = building.setBought()
                boughtBuilding.getIsBought should be(true)
            }
            "get the correct action" should {
                "can buy" in {
                    val player = Player("player", 1500, building, Set(), new FieldIterator(List(building)))
                    building.action(player) should be(GameStatus.CAN_BUY)
                }
                "bought by other" in {
                    val boughtBuilding = building.setBought()
                    val player = Player("player", 1500, boughtBuilding, Set(), new FieldIterator(List(boughtBuilding)))
                    boughtBuilding.action(player) should be(GameStatus.BOUGHT_BY_OTHER)
                }
                "bought by same" in {
                    val boughtBuilding = building.setBought()
                    val player = Player("player", 1500, boughtBuilding, Set(boughtBuilding), new FieldIterator(List(boughtBuilding)))
                    boughtBuilding.action(player) should be(GameStatus.ALREADY_BOUGHT)
                }
            }
        }
    }
}
