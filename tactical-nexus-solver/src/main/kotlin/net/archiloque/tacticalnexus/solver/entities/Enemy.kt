package net.archiloque.tacticalnexus.solver.entities

data class Enemy(
    val hp: Int,
    val atk: Int,
    val def: Int,
    val exp: Int,
    val drop: Item,
) : Entity {
    override fun getType(): Entity.EntityType {
        return Entity.EntityType.Enemy
    }

    enum class EnemyType(val value: Int) {
        fighter(0),
        ranger(1)
    }

    data class EnemyId(
        val type: EnemyType,
        val level: Int,
    )
}
