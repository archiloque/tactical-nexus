package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State

data class Door(val color: KeyOrDoorColor) : Entity(), KeyOrDoor {

    override fun getType(): EntityType {
        return EntityType.Door
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
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
        when (color) {
            KeyOrDoorColor.blue -> {
                newState.blueKeys -= 1
            }

            KeyOrDoorColor.crimson -> {
                newState.crimsonKeys -= 1
            }

            KeyOrDoorColor.platinum -> {
                newState.platinumKeys -= 1
            }

            KeyOrDoorColor.violet -> {
                newState.violetKeys -= 1
            }

            KeyOrDoorColor.yellow -> {
                newState.yellowKeys -= 1
            }
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

