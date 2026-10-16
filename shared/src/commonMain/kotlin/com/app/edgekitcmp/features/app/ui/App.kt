package com.app.edgekitcmp.features.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.app.edgekitcmp.core.di.coreModules
import com.app.edgekitcmp.core.ui.nav.NavigationComponent
import com.app.edgekitcmp.core.ui.theme.AppTheme
import com.app.edgekitcmp.core.ui.locale.LocalAppLocale
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(coreModules) }),
        content = {
            AppContent()
        }
    )
}

@Composable
private fun AppContent() {
    val navHostController = rememberNavController()
    val viewModel: AppViewModel = koinViewModel()
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    val darkTheme = when (state.themeMode) {
        ThemeMode.System -> isSystemInDarkTheme()
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
    }

    CompositionLocalProvider(LocalAppLocale provides state.appLanguage.tag) {
        key(state.appLanguage.tag) {
            AppTheme(darkTheme) {
                NavigationComponent(navHostController)
            }
        }
    }
}