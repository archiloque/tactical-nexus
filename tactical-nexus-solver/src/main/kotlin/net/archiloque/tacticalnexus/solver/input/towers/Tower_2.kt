package net.archiloque.tacticalnexus.solver.input.towers

import javax.`annotation`.processing.Generated
import kotlin.Array
import kotlin.Int
import kotlin.arrayOf
import net.archiloque.tacticalnexus.solver.entities.Direction
import net.archiloque.tacticalnexus.solver.entities.EnemyType
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Door
import net.archiloque.tacticalnexus.solver.entities.input.Enemy
import net.archiloque.tacticalnexus.solver.entities.input.Key
import net.archiloque.tacticalnexus.solver.entities.input.Level
import net.archiloque.tacticalnexus.solver.entities.input.OneWay
import net.archiloque.tacticalnexus.solver.entities.input.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.input.Staircase
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.input.TowerLevel
import net.archiloque.tacticalnexus.solver.entities.input.Wall
import net.archiloque.tacticalnexus.solver.input.Items

@Generated
public class Tower_2 : Tower {
    private val enemies: Array<Enemy> = arrayOf(
        Enemy(EnemyType.burgeoner, 5, 2500, 100, 0, 20, null, null),
        Enemy(EnemyType.fighter, 1, 200, 100, 0, 2, Items.guard_piece, null),
        Enemy(EnemyType.fighter, 3, 300, 200, 0, 5, Items.guard_piece, null),
        Enemy(EnemyType.fighter, 6, 600, 300, 100, 10, Items.guard_gem, null),
        Enemy(EnemyType.fighter, 17, 2000, 700, 300, 30, Items.guard_potion, null),
        Enemy(EnemyType.ranger, 3, 200, 100, 0, 2, Items.red_potion, null),
        Enemy(EnemyType.ranger, 6, 200, 200, 100, 5, Items.red_potion, null),
        Enemy(EnemyType.ranger, 23, 2000, 1000, 100, 47, Items.life_potion, null),
        Enemy(EnemyType.ranger, 32, 2000, 1600, 200, 70, Items.life_potion, null),
        Enemy(EnemyType.ranger, 36, 3000, 2200, 400, 100, Items.heavenly_potion, null),
        Enemy(EnemyType.shadow, 3, 300, 200, 0, 4, null, KeyOrDoorColor.yellow),
        Enemy(EnemyType.shadow, 8, 500, 300, 100, 16, null, KeyOrDoorColor.blue),
        Enemy(EnemyType.shadow, 14, 600, 700, 100, 36, null, KeyOrDoorColor.crimson),
        Enemy(EnemyType.shadow, 60, 7000, 4000, 100, 250, null, KeyOrDoorColor.blue),
        Enemy(EnemyType.slasher, 1, 100, 200, 0, 2, Items.power_piece, null),
        Enemy(EnemyType.slasher, 3, 200, 300, 0, 5, Items.power_piece, null),
        Enemy(EnemyType.slasher, 6, 400, 500, 0, 10, Items.power_gem, null),
    )

    private val levels: Array<Level> = arrayOf(
        Level(4, 0, 0, 0, 0, 0, 0, 0, 0),
        Level(0, 0, 4, 0, 0, 0, 0, 0, 0),
        Level(0, 0, 0, 0, 0, 0, 0, 0, 3),
        Level(0, 0, 0, 0, 0, 0, 0, 1, 0),
        Level(0, 0, 0, 0, 0, 0, 2, 0, 0),
    )

    private val standardLevels: Array<TowerLevel> = arrayOf(
        TowerLevel(
            arrayOf(
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
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null,
                    Items.heavenly_potion, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null,
                    null, null, null, Staircase.up, null, null, null, null, null, null, null,
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
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null,
                ),
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    Key(KeyOrDoorColor.yellow),
                    Wall.instance, Key(KeyOrDoorColor.blue), Items.guard_gem, Wall.instance,
                    Key(KeyOrDoorColor.yellow), enemies[1], enemies[1], Key(KeyOrDoorColor.yellow), Wall.instance,
                    Items.power_gem, Items.blue_potion, Wall.instance, Key(KeyOrDoorColor.yellow),
                    Items.golden_feather,
                ),
                arrayOf(
                    Items.power_gem, Wall.instance, Items.power_gem,
                    Items.blue_potion, Wall.instance, enemies[14], null, Items.guard_gem, enemies[1],
                    Wall.instance, null, enemies[1], Door(KeyOrDoorColor.blue), enemies[6], Items.guard_gem,
                ),
                arrayOf(
                    Door(KeyOrDoorColor.yellow), Wall.instance, Wall.instance, Key(KeyOrDoorColor.yellow),
                    Door(KeyOrDoorColor.yellow), null, enemies[1], null, null, Wall.instance, Wall.instance,
                    Door(KeyOrDoorColor.yellow), Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    enemies[14], null, null, enemies[1], Wall.instance, enemies[1], null,
                    Items.blue_potion, null, Wall.instance, Key(KeyOrDoorColor.yellow), Items.red_potion,
                    Wall.instance, Key(KeyOrDoorColor.yellow), Items.guard_card,
                ),
                arrayOf(
                    Items.guard_gem, null,
                    null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Items.blue_potion,
                    Door(KeyOrDoorColor.yellow), Items.red_potion, Items.guard_gem, Wall.instance, enemies[10],
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
                    Key(KeyOrDoorColor.crimson), null, Wall.instance, enemies[1], null, Wall.instance,
                    Key(KeyOrDoorColor.violet), null,
                ),
                arrayOf(
                    null, null, Wall.instance,
                    Key(KeyOrDoorColor.yellow), Wall.instance, enemies[10], Wall.instance, Wall.instance,
                    Door(KeyOrDoorColor.yellow), Wall.instance, null, Items.guard_gem,
                    Door(KeyOrDoorColor.yellow), Items.power_gem, Items.blue_potion,
                ),
                arrayOf(
                    null,
                    PlayerStartPosition.instance, null, null, Wall.instance, enemies[1], Wall.instance, null,
                    null, Wall.instance, Wall.instance, Door(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.yellow), Wall.instance,
                ),
                arrayOf(
                    null, null, Wall.instance,
                    Wall.instance, Door(KeyOrDoorColor.yellow), null, Items.power_gem, enemies[1], null,
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
                    Wall.instance, null, Items.power_gem, Items.guard_gem, Wall.instance, enemies[1], enemies[14],
                    Items.power_potion, enemies[10], enemies[1],
                ),
                arrayOf(
                    Items.life_crown, Wall.instance,
                    Wall.instance, Items.guard_deck, Wall.instance, enemies[0], Door(KeyOrDoorColor.violet),
                    enemies[5], null, Wall.instance, null, Items.power_potion, Items.blue_potion,
                    Items.guard_potion, null,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.greenblue), Wall.instance,
                    Staircase.down, Door(KeyOrDoorColor.greenblue), Staircase.up, Items.heavenly_potion,
                    Wall.instance, null, enemies[10], Wall.instance, enemies[1], null, Items.guard_potion, null,
                    enemies[1],
                ),
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    Key(KeyOrDoorColor.yellow), Items.guard_piece,
                    Items.power_piece, Key(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.blue), Key(KeyOrDoorColor.crimson), Door(KeyOrDoorColor.blue),
                    Key(KeyOrDoorColor.yellow), enemies[15], Door(KeyOrDoorColor.crimson),
                    Door(KeyOrDoorColor.crimson), Key(KeyOrDoorColor.violet), Wall.instance,
                    Key(KeyOrDoorColor.greenblue),
                ),
                arrayOf(
                    Items.power_piece, Items.life_potion,
                    Items.guard_potion, Items.guard_piece, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, enemies[15], Key(KeyOrDoorColor.blue), Items.life_potion, Wall.instance,
                    Wall.instance, Items.guard_potion, Door(KeyOrDoorColor.crimson),
                ),
                arrayOf(
                    Items.guard_piece,
                    Items.power_potion, Items.life_potion, Items.power_piece, Wall.instance, Items.guard_gem,
                    Items.guard_gem, Door(KeyOrDoorColor.yellow), Key(KeyOrDoorColor.yellow), enemies[2],
                    Wall.instance, Items.heavenly_potion, Items.heavenly_potion, Wall.instance,
                    Door(KeyOrDoorColor.blue),
                ),
                arrayOf(
                    Key(KeyOrDoorColor.yellow), Items.power_piece,
                    Items.guard_piece, enemies[6], Wall.instance, Items.power_gem, Items.power_gem, Wall.instance,
                    enemies[15], enemies[15], Wall.instance, Items.guard_card, Items.guard_card, Wall.instance,
                    Door(KeyOrDoorColor.yellow),
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance,
                    Door(KeyOrDoorColor.yellow), enemies[10], Wall.instance, Wall.instance, Items.power_gem,
                    Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.yellow), null, Wall.instance,
                    Door(KeyOrDoorColor.violet), Wall.instance, enemies[15],
                ),
                arrayOf(
                    Items.heavenly_potion,
                    Items.heavenly_potion, Door(KeyOrDoorColor.crimson), enemies[10], Door(KeyOrDoorColor.yellow),
                    Items.guard_gem, null, enemies[6], Items.guard_gem, enemies[16], Door(KeyOrDoorColor.blue),
                    Items.red_potion, Items.red_potion, enemies[0], enemies[0],
                ),
                arrayOf(
                    Wall.instance,
                    Wall.instance, Wall.instance, enemies[11], enemies[10], null, null, null, enemies[2], null,
                    null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.life_potion, Door(KeyOrDoorColor.blue), Items.power_potion,
                    Door(KeyOrDoorColor.blue), Items.guard_gem, null, Wall.instance, Door(KeyOrDoorColor.crimson),
                    null, enemies[2], null, Wall.instance, Items.power_card, Door(KeyOrDoorColor.crimson),
                    Items.guard_deck,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, enemies[11], null,
                    Wall.instance, Key(KeyOrDoorColor.blue), Items.power_card, Wall.instance, Items.guard_gem,
                    enemies[14], Wall.instance, Door(KeyOrDoorColor.blue), Items.power_card,
                    Door(KeyOrDoorColor.crimson),
                ),
                arrayOf(
                    Items.power_potion, Door(KeyOrDoorColor.yellow),
                    Items.blue_potion, Door(KeyOrDoorColor.yellow), null, Wall.instance, Key(KeyOrDoorColor.blue),
                    Key(KeyOrDoorColor.blue), Wall.instance, enemies[15], Items.power_gem,
                    Door(KeyOrDoorColor.yellow), Items.blue_potion, Door(KeyOrDoorColor.blue), Items.guard_card,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, null, enemies[6], Items.power_gem,
                    Wall.instance, Wall.instance, null, Items.power_gem, enemies[15], Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.guard_gem, enemies[14], null,
                    enemies[14], enemies[5], Wall.instance, Items.guard_gem, Items.guard_gem, enemies[16],
                    Items.power_gem, Items.power_gem, enemies[2], enemies[2], Wall.instance, Items.golden_feather,
                ),
                arrayOf(
                    enemies[1], null, enemies[1], null, enemies[5], null, Door(KeyOrDoorColor.blue),
                    Items.guard_gem, enemies[11], enemies[14], null, Wall.instance, enemies[15], Wall.instance,
                    enemies[7],
                ),
                arrayOf(
                    null, enemies[1], Wall.instance, Wall.instance, Wall.instance,
                    enemies[0], Wall.instance, Wall.instance, Wall.instance, enemies[14], Wall.instance,
                    Wall.instance, enemies[15], Wall.instance, enemies[4],
                ),
                arrayOf(
                    enemies[14], null,
                    OneWay(Direction.left), null, Staircase.down, Door(KeyOrDoorColor.greenblue), Staircase.up,
                    null, enemies[2], enemies[14], Key(KeyOrDoorColor.yellow), Wall.instance, enemies[6],
                    enemies[11], Items.golden_feather,
                ),
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    Items.blue_potion,
                    Key(KeyOrDoorColor.blue), Key(KeyOrDoorColor.yellow), Items.red_potion, Wall.instance,
                    Key(KeyOrDoorColor.crimson), Key(KeyOrDoorColor.crimson), Wall.instance,
                    Door(KeyOrDoorColor.crimson), Items.power_gem, Key(KeyOrDoorColor.yellow), Wall.instance,
                    Items.power_potion, Items.guard_potion, Items.power_potion,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.yellow), Key(KeyOrDoorColor.crimson), Items.guard_card,
                    Key(KeyOrDoorColor.blue), Wall.instance, Key(KeyOrDoorColor.blue), Key(KeyOrDoorColor.blue),
                    enemies[9], enemies[6], Wall.instance, Items.guard_card, Wall.instance, Items.guard_potion,
                    Items.life_crown, Items.guard_potion,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.blue), Items.power_card,
                    Items.heavenly_potion, Key(KeyOrDoorColor.yellow), Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, enemies[14], Wall.instance, Key(KeyOrDoorColor.crimson),
                    Wall.instance, Items.power_potion, Items.guard_potion, Items.power_potion,
                ),
                arrayOf(
                    Items.life_potion, Key(KeyOrDoorColor.yellow), Key(KeyOrDoorColor.blue), enemies[13],
                    Door(KeyOrDoorColor.violet), enemies[6], null, Items.power_gem, enemies[14], null,
                    Wall.instance, Wall.instance, Wall.instance, Door(KeyOrDoorColor.violet), Wall.instance,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null,
                    Items.power_gem, enemies[6], Items.power_gem, enemies[6], enemies[16], null, enemies[2],
                    enemies[8], Items.power_gem,
                ),
                arrayOf(
                    Items.power_potion, Items.guard_potion, Wall.instance,
                    Items.power_gem, Items.guard_gem, Door(KeyOrDoorColor.yellow), enemies[6], Items.power_gem,
                    enemies[3], Items.guard_gem, enemies[6], enemies[15], enemies[2], Items.power_gem,
                    Items.guard_gem,
                ),
                arrayOf(
                    Items.power_potion, enemies[15], Door(KeyOrDoorColor.yellow),
                    Items.guard_gem, Items.power_gem, Wall.instance, null, enemies[6], Items.guard_gem,
                    enemies[3], Items.guard_gem, Door(KeyOrDoorColor.yellow), Items.power_gem, Wall.instance,
                    null,
                ),
                arrayOf(
                    Wall.instance, Door(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.yellow), Wall.instance, Key(KeyOrDoorColor.yellow), enemies[11], null,
                    enemies[6], Items.guard_gem, Wall.instance, Door(KeyOrDoorColor.yellow), Wall.instance,
                    Items.life_potion, enemies[7],
                ),
                arrayOf(
                    Items.power_gem, Items.guard_gem,
                    Door(KeyOrDoorColor.yellow), enemies[15], Items.power_potion, Wall.instance, null,
                    enemies[15], null, Wall.instance, enemies[1], enemies[14], Wall.instance, Items.life_potion,
                    Items.life_potion,
                ),
                arrayOf(
                    Items.guard_gem, Items.power_gem, Wall.instance,
                    Items.power_potion, Items.guard_potion, Wall.instance, null, null, Wall.instance, enemies[14],
                    enemies[1], Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Door(KeyOrDoorColor.yellow), Wall.instance, Items.life_potion, Wall.instance,
                    Wall.instance, Items.power_potion, enemies[16], enemies[0], Wall.instance, enemies[15],
                    Door(KeyOrDoorColor.crimson), Items.life_potion, Items.guard_potion, enemies[3],
                    Items.power_potion,
                ),
                arrayOf(
                    Items.power_piece, enemies[2], Door(KeyOrDoorColor.yellow),
                    Items.red_potion, Items.red_potion, Door(KeyOrDoorColor.yellow), enemies[16], enemies[0],
                    Wall.instance, Items.power_gem, Wall.instance, enemies[12], Door(KeyOrDoorColor.yellow),
                    Wall.instance, enemies[16],
                ),
                arrayOf(
                    Items.power_piece, Items.power_piece, Wall.instance,
                    Items.guard_gem, Wall.instance, Items.blue_potion, Wall.instance, enemies[0], Wall.instance,
                    enemies[15], Wall.instance, Items.power_gem, Items.heavenly_potion, Door(KeyOrDoorColor.blue),
                    Items.red_potion,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Items.power_card, Wall.instance,
                    Items.red_potion, Items.power_gem, Wall.instance, Key(KeyOrDoorColor.crimson), Wall.instance,
                    Items.power_gem, enemies[2], Wall.instance, Door(KeyOrDoorColor.crimson), Wall.instance,
                    enemies[6],
                ),
                arrayOf(
                    Key(KeyOrDoorColor.crimson), enemies[3], Door(KeyOrDoorColor.crimson),
                    Key(KeyOrDoorColor.yellow), enemies[16], null, Staircase.down, Door(KeyOrDoorColor.crimson),
                    Staircase.up, Wall.instance, Items.guard_gem, enemies[2], Items.blue_potion, enemies[6],
                    Items.blue_potion,
                ),
            )
        ),
        TowerLevel(
            arrayOf(
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
                arrayOf(
                    null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    null,
                    Items.guard_gem, Items.guard_gem, null, null, null, null, null, null, null, null, null, null,
                    null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, Staircase.down, null, null, null,
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
                arrayOf(
                    null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null,
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
            )
        ),
    )

    private val nexusLevels: Array<TowerLevel> = arrayOf()

    private val checkScore: Position = Position(4, 4, 1)

    private val starScore: Position = Position(4, 4, 2)

    override fun levels(): Array<Level> = levels

    override fun standardLevels(): Array<TowerLevel> = standardLevels

    override fun nexusLevels(): Array<TowerLevel> = nexusLevels

    override fun atk(): Int = 50

    override fun def(): Int = 0

    override fun hp(): Int = 1_000

    override fun checkScore(): Position = checkScore

    override fun starScore(): Position = starScore
}
