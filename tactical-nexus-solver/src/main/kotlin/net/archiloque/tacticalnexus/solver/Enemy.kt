package net.archiloque.tacticalnexus.solver

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
)

data class EnemyId(
    val type: EnemyType,
    val level: Int,
)
