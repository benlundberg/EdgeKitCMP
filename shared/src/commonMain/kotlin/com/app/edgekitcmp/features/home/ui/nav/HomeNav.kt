package com.app.edgekitcmp.features.home.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.app.edgekitcmp.features.home.ui.HomeScreen
import com.app.edgekitcmp.features.home.ui.model.UiHomeOptionType
import com.app.edgekitcmp.features.settings.ui.nav.SettingsRoute
import com.app.edgekitcmp.features.snackbar.ui.nav.SnackBarRoute
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavGraphBuilder.homeNav(
    navHostController: NavHostController
) {
    composable<HomeRoute> {
        HomeScreen(
            onOptionClick = {
                when (it) {
                    UiHomeOptionType.Settings -> {
                        navHostController.navigate(SettingsRoute)
                    }
                    UiHomeOptionType.Snackbar -> {
                        navHostController.navigate(SnackBarRoute)
                    }
                }
            }
        )
    }
}

