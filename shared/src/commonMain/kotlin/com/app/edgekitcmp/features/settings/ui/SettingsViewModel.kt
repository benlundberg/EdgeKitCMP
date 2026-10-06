package com.app.edgekitcmp.features.settings.ui

import com.app.edgekitcmp.features.settings.domain.SettingsRepository
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import com.app.edgekitcmp.features.settings.ui.error.SettingsUiExceptionHandler
import com.app.edgekitcmp.features.settings.ui.model.SettingsUiState
import com.app.edgekitcmp.core.ui.BaseViewModel
import com.app.edgekitcmp.core.ui.error.BaseUiExceptionHandler

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    settingsUiExceptionHandler: SettingsUiExceptionHandler
) : BaseViewModel<SettingsUiState>(SettingsUiState(settingsRepository.settings.value.themeMode), settingsUiExceptionHandler) {

    init {
        settingsRepository.settings.collectToState { settingsItem -> copy(themeMode = settingsItem.themeMode) }
    }

    fun onChangeTheme(mode: ThemeMode) = launch {
        settingsRepository.update { state -> state.copy(themeMode = mode) }
    }
}