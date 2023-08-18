package net.archiloque.tacticalnexus.solver.database

import java.util.BitSet

data class State(
    var id: Int,

    var status: StateStatus,

    var visited: BitSet,
    var reachable: BitSet,

    var atk: Int,
    var def: Int,
    var hp: Int,

    var blue_keys: Int,
    var crimson_keys: Int,
    var platinum_keys: Int,
    var violet_keys: Int,
    var yellow_keys: Int,

    var moves: Array<Int>,
)