package net.archiloque.tacticalnexus.solver.entities

data class Position(
    val level: Int,
    val line: Int,
    val column: Int,
) {
    override fun toString(): String {
        return "($level, $line, $column)"
    }
}
