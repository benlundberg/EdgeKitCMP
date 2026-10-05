package com.app.edgekitcmp.features.settings.domain.model

enum class ThemeMode { System, Light, Dark }

data class SettingsItem(
    val themeMode: ThemeMode = ThemeMode.System,
)