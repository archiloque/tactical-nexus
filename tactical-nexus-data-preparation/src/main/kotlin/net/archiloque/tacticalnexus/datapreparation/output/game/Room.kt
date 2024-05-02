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
        val tiles =
            Array(LEVEL_DIMENSION_CELLS + 1) { Array<Tile>(LEVEL_DIMENSION_CELLS + 1) { EmptyTile() } }

        fun fromInput(input: TowerLevel): Room {
            for (entity in input.allEntities()) {
                val tile = getTile(entity)
                tiles[entity.y / PIXELS_PER_CELL][entity.x / PIXELS_PER_CELL] = tile
            }

            return Room(
                input.identifier,
                tiles,
            )
        }

        private fun getTile(entity: Entity): Tile {
            when (entity) {
                is Door -> return DoorTile(entity.color())
                is Enemy -> return EnemyTile(entity.type(), entity.level())
                is Item -> return ItemTile(entity.identifier())
                is Key -> return KeyTile(entity.color())
                is OneWay -> return OneWayTile(entity.direction())
                is PlayerStartPosition -> return PlayerStartPositionTile()
                is Score -> return ScoreTile(entity.score())
                is Staircase -> return StaircaseTile(entity.direction())
                is Wall -> return WallTile()
            }
        }
    }
}