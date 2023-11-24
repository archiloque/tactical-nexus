package net.archiloque.tacticalnexus.solver.entities.input

data class Level(
    val atkAdd: Int,
    val atkMul: Int,

    val defAdd: Int,
    val defMul: Int,

    val hpAdd: Int,
    val hpMul: Int,

    val blueKeys: Short,
    val crimsonKeys: Short,
    val yellowKeys: Short,
)
