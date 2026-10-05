package com.app.edgekitcmp.features.app.ui

import com.app.edgekitcmp.features.app.ui.model.AppUiState
import com.app.edgekitcmp.features.settings.domain.SettingsRepository
import com.app.edgekitcmp.ui.BaseViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class AppViewModel(
    settingsRepository: SettingsRepository
) : BaseViewModel<AppUiState>(AppUiState(settingsRepository.settings.value.themeMode)) {

    init {
        settingsRepository.settings.map { it.themeMode }.distinctUntilChanged()
            .collectToState { state -> copy(themeMode = state) }
    }
}