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
    private val item: DropItem?,
    val key: KeyOrDoorColor?,
    entityIndex: Int,
    private val position: Position,
) : PlayEntitySinglePosition(entityIndex, position) {
    override fun getType(): PlayEntityType {
        return PlayEntityType.Enemy
    }

    override fun description(): Array<PositionedDescription> {
        val dropDescription = if (item != null) {
            " and grab the ${item.name.lowercase()}"
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
        val fromLevelUp = levelUp(newState.exp)

        val fightResult = apply(newState)
        if (fightResult != FightResult.LOOSE) {
            val toLevelUp = levelUp(newState.exp)
            if (fromLevelUp != toLevelUp) {
                if (addNewReachablePositions(
                        newState,
                        playableTower,
                        stateManager
                    ) || (fightResult == FightResult.WIN_NO_HP_LOST)
                ) {
                    playableTower.levels.forEachIndexed { index, level ->
                        val levelUpState = newState(-index - 1, newState)
                        applyLevelUp(levelUpState, level, toLevelUp)
                        dropApply(levelUpState)
                        stateManager.save(levelUpState)
                    }
                }
            } else {
                dropApply(newState)
                if (addNewReachablePositions(
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
        item?.apply(state)
        if (key != null) {
            apply(state, key)
        }
    }

    private fun apply(state: State, keyColor: KeyOrDoorColor) {
        when (keyColor) {
            KeyOrDoorColor.blue -> state.blueKeys = (state.blueKeys + 1).toShort()
            KeyOrDoorColor.crimson -> state.crimsonKeys = (state.crimsonKeys + 1).toShort()
            KeyOrDoorColor.greenblue -> state.greenBlueKeys = (state.greenBlueKeys + 1).toShort()
            KeyOrDoorColor.platinum -> state.platinumKeys = (state.platinumKeys + 1).toShort()
            KeyOrDoorColor.violet -> state.violetKeys = (state.violetKeys + 1).toShort()
            KeyOrDoorColor.yellow -> state.yellowKeys = (state.yellowKeys + 1).toShort()
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

        private fun levelUp(level: Int, add: Int, mult: Int): Int {
            if ((add == 0) && (mult <= 1)) {
                return 0
            }
            var result = level
            if (add > 0) {
                result += add
            }
            if (mult > 1) {
                result *= mult
            }
            return result
        }

        fun levelUpHp(
            level: Level,
            levelUp: LevelUp,
        ): Int {
            return levelUp(levelUp.level.toInt(), level.hpAdd, level.hpMul)
        }

        fun levelUpDef(
            level: Level,
            levelUp: LevelUp,
        ): Int {
            return levelUp(levelUp.level.toInt(), level.defAdd, level.defMul)
        }

        fun levelUpAtk(
            level: Level,
            levelUp: LevelUp,
        ): Int {
            return levelUp(levelUp.level.toInt(), level.atkAdd, level.atkMul)
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
                enemy.key,
                itemIndex,
                position
            )
        }
    }
}
