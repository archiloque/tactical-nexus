package net.archiloque.tacticalnexus.solver.input.towers

import javax.annotation.processing.Generated
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
        Enemy(EnemyType.burgeoner, 16, 9999, 820, 10, 300, null),
    )

    private val fighters: Array<Enemy?> = arrayOf(
        null,
        Enemy(EnemyType.fighter, 1, 120, 55, 25, 2, Items.guard_piece),
        null,
        Enemy(EnemyType.fighter, 3, 320, 110, 40, 5, Items.guard_piece),
        null,
        Enemy(EnemyType.fighter, 5, 600, 145, 65, 10, Items.guard_gem),
        null,
        null,
        Enemy(EnemyType.fighter, 8, 1000, 260, 85, 18, Items.guard_gem),
        null,
        null,
        null,
        Enemy(EnemyType.fighter, 12, 2000, 440, 120, 30, Items.guard_potion),
        null,
        null,
        Enemy(EnemyType.fighter, 15, 3500, 590, 170, 47, Items.guard_potion),
        null,
        null,
        null,
        Enemy(EnemyType.fighter, 19, 5500, 1120, 445, 70, Items.guard_card),
        null,
        null,
        null,
        Enemy(EnemyType.fighter, 23, 8000, 1650, 620, 100, Items.guard_card),
        null,
        null,
        null,
        null,
        Enemy(EnemyType.fighter, 28, 1600, 2150, 700, 138, Items.guard_card),
    )

    private val rangers: Array<Enemy?> = arrayOf(
        null,
        null,
        Enemy(EnemyType.ranger, 2, 200, 200, 10, 4, Items.red_potion),
        null,
        Enemy(EnemyType.ranger, 4, 200, 350, 20, 16, Items.red_potion),
        null,
        null,
        Enemy(EnemyType.ranger, 7, 800, 280, 40, 36, Items.blue_potion),
        null,
        null,
        Enemy(EnemyType.ranger, 10, 800, 540, 60, 64, Items.blue_potion),
        null,
        null,
        Enemy(EnemyType.ranger, 13, 800, 1050, 80, 100, Items.blue_potion),
        null,
        null,
        Enemy(EnemyType.ranger, 16, 2000, 1100, 120, 144, Items.life_potion),
        null,
        Enemy(EnemyType.ranger, 18, 2000, 1660, 240, 196, Items.life_potion),
        null,
        null,
        null,
        Enemy(EnemyType.ranger, 22, 3000, 2350, 360, 256, Items.heavenly_potion),
        null,
        null,
        null,
        Enemy(EnemyType.ranger, 26, 3000, 3740, 720, 324, Items.heavenly_potion),
        null,
        null,
        null,
        Enemy(EnemyType.ranger, 30, 9999, 4800, 480, 400, Items.drop_of_dream_ocean),
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
            15, 15, arrayOf(
                arrayOf(
                    null,
                    Wall.instance, null, null, Wall.instance, null, null, null, null, Wall.instance, null, null,
                    Wall.instance, null, null,
                ),
                arrayOf(
                    null, Wall.instance, null, null, Wall.instance, null,
                    null, null, null, Wall.instance, null, null, null, null, null,
                ),
                arrayOf(
                    Door(KeyOrDoorColor.yellow), Wall.instance, Wall.instance, null,
                    Door(KeyOrDoorColor.yellow), null, null, null, null, Wall.instance, Wall.instance, null,
                    Wall.instance, Wall.instance, Wall.instance,
                ),
                arrayOf(
                    null, null, null, null, Wall.instance,
                    null, null, null, null, Wall.instance, null, null, Wall.instance, null, null,
                ),
                arrayOf(
                    null,
                    null, null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, null,
                    null, null, Wall.instance, null, null,
                ),
                arrayOf(
                    null, null, null, null, Wall.instance, null,
                    null, null, null, Wall.instance, null, Wall.instance, Wall.instance, null, Wall.instance,
                ),
                arrayOf(
                    Wall.instance, Wall.instance, Wall.instance, Door(KeyOrDoorColor.yellow),
                    Wall.instance, null, null, null, null, Wall.instance, null, null, Wall.instance, null, null,
                ),
                arrayOf(
                    null, null, Wall.instance, Key(KeyOrDoorColor.yellow), Wall.instance, null,
                    Wall.instance, Wall.instance, null, Wall.instance, null, null, null, null, null,
                ),
                arrayOf(
                    null, PlayerStartPosition.instance, null, null, Wall.instance, null, Wall.instance,
                    null, null, Wall.instance, Wall.instance, null, null, null, Wall.instance,
                ),
                arrayOf(
                    null,
                    null, Wall.instance, Wall.instance, Door(KeyOrDoorColor.yellow), null, null, null, null,
                    Wall.instance, null, null, null, null, null,
                ),
                arrayOf(
                    Wall.instance, Wall.instance,
                    Wall.instance, null, Door(KeyOrDoorColor.yellow), Wall.instance, Wall.instance, null, null,
                    Wall.instance, null, null, Wall.instance, null, null,
                ),
                arrayOf(
                    null, null, null, null,
                    Door(KeyOrDoorColor.yellow), Wall.instance, null, null, null, Wall.instance, Wall.instance,
                    null, Wall.instance, null, Wall.instance,
                ),
                arrayOf(
                    null, Wall.instance, null, null, null,
                    Wall.instance, null, null, null, Wall.instance, null, null, null, null, null,
                ),
                arrayOf(
                    null,
                    Wall.instance, Wall.instance, null, Wall.instance, null, null, null, null, Wall.instance,
                    null, null, null, null, null,
                ),
                arrayOf(
                    null, Wall.instance, null, null, Staircase.up, null,
                    Wall.instance, null, null, Wall.instance, null, null, null, null, null,
                ),
            )
        ),
        TowerLevel(
            9,
            7, arrayOf(
                arrayOf(null, null, null, null, null, null, null),
                arrayOf(
                    null, null, null,
                    null, null, null, null,
                ),
                arrayOf(null, null, null, null, null, null, null),
                arrayOf(
                    null,
                    null, null, null, null, null, null,
                ),
                arrayOf(null, null, null, null, null, null, null),
                arrayOf(null, null, null, null, null, null, null),
                arrayOf(
                    null, null, null, null, null,
                    null, null,
                ),
                arrayOf(null, null, null, null, null, null, null),
                arrayOf(
                    null, null, null,
                    null, null, null, Staircase.down,
                ),
            )
        ),
    )

    private val nexusLevels: Array<TowerLevel> = arrayOf()

    private val checkScore: Position = Position(1, 1, 5)

    private val starScore: Position = Position(1, 4, 6)

    override fun levels(): Array<Level> = levels

    override fun standardLevels(): Array<TowerLevel> = standardLevels

    override fun nexusLevels(): Array<TowerLevel> = nexusLevels

    override fun atk(): Int = 50

    override fun def(): Int = 0

    override fun hp(): Int = 1_000

    override fun checkScore(): Position = checkScore

    override fun starScore(): Position = starScore
}
