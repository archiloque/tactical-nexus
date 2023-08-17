package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PositionSaver
import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.Position

data class Door(val color: KeyOrDoorColor) : Entity(), KeyOrDoor {

    override fun getType(): EntityType {
        return EntityType.Door
    }

    override fun play(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver) {
        when (color) {
            KeyOrDoorColor.blue -> {
                if(position.blue_keys < 1) {
                    return
                }
            }
            KeyOrDoorColor.crimson -> {
                if(position.crimson_keys < 1) {
                    return
                }
            }
            KeyOrDoorColor.platinum -> {
                if(position.platinum_keys < 1) {
                    return
                }
            }
            KeyOrDoorColor.violet -> {
                if(position.violet_keys < 1) {
                    return
                }
            }
            KeyOrDoorColor.yellow -> {
                if(position.yellow_keys < 1) {
                    return
                }
            }
        }

        val newPosition = newPosition(entityIndex, position)
        when (color) {
            KeyOrDoorColor.blue -> {
                newPosition.blue_keys -= 1
            }
            KeyOrDoorColor.crimson -> {
                newPosition.crimson_keys -= 1
            }
            KeyOrDoorColor.platinum -> {
                newPosition.platinum_keys -= 1
            }
            KeyOrDoorColor.violet -> {
                newPosition.violet_keys -= 1
            }
            KeyOrDoorColor.yellow -> {
                newPosition.yellow_keys -= 1
            }
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

