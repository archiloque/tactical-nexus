package net.archiloque.tacticalnexus.solver.database

import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.SqlType
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types
import java.util.*

/**
 * Represent values of PostgreSQL `Int[]` SQL type.
 */
typealias IntArray = Array<Int?>

class Mappings {


    /**
     * Define a column typed [IntArraySqlType].
     */
    fun BaseTable<*>.intArray(name: String): Column<IntArray> {
        return registerColumn(name, IntArraySqlType)
    }

    /**
     * [SqlType] implementation represents PostgreSQL `int[]` type.
     */
    object IntArraySqlType : SqlType<IntArray>(Types.ARRAY, "int[]") {

        override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: IntArray) {
            ps.setObject(index, parameter)
        }

        @Suppress("UNCHECKED_CAST")
        override fun doGetResult(rs: ResultSet, index: Int): IntArray? {
            val sqlArray = rs.getArray(index) ?: return null
            try {
                val objectArray = sqlArray.array as Array<Any?>?
                return objectArray?.map { it as Int? }?.toTypedArray()
            } finally {
                sqlArray.free()
            }
        }
    }

    object BitSetSqlType : SqlType<BitSet>(Types.BINARY, "bit varying") {

        override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: BitSet) {
            val representation: String?
            representation = (0..< parameter.length()).map {value ->
                if(parameter.get(value)) {
                    '1'
                } else {
                    '0'
                }
            }.joinToString("")
            ps.setObject(index, representation, Types.OTHER)
        }

        override fun doGetResult(rs: ResultSet, index: Int): BitSet? {
            val stringValue = rs.getString(index)
            val result = BitSet(stringValue.length)
            stringValue.forEachIndexed { i, c ->
                if (c == '1') {
                    result.set(i, true)
                }
            }
            return result
        }
    }
}