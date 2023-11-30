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
public class Tower_1 : Tower {
    private val enemies: Array<Enemy> = arrayOf(
        Enemy(EnemyType.burgeoner, 16, 9999, 820, 10, 300, null, null),
        Enemy(EnemyType.fighter, 1, 120, 55, 25, 2, Items.guard_piece, null),
        Enemy(EnemyType.fighter, 3, 320, 110, 40, 5, Items.guard_piece, null),
        Enemy(EnemyType.fighter, 5, 600, 145, 65, 10, Items.guard_gem, null),
        Enemy(EnemyType.fighter, 8, 1000, 260, 85, 18, Items.guard_gem, null),
        Enemy(EnemyType.fighter, 12, 2000, 440, 120, 30, Items.guard_potion, null),
        Enemy(EnemyType.fighter, 15, 3500, 590, 170, 47, Items.guard_potion, null),
        Enemy(EnemyType.fighter, 19, 5500, 1120, 445, 70, Items.guard_card, null),
        Enemy(EnemyType.fighter, 23, 8000, 1650, 620, 100, Items.guard_card, null),
        Enemy(EnemyType.fighter, 28, 1600, 2150, 700, 138, Items.guard_card, null),
        Enemy(EnemyType.ranger, 2, 200, 200, 10, 4, Items.red_potion, null),
        Enemy(EnemyType.ranger, 4, 200, 350, 20, 16, Items.red_potion, null),
        Enemy(EnemyType.ranger, 7, 800, 280, 40, 36, Items.blue_potion, null),
        Enemy(EnemyType.ranger, 10, 800, 540, 60, 64, Items.blue_potion, null),
        Enemy(EnemyType.ranger, 13, 800, 1050, 80, 100, Items.blue_potion, null),
        Enemy(EnemyType.ranger, 16, 2000, 1100, 120, 144, Items.life_potion, null),
        Enemy(EnemyType.ranger, 18, 2000, 1660, 240, 196, Items.life_potion, null),
        Enemy(EnemyType.ranger, 22, 3000, 2350, 360, 256, Items.heavenly_potion, null),
        Enemy(EnemyType.ranger, 26, 3000, 3740, 720, 324, Items.heavenly_potion, null),
        Enemy(EnemyType.ranger, 30, 9999, 4800, 480, 400, Items.drop_of_dream_ocean, null),
    )

    private val levels: Array<Level> = arrayOf(
        Level(4, 0, 0, 0, 0, 0, 0, 0, 0),
        Level(0, 0, 4, 2, 0, 0, 0, 0, 0),
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
                    null, null, null, null, null, null, null, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null,
                    Wall.instance, Items.guard_gem, Items.guard_potion, Items.guard_gem, enemies[10], null,
                    Wall.instance, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, Wall.instance,
                    Items.power_piece, Wall.instance, Items.guard_gem, Wall.instance, null, Wall.instance,
                    Items.red_potion, Wall.instance, null,
                ),
                arrayOf(
                    null, null, null, null, Wall.instance,
                    Items.power_piece, Items.power_gem, Items.power_piece, enemies[2], null, Items.power_gem,
                    enemies[1], Items.life_potion, Items.red_potion, Wall.instance,
                ),
                arrayOf(
                    Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Items.power_piece,
                    Wall.instance, Items.blue_potion, Wall.instance, null, Wall.instance, enemies[11],
                    Wall.instance, Wall.instance,
                ),
                arrayOf(
                    null, PlayerStartPosition.instance, null,
                    Items.power_gem, Items.life_potion, null, enemies[1], Items.blue_potion, enemies[1],
                    Items.heavenly_potion, enemies[1], enemies[2], Key(KeyOrDoorColor.yellow),
                    Door(KeyOrDoorColor.yellow), Staircase.up,
                ),
                arrayOf(
                    Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Items.guard_piece, Wall.instance,
                    Items.blue_potion, Wall.instance, null, Wall.instance, enemies[11], Wall.instance,
                    Wall.instance,
                ),
                arrayOf(
                    null, null, null, null, Wall.instance, Items.guard_piece,
                    Items.guard_gem, Items.guard_piece, enemies[2], null, Items.power_gem, enemies[1],
                    Items.life_potion, Items.red_potion, Wall.instance,
                ),
                arrayOf(
                    null, null, null, null, null,
                    Wall.instance, Items.guard_piece, Wall.instance, null, Wall.instance, null, Wall.instance,
                    Items.red_potion, Wall.instance, null,
                ),
                arrayOf(
                    null, null, null, null, null, null,
                    Wall.instance, null, Items.power_potion, null, enemies[10], null, Wall.instance, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, null, null, null,
                ),
                arrayOf(
                    null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                ),
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    Items.guard_deck, Wall.instance, null, Items.guard_piece, null,
                    enemies[3], null, Items.power_gem, null, enemies[4], Key(KeyOrDoorColor.yellow),
                    Items.power_potion, null, enemies[5], Items.power_card,
                ),
                arrayOf(
                    enemies[4], Wall.instance,
                    Items.guard_piece, Items.power_gem, Items.guard_piece, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.yellow), Wall.instance, null,
                    Items.guard_piece, enemies[2], Items.blue_potion, null, null, enemies[2], null,
                    Items.power_gem, Items.life_potion, null, enemies[10], Wall.instance,
                ),
                arrayOf(
                    enemies[4],
                    null, Items.heavenly_potion, null, Wall.instance, Door(KeyOrDoorColor.blue), Wall.instance,
                    Wall.instance, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Items.blue_potion, Wall.instance,
                ),
                arrayOf(
                    null, Items.power_potion, null, Wall.instance,
                    Wall.instance, Items.life_potion, Items.power_potion, Wall.instance, Items.power_gem,
                    Door(KeyOrDoorColor.yellow), Items.guard_gem, Items.guard_potion, Wall.instance,
                    Items.power_potion, Wall.instance,
                ),
                arrayOf(
                    null, null, enemies[11], Items.power_potion,
                    Wall.instance, Items.power_potion, Items.heavenly_potion, Wall.instance, null, Wall.instance,
                    Items.guard_potion, Items.guard_gem, Wall.instance, enemies[1], Wall.instance,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, null, Items.blue_potion, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, null, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, null, Wall.instance,
                ),
                arrayOf(
                    Staircase.up, Items.drop_of_dream_ocean,
                    enemies[3], null, enemies[2], Items.power_gem, null, null, enemies[2], null,
                    Items.life_potion, enemies[10], null, Items.heavenly_potion, Staircase.down,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, null, Items.blue_potion, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, null, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, null, Wall.instance,
                ),
                arrayOf(
                    null, null, enemies[11], Items.guard_potion,
                    Wall.instance, Items.power_potion, Items.power_gem, Wall.instance, null, Wall.instance,
                    Items.guard_card, Items.guard_potion, Wall.instance, enemies[1], Wall.instance,
                ),
                arrayOf(
                    null, Items.guard_potion, null, Wall.instance, Wall.instance, Items.power_gem,
                    Items.power_potion, Door(KeyOrDoorColor.yellow), Items.guard_gem, Wall.instance,
                    Items.guard_potion, Items.guard_card, Wall.instance, Items.guard_potion, Wall.instance,
                ),
                arrayOf(
                    enemies[4], null, Items.heavenly_potion, null, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, null, Wall.instance, Wall.instance, Door(KeyOrDoorColor.blue),
                    Wall.instance, Items.blue_potion, Wall.instance,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.blue),
                    Wall.instance, null, Items.power_piece, enemies[2], Items.blue_potion, null, null,
                    enemies[11], null, Items.power_gem, Items.life_potion, null, enemies[10], Wall.instance,
                ),
                arrayOf(
                    enemies[17], Wall.instance, Items.power_piece, Items.guard_gem, Items.power_piece,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.life_crown,
                    Wall.instance, null, Items.power_piece, null, enemies[12], null, Items.life_potion, null,
                    enemies[13], null, Items.heavenly_potion, null, enemies[14], Key(KeyOrDoorColor.blue),
                ),
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    Items.life_crown, Items.golden_feather,
                    Door(KeyOrDoorColor.violet), Items.heavenly_potion, Wall.instance, Key(KeyOrDoorColor.yellow),
                    enemies[4], Door(KeyOrDoorColor.yellow), Items.power_gem, Key(KeyOrDoorColor.crimson),
                    Wall.instance, Items.guard_potion, Items.power_potion, Items.guard_potion, Items.power_potion,
                ),
                arrayOf(
                    Items.drop_of_dream_ocean, Wall.instance, Items.power_potion, enemies[3],
                    Wall.instance, enemies[4], Items.power_gem, enemies[15], Items.power_gem, Items.power_gem,
                    Wall.instance, Items.power_potion, Items.guard_potion, Items.power_potion, Items.guard_potion,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, enemies[3], Wall.instance,
                    Items.power_gem, Items.power_potion, Items.power_gem, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Door(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.yellow),
                ),
                arrayOf(
                    Items.golden_feather, Wall.instance,
                    Items.life_potion, Items.life_potion, Wall.instance, null, Items.power_gem, enemies[13],
                    enemies[3], Items.heavenly_potion, Wall.instance, null, null, Items.power_potion, enemies[14],
                ),
                arrayOf(
                    Door(KeyOrDoorColor.crimson), Wall.instance, enemies[3], Wall.instance,
                    Wall.instance, Wall.instance, Door(KeyOrDoorColor.yellow), null, null, enemies[3],
                    Wall.instance, enemies[12], Items.blue_potion, Items.heavenly_potion, Items.blue_potion,
                ),
                arrayOf(
                    Items.heavenly_potion, enemies[4], enemies[3], null, Wall.instance, Items.power_gem,
                    null, Wall.instance, null, null, Wall.instance, Door(KeyOrDoorColor.yellow), enemies[3],
                    Items.guard_potion, null,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance,
                    Items.power_potion, Wall.instance, Items.power_potion, Items.power_gem, Wall.instance,
                    enemies[13], Door(KeyOrDoorColor.yellow), null, Items.life_potion, Wall.instance, enemies[14],
                    enemies[12],
                ),
                arrayOf(
                    Staircase.down, null, null, null, Items.power_potion, null, null,
                    enemies[5], null, enemies[12], Items.life_potion, enemies[4], Staircase.up, Wall.instance,
                    enemies[13],
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, enemies[13],
                    Wall.instance, enemies[11], enemies[2], Wall.instance, Items.power_card, null, enemies[12],
                    null, Wall.instance, enemies[5], enemies[4],
                ),
                arrayOf(
                    enemies[12], null, Items.power_gem,
                    Items.heavenly_potion, Wall.instance, enemies[2], enemies[11], Wall.instance, null,
                    enemies[4], null, Door(KeyOrDoorColor.yellow), Items.power_potion, Items.guard_potion,
                    Items.power_potion,
                ),
                arrayOf(
                    Items.power_gem, enemies[12], null, Items.power_gem,
                    Wall.instance, enemies[11], enemies[2], null, enemies[3], Items.heavenly_potion, enemies[4],
                    enemies[5], Items.blue_potion, Items.life_potion, Items.blue_potion,
                ),
                arrayOf(
                    enemies[12],
                    Items.power_gem, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    null, null, enemies[3], null, Wall.instance, Items.blue_potion, Items.heavenly_potion,
                    Items.blue_potion,
                ),
                arrayOf(
                    Items.power_gem, enemies[12], Items.power_gem, enemies[3],
                    Wall.instance, enemies[11], Items.power_gem, enemies[3], enemies[11], null, null,
                    Wall.instance, Items.power_potion, Items.guard_potion, Items.power_potion,
                ),
                arrayOf(
                    enemies[12], Items.power_gem, enemies[12], Items.power_gem, Wall.instance,
                    Items.power_potion, enemies[11], Items.power_gem, enemies[3], Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Door(KeyOrDoorColor.blue),
                ),
                arrayOf(
                    Key(KeyOrDoorColor.yellow), enemies[12], Items.power_gem, enemies[12],
                    Door(KeyOrDoorColor.blue), Key(KeyOrDoorColor.yellow), Items.power_potion, enemies[11],
                    Items.power_gem, enemies[6], Items.golden_feather, Wall.instance, Key(KeyOrDoorColor.yellow),
                    Items.life_crown, enemies[7],
                ),
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    null, null, Items.blue_potion,
                    null, null, Wall.instance, null, null, null, Wall.instance, null, null, null, null, null,
                ),
                arrayOf(
                    null, Items.guard_potion, Items.guard_card, Items.guard_potion, null, Wall.instance,
                    null, null, null, Wall.instance, null, null, Items.heavenly_potion, null, null,
                ),
                arrayOf(
                    Items.blue_potion, Items.guard_card, Items.guard_deck, Items.guard_card,
                    Items.blue_potion, Door(KeyOrDoorColor.blue), null, null, null, Door(KeyOrDoorColor.yellow),
                    null, Items.heavenly_potion, Items.golden_feather, Items.heavenly_potion, null,
                ),
                arrayOf(
                    null, Items.guard_potion, Items.guard_card, Items.guard_potion, null, Wall.instance,
                    null, null, null, Wall.instance, null, null, Items.heavenly_potion, null, null,
                ),
                arrayOf(
                    null, null, Items.blue_potion, null, null, Wall.instance, null, null, null,
                    Wall.instance, null, null, null, null, null,
                ),
                arrayOf(
                    Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, null, null, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    null,
                    null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, Items.drop_of_dream_ocean, null, null, null,
                    null, Staircase.down, null, Staircase.up,
                ),
                arrayOf(
                    null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, null, null, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    null,
                    null, Items.red_potion, null, null, Wall.instance, null, null, null, Wall.instance, null,
                    null, Items.power_piece, null, null,
                ),
                arrayOf(
                    null, Items.power_potion, Items.power_card,
                    Items.power_potion, null, Wall.instance, null, null, null, Wall.instance, null,
                    Items.guard_piece, Items.drop_of_dream_ocean, Items.guard_piece, null,
                ),
                arrayOf(
                    Items.red_potion, Items.power_card, Items.power_deck, Items.power_card,
                    Items.red_potion, Door(KeyOrDoorColor.crimson), null, null, null, Door(KeyOrDoorColor.violet),
                    Items.power_piece, Items.drop_of_dream_ocean, Items.life_crown, Items.drop_of_dream_ocean,
                    Items.power_piece,
                ),
                arrayOf(
                    null, Items.power_potion, Items.power_card, Items.power_potion,
                    null, Wall.instance, null, null, null, Wall.instance, null, Items.guard_piece,
                    Items.drop_of_dream_ocean, Items.guard_piece, null,
                ),
                arrayOf(
                    null, null, Items.red_potion,
                    null, null, Wall.instance, null, null, null, Wall.instance, null, null, Items.power_piece,
                    null, null,
                ),
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    Items.heavenly_potion,
                    Items.drop_of_dream_ocean, Key(KeyOrDoorColor.yellow), Key(KeyOrDoorColor.blue),
                    Key(KeyOrDoorColor.crimson), Door(KeyOrDoorColor.violet), null, enemies[7], Items.power_card,
                    Items.power_deck, Items.power_card, enemies[7], null, Door(KeyOrDoorColor.crimson),
                    Key(KeyOrDoorColor.violet),
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Items.heavenly_potion, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Items.heavenly_potion,
                    Wall.instance, Door(KeyOrDoorColor.blue),
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance,
                    Items.guard_deck, Items.guard_card, enemies[16], Wall.instance, null, enemies[6],
                    Items.power_potion, Items.power_card, Items.power_potion, enemies[6], null, Wall.instance,
                    Items.life_crown,
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, enemies[7], Wall.instance,
                    enemies[16], Wall.instance, enemies[15], Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, enemies[15], Wall.instance, enemies[7],
                ),
                arrayOf(
                    enemies[17],
                    Wall.instance, enemies[7], Wall.instance, Items.power_card, Wall.instance, null, enemies[5],
                    Items.power_gem, Items.heavenly_potion, Items.power_gem, enemies[5], null, Wall.instance,
                    enemies[6],
                ),
                arrayOf(
                    enemies[17], Items.power_deck, Items.power_card, Wall.instance,
                    Items.power_card, enemies[6], Items.heavenly_potion, null, Wall.instance, Wall.instance,
                    Wall.instance, null, Items.heavenly_potion, enemies[6], enemies[6],
                ),
                arrayOf(
                    Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    enemies[15], null, Items.power_card, null, enemies[15], Wall.instance, Wall.instance,
                    Wall.instance,
                ),
                arrayOf(
                    Staircase.up, Items.drop_of_dream_ocean,
                    Door(KeyOrDoorColor.crimson), Door(KeyOrDoorColor.crimson), Door(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.yellow),
                    Items.power_card, Items.power_deck, Items.power_card, enemies[15], Key(KeyOrDoorColor.blue),
                    Key(KeyOrDoorColor.yellow), Staircase.down,
                ),
                arrayOf(
                    Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, enemies[6], null,
                    Items.power_card, null, enemies[6], Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, Items.guard_gem, enemies[16], Items.power_gem,
                    Wall.instance, Items.heavenly_potion, null, Wall.instance, Wall.instance, Wall.instance, null,
                    Items.heavenly_potion, Door(KeyOrDoorColor.blue), Items.drop_of_dream_ocean,
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, Items.guard_potion, Wall.instance,
                    Items.power_potion, Wall.instance, null, enemies[14], Items.guard_gem,
                    Key(KeyOrDoorColor.yellow), Items.guard_gem, enemies[14], null, Wall.instance,
                    Door(KeyOrDoorColor.yellow),
                ),
                arrayOf(
                    Items.golden_feather, Door(KeyOrDoorColor.crimson),
                    Items.guard_card, Wall.instance, Items.power_card, Door(KeyOrDoorColor.yellow), enemies[6],
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, enemies[6],
                    Wall.instance, Items.golden_feather,
                ),
                arrayOf(
                    Items.heavenly_potion, enemies[17],
                    Items.guard_potion, Wall.instance, Items.power_potion, enemies[15], null, enemies[15],
                    Items.guard_potion, Key(KeyOrDoorColor.blue), Items.guard_potion, enemies[15], null,
                    Wall.instance, enemies[18],
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, Items.guard_gem,
                    Wall.instance, Items.power_gem, Wall.instance, null, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, null, Wall.instance, Items.drop_of_dream_ocean,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.yellow), Wall.instance, Key(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.blue), Key(KeyOrDoorColor.crimson), Wall.instance, Items.heavenly_potion,
                    enemies[16], Items.guard_card, Key(KeyOrDoorColor.crimson), Items.guard_card, enemies[16],
                    Items.heavenly_potion, null, enemies[16],
                ),
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    Items.golden_feather, Items.power_deck, Wall.instance,
                    Items.power_deck, Items.power_card, Wall.instance, Items.power_potion, Items.power_card,
                    Items.power_card, Wall.instance, Door(KeyOrDoorColor.yellow), Items.golden_feather,
                    Wall.instance, Items.life_crown, Items.life_crown,
                ),
                arrayOf(
                    Wall.instance,
                    Door(KeyOrDoorColor.crimson), enemies[14], Wall.instance, Items.power_card, Wall.instance,
                    Items.power_potion, Items.power_potion, Items.power_card, Wall.instance,
                    Door(KeyOrDoorColor.yellow), Wall.instance, Door(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.blue), Wall.instance,
                ),
                arrayOf(
                    Wall.instance, enemies[14], enemies[14],
                    Wall.instance, Door(KeyOrDoorColor.blue), Wall.instance, Items.power_potion,
                    Items.power_potion, Wall.instance, Items.power_potion, Items.power_potion,
                    Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.blue), Wall.instance, Items.guard_deck,
                ),
                arrayOf(
                    Items.power_card, Wall.instance, enemies[14], Wall.instance, enemies[15],
                    Wall.instance, Door(KeyOrDoorColor.yellow), Items.power_potion, Wall.instance,
                    Items.power_card, Items.power_potion, Items.guard_potion, Wall.instance,
                    Items.drop_of_dream_ocean, Items.drop_of_dream_ocean,
                ),
                arrayOf(
                    Items.power_card,
                    Wall.instance, enemies[14], Wall.instance, enemies[15], Wall.instance, enemies[8],
                    Wall.instance, Items.power_potion, Items.power_card, Items.power_potion, Wall.instance,
                    Door(KeyOrDoorColor.crimson), Items.guard_deck, Wall.instance,
                ),
                arrayOf(
                    Items.power_card,
                    enemies[16], null, null, Items.guard_potion, Wall.instance, enemies[8], Wall.instance,
                    Items.power_potion, Items.power_potion, Wall.instance, Items.life_potion, enemies[9],
                    Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance,
                    Items.power_potion, Items.guard_potion, Wall.instance, Items.power_potion, Items.power_card,
                    enemies[16], Wall.instance, Items.blue_potion, Items.life_potion, Wall.instance,
                    Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Staircase.down, Key(KeyOrDoorColor.platinum),
                    Door(KeyOrDoorColor.platinum), Items.power_potion, Items.guard_potion, enemies[16],
                    Items.power_potion, Items.power_card, Items.power_deck, enemies[17], Items.blue_potion,
                    Items.life_potion, enemies[19], Items.drop_of_dream_ocean, Staircase.up,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, Items.power_potion, Items.guard_potion,
                    Wall.instance, Items.power_potion, Items.power_card, enemies[7], Wall.instance,
                    Items.blue_potion, Items.life_potion, Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.heavenly_potion, enemies[7], null, null, Items.guard_potion, Wall.instance,
                    enemies[17], Wall.instance, Items.blue_potion, Items.blue_potion, Wall.instance,
                    Items.life_potion, enemies[9], Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.heavenly_potion,
                    Wall.instance, enemies[5], Wall.instance, enemies[6], Wall.instance, enemies[17],
                    Wall.instance, Items.blue_potion, Items.heavenly_potion, Items.life_potion, Wall.instance,
                    Door(KeyOrDoorColor.crimson), Items.power_deck, Wall.instance,
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, enemies[5], Wall.instance, enemies[6],
                    Wall.instance, Door(KeyOrDoorColor.yellow), Items.life_potion, Wall.instance,
                    Items.heavenly_potion, Items.life_potion, Items.power_potion, Wall.instance,
                    Items.drop_of_dream_ocean, Items.drop_of_dream_ocean,
                ),
                arrayOf(
                    Wall.instance, enemies[5],
                    enemies[5], Wall.instance, Door(KeyOrDoorColor.blue), Wall.instance, Items.life_potion,
                    Items.life_potion, Wall.instance, Items.power_potion, Items.power_potion,
                    Door(KeyOrDoorColor.yellow), Items.power_deck, Wall.instance, Items.power_deck,
                ),
                arrayOf(
                    Wall.instance, Door(KeyOrDoorColor.crimson), enemies[5], Wall.instance,
                    Items.guard_deck, Wall.instance, Items.life_potion, Items.life_potion, Items.heavenly_potion,
                    Wall.instance, Door(KeyOrDoorColor.yellow), Wall.instance, Door(KeyOrDoorColor.blue),
                    Items.guard_deck, Door(KeyOrDoorColor.platinum),
                ),
                arrayOf(
                    Items.golden_feather,
                    Items.drop_of_dream_ocean, Wall.instance, Items.drop_of_dream_ocean, Items.heavenly_potion,
                    Wall.instance, Items.life_potion, Items.heavenly_potion, Items.heavenly_potion, Wall.instance,
                    Door(KeyOrDoorColor.yellow), Items.golden_feather, Wall.instance,
                    Door(KeyOrDoorColor.crimson), Key(KeyOrDoorColor.violet),
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
                    null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null,
                    null, Items.heavenly_potion, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null,
                    null, null, null, null, Items.heavenly_potion, Items.drop_of_dream_ocean,
                    Items.heavenly_potion, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null,
                    null, Items.heavenly_potion, Items.drop_of_dream_ocean, Items.life_crown,
                    Items.drop_of_dream_ocean, Items.heavenly_potion, null, null, null, null, Staircase.down,
                ),
                arrayOf(
                    null, null, null, null, null, null, Items.heavenly_potion, Items.drop_of_dream_ocean,
                    Items.heavenly_potion, null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null,
                    null, null, null, Items.heavenly_potion, null, null, null, null, null, null, null,
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
    )

    private val nexusLevels: Array<TowerLevel> = arrayOf(
        TowerLevel(
            arrayOf(
                arrayOf(
                    null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, Staircase.up,
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
                arrayOf(
                    null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, PlayerStartPosition.instance,
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
            )
        ),
        TowerLevel(
            arrayOf(
                arrayOf(
                    null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, Staircase.down,
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
                    Wall.instance, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                ),
                arrayOf(
                    Staircase.up, enemies[0], null, null, null, null, null, null, null, null, null, null,
                    null, null, null,
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
                arrayOf(
                    null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null,
                ),
                arrayOf(
                    Staircase.down, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null,
                ),
            )
        ),
    )

    private val checkScore: Position = Position(3, 7, 7)

    private val starScore: Position = Position(6, 7, 7)

    override fun levels(): Array<Level> = levels

    override fun standardLevels(): Array<TowerLevel> = standardLevels

    override fun nexusLevels(): Array<TowerLevel> = nexusLevels

    override fun atk(): Int = 50

    override fun def(): Int = 0

    override fun hp(): Int = 1_000

    override fun checkScore(): Position = checkScore

    override fun starScore(): Position = starScore
}
