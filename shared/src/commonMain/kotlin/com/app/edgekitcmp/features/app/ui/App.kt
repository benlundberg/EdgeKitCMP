package com.app.edgekitcmp.features.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.app.edgekitcmp.features.app.di.appModules
import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import com.app.edgekitcmp.core.ui.nav.NavigationComponent
import com.app.edgekitcmp.core.ui.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.dsl.koinConfiguration

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { modules(appModules) }),
        content = {
            AppContent()
        })
}

@Composable
private fun AppContent() {
    val viewModel: AppViewModel = koinViewModel()
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    val darkTheme = when (state.themeMode) {
        ThemeMode.System -> isSystemInDarkTheme()
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
    }

    AppTheme(darkTheme) {
        NavigationComponent(rememberNavController())
    }
}