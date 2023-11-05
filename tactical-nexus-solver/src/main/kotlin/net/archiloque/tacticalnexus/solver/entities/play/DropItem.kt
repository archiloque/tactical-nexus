package net.archiloque.tacticalnexus.solver.entities.play

class
DropItem(
    override val atk: Int,
    override val def: Int,
    override val expBonus: Int,
    override val hp: Int,
    override val hpIgnoreBonus: Int,
    override val hpBonus: Int,
    val name: String,
) : Item(atk, def, expBonus, hp, hpIgnoreBonus, hpBonus) {

    companion object {
        private val items = mutableMapOf<String, DropItem>()

        fun item(item: net.archiloque.tacticalnexus.solver.entities.input.Item): DropItem {
            var value = items[item.name]
            return if (value == null) {
                val hp = if (item.ignoreHpBonus)
                    0 else item.hp
                val hpIgnoreBonus = if (item.ignoreHpBonus)
                    item.hp else 0
                value = DropItem(
                    item.atk,
                    item.def,
                    item.expBonus,
                    hp,
                    hpIgnoreBonus,
                    item.hpBonus,
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
