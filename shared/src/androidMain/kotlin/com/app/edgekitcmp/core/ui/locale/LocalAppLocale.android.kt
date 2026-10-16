package com.app.edgekitcmp.core.ui.locale

import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.os.LocaleListCompat
import java.util.Locale

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object LocalAppLocale {

    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val configuration = LocalConfiguration.current
        val wanted = value
            ?.let(LocaleListCompat::forLanguageTags)
            ?: LocaleListCompat.getEmptyLocaleList()   // empty = follow the system

        // 1) Synchronously, so strings composed below already resolve in the new language.
        Locale.setDefault(wanted.get(0) ?: Resources.getSystem().configuration.locales[0])

        // 2) Tell AppCompat / the system, for persistence and Android resources.
        SideEffect {
            if (AppCompatDelegate.getApplicationLocales().toLanguageTags() != wanted.toLanguageTags()) {
                AppCompatDelegate.setApplicationLocales(wanted)
            }
        }
        return LocalConfiguration provides configuration

    }
}