package net.archiloque.tacticalnexus.solver.entities.input

class Item(
    val name: String,
    val atk: Int,
    val def: Int,
    val expMultAdd: Int,
    val expMultMul: Int,
    val hp: Int,
    val hpMultAdd: Int,
    val hpMultMul: Int,
) : InputEntity() {
    override fun getType(): InputEntityType {
        return InputEntityType.Item
    }
}
