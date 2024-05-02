package net.archiloque.tacticalnexus.datapreparation.output.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.enums.Direction
import net.archiloque.tacticalnexus.datapreparation.enums.EnemyType
import net.archiloque.tacticalnexus.datapreparation.enums.KeyOrDoorColor
import net.archiloque.tacticalnexus.datapreparation.enums.ScoreType
import net.archiloque.tacticalnexus.datapreparation.input.level.StaircaseDirection

@Serializable
sealed interface Tile {

}

@Serializable
@SerialName("door")
class DoorTile(val color: KeyOrDoorColor) : Tile {
}

@Serializable
@SerialName("enemy")
class EnemyTile(val enemyType: EnemyType, val level: Int) : Tile {
}

@Serializable
@SerialName("item")
class ItemTile(val name: String) : Tile {
}

@Serializable
@SerialName("key")
class KeyTile(val color: KeyOrDoorColor) : Tile {
}

@Serializable
@SerialName("staircase")
class StaircaseTile(val direction: StaircaseDirection) : Tile {
}

@Serializable
@SerialName("startingPosition")
class PlayerStartPositionTile() : Tile {
}

@Serializable
@SerialName("wall")
class WallTile() : Tile {
}

@Serializable
@SerialName("empty")
class EmptyTile() : Tile {
}

@Serializable
@SerialName("oneWay")
class OneWayTile(val direction: Direction) : Tile {
}

@Serializable
@SerialName("score")
class ScoreTile(val scoreType: ScoreType, val type: TileType = TileType.score) : Tile {
}
