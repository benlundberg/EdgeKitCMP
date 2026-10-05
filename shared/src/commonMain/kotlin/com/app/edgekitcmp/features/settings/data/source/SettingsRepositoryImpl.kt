package com.app.edgekitcmp.features.settings.data.source

import com.app.edgekitcmp.features.settings.data.local.source.SettingsLocalDataSource
import com.app.edgekitcmp.features.settings.domain.SettingsRepository
import com.app.edgekitcmp.features.settings.domain.model.SettingsItem
import kotlinx.coroutines.flow.StateFlow

class SettingsRepositoryImpl(
    private val settingsLocalDataSource: SettingsLocalDataSource
) : SettingsRepository {

    override val settings: StateFlow<SettingsItem>
        get() = settingsLocalDataSource.settingsItem

    override suspend fun update(transform: (SettingsItem) -> SettingsItem) =
        settingsLocalDataSource.update(transform)
}