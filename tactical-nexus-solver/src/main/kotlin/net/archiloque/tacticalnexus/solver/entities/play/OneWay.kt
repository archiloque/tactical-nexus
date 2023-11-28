package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.Direction
import net.archiloque.tacticalnexus.solver.entities.Position

class OneWay(private val direction: Direction, private val entityIndex: Int, val position: Position) :
    PlayEntitySinglePosition(entityIndex, position) {

    override fun getType(): PlayEntityType {
        return PlayEntityType.OneWay
    }

    override fun description(): Array<PositionedDescription> {
        return arrayOf(PositionedDescription("Go through one way", position))
    }

    override fun toString(): String {
        return "One way going $direction at $position and index $entityIndex"
    }

    override fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        TODO()
    }
}