package net.archiloque.tacticalnexus.solver.entities

data class LevelUp(
    val number: Int,
    val exp: Int,

    val atk: Int,
    val def: Int,
)

enum class LevelUpType(val type: Int) {
    atk(-1),
    def(-2),
    blueKeys(-3),
    crimsonKeys(-4),
    yellowKeys(-5),
}