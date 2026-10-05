package com.app.edgekitcmp.features.settings.data.local.source

import com.app.edgekitcmp.features.settings.domain.model.ThemeMode
import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SettingsLocalDataSourceTest {

    @Test
    fun `defaults to System when nothing is stored`() {
        val source = SettingsLocalDataSourceImpl(MapSettings())
        assertEquals(ThemeMode.System, source.settingsItem.value.themeMode)
    }

    @Test
    fun `update persists and is read back by a new instance`() = runTest {
        val storage = MapSettings()
        SettingsLocalDataSourceImpl(storage).update { it.copy(themeMode = ThemeMode.Dark) }

        val restored = SettingsLocalDataSourceImpl(storage)
        assertEquals(ThemeMode.Dark, restored.settingsItem.value.themeMode)
    }

    @Test
    fun `corrupt JSON falls back to defaults`() {
        val storage = MapSettings().apply { putString("settings", "{not valid json") }
        val source = SettingsLocalDataSourceImpl(storage)
        assertEquals(ThemeMode.System, source.settingsItem.value.themeMode)
    }

    @Test
    fun `unknown stored theme value falls back to System`() {
        val storage = MapSettings().apply { putString("settings", """{"themeMode":"Sepia"}""") }
        val source = SettingsLocalDataSourceImpl(storage)
        assertEquals(ThemeMode.System, source.settingsItem.value.themeMode)
    }
}