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

    enum class EnemyType() {
        fighter,
        ranger
    }


    data class EnemyId(
        val type: EnemyType,
        val level: Int,
    )
}
