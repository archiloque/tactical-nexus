package net.archiloque.tacticalnexus.solver.code

import kotlin.test.assertEquals
import net.archiloque.tacticalnexus.solver.entities.Exit
import net.archiloque.tacticalnexus.solver.entities.Level
import net.archiloque.tacticalnexus.solver.entities.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.Staircase
import net.archiloque.tacticalnexus.solver.entities.Wall
import net.archiloque.tacticalnexus.solver.input.Items
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class PlayableTowerTest {

    @Test
    fun prepareHorizontal() {
        val playableTower =
            PlayableTower.prepare(
                TowerForTest(
                    arrayOf(
                        Level(
                            1,
                            5,
                            arrayOf(
                                arrayOf(
                                    PlayerStartPosition.instance,
                                    null,
                                    Items.blue_potion,
                                    null,
                                    Staircase.up,
                                )
                            )
                        ),
                        Level(
                            1,
                            2,
                            arrayOf(
                                arrayOf(
                                    Staircase.down,
                                    Exit.instance,
                                )
                            )
                        )
                    ), 0, 0, 0
                )
            )

        assertArrayEquals(
            playableTower.positionedEntities,
            arrayOf(
                PositionedEntity(PlayerStartPosition.instance, Position(0, 0, 0)),
                PositionedEntity(Items.blue_potion, Position(0, 0, 2)),
                PositionedEntity(Staircase.up, Position(0, 0, 4)),
                PositionedEntity(Exit.instance, Position(1, 0, 1)),
            )
        )
        assertEquals(
            playableTower.entitiesIndexByPosition.size,
            4
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 0, 0)],
            0
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 0, 2)],
            1
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 0, 4)],
            2
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(1, 0, 1)],
            3
        )
        assertArrayEquals(
            playableTower.reachable,
            arrayOf(
                arrayOf(1),
                arrayOf(0, 2),
                arrayOf(3),
                arrayOf(),
            )
        )
    }

    @Test
    fun prepareVertical() {
        val playableTower =
            PlayableTower.prepare(
                TowerForTest(
                    arrayOf(
                        Level(
                            5,
                            1,
                            arrayOf(
                                arrayOf(PlayerStartPosition.instance),
                                arrayOf(null),
                                arrayOf(Items.blue_potion),
                                arrayOf(null),
                                arrayOf(Staircase.up),
                            )
                        ),
                        Level(
                            2,
                            1,
                            arrayOf(
                                arrayOf(
                                    Staircase.down,
                                ),
                                arrayOf(
                                    Exit.instance,
                                )
                            )
                        )
                    ), 0, 0, 0
                )
            )
        assertEquals(playableTower.positionedEntities.size, 4)
        assertArrayEquals(
            playableTower.positionedEntities,
            arrayOf(
                PositionedEntity(PlayerStartPosition.instance, Position(0, 0, 0)),
                PositionedEntity(Items.blue_potion, Position(0, 2, 0)),
                PositionedEntity(Staircase.up, Position(0, 4, 0)),
                PositionedEntity(Exit.instance, Position(1, 1, 0)),
            )
        )
        assertEquals(
            playableTower.entitiesIndexByPosition.size,
            4
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 0, 0)],
            0
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 2, 0)],
            1
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 4, 0)],
            2
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(1, 1, 0)],
            3
        )

        assertArrayEquals(
            playableTower.reachable,
            arrayOf(
                arrayOf(1),
                arrayOf(0, 2),
                arrayOf(3),
                arrayOf(),
            )
        )
    }

    @Test
    fun prepareComplex() {
        // This is the tower:
        // wwwww
        // wX ww
        // w Bww
        // wwRSw
        // wwwww
        val playableTower =
            PlayableTower.prepare(
                TowerForTest(
                    arrayOf(
                        Level(
                            5,
                            5,
                            arrayOf(
                                arrayOf(Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance),
                                arrayOf(
                                    Wall.instance,
                                    PlayerStartPosition.instance,
                                    null,
                                    Wall.instance,
                                    Wall.instance
                                ),
                                arrayOf(Wall.instance, null, Items.blue_potion, Wall.instance, Wall.instance),
                                arrayOf(Wall.instance, Wall.instance, Items.red_potion, Staircase.up, Wall.instance),
                                arrayOf(Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance),
                            )
                        ),
                        Level(
                            1,
                            2,
                            arrayOf(
                                arrayOf(Staircase.down, Exit.instance),
                            )
                        )
                    ), 0, 0, 0
                )
            )
        assertArrayEquals(
            playableTower.positionedEntities,
            arrayOf(
                PositionedEntity(PlayerStartPosition.instance, Position(0, 1, 1)),
                PositionedEntity(Items.blue_potion, Position(0, 2, 2)),
                PositionedEntity(Items.red_potion, Position(0, 3, 2)),
                PositionedEntity(Staircase.up, Position(0, 3, 3)),
                PositionedEntity(Exit.instance, Position(1, 0, 1)),
            )
        )
        assertEquals(
            playableTower.entitiesIndexByPosition.size,
            5
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 1, 1)],
            0
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 2, 2)],
            1
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 3, 2)],
            2
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(0, 3, 3)],
            3
        )
        assertEquals(
            playableTower.entitiesIndexByPosition[Position(1, 0, 1)],
            4
        )
        assertArrayEquals(
            playableTower.reachable,
            arrayOf(
                arrayOf(1),
                arrayOf(0, 2),
                arrayOf(1, 3),
                arrayOf(4),
                arrayOf(),
            )
        )
    }
}