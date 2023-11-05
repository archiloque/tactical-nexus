package net.archiloque.tacticalnexus.solver.entities.play

abstract class PlayEntitySinglePosition(private val index: Int, position: Position) : PlayEntity() {

    private val positions = arrayOf(position)

    override fun getPositions(): Array<Position> {
        return positions
    }

    override fun entityIndex(): Int {
        return index
    }
}
