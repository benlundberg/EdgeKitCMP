package com.app.edgekitcmp.core.ui.error

import com.app.edgekitcmp.core.ui.model.OneTimeEvent

abstract class BaseUiExceptionHandler {

    fun handleException(e: Throwable): OneTimeEvent? {
        trackException(e)

        return evaluateException(e)
    }

    protected abstract fun evaluateException(e: Throwable): OneTimeEvent?

    protected fun trackException(e: Throwable) {
        // TODO: send to analytics / crash reporting
    }
}