package com.marquinhosmorales.f1hub.model.drivers

import com.marquinhosmorales.f1hub.model.TeamID
import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    val driverId: String? = null,
    val name: String,
    val surname: String,
    val nationality: String? = null,
    val birthday: String,
    val number: Int,
    val shortName: String,
    val url: String,
    val teamId: TeamID? = null,
    val country: String? = null
) {
    val id: String
        get() = driverId ?: name

    val age: Int?
        get() {
            val formats = listOf("dd/MM/yyyy", "yyyy-MM-dd")
            for (format in formats) {
                try {
                    val formatter = java.time.format.DateTimeFormatter.ofPattern(format)
                    val birthDate = java.time.LocalDate.parse(birthday, formatter)
                    return java.time.Period.between(birthDate, java.time.LocalDate.now()).years
                } catch (e: Exception) {
                    continue
                }
            }
            return null
        }

    val formattedBirthday: String
        get() {
            val formats = listOf("dd/MM/yyyy", "yyyy-MM-dd")
            for (format in formats) {
                try {
                    val formatter = java.time.format.DateTimeFormatter.ofPattern(format)
                    val birthDate = java.time.LocalDate.parse(birthday, formatter)
                    return birthDate.format(
                        java.time.format.DateTimeFormatter.ofPattern(
                            "MMM d, yyyy",
                            java.util.Locale.ENGLISH
                        )
                    )
                } catch (e: Exception) {
                    continue
                }
            }
            return birthday
        }
}
