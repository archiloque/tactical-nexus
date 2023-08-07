package net.archiloque.tacticalnexus.solver.entities

enum class EnemyType() {
    fighter,
    ranger
}
data class Enemy(
    val hp: Int,
    val atk: Int,
    val def: Int,
    val exp: Int,
    val drop: Item,
) : Entity

data class EnemyId(
    val type: EnemyType,
    val level: Int,
)
