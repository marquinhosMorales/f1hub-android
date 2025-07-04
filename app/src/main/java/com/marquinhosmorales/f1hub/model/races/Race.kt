package com.marquinhosmorales.f1hub.model.races

import com.marquinhosmorales.f1hub.model.Circuit
import com.marquinhosmorales.f1hub.model.Team
import com.marquinhosmorales.f1hub.model.drivers.Driver
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Serializable
data class Race(
    val raceId: String,
    val championshipId: String,
    val raceName: String,
    val laps: Int,
    val round: Int,
    val url: String,
    val schedule: RaceSchedule,
    val circuit: Circuit,
    @SerialName("fast_lap")
    val fastLap: RaceFastLap?,
    val winner: Driver? = null,
    val teamWinner: Team? = null
) {
    val id: String
        get() = raceId

    fun days(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val fp1DateString = schedule.fp1.date ?: return ""
        val raceDateString = schedule.race.date ?: return ""

        val fp1Date = formatter.parse(fp1DateString) ?: return ""
        val raceDate = formatter.parse(raceDateString) ?: return ""

        val dateFormatter = SimpleDateFormat("dd", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val fp1Day = dateFormatter.format(fp1Date)
        val raceDay = dateFormatter.format(raceDate)

        return "$fp1Day-$raceDay"
    }

    fun months(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val fp1DateString = schedule.fp1.date ?: return ""
        val raceDateString = schedule.race.date ?: return ""

        val fp1Date = formatter.parse(fp1DateString) ?: return ""
        val raceDate = formatter.parse(raceDateString) ?: return ""

        val dateFormatter = SimpleDateFormat("MMM", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val fp1Month = dateFormatter.format(fp1Date)
        val raceMonth = dateFormatter.format(raceDate)

        return if (fp1Month == raceMonth) raceMonth else "$fp1Month-$raceMonth"
    }
}