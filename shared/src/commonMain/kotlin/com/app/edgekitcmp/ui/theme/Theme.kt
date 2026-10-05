package com.app.edgekitcmp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val darkColorPalette = darkColorScheme(
    primary = primary,
    secondary = secondary,
    background = darkBackground,
    error = error,
    surface = darkBackground,
    onSurface = white,
    onPrimary = white,
    onBackground = white,
    onSecondary = white,
)

private val lightColorPalette = lightColorScheme(
    primary = primary,
    secondary = secondary,
    background = lightBackground,
    error = error,
    surface = lightBackground,
    onPrimary = white,
    onSurface = black,
    onBackground = black,
    onSecondary = white,
)

val LocalIsDarkTheme = staticCompositionLocalOf<Boolean> {
    error("LocalIsDarkTheme not provided — wrap content in AppTheme { ... }")
}

object AppTheme {
    val spacings: Spacings
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val color: Color
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val shape: Shape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current

    val isDarkTheme: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalIsDarkTheme.current
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    CompositionLocalProvider(LocalIsDarkTheme provides darkTheme) {
        MaterialTheme(
            colorScheme = colors,
            typography = appTypography(),
            content = content
        )
    }
}