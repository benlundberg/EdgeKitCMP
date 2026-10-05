package com.app.edgekitcmp.features.settings.domain

import com.app.edgekitcmp.features.settings.domain.model.SettingsItem
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    val settings: StateFlow<SettingsItem>

    suspend fun update(transform: (SettingsItem) -> SettingsItem)
}