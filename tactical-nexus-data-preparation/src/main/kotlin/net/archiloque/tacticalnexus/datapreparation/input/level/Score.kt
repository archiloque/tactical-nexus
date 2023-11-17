package net.archiloque.tacticalnexus.datapreparation.input.level

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.archiloque.tacticalnexus.datapreparation.enums.ScoreType

@Serializable
data class Score(
    override val x: Int,
    override val y: Int,
    @SerialName("customFields") @Required val scoreCustomFields: ScoreCustomFields,
) : Entity {

    fun score(): ScoreType {
        return scoreCustomFields.score
    }
}


@Serializable
data class ScoreCustomFields(
    @Required val score: ScoreType,
)
