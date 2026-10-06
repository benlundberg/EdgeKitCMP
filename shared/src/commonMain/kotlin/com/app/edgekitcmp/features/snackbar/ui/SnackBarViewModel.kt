package com.app.edgekitcmp.features.snackbar.ui

import com.app.edgekitcmp.features.snackbar.ui.model.SnackBarUiState
import com.app.edgekitcmp.core.ui.BaseViewModel
import com.app.edgekitcmp.core.ui.component.snackbar.SnackBarType
import com.app.edgekitcmp.core.ui.model.BaseEvent

class SnackBarViewModel : BaseViewModel<SnackBarUiState>(SnackBarUiState()) {

    fun onShowSnackBar(type: SnackBarType) {
        val event = when (type) {
            SnackBarType.INFO -> BaseEvent.ShowMessage(
                "This is an info message",
                type = SnackBarType.INFO
            )

            SnackBarType.ERROR -> BaseEvent.ShowError("This is an error message")
            SnackBarType.SUCCESS -> BaseEvent.ShowSuccess("This is a success message")
            SnackBarType.WARNING -> BaseEvent.ShowMessage(
                "This is a warning message",
                type = SnackBarType.WARNING
            )
        }

        sendEvent(event)
    }
}