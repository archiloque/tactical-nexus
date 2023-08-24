package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State

data class Door(val color: KeyOrDoorColor) : Entity(), KeyOrDoor {

    override fun getType(): EntityType {
        return EntityType.Door
    }

    override fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
        newStates: MutableList<State>,
    ) {
        when (color) {
            KeyOrDoorColor.blue -> {
                if (state.blueKeys < 1) {
                    return
                }
            }

            KeyOrDoorColor.crimson -> {
                if (state.crimsonKeys < 1) {
                    return
                }
            }

            KeyOrDoorColor.platinum -> {
                if (state.platinumKeys < 1) {
                    return
                }
            }

            KeyOrDoorColor.violet -> {
                if (state.violetKeys < 1) {
                    return
                }
            }

            KeyOrDoorColor.yellow -> {
                if (state.yellowKeys < 1) {
                    return
                }
            }
        }

        val newState = newState(entityIndex, state)
        apply(newState)
        addNewReachablePositions(
            entityIndex,
            newState,
            playableTower
        )
        newStates.add(newState)
    }

    fun apply(state: State) {
        when (color) {
            KeyOrDoorColor.blue -> {
                state.blueKeys -= 1
            }

            KeyOrDoorColor.crimson -> {
                state.crimsonKeys -= 1
            }

            KeyOrDoorColor.platinum -> {
                state.platinumKeys -= 1
            }

            KeyOrDoorColor.violet -> {
                state.violetKeys -= 1
            }

            KeyOrDoorColor.yellow -> {
                state.yellowKeys -= 1
            }
        }
    }

    override fun color(): KeyOrDoorColor {
        return color
    }

}

