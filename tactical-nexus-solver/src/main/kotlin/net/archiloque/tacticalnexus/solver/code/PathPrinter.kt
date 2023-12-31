package net.archiloque.tacticalnexus.solver.code

import kotlin.system.exitProcess
import net.archiloque.tacticalnexus.solver.database.State
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
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
        printStatus(
            0,
            initialPosition,
            "Starting"
        )
        var lastPositions: Array<Position> = arrayOf(initialPosition)
        printMove(lastPositions, lastPositions, tower)
        var currentLevel = 1
        println()
        for (move in moves) {
            if (move >= 0) {
                val positionedEntity = playableTower.playEntities[move]
                apply(positionedEntity, currentState)
                printStatus(
                    index,
                    currentState,
                    positionedEntity
                )
                printMove(positionedEntity.getPositions(), lastPositions, tower)
                println()

                lastPositions = positionedEntity.getPositions()
            } else {
                val levelUp = LevelUp.levelUps[currentLevel]
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
                val levelUpHp = Enemy.levelUpHp(level, levelUp, currentState)
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
                val description = "Level up: gain ${levelUpDescriptionsParts.joinToString(" and ")}"
                Enemy.applyLevelUp(currentState, level, levelUp)
                printStatus(
                    index,
                    lastPositions.last(),
                    description
                )
                println()
                currentLevel++
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
            "Hp: ${currentState.hp}, Atk: ${currentState.atk}, Def: ${currentState.def}, Exp: ${exp}, HP mult: ${currentState.hpMult}, Exp mult: ${currentState.expMult}"
        )
        println(
            "Keys: ${KeyOrDoorColor.entries.joinToString(", ") { "${it.humanName}: ${currentState.keys(it)}" }}"
        )
    }

    private fun printMove(currentPositions: Array<Position>, previousPositions: Array<Position>, tower: Tower) {
        val levelIndex = currentPositions.first().level
        val level = tower.standardLevels()[levelIndex]
        for ((lineIndex, line) in level.entities.withIndex()) {
            println(line.mapIndexed { columnIndex, entity ->
                val position = Position(levelIndex, lineIndex, columnIndex)
                if (currentPositions.contains(position)) {
                    'X'
                } else if (previousPositions.contains(position)) {
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