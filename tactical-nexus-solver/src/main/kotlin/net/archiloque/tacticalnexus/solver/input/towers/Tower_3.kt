package net.archiloque.tacticalnexus.solver.input.towers

import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.solver.entities.Direction
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Door
import net.archiloque.tacticalnexus.solver.entities.input.Key
import net.archiloque.tacticalnexus.solver.entities.input.Level
import net.archiloque.tacticalnexus.solver.entities.input.OneWay
import net.archiloque.tacticalnexus.solver.entities.input.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.input.TowerLevel
import net.archiloque.tacticalnexus.solver.entities.input.Wall
import net.archiloque.tacticalnexus.solver.input.Items

@Generated
public class Tower_3 : Tower {
    private val levels: Array<Level> = arrayOf(
    )

    private val standardLevels: Array<TowerLevel> =
        arrayOf(
            TowerLevel(
                arrayOf(
                    arrayOf(
                        Items.blue_potion, Door(KeyOrDoorColor.crimson),
                        PlayerStartPosition.instance, Items.guard_potion, OneWay(Direction.right),
                        Key(KeyOrDoorColor.yellow), null, null, null, null, null, null, null, null, null,
                    ),
                    arrayOf(
                        Wall.instance, Wall.instance, Door(KeyOrDoorColor.yellow), Wall.instance,
                        Wall.instance, Key(KeyOrDoorColor.crimson), null, null, null, null, null, null, null, null,
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

    private val checkScore: Position = Position(0, 0, 5)

    private val starScore: Position = Position(0, 1, 5)

    override fun levels(): Array<Level> = levels

    override fun standardLevels(): Array<TowerLevel> = standardLevels

    override fun nexusLevels(): Array<TowerLevel> = nexusLevels

    override fun atk(): Int = 50

    override fun def(): Int = 0

    override fun hp(): Int = 1_000

    override fun checkScore(): Position = checkScore

    override fun starScore(): Position = starScore
}
