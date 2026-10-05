package com.app.edgekitcmp.features.app.di

import com.app.edgekitcmp.features.app.ui.AppViewModel
import com.app.edgekitcmp.features.home.di.homeModule
import com.app.edgekitcmp.features.settings.di.settingsModule
import com.app.edgekitcmp.features.snackbar.di.snackBarModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AppViewModel)
}

val appModules = listOf(
    appModule,
    homeModule,
    settingsModule,
    snackBarModule
)