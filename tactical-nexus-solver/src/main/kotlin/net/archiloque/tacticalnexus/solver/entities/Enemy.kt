package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.LevelUp.Companion.levelUp

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

    override fun play(
        entityIndex: Int,
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
        newStates: MutableList<State>,
    ) {
        val newState = newState(entityIndex, state)
        val fromLevelUp = levelUp(newState.exp)

        val fightResult = apply(newState)
        if (fightResult) {
            val toLevelUp = levelUp(newState.exp)
            if (fromLevelUp != toLevelUp) {
                addNewReachablePositions(entityIndex, newState, playableTower)
                for (levelUpType in LevelUpType.entries) {
                    val levelUpState = newState(levelUpType.type, newState)
                    applyLevelUp(levelUpType, levelUpState, toLevelUp)
                    drop.apply(levelUpState)
                    newStates.add(levelUpState)
                }
            } else {
                drop.apply(newState)
                addNewReachablePositions(entityIndex, newState, playableTower)
                newStates.add(newState)
            }
        }
    }

    fun apply(state: State): Boolean {
        val result = calculate(state)
        return if (result != null) {
            state.hp = result
            state.exp += earnXp(state)
            true
        } else {
            false
        }
    }

    fun earnXp(state: State) = (exp * (100 + state.expBonus)) / 100

    fun killNoHPLost(state: State): Boolean {
        val damagesToEnemy = Math.max(state.atk - def, 0)
        val damagesToPlayer = Math.max(atk - state.def, 0)
        return (damagesToEnemy > hp) || ((damagesToPlayer == 0) && (damagesToEnemy > 0))
    }

    private fun calculate(state: State): Int? {
        val damagesToEnemy = Math.max(state.atk - def, 0)
        val damagesToPlayer = Math.max(atk - state.def, 0)
        return if ((damagesToPlayer == 0) && (damagesToEnemy > 0)) {
            // Enemy can't hurt player but player can hurt enemy
            state.hp
        } else if (damagesToEnemy == 0) {
            // Can't hurt enemy
            null
        } else {
            val turnsToKillEnemy = Math.ceilDiv(hp, damagesToEnemy)
            val turnsToKillPlayer = Math.ceilDiv(state.hp, damagesToPlayer)
            if (turnsToKillEnemy <= turnsToKillPlayer) {
                state.hp - ((turnsToKillEnemy - 1) * damagesToPlayer)
            } else {
                null
            }
        }
    }

    companion object {
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
                    levelUpState.blueKeys += 2
                }

                LevelUpType.crimsonKeys -> {
                    levelUpState.crimsonKeys += 1
                }

                LevelUpType.yellowKeys -> {
                    levelUpState.yellowKeys += 3
                }
            }
        }
    }
}

enum class EnemyType() {
    fighter,
    ranger
}