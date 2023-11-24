package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Position

class Key(private val color: KeyOrDoorColor, private val entityIndex: Int, val position: Position) :
    PlayEntitySinglePosition(entityIndex, position) {

    override fun getType(): PlayEntityType {
        return PlayEntityType.Key
    }

    override fun description(): Array<PositionedDescription> {
        return arrayOf(PositionedDescription("Grab $color key", position))
    }

    override fun toString(): String {
        return "Key $color at $position and index $entityIndex"
    }

    override fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        val newState = newState(entityIndex, state)
        apply(newState)
        if (addNewReachablePositions(
                entityIndex,
                newState,
                playableTower,
                stateManager
            )
        ) {
            stateManager.save(newState)
        }
    }

    fun apply(state: State) {
        when (color) {
            KeyOrDoorColor.blue -> state.blueKeys = (state.blueKeys + 1).toShort()
            KeyOrDoorColor.crimson -> state.crimsonKeys = (state.crimsonKeys + 1).toShort()
            KeyOrDoorColor.platinum -> state.platinumKeys = (state.platinumKeys + 1).toShort()
            KeyOrDoorColor.violet -> state.violetKeys = (state.violetKeys + 1).toShort()
            KeyOrDoorColor.yellow -> state.yellowKeys = (state.yellowKeys + 1).toShort()
        }
    }
}

