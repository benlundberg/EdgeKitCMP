package com.app.edgekitcmp.core.di

import com.app.edgekitcmp.features.app.di.appModule
import com.app.edgekitcmp.features.home.di.homeModule
import com.app.edgekitcmp.features.settings.di.settingsModule
import com.app.edgekitcmp.features.snackbar.di.snackBarModule

val coreModules = listOf(
    appModule,
    homeModule,
    settingsModule,
    snackBarModule
)

