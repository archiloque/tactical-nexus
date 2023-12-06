package net.archiloque.tacticalnexus.datapreparation.input.entities

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

open class ItemDefault(
    val identifier: String,
    val name: String,

    val atk: Int,
    val def: Int,
    val expMultAdd: Int,
    val expMultMul: Int,
    val hp: Int,
    val hpMultAdd: Int,
    val hpMultMul: Int,
) {
    companion object {
        fun parse(filePath: String): List<ItemDefault> {
            println("Reading default items at [${filePath}]")
            var result: List<ItemDefault> = mutableListOf()
            csvReader { delimiter = ';' }.open(filePath) {
                result = readAllWithHeaderAsSequence().map {
                    ItemDefault(
                        it["Identifier"]!!,
                        it["Name"]!!,
                        it["ATK"]!!.toInt(),
                        it["DEF"]!!.toInt(),
                        it["EXP Mult Add"]!!.toInt(),
                        it["EXP Mult Mul"]!!.toInt(),
                        it["HP"]!!.toInt(),
                        it["HP Mult Add"]!!.toInt(),
                        it["HP Mult Mul"]!!.toInt(),
                    )
                }.toList()
            }
            return result
        }
    }
}