package net.archiloque.tacticalnexus.solver.entities.play

class
DropItem(
    override val atk: Int,
    override val def: Int,
    override val expBonusAdd: Int,
    override val expBonusMul: Int,
    override val hp: Int,
    override val hpBonusAdd: Int,
    override val hpBonusMul: Int,
    val name: String,
) : Item(atk, def, expBonusAdd, expBonusMul, hp, hpBonusAdd, hpBonusMul) {

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
                        item.expBonusAdd,
                        item.expBonusMul,
                        item.hp,
                        item.hpBonusAdd,
                        item.hpBonusMul,
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
