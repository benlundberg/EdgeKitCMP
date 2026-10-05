package com.app.edgekitcmp.features.home.di

import com.app.edgekitcmp.features.home.ui.HomeViewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel() }
}