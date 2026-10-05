package com.app.edgekitcmp.ui.component.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CustomSnackBarHostState(
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    var type: SnackBarType by mutableStateOf(SnackBarType.INFO)

    suspend fun showSnackBar(
        message: String,
        type: SnackBarType,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        this.type = type
        snackBarHostState.showSnackbar(
            message = message,
            duration = duration
        )
    }
}