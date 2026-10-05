package com.app.edgekitcmp.features.settings.data.local.source

import com.app.edgekitcmp.features.settings.data.local.mapper.asDomainModel
import com.app.edgekitcmp.features.settings.data.local.mapper.asLocalModel
import com.app.edgekitcmp.features.settings.data.local.model.SettingsLocalItem
import com.app.edgekitcmp.features.settings.domain.model.SettingsItem
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json

/**
 * Implementation of [SettingsLocalDataSource] that persists settings locally using Multiplatform Settings
 * and serializes settings items to/from JSON strings via Kotlinx Serialization.
 *
 * @property settings The platform-specific [Settings] key-value storage instance.
 * @property json The [Json] instance used for serializing and deserializing settings data models.
 */
class SettingsLocalDataSourceImpl(
    private val settings: Settings = Settings(),
    private val json: Json = Json { ignoreUnknownKeys = true },
) : SettingsLocalDataSource {

    private val mutex = Mutex()
    private val _settingsItem = MutableStateFlow(read())

    /** Emits updates to the current [SettingsItem]. */
    override val settingsItem: StateFlow<SettingsItem> get() = _settingsItem.asStateFlow()

    private fun read(): SettingsItem = settings.getStringOrNull(KEY_SETTINGS)
        ?.let { raw ->
            runCatching { json.decodeFromString<SettingsLocalItem>(raw) }
                .map { it.asDomainModel() }
                .getOrElse { error ->
                    // Corrupt data must never crash startup, but it should leave a trace.
                    // Replace println with your logger.
                    println("Settings: failed to decode stored settings, using defaults: $error")
                    SettingsItem()
                }
        } ?: SettingsItem()

    /**
     * Updates the stored settings item atomically using a mutex lock.
     *
     * @param transform A lambda function that takes the current [SettingsItem] and returns the updated [SettingsItem].
     */
    override suspend fun update(transform: (SettingsItem) -> SettingsItem) = mutex.withLock {
        val updated = transform(_settingsItem.value)
        settings.putString(KEY_SETTINGS, json.encodeToString(updated.asLocalModel()))
        _settingsItem.value = updated
    }

    companion object {
        /** Key used to store serialized settings in local key-value storage. */
        const val KEY_SETTINGS = "settings"
    }
}
