package com.marquinhosmorales.f1hub.ui.screens.races

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.marquinhosmorales.f1hub.data.races.mockAustralia
import com.marquinhosmorales.f1hub.data.races.mockSpain
import com.marquinhosmorales.f1hub.model.races.Race
import com.marquinhosmorales.f1hub.ui.components.DottedLine
import com.marquinhosmorales.f1hub.ui.components.LineOrientation
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.accentColor

@Composable
fun RaceItem(race: Race, modifier: Modifier = Modifier) {
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = race.days(),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = race.months().uppercase(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(40.dp)
                )
            }

            DottedLine(
                color = MaterialTheme.colorScheme.onSurface,
                orientation = LineOrientation.Vertical,
                width = 2.dp,
                height = 56.dp
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ROUND ${race.round.toString().padStart(2, '0')}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = accentColor
                )

                Text(
                    text = race.circuit.city,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = race.raceName,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview("RaceItem screen")
@Preview("RaceItem (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RaceItemPreview() {
    F1HubTheme {
        Column {
            RaceItem(mockAustralia)
            RaceItem(mockSpain)
        }
    }
}