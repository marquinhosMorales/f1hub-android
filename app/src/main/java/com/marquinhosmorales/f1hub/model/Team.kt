package com.marquinhosmorales.f1hub.model

import androidx.compose.ui.graphics.Color
import com.marquinhosmorales.f1hub.ui.theme.alpine
import com.marquinhosmorales.f1hub.ui.theme.astonMartin
import com.marquinhosmorales.f1hub.ui.theme.ferrari
import com.marquinhosmorales.f1hub.ui.theme.haas
import com.marquinhosmorales.f1hub.ui.theme.mcLaren
import com.marquinhosmorales.f1hub.ui.theme.mercedes
import com.marquinhosmorales.f1hub.ui.theme.racingBulls
import com.marquinhosmorales.f1hub.ui.theme.redBull
import com.marquinhosmorales.f1hub.ui.theme.sauber
import com.marquinhosmorales.f1hub.ui.theme.williams
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class Team(
    val teamId: TeamID? = null,
    val teamName: String,
    val country: String,
    val firstAppearance: Int? = null,
    val constructorsChampionships: Int? = null,
    val driversChampionships: Int? = null,
    val url: String
) {
    // Computed property
    val id: String
        get() = teamId?.id ?: teamName
}

@Serializable(with = TeamIDSerializer::class)
enum class TeamID(val id: String) {
    McLaren("mclaren"),
    Mercedes("mercedes"),
    RedBull("red_bull"),
    Ferrari("ferrari"),
    Williams("williams"),
    Haas("haas"),
    AstonMartin("aston_martin"),
    RacingBulls("rb"),
    Alpine("alpine"),
    Sauber("sauber"),
    Unknown("unknown");

    fun teamName(): String {
        return when (this) {
            RedBull -> "Red Bull Racing"
            AstonMartin -> "Aston Martin"
            RacingBulls -> "Racing Bulls"
            Sauber -> "Kick Sauber"
            Unknown -> ""
            else -> this.name
        }
    }

    fun color(): Color {
        return when (this) {
            McLaren -> mcLaren
            Mercedes -> mercedes
            RedBull -> redBull
            Ferrari -> ferrari
            Williams -> williams
            Haas -> haas
            AstonMartin -> astonMartin
            RacingBulls -> racingBulls
            Alpine -> alpine
            Sauber -> sauber
            Unknown -> Color.Black
        }
    }

    companion object {
        // Helper to convert raw string to TeamID, defaulting to Unknown
        fun fromValue(id: String): TeamID {
            return entries.find { it.id == id } ?: Unknown
        }
    }
}

object TeamIDSerializer : KSerializer<TeamID> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TeamID", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: TeamID) {
        encoder.encodeString(value.id)
    }

    override fun deserialize(decoder: Decoder): TeamID {
        val value = decoder.decodeString()
        return TeamID.fromValue(value)
    }
}