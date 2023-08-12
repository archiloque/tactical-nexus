package net.archiloque.tacticalnexus.solver.entities

interface KeyOrDoor {
    fun color(): KeyOrDoorColor
}

enum class KeyOrDoorColor() {
    yellow,
}