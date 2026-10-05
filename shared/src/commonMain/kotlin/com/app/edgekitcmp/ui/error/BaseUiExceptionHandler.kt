package com.app.edgekitcmp.ui.error

import com.app.edgekitcmp.ui.model.OneTimeEvent

abstract class BaseUiExceptionHandler {

    fun handleException(e: Throwable): OneTimeEvent? {
        trackException(e)

        return evaluateException(e)
    }

    protected abstract fun evaluateException(e: Throwable): OneTimeEvent?

    protected fun trackException(e: Throwable) {
        // TODO: Track exception in crashlytics
    }
}