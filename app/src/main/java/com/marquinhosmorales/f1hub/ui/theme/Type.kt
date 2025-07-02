package com.marquinhosmorales.f1hub.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.marquinhosmorales.f1hub.R

val Formula1 = FontFamily(
    Font(R.font.formula1_display_regular),
    Font(R.font.formula1_display_bold)
)

val Formula1Wide = FontFamily(
    Font(R.font.formula1_display_wide)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)