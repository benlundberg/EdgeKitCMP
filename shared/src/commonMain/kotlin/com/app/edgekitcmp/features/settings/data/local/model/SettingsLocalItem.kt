package com.app.edgekitcmp.features.settings.data.local.model

import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import kotlinx.serialization.Serializable

@Serializable
data class SettingsLocalItem(
    val themeMode: String = ThemeMode.System.name
)
