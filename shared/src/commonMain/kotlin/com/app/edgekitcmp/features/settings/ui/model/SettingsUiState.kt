package com.app.edgekitcmp.features.settings.ui.model

import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import com.app.edgekitcmp.core.ui.model.ViewState
import com.app.edgekitcmp.features.settings.domain.model.AppLanguage

data class SettingsUiState(
    val themeMode: ThemeMode,
    val appLanguage: AppLanguage
) : ViewState