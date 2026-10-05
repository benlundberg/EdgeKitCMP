package com.app.edgekitcmp.ui.component.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.app.edgekitcmp.ui.model.BaseEvent
import com.app.edgekitcmp.ui.model.OneTimeEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun CustomSnackBarEffect(
    snackbarEventFlow: Flow<OneTimeEvent>?,
    snackBarHostState: CustomSnackBarHostState,
    onEvent: ((OneTimeEvent) -> Unit)? = null,
) {
    if (snackbarEventFlow != null) {
        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(snackbarEventFlow, lifecycleOwner) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                snackbarEventFlow.collect { event ->
                    onEvent?.invoke(event)

                    launch {
                        when (event) {
                            is BaseEvent.ShowMessage -> {
                                snackBarHostState.showSnackBar(
                                    message = event.message,
                                    type = event.type
                                )
                            }

                            is BaseEvent.ShowError -> {
                                snackBarHostState.showSnackBar(
                                    message = event.message,
                                    type = SnackBarType.ERROR
                                )
                            }

                            is BaseEvent.ShowSuccess -> {
                                snackBarHostState.showSnackBar(
                                    message = event.message,
                                    type = SnackBarType.SUCCESS,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}