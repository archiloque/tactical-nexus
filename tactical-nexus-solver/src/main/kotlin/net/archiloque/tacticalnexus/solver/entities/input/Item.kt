package net.archiloque.tacticalnexus.solver.entities.input

class Item(
    val name: String,
    val atk: Int,
    val def: Int,
    val expBonusAdd: Int,
    val expBonusMul: Int,
    val hp: Int,
    val hpBonusAdd: Int,
    val hpBonusMul: Int,
) : InputEntity() {
    override fun getType(): InputEntityType {
        return InputEntityType.Item
    }
}
