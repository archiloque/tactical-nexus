package net.archiloque.tacticalnexus.solver.entities

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus

abstract class Entity {
    abstract fun getType(): EntityType

    abstract fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
        newStates: MutableList<State>,
    )

    enum class EntityType() {
        Door,
        Enemy,
        Exit,
        Item,
        Key,
        PlayerStartPosition,
        Staircase,
        Wall,
    }

    protected fun newState(entityIndex: Int, state: State): State {
        if (entityIndex >= 0) {
            val visitedEntities = state.visited.clone() as BitSet
            visitedEntities.set(entityIndex)
            val reachableEntities = state.reachable.clone() as BitSet
            reachableEntities.set(entityIndex, false)
            return state.copy(
                id = -1,
                status = StateStatus.new,
                visited = visitedEntities,
                reachable = reachableEntities,
                moves = state.moves.plus(entityIndex)
            )
        } else {
            return state.copy(
                id = -1,
                status = StateStatus.new,
                moves = state.moves.plus(entityIndex)
            )
        }
    }


    protected fun addNewReachablePositions(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
    ) {
        val positionsToAdd = playableTower.reachable[entityIndex].toMutableList()
        while (positionsToAdd.isNotEmpty()) {
            val positionToAdd = positionsToAdd.removeLast()
            val elementToAdd = playableTower.positionedEntities[positionToAdd]
            if ((!state.visited.get(positionToAdd)) && (!state.reachable.get(positionToAdd))) {
                when (elementToAdd.entity.getType()) {
                    EntityType.Staircase -> {
                        // If a staircase becomes reachable we immediately treat it as being taken by adding the newly reachable positionw
                        state.visited.set(positionToAdd)
                        state.moves = state.moves.plus(positionToAdd)
                        positionsToAdd.addAll(playableTower.reachable[positionToAdd])
                    }

                    EntityType.Key -> {
                        // If a key becomes reachable we immediately treat it as being grabbed and adding the newly reachable positionw
                        val key = elementToAdd.entity as Key
                        key.apply(state)
                        state.visited.set(positionToAdd)
                        state.moves = state.moves.plus(positionToAdd)
                        positionsToAdd.addAll(playableTower.reachable[positionToAdd])
                    }

                    EntityType.Item -> {
                        val item = elementToAdd.entity as Item
                        // Items should be taken immediately
                        state.visited.set(positionToAdd)
                        state.moves = state.moves.plus(positionToAdd)
                        item.apply(state)
                        positionsToAdd.addAll(playableTower.reachable[positionToAdd])
                    }

                    else -> {
                        state.reachable.set(positionToAdd)
                    }
                }
            }
        }
    }
}
