package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.StaircaseDirection

class Staircase(private val direction: StaircaseDirection, entityIndex: Int, private val position: Position) :
    PlayEntitySinglePosition(entityIndex, position) {

    override fun getType(): PlayEntityType {
        return PlayEntityType.UpStaircase
    }

    override fun description(): Array<PositionedDescription> {
        return emptyArray()
    }

    override fun toString(): String {
        return "Staircase $direction at $position with index $entityIndex"
    }

    override fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        TODO()
        val newState = newState(entityIndex, state)
        if (addNewReachablePositions(
                newState,
                playableTower,
                stateManager
            )
        ) {
            stateManager.save(newState)
        }
    }

}
