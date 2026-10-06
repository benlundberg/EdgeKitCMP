package com.app.edgekitcmp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.edgekitcmp.ui.error.BaseUiExceptionHandler
import com.app.edgekitcmp.ui.model.OneTimeEvent
import kotlinx.coroutines.CancellationException
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * A base class for ViewModels that follow the Unidirectional Data Flow (UDF) pattern.
 *
 * It manages a single UI state and a stream of one-time events.
 *
 * @param VS The type representing the View State.
 * @param initialUiState The initial state of the UI.
 * @param exceptionHandler Optional handler for catching and handling unhandled exceptions during coroutine execution.
 */
abstract class BaseViewModel<VS>(
    initialUiState: VS,
    private val exceptionHandler: BaseUiExceptionHandler? = null,
) : ViewModel() {

    private val _state = MutableStateFlow(initialUiState)

    /** The UI state. Composables collect this. */
    val stateFlow: StateFlow<VS> = _state.asStateFlow()

    /** The current state, for reading inside the ViewModel. */
    val state: VS get() = _state.value

    protected fun setState(update: (VS) -> VS) {
        _state.update(update)
    }

    private val eventChannel = Channel<OneTimeEvent>(Channel.BUFFERED)

    /** One-time events. Should have a single collector. */
    val eventsFlow: Flow<OneTimeEvent> = eventChannel.receiveAsFlow()

    protected fun sendEvent(event: OneTimeEvent) {
        viewModelScope.launch { eventChannel.send(event) }
    }

    /** Collects this flow and folds each value into the UI state. */
    protected fun <T> Flow<T>.collectToState(reduce: VS.(T) -> VS): Job =
        onEach { value -> setState { it.reduce(value) } }
            .launchIn(viewModelScope)

    /** Launches in viewModelScope. Failures go to [onError], cancellation does not. */
    protected fun launch(
        onError: (Throwable) -> Unit = ::onUnhandledError,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = viewModelScope.launch {
        try {
            block()
        } catch (e: CancellationException) {
            throw e
        } catch (t: Throwable) {
            onError(t)
        }
    }

    protected open fun onUnhandledError(t: Throwable) {
        val handler = exceptionHandler ?: return
        handler.handleException(t)?.let(::sendEvent)
    }
}