package net.archiloque.tacticalnexus.solver.input.towers

import javax.`annotation`.processing.Generated
import kotlin.Array
import kotlin.Int
import kotlin.arrayOf
import net.archiloque.tacticalnexus.solver.entities.EnemyType
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Door
import net.archiloque.tacticalnexus.solver.entities.input.Enemy
import net.archiloque.tacticalnexus.solver.entities.input.Key
import net.archiloque.tacticalnexus.solver.entities.input.Level
import net.archiloque.tacticalnexus.solver.entities.input.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.input.Staircase
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.input.TowerLevel
import net.archiloque.tacticalnexus.solver.entities.input.Wall
import net.archiloque.tacticalnexus.solver.input.Items

@Generated
public class Tower_2 : Tower {
    private val burgeoners: Array<Enemy?> = arrayOf(
        null,
        null,
        null,
        null,
        null,
        Enemy(EnemyType.burgeoner, 5, 2500, 100, 0, 20, null, null),
    )

    private val fighters: Array<Enemy?> = arrayOf(
        null,
        Enemy(EnemyType.fighter, 1, 200, 100, 0, 2, Items.guard_piece, null),
        null,
        Enemy(EnemyType.fighter, 3, 1, 1, 0, 1, null, null),
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        Enemy(EnemyType.fighter, 17, 1, 1, 0, 1, null, null),
    )

    private val rangers: Array<Enemy?> = arrayOf(
        null,
        null,
        null,
        Enemy(EnemyType.ranger, 3, 200, 100, 0, 2, Items.red_potion, null),
        null,
        null,
        Enemy(EnemyType.ranger, 6, 200, 200, 100, 5, Items.red_potion, null),
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        Enemy(EnemyType.ranger, 23, 1, 1, 0, 1, null, null),
    )

    private val shadows: Array<Enemy?> = arrayOf(
        null,
        null,
        null,
        Enemy(EnemyType.shadow, 3, 300, 200, 0, 4, null, KeyOrDoorColor.yellow),
        null,
        null,
        null,
        null,
        Enemy(EnemyType.shadow, 8, 1, 1, 0, 1, null, null),
        Enemy(EnemyType.shadow, 9, 1, 1, 0, 1, null, null),
    )

    private val slashers: Array<Enemy?> = arrayOf(
        null,
        Enemy(EnemyType.slasher, 1, 100, 200, 0, 2, Items.power_piece, null),
        null,
        Enemy(EnemyType.slasher, 3, 1, 1, 0, 1, null, null),
        null,
        null,
        Enemy(EnemyType.slasher, 6, 1, 1, 0, 1, null, null),
    )

    private val levels: Array<Level> = arrayOf(
        Level(4, 0, 0, 0, 0, 0, 0, 0, 0),
        Level(0, 0, 4, 0, 0, 0, 0, 0, 0),
        Level(0, 0, 0, 0, 0, 0, 0, 0, 3),
        Level(0, 0, 0, 0, 0, 0, 0, 1, 0),
        Level(0, 0, 0, 0, 0, 0, 2, 0, 0),
    )

    private val standardLevels: Array<TowerLevel> =
        arrayOf(
            TowerLevel(
                arrayOf(
                    arrayOf(
                        Key(KeyOrDoorColor.yellow), Wall.instance,
                        Key(KeyOrDoorColor.blue), Items.guard_gem, Wall.instance, Key(KeyOrDoorColor.yellow),
                        fighters[1], fighters[1], Key(KeyOrDoorColor.yellow), Wall.instance, Items.power_gem,
                        Items.blue_potion, Wall.instance, Key(KeyOrDoorColor.yellow), Items.golden_feather,
                    ),
                    arrayOf(
                        Items.power_gem, Wall.instance, Items.power_gem, Items.blue_potion, Wall.instance,
                        slashers[1], null, Items.guard_gem, fighters[1], Wall.instance, null, fighters[1],
                        Door(KeyOrDoorColor.blue), rangers[6], Items.guard_gem,
                    ),
                    arrayOf(
                        Door(KeyOrDoorColor.yellow), Wall.instance, Wall.instance, Key(KeyOrDoorColor.yellow),
                        Door(KeyOrDoorColor.yellow), null, fighters[1], null, null, Wall.instance, Wall.instance,
                        Door(KeyOrDoorColor.yellow), Wall.instance, Wall.instance, Wall.instance,
                    ),
                    arrayOf(
                        slashers[1], null, null, fighters[1], Wall.instance, fighters[1], null,
                        Items.blue_potion, null, Wall.instance, Key(KeyOrDoorColor.yellow), Items.red_potion,
                        Wall.instance, Key(KeyOrDoorColor.yellow), Items.guard_deck,
                    ),
                    arrayOf(
                        Items.guard_gem, null,
                        null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Items.blue_potion,
                        Door(KeyOrDoorColor.yellow), Items.red_potion, Items.guard_gem, Wall.instance, shadows[3],
                        Key(KeyOrDoorColor.yellow),
                    ),
                    arrayOf(
                        null, null, Items.red_potion, null, Wall.instance,
                        null, Key(KeyOrDoorColor.blue), null, Key(KeyOrDoorColor.yellow), Wall.instance,
                        Door(KeyOrDoorColor.yellow), Wall.instance, Wall.instance, Door(KeyOrDoorColor.blue),
                        Wall.instance,
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance, Wall.instance,
                        Door(KeyOrDoorColor.yellow), Wall.instance, Items.blue_potion, null,
                        Key(KeyOrDoorColor.crimson), null, Wall.instance, fighters[1], null, Wall.instance,
                        Key(KeyOrDoorColor.violet), null,
                    ),
                    arrayOf(
                        null, null, Wall.instance,
                        Key(KeyOrDoorColor.yellow), Wall.instance, shadows[3], Wall.instance, Wall.instance,
                        Door(KeyOrDoorColor.yellow), Wall.instance, null, Items.guard_gem,
                        Door(KeyOrDoorColor.yellow), Items.power_gem, Items.blue_potion,
                    ),
                    arrayOf(
                        null,
                        PlayerStartPosition.instance, null, null, Wall.instance, fighters[1], Wall.instance, null,
                        null, Wall.instance, Wall.instance, Door(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.blue),
                        Door(KeyOrDoorColor.yellow), Wall.instance,
                    ),
                    arrayOf(
                        null, null, Wall.instance,
                        Wall.instance, Door(KeyOrDoorColor.yellow), null, Items.power_gem, fighters[1], null,
                        Wall.instance, Key(KeyOrDoorColor.yellow), Key(KeyOrDoorColor.yellow),
                        Door(KeyOrDoorColor.crimson), Items.power_potion, Items.guard_potion,
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance, Wall.instance, Items.power_card,
                        Door(KeyOrDoorColor.yellow), Wall.instance, Wall.instance, Items.blue_potion, null,
                        Wall.instance, Key(KeyOrDoorColor.yellow), Key(KeyOrDoorColor.yellow), Wall.instance,
                        Items.guard_potion, Items.power_potion,
                    ),
                    arrayOf(
                        Door(KeyOrDoorColor.violet),
                        Door(KeyOrDoorColor.crimson), Door(KeyOrDoorColor.crimson), Wall.instance,
                        Door(KeyOrDoorColor.yellow), Wall.instance, null, null, Items.blue_potion, Wall.instance,
                        Wall.instance, Door(KeyOrDoorColor.blue), Wall.instance, Door(KeyOrDoorColor.yellow),
                        Wall.instance,
                    ),
                    arrayOf(
                        Door(KeyOrDoorColor.violet), Wall.instance,
                        Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.blue),
                        Wall.instance, null, Items.power_gem, Items.guard_gem, Wall.instance, fighters[1],
                        slashers[1], Items.power_potion, shadows[3], fighters[1],
                    ),
                    arrayOf(
                        Items.life_crown,
                        Wall.instance, Wall.instance, Items.guard_deck, Wall.instance, burgeoners[5],
                        Door(KeyOrDoorColor.violet), rangers[3], null, Wall.instance, null, Items.power_potion,
                        Items.blue_potion, Items.guard_potion, null,
                    ),
                    arrayOf(
                        Key(KeyOrDoorColor.greenblue),
                        Wall.instance, null, Door(KeyOrDoorColor.greenblue), Staircase.up, Items.heavenly_potion,
                        Wall.instance, null, shadows[3], Wall.instance, fighters[1], null, Items.guard_potion, null,
                        fighters[1],
                    ),
                )
            ),
            TowerLevel(
                arrayOf(
                    arrayOf(
                        Key(KeyOrDoorColor.yellow), Items.guard_piece,
                        Items.power_piece, Key(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.blue),
                        Door(KeyOrDoorColor.blue), Key(KeyOrDoorColor.crimson), Door(KeyOrDoorColor.blue),
                        Key(KeyOrDoorColor.yellow), slashers[3], Door(KeyOrDoorColor.crimson),
                        Door(KeyOrDoorColor.crimson), Key(KeyOrDoorColor.violet), Wall.instance,
                        Key(KeyOrDoorColor.greenblue),
                    ),
                    arrayOf(
                        Items.power_piece, Items.life_potion,
                        Items.guard_potion, Items.guard_piece, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance, slashers[3], Key(KeyOrDoorColor.blue), null, Wall.instance, Wall.instance,
                        null, Door(KeyOrDoorColor.crimson),
                    ),
                    arrayOf(
                        Items.guard_piece, Items.power_potion,
                        Items.life_potion, Items.power_piece, Wall.instance, null, null, Door(KeyOrDoorColor.yellow),
                        Key(KeyOrDoorColor.yellow), fighters[3], Wall.instance, null, null, Wall.instance,
                        Door(KeyOrDoorColor.blue),
                    ),
                    arrayOf(
                        Key(KeyOrDoorColor.yellow), Items.power_piece,
                        Items.guard_piece, rangers[6], Wall.instance, null, null, Wall.instance, slashers[3],
                        slashers[3], Wall.instance, null, null, Wall.instance, Door(KeyOrDoorColor.yellow),
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance, Wall.instance, Door(KeyOrDoorColor.yellow), shadows[3],
                        Wall.instance, Wall.instance, null, Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.yellow),
                        null, Wall.instance, Door(KeyOrDoorColor.violet), Wall.instance, slashers[3],
                    ),
                    arrayOf(
                        null,
                        null, Door(KeyOrDoorColor.crimson), shadows[3], Door(KeyOrDoorColor.yellow), Items.guard_gem,
                        null, rangers[6], null, slashers[6], Door(KeyOrDoorColor.blue), null, null, burgeoners[5],
                        burgeoners[5],
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance, Wall.instance, shadows[8], shadows[3],
                        null, null, null, fighters[3], null, null, Wall.instance, Wall.instance, Wall.instance,
                        Wall.instance,
                    ),
                    arrayOf(
                        null, Door(KeyOrDoorColor.blue), null, Door(KeyOrDoorColor.blue),
                        Items.guard_gem, null, Wall.instance, Door(KeyOrDoorColor.crimson), null, fighters[3], null,
                        Wall.instance, null, Door(KeyOrDoorColor.crimson), null,
                    ),
                    arrayOf(
                        Wall.instance,
                        Wall.instance, Wall.instance, shadows[8], null, Wall.instance, Key(KeyOrDoorColor.blue), null,
                        Wall.instance, null, slashers[1], Wall.instance, Door(KeyOrDoorColor.blue), null,
                        Door(KeyOrDoorColor.crimson),
                    ),
                    arrayOf(
                        null, Door(KeyOrDoorColor.yellow), null,
                        Door(KeyOrDoorColor.yellow), null, Wall.instance, Key(KeyOrDoorColor.blue),
                        Key(KeyOrDoorColor.blue), Wall.instance, slashers[3], null, Door(KeyOrDoorColor.yellow), null,
                        Door(KeyOrDoorColor.blue), null,
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance, Wall.instance, null,
                        rangers[6], Items.power_gem, Wall.instance, Wall.instance, null, null, slashers[3],
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    ),
                    arrayOf(
                        Items.guard_gem,
                        slashers[1], null, slashers[1], rangers[3], Wall.instance, null, null, slashers[6], null,
                        null, fighters[3], fighters[3], Wall.instance, null,
                    ),
                    arrayOf(
                        fighters[1], null,
                        fighters[1], null, rangers[3], null, Door(KeyOrDoorColor.blue), null, shadows[8], slashers[1],
                        null, Wall.instance, slashers[3], Wall.instance, rangers[23],
                    ),
                    arrayOf(
                        null, fighters[1],
                        Wall.instance, Wall.instance, Wall.instance, null, Wall.instance, Wall.instance,
                        Wall.instance, slashers[1], Wall.instance, Wall.instance, slashers[3], Wall.instance,
                        fighters[17],
                    ),
                    arrayOf(
                        slashers[1], null, null, null, Staircase.down,
                        Door(KeyOrDoorColor.greenblue), Staircase.up, null, fighters[3], slashers[1],
                        Key(KeyOrDoorColor.yellow), Wall.instance, rangers[6], shadows[8], null,
                    ),
                )
            ),
            TowerLevel(
                arrayOf(
                    arrayOf(
                        null, null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, Items.guard_gem, Items.guard_gem,
                        null, null, null, null, null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null,
                        null, null, null, null, null, null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null,
                        null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                        null,
                    ),
                    arrayOf(
                        null, null, null, Staircase.down, null, null, null, null, null, null, null,
                        null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null,
                    ),
                )
            ),
        )

    private val nexusLevels: Array<TowerLevel> = arrayOf()

    private val checkScore: Position = Position(2, 4, 1)

    private val starScore: Position = Position(2, 4, 2)

    override fun levels(): Array<Level> = levels

    override fun standardLevels(): Array<TowerLevel> = standardLevels

    override fun nexusLevels(): Array<TowerLevel> = nexusLevels

    override fun atk(): Int = 50

    override fun def(): Int = 0

    override fun hp(): Int = 1_000

    override fun checkScore(): Position = checkScore

    override fun starScore(): Position = starScore
}
