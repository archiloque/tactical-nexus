package net.archiloque.tacticalnexus.solver.entities.play

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.input.Staircase
import net.archiloque.tacticalnexus.solver.entities.input.TowerLevel
import net.archiloque.tacticalnexus.solver.entities.input.Wall
import net.archiloque.tacticalnexus.solver.input.Items
import org.junit.jupiter.api.Assertions.assertArrayEquals

class PlayableTowerTest {

    @Test
    fun prepareHorizontal() {
        val playableTower =
            TowerPreparer(
                TowerForTest(
                    arrayOf(
                        TowerLevel(
                            1,
                            6,
                            arrayOf(
                                arrayOf(
                                    PlayerStartPosition.instance,
                                    null,
                                    Items.blue_potion,
                                    Items.red_potion,
                                    null,
                                    Staircase.up,
                                )
                            )
                        ),
                        TowerLevel(
                            1,
                            2,
                            arrayOf(
                                arrayOf(
                                    Staircase.down,
                                    null,
                                )
                            )
                        )
                    ), 0, 0, 0
                )
            ).prepare()

        assertEquals(playableTower.entitiesCount, 3)

        assertContentEquals(
            playableTower.playEntities,
            arrayOf(
                ItemGroup(
                    0,
                    arrayOf(
                        PositionedItem(Items.blue_potion, Position(0, 0, 2)),
                        PositionedItem(Items.red_potion, Position(0, 0, 3))
                    )
                ),
                UpStaircase(0, Position(0, 0, 5)),
            )
        )
        assertEquals(
            playableTower.startingPositionPosition,
            Position(0, 0, 0)
        )
        assertContentEquals(
            playableTower.reachableByStartingPosition,
            intArrayOf(0)
        )
        assertArrayEquals(
            playableTower.reachable,
            arrayOf(
                intArrayOf(1),
                intArrayOf(2),
                intArrayOf(),
            )
        )
    }


    @Test
    fun prepareVertical() {
        val playableTower =
            TowerPreparer(
                TowerForTest(
                    arrayOf(
                        TowerLevel(
                            6,
                            1,
                            arrayOf(
                                arrayOf(PlayerStartPosition.instance),
                                arrayOf(null),
                                arrayOf(Items.blue_potion),
                                arrayOf(Items.red_potion),
                                arrayOf(null),
                                arrayOf(Staircase.up),
                            )
                        ),
                        TowerLevel(
                            2,
                            1,
                            arrayOf(
                                arrayOf(
                                    Staircase.down,
                                ),
                                arrayOf(
                                    null,
                                )
                            )
                        )
                    ), 0, 0, 0
                )
            ).prepare()

        assertEquals(playableTower.entitiesCount, 3)

        assertContentEquals(
            playableTower.playEntities,
            arrayOf(
                ItemGroup(
                    0,
                    arrayOf(
                        PositionedItem(Items.blue_potion, Position(0, 2, 0)),
                        PositionedItem(Items.red_potion, Position(0, 3, 0))
                    ),
                ),

                UpStaircase(0, Position(0, 5, 0)),
            )
        )

        assertEquals(
            playableTower.startingPositionPosition,
            Position(0, 0, 0)
        )
        assertContentEquals(
            playableTower.reachableByStartingPosition,
            intArrayOf(0)
        )
        assertArrayEquals(
            playableTower.reachable,
            arrayOf(
                intArrayOf(1),
                intArrayOf(2),
                intArrayOf(),
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
            TowerPreparer(
                TowerForTest(
                    arrayOf(
                        TowerLevel(
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
                        TowerLevel(
                            1,
                            2,
                            arrayOf(
                                arrayOf(Staircase.down, null),
                            )
                        )
                    ), 0, 0, 0
                )
            ).prepare()
        assertContentEquals(
            playableTower.playEntities,
            arrayOf(
                ItemGroup(
                    0,
                    arrayOf(
                        PositionedItem(Items.blue_potion, Position(0, 2, 2)),
                        PositionedItem(Items.red_potion, Position(0, 3, 2))
                    ),
                ),
                UpStaircase(0, Position(0, 3, 3)),
            )
        )

        assertEquals(
            playableTower.startingPositionPosition,
            Position(0, 1, 1)
        )
        assertContentEquals(
            playableTower.reachableByStartingPosition,
            intArrayOf(0)
        )
        assertArrayEquals(
            playableTower.reachable,
            arrayOf(
                intArrayOf(1),
                intArrayOf(2),
                intArrayOf(),
            )
        )
    }
}