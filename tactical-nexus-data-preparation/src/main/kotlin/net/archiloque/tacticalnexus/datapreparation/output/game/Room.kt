package net.archiloque.tacticalnexus.datapreparation.output.game

import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.LEVEL_DIMENSION_CELLS
import net.archiloque.tacticalnexus.datapreparation.PIXELS_PER_CELL
import net.archiloque.tacticalnexus.datapreparation.input.level.Door
import net.archiloque.tacticalnexus.datapreparation.input.level.Enemy
import net.archiloque.tacticalnexus.datapreparation.input.level.Entity
import net.archiloque.tacticalnexus.datapreparation.input.level.Item
import net.archiloque.tacticalnexus.datapreparation.input.level.Key
import net.archiloque.tacticalnexus.datapreparation.input.level.OneWay
import net.archiloque.tacticalnexus.datapreparation.input.level.PlayerStartPosition
import net.archiloque.tacticalnexus.datapreparation.input.level.Score
import net.archiloque.tacticalnexus.datapreparation.input.level.Staircase
import net.archiloque.tacticalnexus.datapreparation.input.level.TowerLevel
import net.archiloque.tacticalnexus.datapreparation.input.level.Wall

@Serializable
data class Room(
    val name: String,
    val tiles: Array<Array<Tile>>,
) {
    companion object {
        fun fromInput(input: TowerLevel): Room {
            val tiles =
                Array(LEVEL_DIMENSION_CELLS + 1) { Array<Tile>(LEVEL_DIMENSION_CELLS + 1) { EmptyTile() } }

            for (entity in input.allEntities()) {
                val tile = getTile(entity)
                val line = entity.y / PIXELS_PER_CELL
                val column = entity.x / PIXELS_PER_CELL
                tiles[line][column] = tile
            }

            return Room(
                input.identifier,
                tiles,
            )
        }

        private fun getTile(entity: Entity): Tile {
            return when (entity) {
                is Door -> DoorTile(entity.color())
                is Enemy -> EnemyTile(entity.type().name.replaceFirstChar { c -> c.uppercase() }, entity.level())
                is Item -> ItemTile(itemTitle(entity.identifier()))
                is Key -> KeyTile(entity.color())
                is OneWay -> OneWayTile(entity.direction())
                is PlayerStartPosition -> PlayerStartPositionTile()
                is Score -> ScoreTile(entity.score())
                is Staircase -> StaircaseTile(entity.direction())
                is Wall -> WallTile()
            }
        }

        private fun itemTitle(itemName: String): String {
            return when (itemName) {
                "blue_potion" -> "Blue potion"
                "drop_of_dream_ocean" -> "Drop of dream ocean"
                "golden_feather" -> "Golden feather"
                "guard_card" -> "Guard card"
                "guard_deck" -> "Guard deck"
                "guard_gem" -> "Guard gem"
                "guard_piece" -> "Guard piece"
                "guard_potion" -> "Guard potion"
                "heavenly_potion" -> "Heavenly potion"
                "life_crown" -> "Life Crown"
                "life_potion" -> "Life potion"
                "power_card" -> "Power card"
                "power_deck" -> "Power deck"
                "power_gem" -> "Power gem"
                "power_piece" -> "Power piece"
                "power_potion" -> "Power potion"
                "pulse_book_shield" -> "Pulse book <Shield>"
                "pulse_book_sword" -> "Pulse book <Sword>"
                "red_potion" -> "Red potion"
                else -> {
                    throw IllegalArgumentException("Unrecognised item: $itemName")
                }
            }
        }
    }

}