package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State

data class Key(val color: KeyOrDoorColor) : Entity(), KeyOrDoor {

    override fun getType(): EntityType {
        return EntityType.Key
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        val newState = newState(entityIndex, state)
        when (color) {
            KeyOrDoorColor.blue -> newState.blueKeys += 1
            KeyOrDoorColor.crimson -> newState.crimsonKeys += 1
            KeyOrDoorColor.platinum -> newState.platinumKeys += 1
            KeyOrDoorColor.violet -> newState.violetKeys += 1
            KeyOrDoorColor.yellow -> newState.yellowKeys += 1
        }
        addNewReachablePositions(
            entityIndex,
            newState,
            playableTower,
            stateSaver
        )
        stateSaver.save(newState)
    }

    override fun color(): KeyOrDoorColor {
        return color
    }

}

