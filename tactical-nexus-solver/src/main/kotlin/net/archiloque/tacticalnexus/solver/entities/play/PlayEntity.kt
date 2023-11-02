package net.archiloque.tacticalnexus.solver.entities.play

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.database.StateStatus

data class PositionedDescription(val description: String, val position: Position)

interface PlayEntity {
    fun entityIndex(): Int

    fun getType(): PlayEntityType

    fun getPositions(): Array<Position>

    fun description(): Array<PositionedDescription>

    fun play(
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
        stateManager: StateManager,
    ): Boolean {
        // We optimize for cases where the decision is automatic, so we avoid adding more states:
        // - staircases
        // - keys
        // - items
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

                    PlayEntityType.Key -> {
                        elementToAdd as Key
                        elementToAdd.apply(state)
                        addNewPosition(state, positionToAdd, positionsToAdd, playableTower)
                        didSomethingAutomatic = true
                    }

                    PlayEntityType.ItemGroup -> {
                        elementToAdd as ItemGroup
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
                            elementToAdd.killNoHPLost(state) && (!elementToAdd.shouldLevelUp(state))
                        if (killEnemyNoHpLostAndNoLevelUp) {
                            elementToAdd.apply(state)
                            elementToAdd.drop.apply(state)
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
                    if ((newEntities.size == 1) && (newEntities.first()
                            .getType() == PlayEntityType.Enemy)
                    ) {
                        newEntities.first().play(state, playableTower, stateManager)
                        return false
                    }
                }

                PlayEntityType.Door -> {
                    if (newEntities.isEmpty()) {
                        return false
                    }
                }

                else -> {

                }
            }
        }
        return true
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
