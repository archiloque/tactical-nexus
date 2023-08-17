package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entities(
    @SerialName("door") var door: List<Door>? = null,
    @SerialName("exit") var exit: List<Exit>? = null,
    @SerialName("enemy") var enemy: List<Enemy>? = null,
    @SerialName("item") var item: List<Item>? = null,
    @SerialName("key") var key: List<Key>? = null,
    @SerialName("player_start_position") var playerStartPosition: List<PlayerStartPosition>? = null,
    @SerialName("staircase") var staircase: List<Staircase>? = null,
    @SerialName("wall") var wall: List<Wall>? = null,
)
