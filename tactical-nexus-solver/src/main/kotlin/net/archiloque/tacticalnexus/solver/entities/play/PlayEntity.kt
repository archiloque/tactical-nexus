package net.archiloque.tacticalnexus.solver.entities.play

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.entities.Position

data class PositionedDescription(val description: String, val position: Position)

abstract class PlayEntity(val entityIndex :Int) {

    abstract fun getType(): PlayEntityType

    abstract fun getPositions(): Array<Position>

    abstract fun description(): Array<PositionedDescription>

    abstract fun play(
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
                moves = state.moves.plus(entityIndex.toShort())
            )
        } else {
            return state.copy(
                id = -1,
                status = StateStatus.new,
                moves = state.moves.plus(entityIndex.toShort())
            )
        }
    }

    fun addNewReachablePositions(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ): Boolean {
        // We optimize for cases where the decision is automatic, so we avoid adding more states:
        // - taking staircases
        // - picking keys
        // - picking items
        // - enemies that can be killed without loosing any HP and without leveling up (because levelling up requires creating branches)
        // - doors that leads to items rooms with a single exit
        var didSomethingAutomatic = false
        val newEntities = mutableListOf<PlayEntity>()
        val entitiesIndexToAdd = playableTower.reachable[entityIndex].toMutableList()

        processOneWays(entityIndex, state, playableTower, entitiesIndexToAdd)

        while (entitiesIndexToAdd.isNotEmpty()) {
            val entityIndexToAdd = entitiesIndexToAdd.removeAt(entitiesIndexToAdd.lastIndex)
            val entityToAdd = playableTower.playEntities[entityIndexToAdd]
            if ((!state.visited.get(entityIndexToAdd)) && (!state.reachable.get(entityIndexToAdd))) {
                when (entityToAdd.getType()) {
                    PlayEntityType.UpStaircase -> {
                        addNewPosition(state, entityIndexToAdd, entitiesIndexToAdd, playableTower)
                        didSomethingAutomatic = true
                    }

                    PlayEntityType.GoodiesGroup -> {
                        entityToAdd as GoodiesGroup
                        entityToAdd.apply(state)
                        addNewPosition(state, entityIndexToAdd, entitiesIndexToAdd, playableTower)
                        didSomethingAutomatic = true
                    }

                    PlayEntityType.Door -> {
                        entityToAdd as Door
                        if ((playableTower.roomsSingleDoor.indexOf(entityToAdd.entityIndex) != -1) && entityToAdd.canApply(
                                state
                            )
                        ) {
                            entityToAdd.apply(state)
                            addNewPosition(state, entityIndexToAdd, entitiesIndexToAdd, playableTower)
                            didSomethingAutomatic = true
                        } else {
                            state.reachable.set(entityIndexToAdd)
                            newEntities.add(entityToAdd)
                        }
                    }

                    PlayEntityType.Enemy -> {
                        entityToAdd as Enemy
                        val killEnemyNoHpLostAndNoLevelUp =
                            entityToAdd.killNoHPLost(state) && (!entityToAdd.shouldLevelUp(state))
                        if (killEnemyNoHpLostAndNoLevelUp) {
                            entityToAdd.apply(state)
                            entityToAdd.dropApply(state)
                            addNewPosition(state, entityIndexToAdd, entitiesIndexToAdd, playableTower)
                            didSomethingAutomatic = true
                        } else {
                            state.reachable.set(entityIndexToAdd)
                            newEntities.add(entityToAdd)
                        }
                    }

                    else -> {
                        state.reachable.set(entityIndexToAdd)
                        newEntities.add(entityToAdd)
                    }
                }
            }
        }
        if (!didSomethingAutomatic) {
            when (getType()) {
                PlayEntityType.Enemy -> {
                    this as Enemy
                    return if (this.key != null) {
                        // Obtaining akey as drop for an enemy is a valid move by itself
                        true
                    } else {
                        checkNewEntities(newEntities, state, playableTower, stateManager)
                    }
                }

                PlayEntityType.Door -> {
                    return checkNewEntities(newEntities, state, playableTower, stateManager)
                }

                else -> {

                }
            }
        }
        return true
    }

    private fun checkNewEntities(
        newEntities: List<PlayEntity>,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ): Boolean {
        if (newEntities.isEmpty()) {
            return false
        } else if (newEntities.size == 1) {
            val newEntity = newEntities.first()
            return when (newEntity.getType()) {
                PlayEntityType.Door -> {
                    val newDoor = newEntity as Door
                    if (newDoor.canApply(state)) {
                        newDoor.play(state, playableTower, stateManager)
                    }
                    false
                }

                PlayEntityType.Enemy -> {
                    newEntity.play(state, playableTower, stateManager)
                    false
                }

                else -> {
                    true
                }
            }
        } else {
            return true
        }
    }

    private fun processOneWays(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        entitiesIndexToAdd: MutableList<Int>,
    ) {
        // Check if we reached a one way we visited before
        for (oneWay in state.oneWays) {
            val oneWayIndex = entitiesIndexToAdd.indexOf(oneWay.toInt())
            if ((entityIndex.toShort() != oneWay) && (oneWayIndex != -1)) {
                entitiesIndexToAdd.removeAt(oneWayIndex)
                // In this case we need to find all the available positions for all the thing we already visited

                // First remove the one way from the ones to check
                val oneWays = state.oneWays.toMutableList()
                oneWays.remove(oneWay)
                state.oneWays = oneWays.toShortArray()

                val entitiesIndexChecked = mutableListOf<Int>()
                entitiesIndexChecked.add(oneWay.toInt())

                val entitiesIndexToCheck = mutableListOf<Int>()
                entitiesIndexChecked.addAll(entitiesIndexToAdd)
                entitiesIndexToCheck.addAll(entitiesIndexToAdd)

                while (entitiesIndexToCheck.isNotEmpty()) {
                    val entityIndexToCheck = entitiesIndexToCheck.removeAt(entitiesIndexToCheck.lastIndex)
                    if (state.visited.get(entityIndexToCheck)) {
                        for (reachableEntityIndex in playableTower.reachable[entityIndexToCheck]) {
                            if (!entitiesIndexChecked.contains(reachableEntityIndex)) {
                                entitiesIndexChecked.add(reachableEntityIndex)
                                entitiesIndexToCheck.add(reachableEntityIndex)
                            }
                        }
                    } else {
                        entitiesIndexToAdd.add(entityIndexToCheck)
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
        state.moves = state.moves.plus(positionToAdd.toShort())
        positionsToAdd.addAll(playableTower.reachable[positionToAdd].asIterable())
    }
}
