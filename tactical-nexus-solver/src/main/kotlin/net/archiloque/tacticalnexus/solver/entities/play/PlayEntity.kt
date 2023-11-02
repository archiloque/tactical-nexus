package net.archiloque.tacticalnexus.solver.entities.play

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus

data class PositionedDescription(val description: String, val position: Position)

interface PlayEntity {
    fun getType(): PlayEntityType

    fun getPositions(): Array<Position>

    fun description(): Array<PositionedDescription>

    fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    )

    fun newState(entityIndex: Int, state: State): State {
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

    fun addNewReachablePositions(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
    ) {
        // We optimize for cases where the decision is automatic, so we avoid adding more states:
        // - staircases
        // - keys
        // - items
        // - enemies that can be killed without loosing any HP and without leveling up (because levelling up requires creating branches)
        val positionsToAdd = playableTower.reachable[entityIndex].toMutableList()
        while (positionsToAdd.isNotEmpty()) {
            val positionToAdd = positionsToAdd.removeAt(positionsToAdd.lastIndex)
            val elementToAdd = playableTower.playEntities[positionToAdd]
            if ((!state.visited.get(positionToAdd)) && (!state.reachable.get(positionToAdd))) {
                when (elementToAdd.getType()) {
                    PlayEntityType.UpStaircase -> {
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                    }

                    PlayEntityType.Key -> {
                        val key = elementToAdd as Key
                        key.apply(state)
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                    }

                    PlayEntityType.ItemGroup -> {
                        val item = elementToAdd as ItemGroup
                        item.apply(state)
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                    }

                    PlayEntityType.Enemy -> {
                        val enemy = elementToAdd as Enemy
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
        positionsToAdd.addAll(playableTower.reachable[positionToAdd].asIterable())
    }
}
