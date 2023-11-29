package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Position

class Door(private val color: KeyOrDoorColor, entityIndex: Int, private val position: Position) :
    PlayEntitySinglePosition(entityIndex, position) {

    override fun getType(): PlayEntityType {
        return PlayEntityType.Door
    }

    override fun description(): Array<PositionedDescription> {
        return arrayOf(PositionedDescription("Open $color door", position))
    }

    override fun toString(): String {
        return "Door $color at $position and index $entityIndex"
    }

    override fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        if (!canApply(state)) {
            return
        }

        val newState = newState(entityIndex, state)
        apply(newState)
        if (addNewReachablePositions(
                newState,
                playableTower,
                stateManager
            )
        ) {
            stateManager.save(newState)
        }
    }

    fun canApply(state: State): Boolean {
        when (color) {
            KeyOrDoorColor.blue -> {
                return state.blueKeys >= 1
            }

            KeyOrDoorColor.crimson -> {
                return state.crimsonKeys >= 1
            }

            KeyOrDoorColor.greenblue -> {
                return state.greenBlueKeys >= 1
            }

            KeyOrDoorColor.platinum -> {
                return state.platinumKeys >= 1
            }

            KeyOrDoorColor.violet -> {
                return state.violetKeys >= 1
            }

            KeyOrDoorColor.yellow -> {
                return state.yellowKeys >= 1
            }
        }
    }

    fun apply(state: State) {
        when (color) {
            KeyOrDoorColor.blue -> {
                state.blueKeys = (state.blueKeys - 1).toShort()
            }

            KeyOrDoorColor.crimson -> {
                state.crimsonKeys = (state.crimsonKeys - 1).toShort()
            }

            KeyOrDoorColor.greenblue -> {
                state.greenBlueKeys = (state.greenBlueKeys - 1).toShort()
            }

            KeyOrDoorColor.platinum -> {
                state.platinumKeys = (state.platinumKeys - 1).toShort()
            }

            KeyOrDoorColor.violet -> {
                state.violetKeys = (state.violetKeys - 1).toShort()
            }

            KeyOrDoorColor.yellow -> {
                state.yellowKeys = (state.yellowKeys - 1).toShort()
            }
        }
    }
}
