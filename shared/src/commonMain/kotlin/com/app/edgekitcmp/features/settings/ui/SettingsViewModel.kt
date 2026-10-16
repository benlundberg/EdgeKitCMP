package com.app.edgekitcmp.features.settings.ui

import com.app.edgekitcmp.features.settings.domain.SettingsRepository
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import com.app.edgekitcmp.features.settings.ui.error.SettingsUiExceptionHandler
import com.app.edgekitcmp.features.settings.ui.model.SettingsUiState
import com.app.edgekitcmp.core.ui.BaseViewModel
import com.app.edgekitcmp.features.settings.domain.model.AppLanguage

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    settingsUiExceptionHandler: SettingsUiExceptionHandler
) : BaseViewModel<SettingsUiState>(
    initialUiState = SettingsUiState(
        themeMode = settingsRepository.settings.value.themeMode,
        appLanguage = settingsRepository.settings.value.appLanguage
    ), exceptionHandler = settingsUiExceptionHandler
) {
    init {
        settingsRepository.settings.collectToState { settingsItem -> copy(themeMode = settingsItem.themeMode, appLanguage = settingsItem.appLanguage) }
    }

    fun onChangeTheme(mode: ThemeMode) = launch {
        settingsRepository.update { state -> state.copy(themeMode = mode) }
    }

    fun onChangeLanguage(language: AppLanguage) = launch {
        settingsRepository.update { state -> state.copy(appLanguage = language) }
    }
}