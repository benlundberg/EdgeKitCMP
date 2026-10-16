package com.app.edgekitcmp.features.app.ui.model

import com.app.edgekitcmp.features.settings.domain.model.AppLanguage
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode

data class AppUiState(
    val themeMode: ThemeMode,
    val appLanguage: AppLanguage
)