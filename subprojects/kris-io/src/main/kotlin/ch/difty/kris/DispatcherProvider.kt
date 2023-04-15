package ch.difty.kris

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provides access to the different types of [CoroutineDispatcher]s,
 * defaulting to the regular kotlin Dispatcher implementation.
 *
 * Useful to inject TestCoroutineDispatcher implementations in test scenarios.
 * See https://craigrussell.io/2021/12/testing-android-coroutines-using-runtest/#injecting-coroutine-dispatchers
 */
@Suppress("InjectDispatcher") // :-)
public interface DispatcherProvider {
    public val default: CoroutineDispatcher get() = Dispatchers.Default
    public val main: CoroutineDispatcher get() = Dispatchers.Main
    public val unconfined: CoroutineDispatcher get() = Dispatchers.Unconfined
    public val io: CoroutineDispatcher get() = Dispatchers.IO
}

//* Default dispatcher giving access to the regular kotlin coroutine dispatcher implementations */
public object DefaultDispatcherProvider : DispatcherProvider
