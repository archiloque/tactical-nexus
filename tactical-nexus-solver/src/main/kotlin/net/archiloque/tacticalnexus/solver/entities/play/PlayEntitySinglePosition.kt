package net.archiloque.tacticalnexus.solver.entities.play

abstract class PlayEntitySinglePosition(position: Position) : PlayEntity {

    private val positions = arrayOf(position)

    override fun getPositions(): Array<Position> {
        return positions
    }
}
