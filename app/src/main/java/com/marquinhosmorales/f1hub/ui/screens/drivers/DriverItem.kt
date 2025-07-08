package com.marquinhosmorales.f1hub.ui.screens.drivers

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marquinhosmorales.f1hub.data.drivers.mockNorris
import com.marquinhosmorales.f1hub.data.drivers.mockVerstappen
import com.marquinhosmorales.f1hub.model.drivers.Driver
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.Formula1Wide
import com.marquinhosmorales.f1hub.utils.CountriesUtils

@Composable
fun DriverItem(driver: Driver, modifier: Modifier = Modifier) {
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
                text = driver.number.toString().padStart(2, '0'),
                style = TextStyle(
                    fontFamily = Formula1Wide,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                ),
                modifier = Modifier.width(38.dp),
                textAlign = TextAlign.Center
            )

            driver.teamId?.let { teamId ->
                Box(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .width(4.dp)
                        .height(36.dp)
                        .background(teamId.color())
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${driver.name} ${driver.surname}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    CountriesUtils.getFlagByNationality(driver.nationality)?.let { flag ->
                        Text(
                            text = flag,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }

                driver.teamId?.teamName()?.let { teamName ->
                    Text(
                        text = teamName,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview("DriverItem")
@Preview("DriverItem (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DriverItemPreview() {
    F1HubTheme {
        Column {
            DriverItem(mockVerstappen)
            DriverItem(mockNorris)
        }
    }
}