package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entities(
    @SerialName("enemy") var enemy: List<Enemy>? = null,
    @SerialName("item") var item: List<Item>? = null,
    @SerialName("key_and_door") var keyAndDoor: List<KeyAndDoor>? = null,
    @SerialName("player_start_position") var playerStartPosition: List<PlayerStartPosition>? = null,
    @SerialName("staircase") var staircase: List<Staircase>? = null,
    @SerialName("wall") var wall: List<Wall>? = null,
)
