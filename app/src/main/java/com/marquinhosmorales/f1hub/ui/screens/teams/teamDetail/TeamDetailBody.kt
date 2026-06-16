package com.marquinhosmorales.f1hub.ui.screens.teams.teamDetail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marquinhosmorales.f1hub.model.teams.Team
import com.marquinhosmorales.f1hub.ui.screens.drivers.driverDetail.InfoRow
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.utils.CountriesUtils

@Composable
fun TeamDetailBody(
    team: Team,
    biography: String? = null
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Information",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            val nationality = team.teamNationality ?: team.country
            val nationalityValue = buildString {
                append(nationality ?: "N/A")
                CountriesUtils.getFlagByNationality(nationality)?.let { flag ->
                    append(" ")
                    append(flag)
                }
            }
            InfoRow(label = "Team Nationality", value = nationalityValue)
            InfoRow(label = "First Appearance", value = team.firstAppearance?.toString() ?: "N/A")
            InfoRow(
                label = "Constructors Championships",
                value = team.constructorsChampionships?.toString() ?: "0"
            )
            InfoRow(
                label = "Drivers Championships",
                value = team.driversChampionships?.toString() ?: "0"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Biography",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = biography ?: "No biography available.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview("Team Detail Body")
@Preview("Team Detail Body (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TeamDetailBodyPreview() {
    F1HubTheme {
        TeamDetailBody(
            team = com.marquinhosmorales.f1hub.data.teams.mockRedBull,
            biography = "Red Bull Racing is a Formula One racing team, currently racing under a British licence but Austrian-owned."
        )
    }
}