package com.app.edgekitcmp.features.app.di

import com.app.edgekitcmp.features.app.ui.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AppViewModel)
}