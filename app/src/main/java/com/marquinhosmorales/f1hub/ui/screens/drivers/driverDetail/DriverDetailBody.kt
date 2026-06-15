package com.marquinhosmorales.f1hub.ui.screens.drivers.driverDetail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marquinhosmorales.f1hub.data.drivers.mockVerstappen
import com.marquinhosmorales.f1hub.model.drivers.Driver
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.utils.CountriesUtils

@Composable
fun DriverDetailBody(
    driver: Driver,
    biography: String? = null
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Information",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                val nationalityValue = buildString {
                    append(driver.nationality ?: "N/A")
                    CountriesUtils.getFlagByNationality(driver.nationality)?.let { flag ->
                        append(" ")
                        append(flag)
                    }
                }
                InfoRow(label = "Nationality", value = nationalityValue)
                InfoRow(label = "Date of Birth", value = driver.formattedBirthday)
                InfoRow(label = "Age", value = driver.age?.toString() ?: "N/A")
                InfoRow(label = "Short Name", value = driver.shortName)
            }

            Column(modifier = Modifier.padding(16.dp)) {
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
}

@Composable
fun InfoRow(label: String, value: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = label,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                fontWeight = FontWeight.Bold
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }
}

@Preview("Driver Detail Body")
@Preview("Driver Detail Body (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DriverDetailBodyPreview() {
    F1HubTheme {
        DriverDetailBody(
            driver = mockVerstappen,
            biography = "Max Emilian Verstappen is a Belgian-Dutch racing driver and the 2021, 2022, and 2023 Formula One World Champion."
        )
    }
}