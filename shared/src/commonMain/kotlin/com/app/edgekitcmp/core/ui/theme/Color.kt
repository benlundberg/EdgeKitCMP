package com.app.edgekitcmp.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFF0088FF)
val secondary = Color(0xFF9A3EF8)

val white = Color(0xFFFFFFFF)
val gray50 = Color(0xFFF4F5F7)
val gray100 = Color(0xFFE1E1E1)
val gray200 = Color(0xFFC8C8C8)
val gray300 = Color(0xFFACACAC)
val gray400 = Color(0xFF919191)
val gray500 = Color(0xFF6E6E6E)
val gray600 = Color(0xFF404040)
val gray900 = Color(0xFF212121)
val gray950 = Color(0xFF141414)
val black = Color(0xFF322F35)

val error = Color(0xFFED6677)
val warning = Color(0xFFF7B86E)
val success = Color(0xFF9ABB70)
val info = Color(0xFF2BC6EB)

val lightBackground = Color(0xFFF4F5F7)
val darkBackground = Color(0xFF212121)

val colors = Color(
    lightBackground = lightBackground,
    darkBackground = darkBackground,
    primary = primary,
    secondary = secondary,
    white = white,
    black = black,
    error = error,
    warning = warning,
    success = success,
    info = info,
    gray50 = gray50,
    gray100 = gray100,
    gray200 = gray200,
    gray300 = gray300,
    gray400 = gray400,
    gray500 = gray500,
    gray600 = gray600,
    gray900 = gray900,
    gray950 = gray950
)

@Immutable
data class Color(
    val lightBackground: Color,
    val darkBackground: Color,
    val primary: Color,
    val secondary: Color,
    val white: Color,
    val black: Color,
    val error: Color,
    val warning: Color,
    val success: Color,
    val info: Color,
    val gray50: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,
    val gray900: Color,
    val gray950: Color
)

val LocalColor = staticCompositionLocalOf { colors }