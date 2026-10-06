package com.app.edgekitcmp.ui

import com.app.edgekitcmp.core.ui.error.BaseUiExceptionHandler
import com.app.edgekitcmp.core.ui.model.OneTimeEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {

    private class CounterViewModel(
        handler: com.app.edgekitcmp.core.ui.error.BaseUiExceptionHandler? = null,
    ) : com.app.edgekitcmp.core.ui.BaseViewModel<Int>(0, handler) {
        fun increment() = setState { it + 1 }
        fun failWith(t: Throwable) = launch { throw t }
        override fun onUnhandledError(t: Throwable) {
            reported += t
            super.onUnhandledError(t)
        }
        val reported = mutableListOf<Throwable>()
    }

    private object TestEvent : com.app.edgekitcmp.core.ui.model.OneTimeEvent

    @BeforeTest fun setUp() = Dispatchers.setMain(UnconfinedTestDispatcher())
    @AfterTest fun tearDown() = Dispatchers.resetMain()

    @Test
    fun `concurrent setState calls do not lose updates`() = runTest {
        val vm = CounterViewModel()
        withContext(Dispatchers.Default) {
            (1..1000).map { async { vm.increment() } }.awaitAll()
        }
        assertEquals(1000, vm.stateFlow.value)
    }

    @Test
    fun `cancellation is not reported as an error`() = runTest {
        val vm = CounterViewModel()
        vm.failWith(CancellationException("screen closed"))
        assertTrue(vm.reported.isEmpty())
    }

    @Test
    fun `unhandled error becomes a one-time event`() = runTest {
        val handler = object : com.app.edgekitcmp.core.ui.error.BaseUiExceptionHandler() {
            override fun evaluateException(e: Throwable) = TestEvent
        }
        val vm = CounterViewModel(handler)
        vm.failWith(IllegalStateException("boom"))
        assertEquals(TestEvent, vm.eventsFlow.first())
    }
}