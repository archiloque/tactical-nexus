package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.database.State

data class Enemy(
    val hp: Int,
    val atk: Int,
    val def: Int,
    val exp: Int,
    val drop: Item,
) : Entity() {
    override fun getType(): EntityType {
        return EntityType.Enemy
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        val newPosition = newState(entityIndex, state)
        drop.collect(newPosition)
        addNewPositions(entityIndex, newPosition, playableTower, stateSaver)
    }
}
