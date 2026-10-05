package com.app.edgekitcmp.features.snackbar.di

import com.app.edgekitcmp.features.snackbar.ui.SnackBarViewModel
import org.koin.dsl.module

val snackBarModule = module {
    factory { SnackBarViewModel() }
}