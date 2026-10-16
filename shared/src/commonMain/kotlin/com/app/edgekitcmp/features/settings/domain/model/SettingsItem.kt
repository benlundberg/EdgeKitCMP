package com.app.edgekitcmp.features.settings.domain.model

enum class ThemeMode { System, Light, Dark }

enum class AppLanguage(val tag: String) {
    System(""),
    English("en"),
    Swedish("sv")
}

data class SettingsItem(
    val themeMode: ThemeMode = ThemeMode.System,
    val appLanguage: AppLanguage = AppLanguage.System
)