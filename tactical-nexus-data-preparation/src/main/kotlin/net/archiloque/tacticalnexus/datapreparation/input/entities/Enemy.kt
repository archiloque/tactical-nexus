package net.archiloque.tacticalnexus.datapreparation.input.entities

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import net.archiloque.tacticalnexus.datapreparation.enums.EnemyType

data class Enemy(
    val tower: Int,
    val type: EnemyType,
    val level: Int,
    val name: String,

    val atk: Int,
    val def: Int,
    val drop: String,
    val exp: Int,
    val hp: Int,
) {
    companion object {
        const val KEY_SUFFIX = "_key"

        fun parse(filePath: String): List<Enemy> {
            println("Reading enemies at [${filePath}]")
            var result: List<Enemy> = mutableListOf()
            csvReader { delimiter = ';' }.open(filePath) {
                result = readAllWithHeaderAsSequence().map {
                    Enemy(
                        it["Tower"]!!.toInt(),
                        EnemyType.valueOf(it["Type"]!!),
                        it["Level"]!!.toInt(),
                        it["Name"]!!,

                        it["ATK"]!!.toInt(),
                        it["DEF"]!!.toInt(),
                        it["Drop"]!!,
                        it["EXP"]!!.toInt(),
                        it["HP"]!!.toInt(),
                    )
                }.toList()
            }
            return result
        }
    }
}
