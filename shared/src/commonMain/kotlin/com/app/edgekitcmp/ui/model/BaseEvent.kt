package com.app.edgekitcmp.ui.model

import com.app.edgekitcmp.ui.component.snackbar.SnackBarType

sealed class BaseEvent : OneTimeEvent {
    data class ShowMessage(val message: String, val type: SnackBarType) : BaseEvent()
    data class ShowError(val message: String) : BaseEvent()
    data class ShowSuccess(val message: String) : BaseEvent()
}