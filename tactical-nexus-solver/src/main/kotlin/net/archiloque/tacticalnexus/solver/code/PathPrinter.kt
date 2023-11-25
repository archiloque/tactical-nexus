package net.archiloque.tacticalnexus.solver.code

import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.Position
import net.archiloque.tacticalnexus.solver.entities.input.InputEntityType
import net.archiloque.tacticalnexus.solver.entities.input.Tower
import net.archiloque.tacticalnexus.solver.entities.play.Door
import net.archiloque.tacticalnexus.solver.entities.play.Enemy
import net.archiloque.tacticalnexus.solver.entities.play.ItemGroup
import net.archiloque.tacticalnexus.solver.entities.play.Key
import net.archiloque.tacticalnexus.solver.entities.play.LevelUp
import net.archiloque.tacticalnexus.solver.entities.play.PlayEntity
import net.archiloque.tacticalnexus.solver.entities.play.PlayEntityType
import net.archiloque.tacticalnexus.solver.entities.play.PlayableTower

class PathPrinter(private val tower: Tower, val playableTower: PlayableTower, private val initialState: State) {
    fun printMoves(moves: IntArray) {
        val currentState = initialState.copy()

        val moveIndexLength = moves.size.toString().length
        val positionedEntities = moves.filter { it >= 0 }.map { playableTower.playEntities[it] }
        positionedEntities.map { it.getPositions().map { it.level }.max() }.max().toString().length
        var index = 1
        val initialPosition = playableTower.startingPositionPosition
        var currentLevelUpIndex = 0
        printStatus(
            0,
            moveIndexLength,
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
                if (!positionedEntity.isUpStaircase()) {
                    printStatus(
                        index,
                        moveIndexLength,
                        currentState,
                        positionedEntity
                    )
                    printMove(positionedEntity.getPositions(), lastPosition, tower)
                }
                println()

                lastPosition = positionedEntity.getPositions().last()
            } else {
                currentLevelUpIndex++
                val levelUp = LevelUp.levelUp(currentState.exp, playableTower)
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
                    moveIndexLength,
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
        moveIndexLength: Int,
        currentState: State,
        entity: PlayEntity,
    ) {
        entity.description().forEach { positionedDescription ->
            printStatus(
                index,
                moveIndexLength,
                positionedDescription.position,
                positionedDescription.description
            )
        }
        val exp = currentState.exp - LevelUp.levelUp(currentState.exp, playableTower).exp
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
        moveIndexLength: Int,
        position: Position,
        description: String,
    ) {
        println(
            "${index.toString().padStart(moveIndexLength)} (${
                position.level.toString().padStart(3)
            }, ${position.line.toString().padStart(2)}, ${
                position.column.toString().padStart(2)
            }) $description"
        )
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

            PlayEntityType.Exit -> {
            }

            PlayEntityType.ItemGroup -> {
                val item = inputEntity as ItemGroup
                item.apply(state)
            }

            PlayEntityType.Key -> {
                val key = inputEntity as Key
                key.apply(state)
            }

            PlayEntityType.UpStaircase -> {
            }
        }
    }
}