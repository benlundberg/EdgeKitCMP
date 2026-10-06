package com.app.edgekitcmp.core.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.edgekitcmp.features.home.ui.nav.HomeRoute
import com.app.edgekitcmp.features.home.ui.nav.homeNav
import com.app.edgekitcmp.features.settings.ui.nav.settingsNav
import com.app.edgekitcmp.features.snackbar.ui.nav.snackBarNav

@Composable
fun NavigationComponent(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = HomeRoute
    ) {
        homeNav(navHostController)
        settingsNav(navHostController)
        snackBarNav(navHostController)
    }
}