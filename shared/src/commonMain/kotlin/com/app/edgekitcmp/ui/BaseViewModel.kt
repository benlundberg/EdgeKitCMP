package com.app.edgekitcmp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.edgekitcmp.ui.error.BaseUiExceptionHandler
import com.app.edgekitcmp.ui.model.OneTimeEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * A base class for ViewModels that follow the Unidirectional Data Flow (UDF) pattern.
 *
 * It manages a single UI state and a stream of one-time events.
 *
 * @param VS The type representing the View State.
 * @param initialUiState The initial state of the UI.
 */
abstract class BaseViewModel<VS>(initialUiState: VS, private val exceptionHandler: BaseUiExceptionHandler? = null) : ViewModel() {

    private val _state: MutableStateFlow<VS> = MutableStateFlow(initialUiState)

    /**
     * A [StateFlow] that emits the current UI state.
     * Observers (like Composable functions) should collect from this flow to react to state changes.
     */
    val stateFlow: StateFlow<VS> get() = _state.asStateFlow()

    /**
     * Returns the current value of the UI state.
     */
    val state: VS get() = _state.value

    /**
     * Updates the UI state.
     *
     * @param update A lambda that receives the current state and returns the updated state.
     */
    protected fun setState(update: (VS) -> VS) {
        viewModelScope.launch {
            _state.emit(update(state))
        }
    }

    private val eventChannel = Channel<OneTimeEvent>(Channel.BUFFERED)

    /**
     * A [Flow] of one-time events (e.g., navigation, showing a snackbar).
     * These events are consumed once and not replayed upon configuration changes.
     */
    val eventsFlow get() = eventChannel.receiveAsFlow()

    /**
     * Triggers a one-time event.
     *
     * @param event The event to be sent to the UI.
     */
    protected fun sendEvent(event: OneTimeEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    protected fun <T> Flow<T>.collectToState(reduce: VS.(T) -> VS): Job =
        onEach { value -> setState { state.reduce(value) } }
            .launchIn(viewModelScope)

    protected fun launch(
        onError: (Throwable) -> Unit = ::onUnhandledError,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch {
        try {
            block()
        } catch (t: Throwable) {
            onError(t)
        }
    }

    protected open fun onUnhandledError(t: Throwable) {
        val exceptionHandler = exceptionHandler ?: return

        exceptionHandler.handleException(t)?.let {
            sendEvent(it)
        }
    }
}