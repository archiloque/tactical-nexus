package net.archiloque.tacticalnexus.solver.code

import net.archiloque.tacticalnexus.solver.Position
import net.archiloque.tacticalnexus.solver.entities.Level
import net.archiloque.tacticalnexus.solver.entities.Staircase
import net.archiloque.tacticalnexus.solver.entities.Wall
import net.archiloque.tacticalnexus.solver.input.Items
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TowerTest {

    @Test
    fun prepareHorizontal() {
        val tower =
            Tower.prepare(
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
                )
            )
        assertEquals(tower.positionedEntities.size, 3)
        assertArrayEquals(
            tower.positionedEntities,
            arrayOf(
                Tower.PositionedEntity(Staircase.up, 0, 0, 0),
                Tower.PositionedEntity(Items.blue_potion, 0, 0, 2),
                Tower.PositionedEntity(Staircase.down, 0, 0, 4),
            )
        )
        assertEquals(
            tower.entitiesIndexByPosition.size,
            3
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 0, 0)],
            0
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 0, 2)],
            1
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 0, 4)],
            2
        )
        assertEquals(
            tower.reachableEntities.size,
            3
        )
        assertArrayEquals(
            tower.reachableEntities[0],
            arrayOf(1)
        )
        assertArrayEquals(
            tower.reachableEntities[1],
            arrayOf(0, 2)
        )
        assertArrayEquals(
            tower.reachableEntities[2],
            arrayOf(1)
        )
    }

    @Test
    fun prepareVertical() {
        val tower =
            Tower.prepare(
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
                )
            )
        assertEquals(tower.positionedEntities.size, 3)
        assertArrayEquals(
            tower.positionedEntities,
            arrayOf(
                Tower.PositionedEntity(Staircase.up, 0, 0, 0),
                Tower.PositionedEntity(Items.blue_potion, 0, 2, 0),
                Tower.PositionedEntity(Staircase.down, 0, 4, 0),
            )
        )
        assertEquals(
            tower.entitiesIndexByPosition.size,
            3
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 0, 0)],
            0
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 2, 0)],
            1
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 4, 0)],
            2
        )
        assertEquals(
            tower.reachableEntities.size,
            3
        )
        assertArrayEquals(
            tower.reachableEntities[0],
            arrayOf(1)
        )
        assertArrayEquals(
            tower.reachableEntities[1],
            arrayOf(0, 2)
        )
        assertArrayEquals(
            tower.reachableEntities[2],
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
        val tower =
            Tower.prepare(
                arrayOf(
                    Level(
                        5,
                        5,
                        arrayOf(
                            arrayOf(Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,),
                            arrayOf(Wall.instance, Staircase.up, null, Wall.instance, Wall.instance,),
                            arrayOf(Wall.instance, null, Items.blue_potion, Wall.instance, Wall.instance,),
                            arrayOf(Wall.instance, Wall.instance, null, Staircase.down, Wall.instance,),
                            arrayOf(Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance),
                        )
                    )
                )
            )
        assertEquals(tower.positionedEntities.size, 3)
        assertArrayEquals(
            tower.positionedEntities,
            arrayOf(
                Tower.PositionedEntity(Staircase.up, 0, 1, 1),
                Tower.PositionedEntity(Items.blue_potion, 0, 2, 2),
                Tower.PositionedEntity(Staircase.down, 0, 3, 3),
            )
        )
        assertEquals(
            tower.entitiesIndexByPosition.size,
            3
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 1, 1)],
            0
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 2, 2)],
            1
        )
        assertEquals(
            tower.entitiesIndexByPosition[Position(0, 3, 3)],
            2
        )
        assertEquals(
            tower.reachableEntities.size,
            3
        )
        assertArrayEquals(
            tower.reachableEntities[0],
            arrayOf(1)
        )
        assertArrayEquals(
            tower.reachableEntities[1],
            arrayOf(0, 2)
        )
        assertArrayEquals(
            tower.reachableEntities[2],
            arrayOf(1)
        )
    }
}