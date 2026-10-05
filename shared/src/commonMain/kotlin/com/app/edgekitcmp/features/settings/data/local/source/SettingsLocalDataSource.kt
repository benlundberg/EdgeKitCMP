package com.app.edgekitcmp.features.settings.data.local.source

import com.app.edgekitcmp.features.settings.domain.model.SettingsItem
import kotlinx.coroutines.flow.StateFlow

interface SettingsLocalDataSource {
    val settingsItem: StateFlow<SettingsItem>

    suspend fun update(transform: (SettingsItem) -> SettingsItem)
}