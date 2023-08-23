package net.archiloque.tacticalnexus.solver.input

import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.solver.entities.LevelUp

@Generated
public class LevelUps {
    public companion object {
        public val levelUps: Array<LevelUp> = arrayOf(
            LevelUp(0, 0, 0, 0, 0, 0, 0),
            LevelUp(
                1, 10, 5,
                10, 3, 2, 1,
            ),
            LevelUp(2, 30, 6, 12, 3, 2, 1), LevelUp(3, 60, 7, 14, 3, 2, 1),
            LevelUp(
                4,
                100, 8, 16, 3, 2, 1,
            ),
        )
    }
}
