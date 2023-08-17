package net.archiloque.tacticalnexus.solver.input

import javax.`annotation`.processing.Generated
import kotlin.Array
import kotlin.arrayOf
import net.archiloque.tacticalnexus.solver.entities.Item

@Generated
public class Items {
    public companion object {
        public val blue_potion: Item = Item("blue_potion", 0, 0, 800)

        public val guard_gem: Item = Item("guard_gem", 0, 2, 0)

        public val guard_piece: Item = Item("guard_piece", 0, 1, 0)

        public val guard_potion: Item = Item("guard_potion", 0, 3, 300)

        public val heavenly_potion: Item = Item("heavenly_potion", 0, 3, 3000)

        public val life_potion: Item = Item("life_potion", 3, 0, 300)

        public val power_card: Item = Item("power_card", 5, 0, 0)

        public val power_gem: Item = Item("power_gem", 2, 0, 0)

        public val power_piece: Item = Item("power_piece", 1, 0, 0)

        public val power_potion: Item = Item("power_potion", 2, 0, 0)

        public val red_potion: Item = Item("red_potion", 0, 0, 200)

        public val items: Array<Item> = arrayOf(
            power_piece, power_gem, guard_piece, guard_gem,
            blue_potion, red_potion, heavenly_potion, guard_potion, life_potion, power_potion,
            power_card,
        )
    }
}
