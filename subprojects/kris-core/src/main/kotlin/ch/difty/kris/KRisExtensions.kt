@file:Suppress("SpellCheckingInspection", "RedundantVisibilityModifier")
@file:OptIn(ExperimentalCoroutinesApi::class)

package ch.difty.kris

import ch.difty.kris.domain.RisRecord
import ch.difty.kris.domain.RisTag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import java.nio.channels.ClosedChannelException

//region:import - RISFile lines -> RisRecords

/**
 * Converts a flow of Strings (representing lines in a RIS file) (as receiver) into a flow of [RisRecord]s.
 * May throw a [KRisException] if the line flow cannot be parsed successfully.
 */
public fun Flow<String>.toRisRecords(): Flow<RisRecord> = KRis.process(this)

/**
 * Converts a list of Strings (representing lines in a RIS file) (as receiver) itno a list of [RisRecord]s.
 * May throw a [KRisException] if the line list of Strings cannot be parsed successfully.
 */
public fun List<String>.toRisRecords(): List<RisRecord> = KRis.processList(this)

/**
 * Converts a sequence of Strings (representing lines in a RIS file) (as receiver) into a sequence of [RisRecord]s
 * in a blocking manner. May throw a [KRisException] if the line flow cannot be parsed successfully.
 */
@OptIn(FlowPreview::class, DelicateCoroutinesApi::class)
public fun Sequence<String>.toRisRecords(scope: CoroutineScope = GlobalScope): Sequence<RisRecord> = mapSequence(KRis::process, scope)
//endregion

//region:export - RisRecords -> RISFile lines

/**
 * Converts a flow of [RisRecord]s into a flow of [String]s in RIS file format.
 * Optionally accepts a list of names of RisTags defining a sort order for the RisTags in the file.
 */
@JvmOverloads
public fun Flow<RisRecord>.toRisLines(sort: List<String> = emptyList()): Flow<String> = KRis.build(this, sort)

/**
 * Converts a list of [RisRecord]s into a list of [String]s in RIS file format.
 * Optionally accepts a list of names of RisTags defining a sort order for the RisTags in the file.
 */
@JvmOverloads
public fun List<RisRecord>.toRisLines(sort: List<String> = emptyList()): List<String> =
    runBlocking { asFlow().toRisLines(sort).toList() }

/**
 * Processes a sequence of [RisRecord]s into a sequence of Strings representing lines in RIS file format.
 */
@OptIn(FlowPreview::class, DelicateCoroutinesApi::class)
public fun Sequence<RisRecord>.toRisLines(scope: CoroutineScope = GlobalScope): Sequence<String> = mapSequence(KRis::build, scope)
//endregion

/**
 * Processes a sequence of type [T] into a sequence of type [R] using a [flowMapper]
 * accepting a flow of type [T] and returning a flow of type [R].
 * Thanks to @jcornaz for the help.
 */
@OptIn(FlowPreview::class)
@Suppress("SwallowedException")
private fun <T, R> Sequence<T>.mapSequence(
    flowMapper: (Flow<T>) -> Flow<R>,
    scope: CoroutineScope
): Sequence<R> = sequence {
    val sourceFlow: Flow<T> = this@mapSequence.asFlow()
    val targetFlow: Flow<R> = flowMapper(sourceFlow)

    val channel: ReceiveChannel<R> = targetFlow.produceIn(scope)
    try {
        while (!channel.isClosedForReceive) {
            yield(runBlocking { channel.receive() })
        }
    } catch (closed: ClosedReceiveChannelException) {
        // flow is completed -> swallow
    } catch (closed: ClosedChannelException) {
        // flow is completed -> swallow
    } finally {
        channel.cancel()
    }
}

/**
 * List of the names of all [RisTag]s.
 */
public val risTagNames: List<String> get() = RisTag.values().map { it.name }
