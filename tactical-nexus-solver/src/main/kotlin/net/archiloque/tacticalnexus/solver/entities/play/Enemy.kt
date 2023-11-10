package net.archiloque.tacticalnexus.solver.entities.play

import kotlin.math.max
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.EnemyType
import net.archiloque.tacticalnexus.solver.entities.play.LevelUp.Companion.levelUp

enum class FightResult {
    WIN,
    LOOSE,
    WIN_NO_HP_LOST,
}

class Enemy(
    private val type: EnemyType,
    private val level: Int,
    val hp: Int,
    val atk: Int,
    val def: Int,
    val exp: Int,
    val drop: DropItem,
    private val entityIndex: Int,
    val position: Position,
) : PlayEntitySinglePosition(entityIndex, position) {
    override fun getType(): PlayEntityType {
        return PlayEntityType.Enemy
    }

    override fun isEnemy(): Boolean {
        return true
    }

    override fun description(): Array<PositionedDescription> {
        return arrayOf(PositionedDescription("Fight lv $level $type and grab the ${drop.name.lowercase()}", position))
    }

    override fun toString(): String {
        return "Enemy $level $type at $position and index $entityIndex"
    }

    override fun play(
        state: State,
        playableTower: PlayableTower,
        stateManager: StateManager,
    ) {
        val newState = newState(entityIndex, state)
        val fromLevelUp = levelUp(newState.exp)

        val fightResult = apply(newState)
        if (fightResult != FightResult.LOOSE) {
            val toLevelUp = levelUp(newState.exp)
            if (fromLevelUp != toLevelUp) {
                if (addNewReachablePositions(
                        entityIndex,
                        newState,
                        playableTower,
                        stateManager
                    ) || (fightResult == FightResult.WIN_NO_HP_LOST)
                ) {
                    for (levelUpType in LevelUpType.entries) {
                        val levelUpState = newState(levelUpType.type, newState)
                        applyLevelUp(levelUpState, levelUpType, toLevelUp)
                        drop.apply(levelUpState)
                        stateManager.save(levelUpState)
                    }
                }
            } else {
                drop.apply(newState)
                if (addNewReachablePositions(
                        entityIndex,
                        newState,
                        playableTower,
                        stateManager
                    ) || (fightResult == FightResult.WIN_NO_HP_LOST)
                ) {
                    stateManager.save(newState)
                }
            }
        }
    }

    fun apply(state: State): FightResult {
        val result = calculate(state)
        return if (result != null) {
            val noHpLost = state.hp == result
            state.hp = result
            state.exp += earnXp(state)
            if (noHpLost) {
                FightResult.WIN_NO_HP_LOST
            } else {
                FightResult.WIN
            }
        } else {
            FightResult.LOOSE
        }
    }

    private fun earnXp(state: State) = (exp * (100 + state.expBonus)) / 100

    fun killNoHPLost(state: State): Boolean {
        val damagesToEnemy = max(state.atk - def, 0)
        val damagesToPlayer = max(atk - state.def, 0)
        return (damagesToEnemy > hp) || ((damagesToPlayer == 0) && (damagesToEnemy > 0))
    }

    fun shouldLevelUp(state: State): Boolean {
        return levelUp(state.exp) != levelUp(state.exp + this.earnXp(state))
    }

    private fun calculate(state: State): Int? {
        val damagesToEnemy = max(state.atk - def, 0)
        val damagesToPlayer = max(atk - state.def, 0)
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
            state: State,
            levelUpType: LevelUpType,
            levelUp: LevelUp,
        ) {
            state.level = levelUp.level
            when (levelUpType) {
                LevelUpType.atk -> {
                    state.atk += levelUp.atk
                }

                LevelUpType.def -> {
                    state.def += levelUp.def
                }

                LevelUpType.blueKeys -> {
                    state.blueKeys += LevelUp.BLUE_KEYS_NUMBER
                }

                LevelUpType.crimsonKeys -> {
                    state.crimsonKeys += LevelUp.CRIMSON_KEYS_NUMBER
                }

                LevelUpType.yellowKeys -> {
                    state.yellowKeys += LevelUp.YELLOW_KEYS_NUMBER
                }
            }
        }

        fun enemy(
            enemy: net.archiloque.tacticalnexus.solver.entities.input.Enemy,
            itemIndex: Int,
            position: Position,
        ): Enemy {
            return Enemy(
                enemy.type,
                enemy.level,
                enemy.hp,
                enemy.atk,
                enemy.def,
                enemy.exp,
                DropItem.item(enemy.drop),
                itemIndex,
                position
            )
        }
    }
}
