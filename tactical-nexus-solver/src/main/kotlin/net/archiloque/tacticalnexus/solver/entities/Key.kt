package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PositionSaver
import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.Position

data class Key(val color: KeyOrDoorColor) : Entity(), KeyOrDoor {

    override fun getType(): EntityType {
        return EntityType.Key
    }

    override fun play(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver) {
        val newPosition = newPosition(entityIndex, position)
        when (color) {
            KeyOrDoorColor.blue -> newPosition.blue_keys += 1
            KeyOrDoorColor.crimson -> newPosition.crimson_keys += 1
            KeyOrDoorColor.platinum -> newPosition.platinum_keys += 1
            KeyOrDoorColor.violet -> newPosition.violet_keys += 1
            KeyOrDoorColor.yellow -> newPosition.yellow_keys += 1
        }
        addNewPositions(
            entityIndex,
            newPosition,
            tower,
            positionSaver
        )
    }

    override fun color(): KeyOrDoorColor {
        return color
    }

}

