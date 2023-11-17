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

    var expBonus: Int,
    var hpBonus: Int,

    var blueKeys: Int,
    var crimsonKeys: Int,
    var platinumKeys: Int,
    var violetKeys: Int,
    var yellowKeys: Int,

    var visited: BitSet,
    var moves: IntArray,
    var level: Int,
)