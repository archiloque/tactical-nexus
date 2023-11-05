package net.archiloque.tacticalnexus.solver.input.towers

import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.solver.entities.EnemyType
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.input.Door
import net.archiloque.tacticalnexus.solver.entities.input.Enemy
import net.archiloque.tacticalnexus.solver.entities.input.Exit
import net.archiloque.tacticalnexus.solver.entities.input.Key
import net.archiloque.tacticalnexus.solver.entities.input.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.input.Staircase
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.input.TowerLevel
import net.archiloque.tacticalnexus.solver.entities.input.Wall
import net.archiloque.tacticalnexus.solver.input.Items

@Generated
public class Tower_1 : Tower {
    private val fighters: Array<Enemy?> = arrayOf(
        null,
        Enemy(
            EnemyType.fighter, 1, 120, 55, 25, 2,
            Items.guard_piece
        ),
        null, Enemy(EnemyType.fighter, 3, 320, 110, 40, 5, Items.guard_piece),
        null, Enemy(EnemyType.fighter, 5, 600, 145, 65, 10, Items.guard_gem), null, null,
        Enemy(EnemyType.fighter, 8, 1000, 260, 85, 18, Items.guard_gem), null, null, null,
        Enemy(EnemyType.fighter, 12, 2000, 440, 120, 30, Items.guard_potion), null, null,
        Enemy(EnemyType.fighter, 15, 3500, 590, 170, 47, Items.guard_potion), null, null, null,
        Enemy(EnemyType.fighter, 19, 5500, 1120, 445, 70, Items.guard_card), null, null, null,
        Enemy(EnemyType.fighter, 23, 8000, 1650, 620, 100, Items.guard_card), null, null, null, null,
        Enemy(EnemyType.fighter, 28, 1600, 2150, 700, 138, Items.guard_card),
    )

    private val rangers: Array<Enemy?> = arrayOf(
        null, null,
        Enemy(
            EnemyType.ranger, 2, 200, 200, 10,
            4, Items.red_potion
        ),
        null, Enemy(EnemyType.ranger, 4, 200, 350, 20, 16, Items.red_potion),
        null, null, Enemy(EnemyType.ranger, 7, 800, 280, 40, 36, Items.blue_potion), null, null,
        Enemy(EnemyType.ranger, 10, 800, 540, 60, 64, Items.blue_potion), null, null,
        Enemy(EnemyType.ranger, 13, 800, 1050, 80, 100, Items.blue_potion), null, null,
        Enemy(EnemyType.ranger, 16, 2000, 1100, 120, 144, Items.life_potion), null,
        Enemy(EnemyType.ranger, 18, 2000, 1660, 240, 196, Items.life_potion), null, null, null,
        Enemy(EnemyType.ranger, 22, 3000, 2350, 360, 256, Items.heavenly_potion), null, null, null,
        Enemy(EnemyType.ranger, 26, 3000, 3740, 720, 324, Items.heavenly_potion), null, null, null,
        Enemy(EnemyType.ranger, 30, 9999, 4800, 480, 400, Items.drop_of_dream_ocean),
    )

    private val levels: Array<TowerLevel> = arrayOf(
        TowerLevel(
            11, 15, arrayOf(
                arrayOf(
                    null, null,
                    null, null, null, null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, Wall.instance,
                    Items.guard_gem, Items.guard_potion, Items.guard_gem, rangers[2], null, Wall.instance, null,
                    null,
                ),
                arrayOf(
                    null, null, null, null, null, Wall.instance, Items.power_piece,
                    Wall.instance, Items.guard_gem, Wall.instance, null, Wall.instance, Items.red_potion,
                    Wall.instance, null,
                ),
                arrayOf(
                    null, null, null, null, Wall.instance, Items.power_piece,
                    Items.power_gem, Items.power_piece, fighters[3], null, Items.power_gem, fighters[1],
                    Items.life_potion, Items.red_potion, Wall.instance,
                ),
                arrayOf(
                    Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Items.power_piece, Wall.instance,
                    Items.blue_potion, Wall.instance, null, Wall.instance, rangers[4], Wall.instance,
                    Wall.instance,
                ),
                arrayOf(
                    null, PlayerStartPosition.instance, null, Items.power_gem,
                    Items.life_potion, null, fighters[1], Items.blue_potion, fighters[1], Items.heavenly_potion,
                    fighters[1], fighters[3], Key(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.yellow),
                    Staircase.up,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Items.guard_piece, Wall.instance, Items.blue_potion,
                    Wall.instance, null, Wall.instance, rangers[4], Wall.instance, Wall.instance,
                ),
                arrayOf(
                    null,
                    null, null, null, Wall.instance, Items.guard_piece, Items.guard_gem, Items.guard_piece,
                    fighters[3], null, Items.power_gem, fighters[1], Items.life_potion, Items.red_potion,
                    Wall.instance,
                ),
                arrayOf(
                    null, null, null, null, null, Wall.instance, Items.guard_piece,
                    Wall.instance, null, Wall.instance, null, Wall.instance, Items.red_potion, Wall.instance,
                    null,
                ),
                arrayOf(
                    null, null, null, null, null, null, Wall.instance, null, Items.power_potion,
                    null, rangers[2], null, Wall.instance, null, null,
                ),
                arrayOf(
                    null, null, null, null, null,
                    null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null,
                    null, null,
                ),
            )
        ),
        TowerLevel(
            15, 15, arrayOf(
                arrayOf(
                    Items.guard_deck, Wall.instance, null,
                    Items.guard_piece, null, fighters[5], null, Items.power_gem, null, fighters[8],
                    Key(KeyOrDoorColor.yellow), Items.power_potion, null, fighters[12], Items.power_card,
                ),
                arrayOf(
                    fighters[8], Wall.instance, Items.guard_piece, Items.power_gem, Items.guard_piece,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.yellow), Wall.instance, null, Items.guard_piece, fighters[3],
                    Items.blue_potion, null, null, fighters[3], null, Items.power_gem, Items.life_potion, null,
                    rangers[2], Wall.instance,
                ),
                arrayOf(
                    fighters[8], null, Items.heavenly_potion, null,
                    Wall.instance, Door(KeyOrDoorColor.blue), Wall.instance, Wall.instance, null, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Items.blue_potion, Wall.instance,
                ),
                arrayOf(
                    null, Items.power_potion, null, Wall.instance, Wall.instance, Items.life_potion,
                    Items.power_potion, Wall.instance, Items.power_gem, Door(KeyOrDoorColor.yellow),
                    Items.guard_gem, Items.guard_potion, Wall.instance, Items.power_potion, Wall.instance,
                ),
                arrayOf(
                    null, null, rangers[4], Items.power_potion, Wall.instance, Items.power_potion,
                    Items.heavenly_potion, Wall.instance, null, Wall.instance, Items.guard_potion,
                    Items.guard_gem, Wall.instance, fighters[1], Wall.instance,
                ),
                arrayOf(
                    Wall.instance,
                    Wall.instance, null, Items.blue_potion, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null,
                    Wall.instance,
                ),
                arrayOf(
                    Staircase.up, Items.drop_of_dream_ocean, fighters[5], null,
                    fighters[3], Items.power_gem, null, null, fighters[3], null, Items.life_potion, rangers[2],
                    null, Items.heavenly_potion, Staircase.down,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, null,
                    Items.blue_potion, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, Wall.instance,
                ),
                arrayOf(
                    null, null, rangers[4], Items.guard_potion, Wall.instance, Items.power_potion,
                    Items.power_gem, Wall.instance, null, Wall.instance, Items.guard_card, Items.guard_potion,
                    Wall.instance, fighters[1], Wall.instance,
                ),
                arrayOf(
                    null, Items.guard_potion, null,
                    Wall.instance, Wall.instance, Items.power_gem, Items.power_potion,
                    Door(KeyOrDoorColor.yellow), Items.guard_gem, Wall.instance, Items.guard_potion,
                    Items.guard_card, Wall.instance, Items.guard_potion, Wall.instance,
                ),
                arrayOf(
                    fighters[8],
                    null, Items.heavenly_potion, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    null, Wall.instance, Wall.instance, Door(KeyOrDoorColor.blue), Wall.instance,
                    Items.blue_potion, Wall.instance,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.blue), Wall.instance, null,
                    Items.power_piece, fighters[3], Items.blue_potion, null, null, rangers[4], null,
                    Items.power_gem, Items.life_potion, null, rangers[2], Wall.instance,
                ),
                arrayOf(
                    rangers[22],
                    Wall.instance, Items.power_piece, Items.guard_gem, Items.power_piece, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.life_crown, Wall.instance, null,
                    Items.power_piece, null, rangers[7], null, Items.life_potion, null, rangers[10], null,
                    Items.heavenly_potion, null, rangers[13], Key(KeyOrDoorColor.blue),
                ),
            )
        ),
        TowerLevel(
            15, 15,
            arrayOf(
                arrayOf(
                    Items.life_crown, Items.golden_feather, Door(KeyOrDoorColor.violet),
                    Items.heavenly_potion, Wall.instance, Key(KeyOrDoorColor.yellow), fighters[8],
                    Door(KeyOrDoorColor.yellow), Items.power_gem, Key(KeyOrDoorColor.crimson), Wall.instance,
                    Items.guard_potion, Items.power_potion, Items.guard_potion, Items.power_potion,
                ),
                arrayOf(
                    Items.drop_of_dream_ocean, Wall.instance, Items.power_potion, fighters[5],
                    Wall.instance, fighters[8], Items.power_gem, rangers[16], Items.power_gem, Items.power_gem,
                    Wall.instance, Items.power_potion, Items.guard_potion, Items.power_potion, Items.guard_potion,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, fighters[5], Wall.instance,
                    Items.power_gem, Items.power_potion, Items.power_gem, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Door(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.yellow),
                ),
                arrayOf(
                    Items.golden_feather, Wall.instance,
                    Items.life_potion, Items.life_potion, Wall.instance, null, Items.power_gem, rangers[10],
                    fighters[5], Items.heavenly_potion, Wall.instance, null, null, Items.power_potion,
                    rangers[13],
                ),
                arrayOf(
                    Door(KeyOrDoorColor.crimson), Wall.instance, fighters[5],
                    Wall.instance, Wall.instance, Wall.instance, Door(KeyOrDoorColor.yellow), null, null,
                    fighters[5], Wall.instance, rangers[7], Items.blue_potion, Items.heavenly_potion,
                    Items.blue_potion,
                ),
                arrayOf(
                    Items.heavenly_potion, fighters[8], fighters[5], null,
                    Wall.instance, Items.power_gem, null, Wall.instance, null, null, Wall.instance,
                    Door(KeyOrDoorColor.yellow), fighters[5], Items.guard_potion, null,
                ),
                arrayOf(
                    Wall.instance,
                    Wall.instance, Wall.instance, Items.power_potion, Wall.instance, Items.power_potion,
                    Items.power_gem, Wall.instance, rangers[10], Door(KeyOrDoorColor.yellow), null,
                    Items.life_potion, Wall.instance, rangers[13], rangers[7],
                ),
                arrayOf(
                    Staircase.down, null,
                    null, null, Items.power_potion, null, null, fighters[12], null, rangers[7], Items.life_potion,
                    fighters[8], Staircase.up, Wall.instance, rangers[10],
                ),
                arrayOf(
                    Wall.instance,
                    Wall.instance, Wall.instance, rangers[10], Wall.instance, rangers[4], fighters[3],
                    Wall.instance, Items.power_card, null, rangers[7], null, Wall.instance, fighters[12],
                    fighters[8],
                ),
                arrayOf(
                    rangers[7], null, Items.power_gem, Items.heavenly_potion,
                    Wall.instance, fighters[3], rangers[4], Wall.instance, null, fighters[8], null,
                    Door(KeyOrDoorColor.yellow), Items.power_potion, Items.guard_potion, Items.power_potion,
                ),
                arrayOf(
                    Items.power_gem, rangers[7], null, Items.power_gem, Wall.instance, rangers[4],
                    fighters[3], null, fighters[5], Items.heavenly_potion, fighters[8], fighters[12],
                    Items.blue_potion, Items.life_potion, Items.blue_potion,
                ),
                arrayOf(
                    rangers[7],
                    Items.power_gem, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    null, null, fighters[5], null, Wall.instance, Items.blue_potion, Items.heavenly_potion,
                    Items.blue_potion,
                ),
                arrayOf(
                    Items.power_gem, rangers[7], Items.power_gem, fighters[5],
                    Wall.instance, rangers[4], Items.power_gem, fighters[5], rangers[4], null, null,
                    Wall.instance, Items.power_potion, Items.guard_potion, Items.power_potion,
                ),
                arrayOf(
                    rangers[7], Items.power_gem, rangers[7], Items.power_gem, Wall.instance,
                    Items.power_potion, rangers[4], Items.power_gem, fighters[5], Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Door(KeyOrDoorColor.blue),
                ),
                arrayOf(
                    Key(KeyOrDoorColor.yellow), rangers[7], Items.power_gem, rangers[7],
                    Door(KeyOrDoorColor.blue), Key(KeyOrDoorColor.yellow), Items.power_potion, rangers[4],
                    Items.power_gem, fighters[15], Items.golden_feather, Wall.instance,
                    Key(KeyOrDoorColor.yellow), Items.life_crown, fighters[19],
                ),
            )
        ),
        TowerLevel(
            15, 15,
            arrayOf(
                arrayOf(
                    null, null, Items.blue_potion, null, null, Wall.instance, null, null, null,
                    Wall.instance, null, null, null, null, null,
                ),
                arrayOf(
                    null, Items.guard_potion,
                    Items.guard_card, Items.guard_potion, null, Wall.instance, null, null, null, Wall.instance,
                    null, null, Items.heavenly_potion, null, null,
                ),
                arrayOf(
                    Items.blue_potion, Items.guard_card,
                    Items.guard_deck, Items.guard_card, Items.blue_potion, Door(KeyOrDoorColor.blue), null, null,
                    null, Door(KeyOrDoorColor.yellow), null, Items.heavenly_potion, Items.golden_feather,
                    Items.heavenly_potion, null,
                ),
                arrayOf(
                    null, Items.guard_potion, Items.guard_card,
                    Items.guard_potion, null, Wall.instance, null, null, null, Wall.instance, null, null,
                    Items.heavenly_potion, null, null,
                ),
                arrayOf(
                    null, null, Items.blue_potion, null, null,
                    Wall.instance, null, null, null, Wall.instance, null, null, null, null, null,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, null, null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null,
                ),
                arrayOf(
                    null, null, null, null, null, null, null,
                    Items.drop_of_dream_ocean, null, null, null, null, Staircase.down, null, Staircase.up,
                ),
                arrayOf(
                    null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, null, null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance,
                ),
                arrayOf(
                    null, null, Items.red_potion, null, null,
                    Wall.instance, null, null, null, Wall.instance, null, null, Items.power_piece, null, null,
                ),
                arrayOf(
                    null, Items.power_potion, Items.power_card, Items.power_potion, null, Wall.instance,
                    null, null, null, Wall.instance, null, Items.guard_piece, Items.drop_of_dream_ocean,
                    Items.guard_piece, null,
                ),
                arrayOf(
                    Items.red_potion, Items.power_card, Items.power_deck,
                    Items.power_card, Items.red_potion, Door(KeyOrDoorColor.crimson), null, null, null,
                    Door(KeyOrDoorColor.violet), Items.power_piece, Items.drop_of_dream_ocean, Items.life_crown,
                    Items.drop_of_dream_ocean, Items.power_piece,
                ),
                arrayOf(
                    null, Items.power_potion,
                    Items.power_card, Items.power_potion, null, Wall.instance, null, null, null, Wall.instance,
                    null, Items.guard_piece, Items.drop_of_dream_ocean, Items.guard_piece, null,
                ),
                arrayOf(
                    null,
                    null, Items.red_potion, null, null, Wall.instance, null, null, null, Wall.instance, null,
                    null, Items.power_piece, null, null,
                ),
            )
        ),
        TowerLevel(
            15, 15,
            arrayOf(
                arrayOf(
                    Items.heavenly_potion, Items.drop_of_dream_ocean, Key(KeyOrDoorColor.yellow),
                    Key(KeyOrDoorColor.blue), Key(KeyOrDoorColor.crimson), Door(KeyOrDoorColor.violet), null,
                    fighters[19], Items.power_card, Items.power_deck, Items.power_card, fighters[19], null,
                    Door(KeyOrDoorColor.crimson), Key(KeyOrDoorColor.violet),
                ),
                arrayOf(
                    Items.heavenly_potion,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Items.heavenly_potion, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Items.heavenly_potion, Wall.instance, Door(KeyOrDoorColor.blue),
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, Items.guard_deck, Items.guard_card, rangers[18],
                    Wall.instance, null, fighters[15], Items.power_potion, Items.power_card, Items.power_potion,
                    fighters[15], null, Wall.instance, Items.life_crown,
                ),
                arrayOf(
                    Items.heavenly_potion,
                    Wall.instance, fighters[19], Wall.instance, rangers[18], Wall.instance, rangers[16],
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, rangers[16],
                    Wall.instance, fighters[19],
                ),
                arrayOf(
                    rangers[22], Wall.instance, fighters[19],
                    Wall.instance, Items.power_card, Wall.instance, null, fighters[12], Items.power_gem,
                    Items.heavenly_potion, Items.power_gem, fighters[12], null, Wall.instance, fighters[15],
                ),
                arrayOf(
                    rangers[22], Items.power_deck, Items.power_card, Wall.instance, Items.power_card,
                    fighters[15], Items.heavenly_potion, null, Wall.instance, Wall.instance, Wall.instance, null,
                    Items.heavenly_potion, fighters[15], fighters[15],
                ),
                arrayOf(
                    Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, rangers[16], null,
                    Items.power_card, null, rangers[16], Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Staircase.up, Items.drop_of_dream_ocean, Door(KeyOrDoorColor.crimson),
                    Door(KeyOrDoorColor.crimson), Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.yellow), Items.power_card, Items.power_deck,
                    Items.power_card, rangers[16], Key(KeyOrDoorColor.blue), Key(KeyOrDoorColor.yellow),
                    Staircase.down,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, fighters[15], null, Items.power_card, null,
                    fighters[15], Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.heavenly_potion,
                    Wall.instance, Items.guard_gem, rangers[18], Items.power_gem, Wall.instance,
                    Items.heavenly_potion, null, Wall.instance, Wall.instance, Wall.instance, null,
                    Items.heavenly_potion, Door(KeyOrDoorColor.blue), Items.drop_of_dream_ocean,
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, Items.guard_potion, Wall.instance,
                    Items.power_potion, Wall.instance, null, rangers[13], Items.guard_gem,
                    Key(KeyOrDoorColor.yellow), Items.guard_gem, rangers[13], null, Wall.instance,
                    Door(KeyOrDoorColor.yellow),
                ),
                arrayOf(
                    Items.golden_feather, Door(KeyOrDoorColor.crimson),
                    Items.guard_card, Wall.instance, Items.power_card, Door(KeyOrDoorColor.yellow), fighters[15],
                    Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, fighters[15],
                    Wall.instance, Items.golden_feather,
                ),
                arrayOf(
                    Items.heavenly_potion, rangers[22],
                    Items.guard_potion, Wall.instance, Items.power_potion, rangers[16], null, rangers[16],
                    Items.guard_potion, Key(KeyOrDoorColor.blue), Items.guard_potion, rangers[16], null,
                    Wall.instance, rangers[26],
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, Items.guard_gem,
                    Wall.instance, Items.power_gem, Wall.instance, null, Wall.instance, Wall.instance,
                    Wall.instance, Wall.instance, Wall.instance, null, Wall.instance, Items.drop_of_dream_ocean,
                ),
                arrayOf(
                    Key(KeyOrDoorColor.yellow), Wall.instance, Key(KeyOrDoorColor.blue),
                    Door(KeyOrDoorColor.blue), Key(KeyOrDoorColor.crimson), Wall.instance, Items.heavenly_potion,
                    rangers[18], Items.guard_card, Key(KeyOrDoorColor.crimson), Items.guard_card, rangers[18],
                    Items.heavenly_potion, null, rangers[18],
                ),
            )
        ),
        TowerLevel(
            15, 15,
            arrayOf(
                arrayOf(
                    Items.golden_feather, Items.power_deck, Wall.instance, Items.power_deck,
                    Items.power_card, Wall.instance, Items.power_potion, Items.power_card, Items.power_card,
                    Wall.instance, Door(KeyOrDoorColor.yellow), Items.golden_feather, Wall.instance,
                    Items.life_crown, Items.life_crown,
                ),
                arrayOf(
                    Wall.instance, Door(KeyOrDoorColor.crimson),
                    rangers[13], Wall.instance, Items.power_card, Wall.instance, Items.power_potion,
                    Items.power_potion, Items.power_card, Wall.instance, Door(KeyOrDoorColor.yellow),
                    Wall.instance, Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.blue), Wall.instance,
                ),
                arrayOf(
                    Wall.instance, rangers[13], rangers[13], Wall.instance, Door(KeyOrDoorColor.blue),
                    Wall.instance, Items.power_potion, Items.power_potion, Wall.instance, Items.power_potion,
                    Items.power_potion, Door(KeyOrDoorColor.blue), Door(KeyOrDoorColor.blue), Wall.instance,
                    Items.guard_deck,
                ),
                arrayOf(
                    Items.power_card, Wall.instance, rangers[13], Wall.instance,
                    rangers[16], Wall.instance, Door(KeyOrDoorColor.yellow), Items.power_potion, Wall.instance,
                    Items.power_card, Items.power_potion, Items.guard_potion, Wall.instance,
                    Items.drop_of_dream_ocean, Items.drop_of_dream_ocean,
                ),
                arrayOf(
                    Items.power_card,
                    Wall.instance, rangers[13], Wall.instance, rangers[16], Wall.instance, fighters[23],
                    Wall.instance, Items.power_potion, Items.power_card, Items.power_potion, Wall.instance,
                    Door(KeyOrDoorColor.crimson), Items.guard_deck, Wall.instance,
                ),
                arrayOf(
                    Items.power_card,
                    rangers[18], null, null, Items.guard_potion, Wall.instance, fighters[23], Wall.instance,
                    Items.power_potion, Items.guard_potion, Wall.instance, Items.life_potion, fighters[28],
                    Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance,
                    Items.power_potion, Items.guard_potion, Wall.instance, Items.power_potion, Items.power_card,
                    rangers[18], Wall.instance, Items.blue_potion, Items.life_potion, Wall.instance,
                    Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Staircase.down, Key(KeyOrDoorColor.platinum),
                    Door(KeyOrDoorColor.platinum), Items.power_potion, Items.guard_potion, rangers[18],
                    Items.power_potion, Items.power_card, Items.power_deck, rangers[22], Items.blue_potion,
                    Items.life_potion, rangers[30], Items.drop_of_dream_ocean, Exit.instance,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, Items.power_potion, Items.guard_potion,
                    Wall.instance, Items.power_potion, Items.power_card, fighters[19], Wall.instance,
                    Items.blue_potion, Items.life_potion, Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.heavenly_potion, fighters[19], null, null, Items.guard_potion, Wall.instance,
                    rangers[22], Wall.instance, Items.blue_potion, Items.blue_potion, Wall.instance,
                    Items.life_potion, fighters[28], Wall.instance, Wall.instance,
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, fighters[12], Wall.instance, fighters[15],
                    Wall.instance, rangers[22], Wall.instance, Items.blue_potion, Items.heavenly_potion,
                    Items.life_potion, Wall.instance, Door(KeyOrDoorColor.crimson), Items.power_deck,
                    Wall.instance,
                ),
                arrayOf(
                    Items.heavenly_potion, Wall.instance, fighters[12], Wall.instance,
                    fighters[15], Wall.instance, Door(KeyOrDoorColor.yellow), Items.life_potion, Wall.instance,
                    Items.heavenly_potion, Items.life_potion, Items.power_potion, Wall.instance,
                    Items.drop_of_dream_ocean, Items.drop_of_dream_ocean,
                ),
                arrayOf(
                    Wall.instance, fighters[12],
                    fighters[12], Wall.instance, Door(KeyOrDoorColor.blue), Wall.instance, Items.life_potion,
                    Items.life_potion, Wall.instance, Items.power_potion, Items.power_potion,
                    Door(KeyOrDoorColor.yellow), Items.power_deck, Wall.instance, Items.power_deck,
                ),
                arrayOf(
                    Wall.instance, Door(KeyOrDoorColor.crimson), fighters[12], Wall.instance,
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
    )

    override fun levels(): Array<TowerLevel> = levels

    override fun atk(): Int = 50

    override fun def(): Int = 0

    override fun hp(): Int = 1_000
}
