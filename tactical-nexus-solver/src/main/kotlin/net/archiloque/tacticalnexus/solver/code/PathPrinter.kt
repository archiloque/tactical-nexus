package net.archiloque.tacticalnexus.solver.code

import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.InputEntityType
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.play.Door
import net.archiloque.tacticalnexus.solver.entities.play.Enemy
import net.archiloque.tacticalnexus.solver.entities.play.GoodiesGroup
import net.archiloque.tacticalnexus.solver.entities.play.LevelUp
import net.archiloque.tacticalnexus.solver.entities.play.PlayEntity
import net.archiloque.tacticalnexus.solver.entities.play.PlayEntityType
import net.archiloque.tacticalnexus.solver.entities.play.PlayableTower

class PathPrinter(private val tower: Tower, val playableTower: PlayableTower, private val initialState: State) {
    fun printMoves(moves: IntArray) {
        val currentState = initialState.copy()

        moves.size.toString().length
        val positionedEntities = moves.filter { it >= 0 }.map { playableTower.playEntities[it] }
        positionedEntities.maxOfOrNull { it.getPositions().map { it.level }.max() }.toString().length
        var index = 1
        val initialPosition = playableTower.startingPositionPosition
        var currentLevelUpIndex = 0
        printStatus(
            0,
            initialPosition,
            "Starting"
        )
        printMove(arrayOf(initialPosition), initialPosition, tower)
        println()
        var lastPosition: Position = initialPosition
        for (move in moves) {
            if (move >= 0) {
                val positionedEntity = playableTower.playEntities[move]
                apply(positionedEntity, currentState)
                printStatus(
                    index,
                    currentState,
                    positionedEntity
                )
                printMove(positionedEntity.getPositions(), lastPosition, tower)
                println()

                lastPosition = positionedEntity.getPositions().last()
            } else {
                currentLevelUpIndex++
                val levelUp = LevelUp.levelUp(currentState.exp)
                val level = playableTower.levels[-move - 1]

                val levelUpDescriptionsParts = mutableListOf<String>()
                val levelUpAtk = Enemy.levelUpAtk(level, levelUp)
                if (levelUpAtk > 0) {
                    levelUpDescriptionsParts.add("$levelUpAtk atk")
                }
                val levelUpDef = Enemy.levelUpDef(level, levelUp)
                if (levelUpDef > 0) {
                    levelUpDescriptionsParts.add("$levelUpDef def")
                }
                val levelUpHp = Enemy.levelUpHp(level, levelUp)
                if (levelUpHp > 0) {
                    levelUpDescriptionsParts.add("$levelUpHp hp")
                }
                if (level.blueKeys > 0) {
                    levelUpDescriptionsParts.add("${level.blueKeys} blue key(s)")
                }
                if (level.crimsonKeys > 0) {
                    levelUpDescriptionsParts.add("${level.crimsonKeys} crimson key(s)")
                }
                if (level.yellowKeys > 0) {
                    levelUpDescriptionsParts.add("${level.yellowKeys} yellow key(s)")
                }
                val description = "Gain ${levelUpDescriptionsParts.joinToString(" and ")}"
                Enemy.applyLevelUp(currentState, level, levelUp)
                printStatus(
                    index,
                    lastPosition,
                    description
                )
                println()
            }
            index++
        }
        exitProcess(0)
    }

    private fun printStatus(
        index: Int,
        currentState: State,
        entity: PlayEntity,
    ) {
        entity.description().forEach { positionedDescription ->
            printStatus(
                index,
                positionedDescription.position,
                positionedDescription.description
            )
        }
        val exp = currentState.exp - LevelUp.levelUp(currentState.exp).exp
        println(
            "Hp: ${currentState.hp}, Atk: ${currentState.atk}, Def: ${currentState.def}, Exp: ${exp}, Exp bonus: ${currentState.expBonus}, HP bonus: ${currentState.hpBonus}"
        )
    }

    private fun printMove(currentPositions: Array<Position>, previousPosition: Position, tower: Tower) {
        val levelIndex = currentPositions.first().level
        val level = tower.standardLevels()[levelIndex]
        for ((lineIndex, line) in level.entities.withIndex()) {
            println(line.mapIndexed { columnIndex, entity ->
                if (currentPositions.contains(Position(levelIndex, lineIndex, columnIndex))) {
                    'X'
                } else if ((levelIndex == previousPosition.level) && (lineIndex == previousPosition.line) && (columnIndex == previousPosition.column)) {
                    '□'
                } else if ((entity != null) && (entity.getType() == InputEntityType.Wall)) {
                    '#'
                } else {
                    ' '
                }
            }.joinToString(""))
        }
    }

    private fun printStatus(
        index: Int,
        position: Position,
        description: String,
    ) {
        println("$index (${position.level}, ${position.line}, ${position.column}) $description")
    }

    private fun apply(
        inputEntity: PlayEntity,
        state: State,
    ) {
        when (inputEntity.getType()) {
            PlayEntityType.Door -> {
                val door = inputEntity as Door
                door.apply(state)
            }

            PlayEntityType.Enemy -> {
                val enemy = inputEntity as Enemy
                enemy.apply(state)
                enemy.dropApply(state)
            }

            PlayEntityType.GoodiesGroup -> {
                val item = inputEntity as GoodiesGroup
                item.apply(state)
            }

            PlayEntityType.OneWay -> {
            }

            PlayEntityType.UpStaircase -> {
            }

        }
    }
}