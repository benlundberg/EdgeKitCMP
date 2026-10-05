package com.app.edgekitcmp.features.snackbar.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.app.edgekitcmp.features.snackbar.ui.SnackBarScreen
import kotlinx.serialization.Serializable

@Serializable
object SnackBarRoute

fun NavGraphBuilder.snackBarNav(navHostController: NavHostController) {
    composable<SnackBarRoute> {
        SnackBarScreen(
            onBack = { navHostController.popBackStack() }
        )
    }
}