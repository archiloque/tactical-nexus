package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.enums.EnemyType

@Serializable
data class Enemy(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val enemyCustomFields: EnemyCustomFields,
) : Entity {
    fun level(): Int {
        return enemyCustomFields.level
    }

    fun type(): EnemyType {
        return enemyCustomFields.type
    }
}


@Serializable
data class EnemyCustomFields(
    @Required val type: EnemyType,
    @Required val level: Int,
)
