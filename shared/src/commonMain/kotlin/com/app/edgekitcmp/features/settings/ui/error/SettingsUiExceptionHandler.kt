package com.app.edgekitcmp.features.settings.ui.error

import com.app.edgekitcmp.ui.error.BaseUiExceptionHandler
import com.app.edgekitcmp.ui.model.OneTimeEvent

class SettingsUiExceptionHandler : BaseUiExceptionHandler() {

    override fun evaluateException(e: Throwable): OneTimeEvent? {
        return null // TODO: Handle specific exceptions here
    }
}