package net.archiloque.tacticalnexus.solver.database

import java.util.BitSet
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor

data class State(
    val id: Int,

    var status: StateStatus,

    var visited: BitSet,

    var atk: Int,
    var def: Int,
    var exp: Int,
    var hp: Int,

    var expBonus: Short,
    var hpBonus: Short,

    var blueKeys: Short,
    var crimsonKeys: Short,
    var greenBlueKeys: Short,
    var platinumKeys: Short,
    var violetKeys: Short,
    var yellowKeys: Short,

    var reachable: BitSet,
    var moves: ShortArray,
    var oneWays: ShortArray,
    var level: Short,
) {
    fun keys(color: KeyOrDoorColor): Short {
        return when (color) {
            KeyOrDoorColor.blue -> blueKeys
            KeyOrDoorColor.crimson -> crimsonKeys
            KeyOrDoorColor.greenblue -> greenBlueKeys
            KeyOrDoorColor.platinum -> platinumKeys
            KeyOrDoorColor.violet -> violetKeys
            KeyOrDoorColor.yellow -> yellowKeys
        }
    }
}