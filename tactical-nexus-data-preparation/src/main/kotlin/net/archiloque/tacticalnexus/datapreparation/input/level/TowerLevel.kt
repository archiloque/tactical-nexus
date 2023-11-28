package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TowerLevel(
    @Required @SerialName("identifier") val identifier: String,

    @Required @SerialName("entities") val entities: Entities,

    @Required @SerialName("height") val height: Int,
    @Required @SerialName("width") val width: Int,
    @SerialName("customFields") @Required val levelCustomFields: LevelCustomFields,
) {
    fun allEntities(): List<Entity> {
        val result = mutableListOf<Entity>()
        addEntities(entities.door, result)
        addEntities(entities.enemy, result)
        addEntities(entities.item, result)
        addEntities(entities.key, result)
        addEntities(entities.oneWay, result)
        addEntities(entities.playerStartPosition, result)
        addEntities(entities.staircase, result)
        addEntities(entities.wall, result)
        return result
    }

    private fun addEntities(entities: List<Entity>?, targetList: MutableList<Entity>) {
        if (entities != null) {
            targetList.addAll(entities)
        }
    }

}

@Serializable
data class LevelCustomFields(
    @Required @SerialName("tower") val tower: Int,
    @Required @SerialName("level") val level: Int,
    @Required @SerialName("nexus") val nexus: Boolean,
)
