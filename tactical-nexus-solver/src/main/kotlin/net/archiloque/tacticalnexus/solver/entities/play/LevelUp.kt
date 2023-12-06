package net.archiloque.tacticalnexus.solver.entities.play

import java.util.Collections

data class LevelUp(
    val level: Short,
    val deltaExp: Int,
    val exp: Int,
) {
    companion object {
        val levelUps = Collections.synchronizedList(mutableListOf(LevelUp(0, 0, 0)))

        fun levelUp(exp: Int): LevelUp {
            val nexLevelUpExp = levelUps.indexOfFirst { it.exp > exp }
            return if (nexLevelUpExp == -1) {
                createLevelsUp(exp)
                levelUp(exp)
            } else {
                levelUps[nexLevelUpExp - 1]
            }
        }

        private fun createLevelsUp(exp: Int) {
            synchronized(Enemy) {
                var maxLevel = levelUps.last()
                while (maxLevel.exp <= exp) {
                    val newLevelNumber = (maxLevel.level + 1).toShort()
                    val deltaExp = maxLevel.deltaExp + (newLevelNumber * 10)
                    maxLevel = LevelUp(
                        newLevelNumber,
                        deltaExp,
                        deltaExp + maxLevel.exp,
                    )
                    levelUps.add(maxLevel)
                }
            }
        }
    }
}
