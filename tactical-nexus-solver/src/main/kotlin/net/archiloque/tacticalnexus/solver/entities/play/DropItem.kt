package net.archiloque.tacticalnexus.solver.entities.play

class
DropItem(
    override val atk: Int,
    override val def: Int,
    override val expBonus: Int,
    override val hp: Int,
    override val hpBonus: Int,
): Item(atk, def, expBonus, hp, hpBonus) {

    companion object {
        private val items = mutableMapOf<String, DropItem>()

        fun item(item: net.archiloque.tacticalnexus.solver.entities.input.Item): DropItem {
            var value = items[item.name]
            return if (value == null) {
                value = DropItem(
                    item.atk,
                    item.def,
                    item.expBonus,
                    item.hp,
                    item.hpBonus,
                )
                items[item.name] = value
                value
            } else {
                value
            }
        }
    }
}
