package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

data class Key(val color: KeyOrDoorColor) : Entity(), KeyOrDoor {

    override fun getType(): EntityType {
        return EntityType.Key
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateManager: StateManager) {
        val newState = newState(entityIndex, state)
        apply(newState)
        addNewReachablePositions(
            entityIndex,
            newState,
            playableTower
        )
        stateManager.save(newState)
    }

    fun apply(state: State) {
        when (color) {
            KeyOrDoorColor.blue -> state.blueKeys += 1
            KeyOrDoorColor.crimson -> state.crimsonKeys += 1
            KeyOrDoorColor.platinum -> state.platinumKeys += 1
            KeyOrDoorColor.violet -> state.violetKeys += 1
            KeyOrDoorColor.yellow -> state.yellowKeys += 1
        }
    }

    override fun color(): KeyOrDoorColor {
        return color
    }

}

