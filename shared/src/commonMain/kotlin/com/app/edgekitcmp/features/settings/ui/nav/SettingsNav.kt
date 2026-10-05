package com.app.edgekitcmp.features.settings.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.app.edgekitcmp.features.settings.ui.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

fun NavGraphBuilder.settingsNav(
    navHostController: NavHostController
) {
    composable<SettingsRoute> {
        SettingsScreen(
            onBack = {
                navHostController.popBackStack()
            }
        )
    }
}