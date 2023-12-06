package net.archiloque.tacticalnexus.solver.entities.play

class
DropItem(
    override val atk: Int,
    override val def: Int,
    override val expMultAdd: Int,
    override val expMultMul: Int,
    override val hp: Int,
    override val hpMultAdd: Int,
    override val hpMultMul: Int,
    val name: String,
) : Item(atk, def, expMultAdd, expMultMul, hp, hpMultAdd, hpMultMul) {

    companion object {
        private val items = mutableMapOf<String, DropItem>()

        fun item(item: net.archiloque.tacticalnexus.solver.entities.input.Item?): DropItem? {
            if (item == null) {
                return null
            } else {
                var value = items[item.name]
                return if (value == null) {
                    value = DropItem(
                        item.atk,
                        item.def,
                        item.expMultAdd,
                        item.expMultMul,
                        item.hp,
                        item.hpMultAdd,
                        item.hpMultMul,
                        item.name,
                    )
                    items[item.name] = value
                    value
                } else {
                    value
                }
            }
        }
    }
}
