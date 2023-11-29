package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.entities.Position

abstract class PlayEntitySinglePosition(entityIndex: Int, position: Position) : PlayEntity(entityIndex) {

    private val positions = arrayOf(position)

    override fun getPositions(): Array<Position> {
        return positions
    }
}
