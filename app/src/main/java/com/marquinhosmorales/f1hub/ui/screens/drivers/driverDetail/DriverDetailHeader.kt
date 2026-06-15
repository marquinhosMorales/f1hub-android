package com.marquinhosmorales.f1hub.ui.screens.drivers.driverDetail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.commit451.coiltransformations.facedetection.CenterOnFaceTransformation
import com.marquinhosmorales.f1hub.data.drivers.mockVerstappen
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.Formula1Wide
import com.marquinhosmorales.f1hub.ui.theme.accentColor
import com.marquinhosmorales.f1hub.utils.ImageLoaderProvider

@Composable
fun DriverDetailHeader(
    number: Int,
    name: String,
    surname: String,
    imageUrl: String?
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
        ) {
            if (!imageUrl.isNullOrEmpty()) {
                val context = LocalContext.current
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .crossfade(true)
                        .transformations(CenterOnFaceTransformation(zoom = 20))
                        .build(),
                    imageLoader = ImageLoaderProvider.getImageLoader(context),
                    contentDescription = "$name $surname",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.width(32.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                            )
                        }
                    },
                    error = {
                        DriverInitialsPlaceholder(name, surname)
                    }
                )
            } else {
                DriverInitialsPlaceholder(name, surname)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = Formula1Wide,
                    fontWeight = FontWeight.Bold,
                    color = accentColor
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = surname.uppercase(),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
private fun DriverInitialsPlaceholder(name: String, surname: String) {
    val initials = (name.take(1) + surname.take(1)).uppercase()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 120.sp,
                fontWeight = FontWeight.Black,
                fontFamily = Formula1Wide,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            )
        )
    }
}

@Preview("Driver Detail Header")
@Preview("Driver Detail Header (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DriverDetailHeaderPreview() {
    F1HubTheme {
        DriverDetailHeader(
            number = mockVerstappen.number,
            name = mockVerstappen.name,
            surname = mockVerstappen.surname,
            imageUrl = null
        )
    }
}