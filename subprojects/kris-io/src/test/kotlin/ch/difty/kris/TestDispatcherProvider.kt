package ch.difty.kris

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@Suppress("TestFunctionName")
@OptIn(ExperimentalCoroutinesApi::class)
fun TestDispatcherProvider(testDispatcher: TestDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())) =
    object : DispatcherProvider {
        override val default: CoroutineDispatcher get() = testDispatcher
        override val main: CoroutineDispatcher get() = testDispatcher
        override val unconfined: CoroutineDispatcher get() = testDispatcher
        override val io: CoroutineDispatcher get() = testDispatcher
    }
