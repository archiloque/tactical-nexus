package net.archiloque.tacticalnexus.solver.input.towers

import net.archiloque.tacticalnexus.solver.entities.*
import net.archiloque.tacticalnexus.solver.input.Enemies
import net.archiloque.tacticalnexus.solver.input.Items
import javax.annotation.processing.Generated

@Generated
public class Tower_1 {
    public companion object {
        public val levels: Array<Level> = arrayOf(
            Level(
                16, 10, arrayOf(
                    arrayOf(
                        null, null, null, null,
                        Wall.instance, Wall.instance, Wall.instance, null, null, null, null,
                    ),
                    arrayOf(
                        null, null,
                        null, null, Wall.instance, null, Wall.instance, null, null, null, null,
                    ),
                    arrayOf(
                        null,
                        null, null, null, Wall.instance, PlayerStartPosition.instance, Wall.instance, null, null,
                        null, null,
                    ),
                    arrayOf(
                        null, null, null, null, Wall.instance, null, Wall.instance, null,
                        null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, Wall.instance, null, Wall.instance,
                        null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, Wall.instance, Wall.instance, null,
                        Wall.instance, Wall.instance, null, null, null,
                    ),
                    arrayOf(
                        null, null, Wall.instance,
                        Items.power_piece, Wall.instance, null, Wall.instance, Items.guard_piece, Wall.instance,
                        null, null,
                    ),
                    arrayOf(
                        null, Wall.instance, Items.power_piece, Items.power_gem,
                        Items.power_piece, Enemies.fighters[1], Items.guard_piece, Items.guard_gem,
                        Items.guard_piece, Wall.instance, null,
                    ),
                    arrayOf(
                        Wall.instance, Items.guard_gem,
                        Wall.instance, Items.power_piece, Wall.instance, Items.blue_potion, Wall.instance,
                        Items.guard_piece, Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance,
                        Items.guard_potion, Items.guard_gem, Enemies.fighters[3], Items.blue_potion,
                        Enemies.fighters[1], Items.blue_potion, Enemies.fighters[3], null, Items.power_potion,
                        Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, Items.guard_gem, Wall.instance, null,
                        Wall.instance, Items.heavenly_potion, Wall.instance, null, Wall.instance, null,
                        Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, Enemies.rangers[2], null, Items.power_gem, null,
                        Enemies.fighters[1], null, Items.power_gem, null, Enemies.rangers[2], Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, Wall.instance, Enemies.fighters[1], Wall.instance,
                        Enemies.fighters[3], Wall.instance, Enemies.fighters[1], Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        null, Wall.instance, Items.red_potion, Items.life_potion, Enemies.rangers[4],
                        Key(KeyOrDoorColor.yellow), Enemies.rangers[4], Items.life_potion, Items.red_potion,
                        Wall.instance, null,
                    ),
                    arrayOf(
                        null, null, Wall.instance, Items.red_potion, Wall.instance,
                        Door(KeyOrDoorColor.yellow), Wall.instance, Items.red_potion, Wall.instance, null, null,
                    ),
                    arrayOf(
                        null, null, null, Wall.instance, Wall.instance,
                        Staircase(Staircase.StaircaseDirection.up), Wall.instance, Wall.instance, null, null, null,
                    ),
                    arrayOf(null, null, null, null, null, Wall.instance, null, null, null, null, null),
                )
            ),
            Level(
                16, 16, arrayOf(
                    arrayOf(
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, null, null, null, null, null, Wall.instance,
                        Staircase(Staircase.StaircaseDirection.up), Wall.instance, null, null, null, null, null,
                        null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                        null, null, null, Wall.instance, null, Wall.instance, null, null, null, Wall.instance,
                        Wall.instance, Wall.instance, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, null, null,
                        null, null, null, null, null, null, null, null, null, null, null, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, null, null, null, Wall.instance, null, null, null, null, null,
                        Wall.instance, null, null, null, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, null,
                        null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, Wall.instance,
                        Wall.instance, Wall.instance, Wall.instance, null, null, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, Wall.instance, null, null, null, null, Wall.instance, null,
                        Wall.instance, null, null, Wall.instance, null, Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, Wall.instance, null, Wall.instance, null, null, Wall.instance,
                        null, Wall.instance, null, null, Wall.instance, null, Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, Wall.instance, null, Wall.instance, Wall.instance,
                        Wall.instance, Wall.instance, null, Wall.instance, Wall.instance, null, Wall.instance, null,
                        Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, Wall.instance, null,
                        null, null, null, null, null, null, null, null, null, null, Wall.instance, null,
                        Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, Wall.instance, null, Wall.instance, null,
                        Wall.instance, Wall.instance, null, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance, null, Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null,
                        Wall.instance, null, Wall.instance, null, null, Wall.instance, null, Wall.instance, null,
                        null, Wall.instance, null, Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance,
                        null, Wall.instance, null, Wall.instance, null, null, Wall.instance, null, Wall.instance,
                        null, null, null, null, Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null,
                        Wall.instance, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null,
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, Wall.instance, null,
                        Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, null, Wall.instance, null, null, null, null, null,
                        null, null, null, null, null, null, Wall.instance, null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, Items.power_card, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance, Wall.instance, Wall.instance, Staircase(Staircase.StaircaseDirection.down),
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                        null, Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance,
                    ),
                )
            ),
        )
    }
}
