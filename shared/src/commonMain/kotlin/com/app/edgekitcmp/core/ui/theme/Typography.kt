package com.app.edgekitcmp.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
private fun appFont() = FontFamily.SansSerif

val TextStyle.primaryColor: TextStyle
    get() = this.copy(color = primary)

val TextStyle.blackColor: TextStyle
    get() = this.copy(color = black)

val TextStyle.whiteColor: TextStyle
    get() = this.copy(color = white)

val TextStyle.bold: TextStyle
    get() = this.copy(fontWeight = FontWeight.Bold)

@Composable
fun appTypography() = androidx.compose.material3.Typography().run {
    val appFont = appFont()
    Typography(
        displayLarge = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp
        ),
        displayMedium = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp
        ),
        displaySmall = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp
        ),
        titleLarge = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        ),
        titleMedium = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        titleSmall = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodySmall = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        labelLarge = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        labelMedium = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        labelSmall = TextStyle(
            fontFamily = appFont,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp
        ),
    )
}