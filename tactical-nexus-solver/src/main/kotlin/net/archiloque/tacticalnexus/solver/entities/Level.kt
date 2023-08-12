package net.archiloque.tacticalnexus.solver.entities

data class Level(
    val lines: Int,
    val columns: Int,
    val entities: Array<Array<Entity?>>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Level

        if (lines != other.lines) return false
        if (columns != other.columns) return false

        return true
    }

    override fun hashCode(): Int {
        var result = lines
        result = 31 * result + columns
        return result
    }
}
