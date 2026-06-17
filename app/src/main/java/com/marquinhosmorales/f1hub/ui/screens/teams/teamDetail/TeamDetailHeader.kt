package com.marquinhosmorales.f1hub.ui.screens.teams.teamDetail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.marquinhosmorales.f1hub.model.teams.Team
import com.marquinhosmorales.f1hub.ui.theme.F1HubTheme
import com.marquinhosmorales.f1hub.ui.theme.Formula1Wide
import com.marquinhosmorales.f1hub.utils.ImageLoaderProvider

@Composable
fun TeamDetailHeader(
    team: Team,
    imageUrl: String?
) {
    val teamColor = team.teamId?.color(0.5f) ?: Color.Gray
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(teamColor)
            ) {
                if (!imageUrl.isNullOrEmpty()) {
                    val context = LocalContext.current
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        imageLoader = ImageLoaderProvider.getImageLoader(context),
                        contentDescription = team.teamName,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center,
                        loading = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.width(32.dp),
                                    color = Color.White.copy(alpha = 0.5f)
                                )
                            }
                        },
                        error = {
                            TeamNamePlaceholder(team.teamName)
                        }
                    )
                } else {
                    TeamNamePlaceholder(team.teamName)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = team.teamName,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
private fun TeamNamePlaceholder(name: String) {
    val initials = name.split(" ")
        .mapNotNull { it.firstOrNull()?.uppercase() }
        .joinToString("")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 100.sp,
                fontWeight = FontWeight.Black,
                fontFamily = Formula1Wide,
                color = Color.White.copy(alpha = 0.15f),
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            )
        )
    }
}

@Preview("Team Detail Header")
@Preview("Team Detail Header (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TeamDetailHeaderPreview() {
    F1HubTheme {
        TeamDetailHeader(
            team = com.marquinhosmorales.f1hub.data.teams.mockRedBull,
            imageUrl = null
        )
    }
}