package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State

data class Staircase private constructor(val direction: StaircaseDirection) : Entity() {
    companion object {
        val up = Staircase(StaircaseDirection.up)
        val down = Staircase(StaircaseDirection.down)
    }

    override fun getType(): EntityType {
        return EntityType.Staircase
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        when(direction) {
            StaircaseDirection.up -> {
                val newState = newState(entityIndex, state)
                addNewReachablePositions(
                    entityIndex,
                    newState,
                    playableTower,
                    stateSaver
                )
                stateSaver.save(newState)
            }
            StaircaseDirection.down -> {
                throw IllegalStateException("Should not happen")
            }
        }
    }

    enum class StaircaseDirection() {
        up,
        down
    }
}
