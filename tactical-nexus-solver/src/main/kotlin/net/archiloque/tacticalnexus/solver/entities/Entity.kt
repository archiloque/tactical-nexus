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
        // We optimize for cases where the decision is automatic so we avoid adding more states:
        // - staircases
        // - keys
        // - items
        // - enemies that can be killed without loosing any HP and without leveling up (because levelling up requires creating branches)
        val positionsToAdd = playableTower.reachable[entityIndex].toMutableList()
        while (positionsToAdd.isNotEmpty()) {
            val positionToAdd = positionsToAdd.removeLast()
            val elementToAdd = playableTower.positionedEntities[positionToAdd]
            if ((!state.visited.get(positionToAdd)) && (!state.reachable.get(positionToAdd))) {
                when (elementToAdd.entity.getType()) {
                    EntityType.Staircase -> {
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                    }

                    EntityType.Key -> {
                        val key = elementToAdd.entity as Key
                        key.apply(state)
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                    }

                    EntityType.Item -> {
                        val item = elementToAdd.entity as Item
                        item.apply(state)
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                    }

                    EntityType.Enemy -> {
                        val enemy = elementToAdd.entity as Enemy
                        val killEnemyNoHpLostAndNoLevelUp =
                            enemy.killNoHPLost(state) && (LevelUp.levelUp(state.exp) == LevelUp.levelUp(
                                state.exp + enemy.earnXp(state)
                            ))
                        if (killEnemyNoHpLostAndNoLevelUp) {
                            enemy.apply(state)
                            enemy.drop.apply(state)
                            addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                        } else {
                            state.reachable.set(positionToAdd)
                        }
                    }

                    else -> {
                        state.reachable.set(positionToAdd)
                    }
                }
            }
        }
    }

    private fun addNewPosition(
        state: State,
        positionToAdd: Int,
        positionsToAdd: MutableList<Int>,
        playableTower: PlayableTower,
    ) {
        state.visited.set(positionToAdd)
        state.moves = state.moves.plus(positionToAdd)
        positionsToAdd.addAll(playableTower.reachable[positionToAdd])
    }
}
