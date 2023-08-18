package net.archiloque.tacticalnexus.solver.database

enum class StateStatus(val value: String) {
    new("new"),
    in_progress("in_progress"),
    processed("processed"),
}