package net.archiloque.tacticalnexus.solver.entities.play

import java.util.Collections

data class LevelUp(
    val number: Int,
    val deltaExp: Int,
    val exp: Int,

    val atk: Int,
    val def: Int,
) {
    companion object {
        private val levelUps = Collections.synchronizedList(mutableListOf(LevelUp(0, 0, 0, 0, 0)))

        fun levelUp(exp: Int): LevelUp {
            val nexLevelUp = levelUps.indexOfFirst { it.exp > exp }
            return if (nexLevelUp == -1) {
                createLevelsUp(exp)
                levelUp(exp)
            } else {
                levelUps[nexLevelUp - 1]
            }
        }

        private fun createLevelsUp(exp: Int) {
            synchronized(Enemy) {
                var maxLevel = levelUps.last()
                while (maxLevel.exp <= exp) {
                    val newLevelNumber = maxLevel.number + 1
                    val deltaExp = maxLevel.deltaExp + (newLevelNumber * 10)
                    maxLevel = LevelUp(
                        newLevelNumber,
                        deltaExp,
                        deltaExp + maxLevel.exp,
                        4 + newLevelNumber,
                        8 + (newLevelNumber * 2)
                    )
                    levelUps.add(maxLevel)
                }
            }
        }

        const val YELLOW_KEYS_NUMBER: Int = 3
        const val CRIMSON_KEYS_NUMBER: Int = 1
        const val BLUE_KEYS_NUMBER: Int = 2
    }
}

enum class LevelUpType(val type: Int) {
    yellowKeys(-1),
    crimsonKeys(-2),
    blueKeys(-3),
    def(-4),
    atk(-5),
}