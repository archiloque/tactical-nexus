package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State

data class Staircase(val direction: StaircaseDirection) : Entity() {
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
                val matchingDownStaircase = playableTower.stairs[entityIndex]!!
                newState.visited.set(matchingDownStaircase)
                for(entityReachableOnNextLevel in playableTower.reachable[matchingDownStaircase]) {
                    if(newState.reachable.get(entityReachableOnNextLevel) || newState.visited.get(entityReachableOnNextLevel)) {
                        throw IllegalStateException("Should not happen")
                    }
                    newState.reachable.set(entityReachableOnNextLevel)
                }
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
