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
            KeyOrDoorColor.blue -> state.blueKeys += 1
            KeyOrDoorColor.crimson -> state.crimsonKeys += 1
            KeyOrDoorColor.platinum -> state.platinumKeys += 1
            KeyOrDoorColor.violet -> state.violetKeys += 1
            KeyOrDoorColor.yellow -> state.yellowKeys += 1
        }
    }
}

