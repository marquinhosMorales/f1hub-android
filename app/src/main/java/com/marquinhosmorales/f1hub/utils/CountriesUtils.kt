package com.marquinhosmorales.f1hub.utils

import java.util.Locale

object CountriesUtils {
    private val countryFlagMap: MutableMap<String, String> = mutableMapOf()

    val countryAliases = mapOf(
        "great britain" to "United Kingdom",
        "british" to "United Kingdom",
        "uk" to "United Kingdom",
        "new zealander" to "New Zealand",
        "kiwi" to "New Zealand",
        "italian" to "Italy",
        "usa" to "United States",
        "american" to "United States",
        "french" to "France",
        "german" to "Germany",
        "spanish" to "Spain",
        "dutch" to "Netherlands",
        "argentine" to "Argentina"
    )

    init {
        Locale.getISOCountries().forEach { countryCode ->
            val locale = Locale.Builder().setRegion(countryCode).build()
            val countryName = locale.displayCountry
            val flagEmoji = getFlagEmoji(countryCode)

            countryFlagMap[countryName] = flagEmoji
        }
    }

    fun getFlagByNationality(nationality: String?): String? {
        if (nationality.isNullOrBlank()) return null

        val countryName = countryAliases[nationality.lowercase()] ?: nationality

        return countryFlagMap[countryName]
    }

    private fun getFlagEmoji(countryCode: String): String {
        if (countryCode.length != 2) return ""
        // Convert the country code to Unicode regional indicator symbols
        val firstChar = countryCode.uppercase()[0].code - 'A'.code + 0x1F1E6
        val secondChar = countryCode.uppercase()[1].code - 'A'.code + 0x1F1E6
        return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
    }
}