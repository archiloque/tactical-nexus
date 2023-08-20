package net.archiloque.tacticalnexus.solver.entities

import net.archiloque.tacticalnexus.solver.code.PlayableTower
import net.archiloque.tacticalnexus.solver.code.StateSaver
import net.archiloque.tacticalnexus.solver.database.State

data class Enemy(
    val hp: Int,
    val atk: Int,
    val def: Int,
    val exp: Int,
    val drop: Item,
) : Entity() {
    override fun getType(): EntityType {
        return EntityType.Enemy
    }

    override fun play(entityIndex: Int, state: State, playableTower: PlayableTower, stateSaver: StateSaver) {
        val newState = newState(entityIndex, state)
        val fightResult = fight(
            newState.atk,
            newState.def,
            newState.hp,
            atk,
            def,
            hp
        )
        if (fightResult != null) {
            newState.hp = fightResult
            newState.exp += (exp * (100 + state.expBonus)) / 100
            drop.collect(newState)
            addNewReachablePositions(entityIndex, newState, playableTower, stateSaver)
            stateSaver.save(newState)
        }
    }

    companion object {
        fun fight(playerAtk: Int, playerDef: Int, playerHp: Int, enemyAtk: Int, enemyDef: Int, enemyHp: Int): Int? {
            val damagesToEnemy = Math.max(playerAtk - enemyDef, 0)
            val damagesToPlayer = Math.max(enemyAtk - playerDef, 0)
            if ((damagesToPlayer == 0) && (damagesToEnemy > 0)) {
                // Enemy can't hurt player but player can hurt enemy
                return playerHp
            } else if (damagesToEnemy == 0) {
                // Can't hurt enemy
                return null
            } else {
                var playerCurrentHp = playerHp
                var enemyCurrentHp = enemyHp
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
                throw RuntimeException("Too many turns: ${playerAtk} ${playerDef} ${playerHp} ${enemyAtk} ${enemyDef} ${enemyHp}")
            }
        }
    }
}
