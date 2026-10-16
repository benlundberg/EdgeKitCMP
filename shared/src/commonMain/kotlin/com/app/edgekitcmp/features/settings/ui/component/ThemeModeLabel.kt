package com.app.edgekitcmp.features.settings.ui.component

import androidx.compose.runtime.Composable
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import com.app.edgekitcmp.resources.Res
import com.app.edgekitcmp.resources.theme_dark
import com.app.edgekitcmp.resources.theme_light
import com.app.edgekitcmp.resources.theme_system
import org.jetbrains.compose.resources.stringResource

@Composable
fun ThemeMode.label(): String = when (this) {
    ThemeMode.System -> stringResource(Res.string.theme_system)
    ThemeMode.Light -> stringResource(Res.string.theme_light)
    ThemeMode.Dark -> stringResource(Res.string.theme_dark)
}