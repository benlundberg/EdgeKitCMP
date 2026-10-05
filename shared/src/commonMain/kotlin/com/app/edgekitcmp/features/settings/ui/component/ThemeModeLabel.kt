package com.app.edgekitcmp.features.settings.ui.component

import androidx.compose.runtime.Composable
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import edgekitcmp.shared.generated.resources.Res
import edgekitcmp.shared.generated.resources.theme_dark
import edgekitcmp.shared.generated.resources.theme_light
import edgekitcmp.shared.generated.resources.theme_system
import org.jetbrains.compose.resources.stringResource

@Composable
fun ThemeMode.label(): String = when (this) {
    ThemeMode.System -> stringResource(Res.string.theme_system)
    ThemeMode.Light -> stringResource(Res.string.theme_light)
    ThemeMode.Dark -> stringResource(Res.string.theme_dark)
}