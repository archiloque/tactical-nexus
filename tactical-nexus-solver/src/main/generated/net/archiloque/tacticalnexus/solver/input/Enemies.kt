package net.archiloque.tacticalnexus.solver.input

import net.archiloque.tacticalnexus.solver.Enemy
import net.archiloque.tacticalnexus.solver.EnemyId
import net.archiloque.tacticalnexus.solver.EnemyType
import javax.annotation.processing.Generated

@Generated
class Enemies {
    companion object {
        val enemies = mutableMapOf<EnemyId, Enemy>()

        init {
            enemies[EnemyId(EnemyType.fighter, 1)] = Enemy(0, 1, 1, 1, "")
        }
    }
}
