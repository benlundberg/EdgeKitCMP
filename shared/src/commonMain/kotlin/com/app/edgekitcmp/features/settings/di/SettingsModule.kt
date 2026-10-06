package com.app.edgekitcmp.features.settings.di

import com.app.edgekitcmp.features.settings.data.local.source.SettingsLocalDataSource
import com.app.edgekitcmp.features.settings.data.local.source.SettingsLocalDataSourceImpl
import com.app.edgekitcmp.features.settings.data.source.SettingsRepositoryImpl
import com.app.edgekitcmp.features.settings.domain.SettingsRepository
import com.app.edgekitcmp.features.settings.ui.SettingsViewModel
import com.app.edgekitcmp.features.settings.ui.error.SettingsUiExceptionHandler
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    single<Settings> { Settings() }
    single<SettingsLocalDataSource> { SettingsLocalDataSourceImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    singleOf(::SettingsUiExceptionHandler)
    viewModelOf(::SettingsViewModel)
}