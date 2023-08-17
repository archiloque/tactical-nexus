package net.archiloque.tacticalnexus.solver.entities

interface KeyOrDoor {
    fun color(): KeyOrDoorColor
}

enum class KeyOrDoorColor() {
    blue,
    crimson,
    platinum,
    violet,
    yellow,
}