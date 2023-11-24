package net.archiloque.tacticalnexus.datapreparation.input.entities

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

data class Level(
    val tower: Int,

    val atkAdd: Int,
    val atkMul: Int,

    val defAdd: Int,
    val defMul: Int,

    val hpAdd: Int,
    val hpMul: Int,

    val blueKey: Int,
    val crimsonKey: Int,
    val yellowKey: Int,

    ) {
    companion object {
        fun parse(filePath: String): List<Level> {
            println("Reading levels at [${filePath}]")
            var result: List<Level> = mutableListOf()
            csvReader { delimiter = ';' }.open(filePath) {
                result = readAllWithHeaderAsSequence().map {
                    Level(
                        it["Tower"]!!.toInt(),

                        it["ATK ADD"]!!.toInt(),
                        it["ATK MUL"]!!.toInt(),

                        it["DEF ADD"]!!.toInt(),
                        it["DEF MUL"]!!.toInt(),

                        it["HP ADD"]!!.toInt(),
                        it["HP MUL"]!!.toInt(),

                        it["BLUE"]!!.toInt(),
                        it["CRIMSON"]!!.toInt(),
                        it["YELLOW"]!!.toInt(),
                    )
                }.toList()
            }
            return result
        }
    }
}