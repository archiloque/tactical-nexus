package net.archiloque.tacticalnexus.solver.entities

import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.input.LevelUps

data class Enemy(
    val type: EnemyType,
    val level: Int,
    val hp: Int,
    val atk: Int,
    val def: Int,
    val exp: Int,
    val drop: Item,
) : Entity() {
    override fun getType(): EntityType {
        return EntityType.Enemy
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateManager: StateManager) {
        val newState = newState(entityIndex, state)
        val fromLevelUp = findLevelUp(newState.exp)!!

        val fightResult = apply(newState)
        if (fightResult != null) {
            val toLevelUp = findLevelUp(newState.exp)
            if (toLevelUp == null) {
                System.err.println("No level up found")
                stateManager.reachedExit(newState)
                exitProcess(-1)
            } else if (fromLevelUp != toLevelUp) {
                newState.exp -= toLevelUp.exp
                addNewReachablePositions(entityIndex, newState, playableTower)
                for (levelUpType in LevelUpType.entries) {
                    val levelUpState = newState(levelUpType.type, newState)
                    applyLevelUp(levelUpType, levelUpState, toLevelUp)
                    drop.apply(levelUpState)
                    stateManager.save(levelUpState)
                }
            } else {
                drop.apply(state)
                addNewReachablePositions(entityIndex, newState, playableTower)
                stateManager.save(newState)
            }
        }
    }

    fun apply(state: State): Int? {
        val result = calculate(state)
        if (result != null) {
            state.hp = result
            state.exp += (exp * (100 + state.expBonus)) / 100
        }
        return result
    }

    private fun calculate(state: State): Int? {
        val damagesToEnemy = Math.max(state.atk - def, 0)
        val damagesToPlayer = Math.max(atk - state.def, 0)
        if ((damagesToPlayer == 0) && (damagesToEnemy > 0)) {
            // Enemy can't hurt player but player can hurt enemy
            return state.hp
        } else if (damagesToEnemy == 0) {
            // Can't hurt enemy
            return null
        } else {
            var playerCurrentHp = state.hp
            var enemyCurrentHp = hp
            for (turn in 0..1000) {
                enemyCurrentHp -= damagesToEnemy
                if (enemyCurrentHp <= 0) {
                    return playerCurrentHp
                }
                playerCurrentHp -= damagesToPlayer
                if (playerCurrentHp <= 0) {
                    return null
                }
            }
            throw RuntimeException("Too many turns: ${state.atk} ${state.def} ${state.hp} ${atk} ${def} ${hp}")
        }
    }

    companion object {
        private fun findLevelUp(exp: Int): LevelUp? {
            return LevelUps.levelUps.find { it.exp >= exp }
        }

        fun applyLevelUp(
            levelUpType: LevelUpType,
            levelUpState: State,
            toLevelUp: LevelUp,
        ) {
            when (levelUpType) {
                LevelUpType.atk -> {
                    levelUpState.atk += toLevelUp.atk
                }

                LevelUpType.def -> {
                    levelUpState.def += toLevelUp.def
                }

                LevelUpType.blueKeys -> {
                    levelUpState.blueKeys += toLevelUp.blueKeys
                }

                LevelUpType.crimsonKeys -> {
                    levelUpState.crimsonKeys += toLevelUp.crimsonKeys
                }

                LevelUpType.yellowKeys -> {
                    levelUpState.yellowKeys += toLevelUp.yellowKeys
                }
            }
        }
    }
}

enum class EnemyType() {
    fighter,
    ranger
}