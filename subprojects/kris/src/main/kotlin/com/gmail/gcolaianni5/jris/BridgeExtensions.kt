package com.gmail.gcolaianni5.jris

import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Bridges between RxJava observables and kotlin flows. Inspired by @jcornaz and Roman
 * Elizarovs article https://medium.com/@elizarov/callbacks-and-kotlin-flows-2b53aa2525cf
 */
@ExperimentalCoroutinesApi
internal fun <T> Observable<out T>.asFlow(): Flow<T> = callbackFlow {
    val disposable = subscribe(
        { value -> sendBlocking(value) },
        { exception -> close(exception) },
        { close() }
    )
    awaitClose { disposable.dispose() }
}