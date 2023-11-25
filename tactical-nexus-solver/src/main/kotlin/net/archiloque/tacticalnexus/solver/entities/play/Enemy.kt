package net.archiloque.tacticalnexus.solver.entities.play

import kotlin.math.max
import net.archiloque.tacticalnexus.solver.code.StateManager
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.EnemyType
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.Level
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
    val drop: DropItem?,
    val key: KeyOrDoorColor?,
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
        val dropDescription = if (drop != null) {
            " and grab the ${drop.name.lowercase()}"
        } else if (key != null) {
            " and grab the ${key.humanName} key"
        } else {
            ""
        }

        val description = "Fight lv $level $type$dropDescription"
        return arrayOf(PositionedDescription(description, position))
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
        val fromLevelUp = levelUp(newState.exp, playableTower)

        val fightResult = apply(newState)
        if (fightResult != FightResult.LOOSE) {
            val toLevelUp = levelUp(newState.exp, playableTower)
            if (fromLevelUp != toLevelUp) {
                if (addNewReachablePositions(
                        entityIndex,
                        newState,
                        playableTower,
                        stateManager
                    ) || (fightResult == FightResult.WIN_NO_HP_LOST)
                ) {
                    playableTower.levels.forEachIndexed() { index, level ->
                        val levelUpState = newState(-index - 1, newState)
                        applyLevelUp(levelUpState, level, toLevelUp)
                        dropApply(levelUpState)
                        stateManager.save(levelUpState)
                    }
                }
            } else {
                dropApply(newState)
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

    fun dropApply(state: State) {
        drop?.apply(state)
        if (key != null) {
            Key.apply(state, key)
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

    fun shouldLevelUp(state: State, playableTower: PlayableTower): Boolean {
        return levelUp(state.exp, playableTower) != levelUp(state.exp + this.earnXp(state), playableTower)
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
            level: Level,
            levelUp: LevelUp,
        ) {
            state.level = levelUp.level
            state.atk += levelUpAtk(level, levelUp)
            state.def += levelUpDef(level, levelUp)
            state.hp += levelUpHp(level, levelUp)
            state.blueKeys = (state.blueKeys + level.blueKeys).toShort()
            state.crimsonKeys = (state.crimsonKeys + level.crimsonKeys).toShort()
            state.yellowKeys = (state.yellowKeys + level.yellowKeys).toShort()
        }

        public fun levelUpHp(
            level: Level,
            levelUp: LevelUp,
        ) = level.hpAdd + (level.hpMul * levelUp.level)

        public fun levelUpDef(
            level: Level,
            levelUp: LevelUp,
        ) = level.defAdd + (level.defMul * levelUp.level)

        public fun levelUpAtk(
            level: Level,
            levelUp: LevelUp,
        ) = level.atkAdd + (level.atkMul * levelUp.level)

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
                enemy.key,
                itemIndex,
                position
            )
        }
    }
}
