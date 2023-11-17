package net.archiloque.tacticalnexus.solver.entities.input

import net.archiloque.tacticalnexus.solver.entities.EnemyType

class Enemy(
    val type: EnemyType,
    val level: Int,
    val hp: Int,
    val atk: Int,
    val def: Int,
    val exp: Int,
    val drop: Item?,
) : InputEntity() {
    override fun getType(): InputEntityType {
        return InputEntityType.Enemy
    }

}
