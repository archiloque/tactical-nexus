package net.archiloque.tacticalnexus.solver.database

import java.util.BitSet

data class State(
    val id: Int,

    var status: StateStatus,

    var reachable: BitSet,

    var atk: Int,
    var def: Int,
    var exp: Int,
    var hp: Int,

    var expBonus: Short,
    var hpBonus: Short,

    var blueKeys: Short,
    var crimsonKeys: Short,
    var platinumKeys: Short,
    var violetKeys: Short,
    var yellowKeys: Short,

    var visited: BitSet,
    var moves: ShortArray,
    var level: Short,
)