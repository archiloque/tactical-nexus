package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor

class Door(val color: KeyOrDoorColor, itemIndex: Int, val position: Position) :
    PlayEntitySinglePosition(itemIndex, position) {

    override fun getType(): PlayEntityType {
        return PlayEntityType.Door
    }

    override fun description(): Array<PositionedDescription> {
        return arrayOf(PositionedDescription("Open $color door", position))
    }

    override fun toString(): String {
        return "Door $color at $position"
    }

    override fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        if (!canApply(state)) {
            return
        }

        val newState = newState(entityIndex, state)
        apply(newState)
        addNewReachablePositions(
            entityIndex,
            newState,
            playableTower

        )
        stateManager.save(newState)
    }

    fun canApply(state: State): Boolean {
        when (color) {
            KeyOrDoorColor.blue -> {
                return (state.blueKeys >= 1)
            }

            KeyOrDoorColor.crimson -> {
                return (state.crimsonKeys >= 1)
            }

            KeyOrDoorColor.platinum -> {
                return (state.platinumKeys >= 1)
            }

            KeyOrDoorColor.violet -> {
                return (state.violetKeys >= 1)
            }

            KeyOrDoorColor.yellow -> {
                return (state.yellowKeys >= 1)
            }
        }
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
}
