package net.archiloque.tacticalnexus.solver.input.towers

import javax.`annotation`.processing.Generated
import kotlin.Array
import kotlin.Int
import kotlin.arrayOf
import net.archiloque.tacticalnexus.solver.entities.Direction
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
public class Tower_3 : Tower {
    private val enemies: Array<Enemy> = arrayOf(
    )

    private val levels: Array<Level> = arrayOf(
    )

    private val standardLevels: Array<TowerLevel> =
        arrayOf(
            TowerLevel(
                arrayOf(
                    arrayOf(
                        Items.blue_potion, Door(KeyOrDoorColor.yellow),
                        Key(KeyOrDoorColor.crimson), PlayerStartPosition.instance, Door(KeyOrDoorColor.crimson),
                        Staircase.up, null, null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        Wall.instance,
                        Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, null, null,
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
                )
            ),
            TowerLevel(
                arrayOf(
                    arrayOf(
                        Staircase.down,
                        OneWay(Direction.right), Key(KeyOrDoorColor.platinum), null, Wall.instance, null, null, null,
                        null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        Door(KeyOrDoorColor.platinum),
                        Wall.instance, Key(KeyOrDoorColor.yellow), null, Wall.instance, null, null, null, null, null,
                        null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null, Wall.instance, null, null,
                        null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance,
                        Wall.instance, null, null, null, null, null, null, null, null, null, null, null, null,
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
                        null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null, null,
                    ),
                )
            ),
        )

    private val nexusLevels: Array<TowerLevel> = arrayOf()

    private val checkScore: Position = Position(1, 0, 2)

    private val starScore: Position = Position(1, 1, 2)

    override fun levels(): Array<Level> = levels

    override fun standardLevels(): Array<TowerLevel> = standardLevels

    override fun nexusLevels(): Array<TowerLevel> = nexusLevels

    override fun atk(): Int = 50

    override fun def(): Int = 0

    override fun hp(): Int = 1_000

    override fun checkScore(): Position = checkScore

    override fun starScore(): Position = starScore
}
