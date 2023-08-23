package net.archiloque.tacticalnexus.datapreparation.input.entities

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

data class Stat(
    val tower: Int,

    val atk: Int,
    val def: Int,
    val hp: Int,
) {
    companion object {
        fun parse(filePath: String): List<Stat> {
            println("Reading stats at [${filePath}]")
            var result: List<Stat> = mutableListOf()
            csvReader { delimiter = ';' }.open(filePath) {
                result = readAllWithHeaderAsSequence().map {
                    Stat(
                        it["Tower"]!!.toInt(),
                        it["ATK"]!!.toInt(),
                        it["DEF"]!!.toInt(),
                        it["HP"]!!.toInt(),
                    )
                }.toList()
            }
            return result
        }
    }
}