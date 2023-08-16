package net.archiloque.tacticalnexus.solver.database

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types
import java.util.BitSet
import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.SqlType

class Mappings {

    object IntArraySqlType : SqlType<Array<Int>>(Types.ARRAY, "int[]") {

        override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: Array<Int>) {
            ps.setObject(index, parameter)
        }

        @Suppress("UNCHECKED_CAST")
        override fun doGetResult(rs: ResultSet, index: Int): Array<Int>? {
            val sqlArray = rs.getArray(index) ?: return null
            try {
                val objectArray = sqlArray.array as Array<Any>?
                return objectArray?.map { it as Int }?.toTypedArray()
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

        override fun doGetResult(rs: ResultSet, index: Int): BitSet {
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

fun BaseTable<*>.intArray(name: String): Column<Array<Int>> {
    return registerColumn(name, Mappings.IntArraySqlType)
}

fun BaseTable<*>.bitSet(name: String): Column<BitSet> {
    return registerColumn(name, Mappings.BitSetSqlType)
}