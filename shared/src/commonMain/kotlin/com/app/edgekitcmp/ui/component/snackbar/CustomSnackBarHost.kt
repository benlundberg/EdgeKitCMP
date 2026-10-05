package com.app.edgekitcmp.ui.component.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable

@Composable
fun CustomSnackBarHost(
    snackBarHostState: CustomSnackBarHostState
) {
    SnackbarHost(hostState = snackBarHostState.snackBarHostState) {
        CustomSnackBar(
            message = it.visuals.message,
            type = snackBarHostState.type,
            duration = it.visuals.duration,
            onClose = {
                it.dismiss()
            }
        )
    }
}