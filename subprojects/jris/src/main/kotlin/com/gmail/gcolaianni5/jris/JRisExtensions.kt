package com.gmail.gcolaianni5.jris

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import java.nio.channels.ClosedChannelException

//region:import - RISFile lines -> RisRecords

/**
 * Converts a flow of Strings (representing lines in a RIS file) (as receiver) into a flow of [RisRecord]s.
 * May throw a [JRisException] if the line flow cannot be parsed successfully.
 */
@ExperimentalCoroutinesApi
fun Flow<String>.toRisRecords(): Flow<RisRecord> = JRis.process(this)


@ExperimentalCoroutinesApi
fun List<String>.toRisRecords(): List<RisRecord> = JRis.processList(this)

/**
 * Converts a sequence of Strings (representing lines in a RIS file) (as receiver) into a sequence of [RisRecord]s
 * in a blocking manner. May throw a [JRisException] if the line flow cannot be parsed successfully.
 */
@FlowPreview
@ExperimentalCoroutinesApi
fun Sequence<String>.toRisRecords(): Sequence<RisRecord> {
    val lineFlow = this.asFlow()
    return sequence {
        val channel = JRis.process(lineFlow).produceIn(GlobalScope)

        try {
            while (!channel.isClosedForReceive) {
                yield(runBlocking { channel.receive() })
            }
        } catch (closed: ClosedReceiveChannelException) {
            // flow is completed -> swallow
        } finally {
            channel.cancel()
        }
    }
}
//endregion

//region:export - RisRecords -> RISFile lines

/**
 * Converts a flow of [RisRecord]s into a flow of [String]s in RIS file format.
 * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
 */
@JvmOverloads
@ExperimentalCoroutinesApi
fun Flow<RisRecord>.toRisLines(sort: List<String> = emptyList()): Flow<String> = JRis.build(this, sort)

/**
 * Converts a list of [RisRecord]s into a list of [String]s in RIS file format.
 * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
 */
@JvmOverloads
@ExperimentalCoroutinesApi
fun List<RisRecord>.toRisLines(sort: List<String> = emptyList()): List<String> =
    runBlocking { asFlow().toRisLines(sort).toList() }

/**
 * Processes a sequence of [RisRecord]s into a sequence of Strings
 * representing lines in RIS file format.
 * Thanks to @jcornaz for the help.
 */
@FlowPreview
@ExperimentalCoroutinesApi
fun Sequence<RisRecord>.toRisLines(): Sequence<String> =
    sequence {
        val channel = JRis.build(asFlow()).produceIn(GlobalScope)

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

//endregion

