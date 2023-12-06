package net.archiloque.tacticalnexus.solver.entities.play

import net.archiloque.tacticalnexus.solver.database.State

open class
Item(
    open val atk: Int,
    open val def: Int,
    open val expBonusAdd: Int,
    open val expBonusMul: Int,
    open val hp: Int,
    open val hpBonusAdd: Int,
    open val hpBonusMul: Int,
) {

    fun apply(state: State) {
        state.atk += atk
        state.def += def
        state.expBonus = ((state.expBonus + expBonusAdd) * expBonusMul).toShort()
        state.hp += (hp * (100 + state.hpBonus)) / 100
        state.hpBonus = ((state.hpBonus + hpBonusAdd) * expBonusMul).toShort()
    }

}
