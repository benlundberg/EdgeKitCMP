package com.app.edgekitcmp.features.settings.ui.component

import androidx.compose.runtime.Composable
import com.app.edgekitcmp.features.settings.domain.model.AppLanguage
import com.app.edgekitcmp.resources.Res
import com.app.edgekitcmp.resources.settings_language_system
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppLanguage.label(): String = when (this) {
    AppLanguage.System -> stringResource(Res.string.settings_language_system)
    AppLanguage.English -> "English"   // endonyms are deliberately not translated
    AppLanguage.Swedish -> "Svenska"
}