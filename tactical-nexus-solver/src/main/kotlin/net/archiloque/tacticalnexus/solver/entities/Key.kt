package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.database.State

data class Key(val color: KeyOrDoorColor) : Entity(), KeyOrDoor {

    override fun getType(): EntityType {
        return EntityType.Key
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        val newPosition = newState(entityIndex, state)
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
            playableTower,
            stateSaver
        )
    }

    override fun color(): KeyOrDoorColor {
        return color
    }

}

