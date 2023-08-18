package net.archiloque.tacticalnexus.solver.code

import kotlin.test.assertEquals
import net.archiloque.tacticalnexus.solver.Position
import net.archiloque.tacticalnexus.solver.entities.Level
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
                                    Staircase.up, null, Items.blue_potion, null, Staircase.down
                                )
                            )
                        )
                    ), 0, 0, 0
                )
            )
            
        assertEquals(playableTower.positionedEntities.size, 3)
        assertArrayEquals(
            playableTower.positionedEntities,
            arrayOf(
                PlayableTower.PositionedEntity(Staircase.up, 0, 0, 0),
                PlayableTower.PositionedEntity(Items.blue_potion, 0, 0, 2),
                PlayableTower.PositionedEntity(Staircase.down, 0, 0, 4),
            )
        )
        assertEquals(
            playableTower.entitiesIndexByPosition.size,
            3
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
            playableTower.reachable.size,
            3
        )
        assertArrayEquals(
            playableTower.reachable[0],
            arrayOf(1)
        )
        assertArrayEquals(
            playableTower.reachable[1],
            arrayOf(0, 2)
        )
        assertArrayEquals(
            playableTower.reachable[2],
            arrayOf(1)
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
                                arrayOf(Staircase.up),
                                arrayOf(null),
                                arrayOf(Items.blue_potion),
                                arrayOf(null),
                                arrayOf(Staircase.down),
                            )
                        )
                    ), 0, 0, 0
                )
            )
        assertEquals(playableTower.positionedEntities.size, 3)
        assertArrayEquals(
            playableTower.positionedEntities,
            arrayOf(
                PlayableTower.PositionedEntity(Staircase.up, 0, 0, 0),
                PlayableTower.PositionedEntity(Items.blue_potion, 0, 2, 0),
                PlayableTower.PositionedEntity(Staircase.down, 0, 4, 0),
            )
        )
        assertEquals(
            playableTower.entitiesIndexByPosition.size,
            3
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
            playableTower.reachable.size,
            3
        )
        assertArrayEquals(
            playableTower.reachable[0],
            arrayOf(1)
        )
        assertArrayEquals(
            playableTower.reachable[1],
            arrayOf(0, 2)
        )
        assertArrayEquals(
            playableTower.reachable[2],
            arrayOf(1)
        )
    }

    @Test
    fun prepareComplex() {
        // This is the tower:
        // wwwww
        // ws ww
        // w pww
        // ww Sw
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
                                arrayOf(Wall.instance, Staircase.up, null, Wall.instance, Wall.instance),
                                arrayOf(Wall.instance, null, Items.blue_potion, Wall.instance, Wall.instance),
                                arrayOf(Wall.instance, Wall.instance, null, Staircase.down, Wall.instance),
                                arrayOf(Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance),
                            )
                        )
                    ), 0, 0, 0
                )
            )
        assertEquals(playableTower.positionedEntities.size, 3)
        assertArrayEquals(
            playableTower.positionedEntities,
            arrayOf(
                PlayableTower.PositionedEntity(Staircase.up, 0, 1, 1),
                PlayableTower.PositionedEntity(Items.blue_potion, 0, 2, 2),
                PlayableTower.PositionedEntity(Staircase.down, 0, 3, 3),
            )
        )
        assertEquals(
            playableTower.entitiesIndexByPosition.size,
            3
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
            playableTower.entitiesIndexByPosition[Position(0, 3, 3)],
            2
        )
        assertEquals(
            playableTower.reachable.size,
            3
        )
        assertArrayEquals(
            playableTower.reachable[0],
            arrayOf(1)
        )
        assertArrayEquals(
            playableTower.reachable[1],
            arrayOf(0, 2)
        )
        assertArrayEquals(
            playableTower.reachable[2],
            arrayOf(1)
        )
    }
}