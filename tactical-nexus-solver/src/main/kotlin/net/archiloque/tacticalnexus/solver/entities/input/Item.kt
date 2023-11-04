package net.archiloque.tacticalnexus.solver.entities.input

class Item(
    val name: String,
    val atk: Int,
    val def: Int,
    val expBonus: Int,
    val hp: Int,
    val hpBonus: Int,
    val ignoreHpBonus: Boolean,
) : InputEntity() {
    override fun getType(): InputEntityType {
        return InputEntityType.Item
    }
}
