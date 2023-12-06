package net.archiloque.tacticalnexus.solver.input.towers

import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.solver.entities.EnemyType
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Enemy
import net.archiloque.tacticalnexus.solver.entities.input.Item
import net.archiloque.tacticalnexus.solver.entities.input.Level
import net.archiloque.tacticalnexus.solver.entities.input.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.input.TowerLevel
import net.archiloque.tacticalnexus.solver.entities.input.Wall
import net.archiloque.tacticalnexus.solver.input.Items

@Generated
public class Tower_3 : Tower {
    private val item_blue_potion: Item = Item("Blue potion", 0, 0, 0, 1, 80, 0, 1)

    private val item_drop_of_dream_ocean: Item = Item("Drop of dream ocean", 5, 5, 0, 1, 999, 0, 1)

    private val item_golden_feather: Item = Item("Golden feather", 0, 0, 0, 2, 0, 0, 1)

    private val item_guard_potion: Item = Item("Guard potion", 0, 3, 0, 1, 30, 0, 1)

    private val item_heavenly_potion: Item = Item("Heavenly potion", 3, 3, 0, 1, 300, 0, 1)

    private val item_life_crown: Item = Item("Life Crown", 0, 0, 0, 1, 0, 100, 1)

    private val item_life_potion: Item = Item("Life potion", 0, 0, 0, 1, 200, 0, 1)

    private val item_power_potion: Item = Item("Power potion", 3, 0, 0, 1, 30, 0, 1)

    private val item_red_potion: Item = Item("Red potion", 0, 0, 0, 1, 20, 0, 1)

    private val enemies: Array<Enemy> = arrayOf(
        Enemy(EnemyType.burgeoner, 3, 100, 15, 0, 30, null, null),
        Enemy(EnemyType.burgeoner, 6, 200, 25, 0, 40, null, null),
        Enemy(EnemyType.burgeoner, 9, 300, 40, 10, 50, null, null),
        Enemy(EnemyType.burgeoner, 12, 500, 60, 10, 60, null, null),
        Enemy(EnemyType.burgeoner, 15, 999, 80, 20, 70, null, null),
        Enemy(EnemyType.burgeoner, 18, 1400, 100, 30, 80, null, null),
        Enemy(EnemyType.burgeoner, 21, 1800, 140, 40, 90, null, null),
        Enemy(EnemyType.burgeoner, 24, 2200, 180, 40, 100, null, null),
        Enemy(EnemyType.burgeoner, 32, 5000, 360, 70, 120, null, null),
        Enemy(EnemyType.burgeoner, 40, 9999, 600, 200, 140, null, null),
        Enemy(EnemyType.burgeoner, 50, 18000, 999, 255, 160, null, null),
        Enemy(EnemyType.burgeoner, 70, 44444, 1800, 500, 300, null, null),
        Enemy(EnemyType.fighter, 1, 20, 10, 0, 3, Items.guard_piece, null),
        Enemy(EnemyType.fighter, 3, 30, 20, 0, 4, Items.guard_piece, null),
        Enemy(EnemyType.fighter, 5, 60, 30, 10, 5, Items.guard_gem, null),
        Enemy(EnemyType.fighter, 7, 100, 40, 20, 6, Items.guard_gem, null),
        Enemy(EnemyType.fighter, 9, 200, 70, 30, 7, item_guard_potion, null),
        Enemy(EnemyType.fighter, 11, 350, 90, 40, 8, item_guard_potion, null),
        Enemy(EnemyType.fighter, 15, 550, 120, 60, 9, Items.guard_card, null),
        Enemy(EnemyType.fighter, 22, 800, 160, 90, 10, Items.guard_card, null),
        Enemy(EnemyType.fighter, 32, 2000, 280, 150, 12, Items.guard_card, null),
        Enemy(EnemyType.fighter, 40, 3000, 450, 250, 15, Items.guard_deck, null),
        Enemy(EnemyType.fighter, 45, 6000, 900, 350, 19, Items.guard_deck, null),
        Enemy(EnemyType.fighter, 65, 16000, 1400, 600, 200, Items.pulse_book, null),
        Enemy(EnemyType.ranger, 1, 20, 15, 0, 3, item_red_potion, null),
        Enemy(EnemyType.ranger, 3, 20, 30, 10, 4, item_red_potion, null),
        Enemy(EnemyType.ranger, 5, 80, 40, 10, 5, item_blue_potion, null),
        Enemy(EnemyType.ranger, 7, 80, 70, 10, 6, item_blue_potion, null),
        Enemy(EnemyType.ranger, 9, 80, 100, 20, 7, item_blue_potion, null),
        Enemy(EnemyType.ranger, 11, 200, 100, 30, 8, item_life_potion, null),
        Enemy(EnemyType.ranger, 15, 200, 160, 50, 9, item_life_potion, null),
        Enemy(EnemyType.ranger, 22, 300, 220, 80, 10, item_heavenly_potion, null),
        Enemy(EnemyType.ranger, 26, 300, 300, 120, 12, item_heavenly_potion, null),
        Enemy(EnemyType.ranger, 35, 999, 500, 160, 15, item_drop_of_dream_ocean, null),
        Enemy(EnemyType.ranger, 52, 999, 2500, 250, 19, item_drop_of_dream_ocean, null),
        Enemy(EnemyType.ranger, 60, 20000, 2000, 200, 200, item_golden_feather, null),
        Enemy(EnemyType.slasher, 1, 20, 10, 0, 3, Items.power_piece, null),
        Enemy(EnemyType.slasher, 3, 40, 20, 0, 4, Items.power_piece, null),
        Enemy(EnemyType.slasher, 5, 80, 50, 0, 5, Items.power_gem, null),
        Enemy(EnemyType.slasher, 7, 160, 60, 5, 6, Items.power_gem, null),
        Enemy(EnemyType.slasher, 9, 200, 100, 10, 7, item_power_potion, null),
        Enemy(EnemyType.slasher, 11, 320, 150, 15, 8, item_power_potion, null),
        Enemy(EnemyType.slasher, 15, 500, 210, 20, 9, Items.power_card, null),
        Enemy(EnemyType.slasher, 22, 800, 300, 40, 10, Items.power_card, null),
        Enemy(EnemyType.slasher, 32, 1200, 400, 75, 12, Items.power_card, null),
        Enemy(EnemyType.slasher, 40, 2500, 650, 140, 15, Items.power_deck, null),
        Enemy(EnemyType.slasher, 45, 4000, 1200, 210, 19, Items.power_deck, null),
        Enemy(EnemyType.slasher, 60, 8000, 1850, 300, 200, Items.pulse_book, null),
    )

    private val levels: Array<Level> = arrayOf(
        Level(0, 1, 0, 0, 0, 0, 0, 0, 0),
        Level(0, 0, 0, 1, 0, 0, 0, 0, 0),
        Level(0, 0, 0, 1, 0, 50, 0, 0, 0),
    )

    private val standardLevels: Array<TowerLevel> =
        arrayOf(
            TowerLevel(
                arrayOf(
                    arrayOf(
                        item_golden_feather, Wall.instance, item_life_crown,
                        enemies[43], Wall.instance, item_golden_feather, null, item_golden_feather, null,
                        item_life_crown, Wall.instance, item_golden_feather, enemies[9], enemies[45], item_life_crown,
                    ),
                    arrayOf(
                        enemies[7], enemies[19], enemies[19], enemies[42], enemies[31], enemies[47],
                        enemies[22], enemies[11], enemies[46], enemies[23], enemies[32], Wall.instance, enemies[21],
                        Wall.instance, enemies[45],
                    ),
                    arrayOf(
                        enemies[43], enemies[43], enemies[42], enemies[30],
                        Items.guard_deck, enemies[22], enemies[34], enemies[21], enemies[34], enemies[46],
                        enemies[32], enemies[21], enemies[21], enemies[33], enemies[8],
                    ),
                    arrayOf(
                        enemies[19],
                        enemies[18], enemies[30], enemies[31], item_heavenly_potion, enemies[45], enemies[22],
                        item_drop_of_dream_ocean, enemies[22], enemies[21], enemies[45], Wall.instance,
                        item_life_potion, Wall.instance, item_life_potion,
                    ),
                    arrayOf(
                        enemies[18], enemies[30],
                        enemies[6], enemies[43], enemies[19], enemies[45], enemies[34], enemies[45], enemies[34],
                        enemies[21], enemies[44], enemies[20], enemies[32], enemies[44], enemies[20],
                    ),
                    arrayOf(
                        item_life_crown, enemies[41], item_power_potion, enemies[13], item_guard_potion,
                        enemies[26], item_red_potion, enemies[10], item_red_potion, enemies[25], Wall.instance,
                        item_power_potion, enemies[8], enemies[14], item_life_potion,
                    ),
                    arrayOf(
                        Wall.instance,
                        item_blue_potion, enemies[37], Items.power_gem, enemies[38], Items.power_piece, enemies[36],
                        enemies[24], enemies[12], Items.power_gem, enemies[39], enemies[14], enemies[26], enemies[26],
                        Wall.instance,
                    ),
                    arrayOf(
                        item_golden_feather, enemies[1], enemies[25], enemies[25],
                        enemies[25], enemies[0], null, PlayerStartPosition.instance, null, enemies[1], enemies[26],
                        item_blue_potion, enemies[15], enemies[2], item_golden_feather,
                    ),
                    arrayOf(
                        Wall.instance,
                        enemies[38], enemies[13], Items.guard_gem, enemies[37], Items.guard_piece, enemies[12],
                        enemies[24], enemies[36], Items.guard_gem, enemies[15], enemies[38], enemies[26], enemies[26],
                        Wall.instance,
                    ),
                    arrayOf(
                        enemies[4], item_life_potion, Wall.instance, Wall.instance,
                        Wall.instance, enemies[24], Items.power_piece, enemies[3], Items.power_piece, enemies[27],
                        Wall.instance, item_guard_potion, enemies[7], enemies[39], item_heavenly_potion,
                    ),
                    arrayOf(
                        enemies[28], Wall.instance, enemies[17], enemies[17], item_heavenly_potion,
                        Wall.instance, enemies[16], enemies[15], enemies[40], item_life_crown, enemies[43],
                        enemies[19], enemies[31], enemies[19], Wall.instance,
                    ),
                    arrayOf(
                        enemies[28], Wall.instance,
                        enemies[5], Wall.instance, enemies[41], Wall.instance, item_life_potion, enemies[27],
                        item_life_potion, enemies[28], enemies[44], enemies[20], enemies[31], enemies[8],
                        item_golden_feather,
                    ),
                    arrayOf(
                        enemies[40], Wall.instance, item_golden_feather,
                        Wall.instance, enemies[41], Wall.instance, enemies[27], enemies[16], enemies[28],
                        item_life_potion, enemies[44], enemies[31], item_heavenly_potion, enemies[32], Wall.instance,
                    ),
                    arrayOf(
                        enemies[40], Wall.instance, Wall.instance, enemies[29], item_heavenly_potion,
                        enemies[18], enemies[40], enemies[4], enemies[40], enemies[42], enemies[43], enemies[32],
                        enemies[33], enemies[20], enemies[35],
                    ),
                    arrayOf(
                        enemies[16], enemies[16], item_life_potion,
                        enemies[29], Wall.instance, item_life_potion, Wall.instance, item_golden_feather,
                        Wall.instance, item_life_crown, enemies[43], Wall.instance, Items.guard_deck, Wall.instance,
                        item_life_crown,
                    ),
                )
            ),
        )

    private val nexusLevels: Array<TowerLevel> = arrayOf()

    private val checkScore: Position? = null

    private val crownScore: Position? = Position(0, 0, 7)

    private val starScore: Position? = null

    override fun levels(): Array<Level> = levels

    override fun standardLevels(): Array<TowerLevel> = standardLevels

    override fun nexusLevels(): Array<TowerLevel> = nexusLevels

    override fun atk(): Int = 10

    override fun def(): Int = 0

    override fun hp(): Int = 100

    override fun checkScore(): Position? = checkScore

    override fun crownScore(): Position? = crownScore

    override fun starScore(): Position? = starScore
}
