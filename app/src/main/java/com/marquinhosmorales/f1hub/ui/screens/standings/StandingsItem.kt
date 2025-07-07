package com.marquinhosmorales.f1hub.ui.screens.standings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marquinhosmorales.f1hub.data.standings.mockNorrisEntry
import com.marquinhosmorales.f1hub.data.standings.mockVerstappenEntry
import com.marquinhosmorales.f1hub.model.standings.StandingsEntry
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme

@Composable
fun StandingsItem(standingsEntry: StandingsEntry, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = standingsEntry.position.toString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.width(38.dp),
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(36.dp)
                    .background(standingsEntry.teamId.color())
            )

            standingsEntry.driver?.let { driver ->
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${driver.name} ${driver.surname}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = standingsEntry.teamId.teamName(),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview("StandingsItem screen")
@Preview("StandingsItem (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun StandingsItemPreview() {
    F1HubTheme {
        Column {
            StandingsItem(mockVerstappenEntry)
            StandingsItem(mockNorrisEntry)
        }
    }
}