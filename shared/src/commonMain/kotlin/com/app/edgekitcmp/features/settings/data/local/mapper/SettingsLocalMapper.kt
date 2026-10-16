package com.app.edgekitcmp.features.settings.data.local.mapper

import com.app.edgekitcmp.features.settings.data.local.model.SettingsLocalItem
import com.app.edgekitcmp.features.settings.domain.model.AppLanguage
import com.app.edgekitcmp.features.settings.domain.model.SettingsItem
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode

fun SettingsItem.asLocalModel() = SettingsLocalItem(themeMode = themeMode.name, language = appLanguage.name)

fun SettingsLocalItem.asDomainModel() = SettingsItem(
    themeMode = ThemeMode.entries.firstOrNull { it.name == themeMode } ?: ThemeMode.System,
    appLanguage = AppLanguage.entries.firstOrNull { it.name == language } ?: AppLanguage.System
)