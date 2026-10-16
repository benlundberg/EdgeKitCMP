package com.app.edgekitcmp.core.ui.locale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.currentLocale
import platform.Foundation.localeIdentifier

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object LocalAppLocale {
    private val LocalLocale = staticCompositionLocalOf { NSLocale.currentLocale.localeIdentifier }

    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val defaults = NSUserDefaults.standardUserDefaults
        if (value == null) {
            defaults.removeObjectForKey("AppleLanguages")
        } else {
            defaults.setObject(listOf(value), forKey = "AppleLanguages")
        }
        return LocalLocale provides (value ?: NSLocale.currentLocale.localeIdentifier)
    }
}