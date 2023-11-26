package net.archiloque.tacticalnexus.solver.entities.play

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus
import net.archiloque.tacticalnexus.solver.entities.Position

data class PositionedDescription(val description: String, val position: Position)

abstract class PlayEntity {
    abstract fun entityIndex(): Int

    abstract fun getType(): PlayEntityType

    abstract fun getPositions(): Array<Position>

    abstract fun description(): Array<PositionedDescription>

    abstract fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    )

    open fun isEnemy(): Boolean {
        return false
    }

    open fun isDoor(): Boolean {
        return false
    }

    open fun isUpStaircase(): Boolean {
        return false
    }

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
        entityIndex: Int,
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
        val positionsToAdd = playableTower.reachable[entityIndex].toMutableList()
        while (positionsToAdd.isNotEmpty()) {
            val positionToAdd = positionsToAdd.removeAt(positionsToAdd.lastIndex)
            val elementToAdd = playableTower.playEntities[positionToAdd]
            if ((!state.visited.get(positionToAdd)) && (!state.reachable.get(positionToAdd))) {
                when (elementToAdd.getType()) {
                    PlayEntityType.UpStaircase -> {
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                        didSomethingAutomatic = true
                    }

                    PlayEntityType.GoodiesGroup -> {
                        elementToAdd as GoodiesGroup
                        elementToAdd.apply(state)
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                        didSomethingAutomatic = true
                    }

                    PlayEntityType.Door -> {
                        elementToAdd as Door
                        if ((playableTower.roomsSingleDoor.indexOf(elementToAdd.entityIndex()) != -1) && elementToAdd.canApply(
                                state
                            )
                        ) {
                            elementToAdd.apply(state)
                            addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                            didSomethingAutomatic = true
                        } else {
                            state.reachable.set(positionToAdd)
                            newEntities.add(elementToAdd)
                        }
                    }

                    PlayEntityType.Enemy -> {
                        elementToAdd as Enemy
                        val killEnemyNoHpLostAndNoLevelUp =
                            elementToAdd.killNoHPLost(state) && (!elementToAdd.shouldLevelUp(state, playableTower))
                        if (killEnemyNoHpLostAndNoLevelUp) {
                            elementToAdd.apply(state)
                            elementToAdd.dropApply(state)
                            addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                            didSomethingAutomatic = true
                        } else {
                            state.reachable.set(positionToAdd)
                            newEntities.add(elementToAdd)
                        }
                    }

                    else -> {
                        state.reachable.set(positionToAdd)
                        newEntities.add(elementToAdd)
                    }
                }
            }
        }
        if (!didSomethingAutomatic) {
            when (getType()) {
                PlayEntityType.Enemy -> {
                    this as Enemy
                    return if (this.key != null) {
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
