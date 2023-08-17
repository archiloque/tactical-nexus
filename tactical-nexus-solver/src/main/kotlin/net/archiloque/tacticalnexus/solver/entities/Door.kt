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
                if (state.blue_keys < 1) {
                    return
                }
            }

            KeyOrDoorColor.crimson -> {
                if (state.crimson_keys < 1) {
                    return
                }
            }

            KeyOrDoorColor.platinum -> {
                if (state.platinum_keys < 1) {
                    return
                }
            }

            KeyOrDoorColor.violet -> {
                if (state.violet_keys < 1) {
                    return
                }
            }

            KeyOrDoorColor.yellow -> {
                if (state.yellow_keys < 1) {
                    return
                }
            }
        }

        val newState = newState(entityIndex, state)
        when (color) {
            KeyOrDoorColor.blue -> {
                newState.blue_keys -= 1
            }

            KeyOrDoorColor.crimson -> {
                newState.crimson_keys -= 1
            }

            KeyOrDoorColor.platinum -> {
                newState.platinum_keys -= 1
            }

            KeyOrDoorColor.violet -> {
                newState.violet_keys -= 1
            }

            KeyOrDoorColor.yellow -> {
                newState.yellow_keys -= 1
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

