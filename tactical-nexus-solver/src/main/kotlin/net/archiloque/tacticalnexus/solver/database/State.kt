package net.archiloque.tacticalnexus.solver.database

import java.util.BitSet

data class State(
    var id: Int,

    var status: StateStatus,

    var visited: BitSet,
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

    var moves: Array<Int>,
)