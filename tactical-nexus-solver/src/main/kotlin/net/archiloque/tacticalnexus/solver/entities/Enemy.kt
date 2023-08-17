package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PositionSaver
import net.archiloque.tacticalnexus.solver.code.Tower
import net.archiloque.tacticalnexus.solver.database.Position

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

    override fun play(entityIndex: Int, position: Position, tower: Tower, positionSaver: PositionSaver) {
        val newPosition = newPosition(entityIndex, position)
        drop.collect(newPosition)
        addNewPositions(entityIndex, newPosition, tower, positionSaver)
    }
}
