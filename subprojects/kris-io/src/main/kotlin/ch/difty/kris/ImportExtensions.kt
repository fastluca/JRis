package ch.difty.kris

import ch.difty.kris.domain.RisRecord
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.Reader
import java.util.stream.Stream

/**
 * Converts the RISFile lines provided by the [Reader] as receiver into a list of RisRecords.
 * May throw an [IOException] if the reader fails to deliver lines or a [KRisException]
 * if the lines cannot be parsed successfully.
 */
@JvmOverloads
public fun Reader.process(dispatchers: DispatcherProvider = DefaultDispatcherProvider): List<RisRecord> =
    KRisIO.process(this, dispatchers)

/**
 * Converts the RISFile lines in the [File] provided as receiver into a list of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@JvmOverloads
public fun File.process(dispatchers: DispatcherProvider = DefaultDispatcherProvider): List<RisRecord> =
    KRisIO.process(this, dispatchers)

/**
 * Converts the RISFile lines from the file with the path provided as receiver into a list of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@JvmOverloads
public fun String.process(dispatchers: DispatcherProvider = DefaultDispatcherProvider): List<RisRecord> =
    KRisIO.process(this, dispatchers)

/**
 * Converts the RISFile lines provided by the [InputStream] as receiver  into a list of RisRecords.
 * May throw an [IOException] if the stream cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@JvmOverloads
public fun InputStream.process(dispatchers: DispatcherProvider = DefaultDispatcherProvider): List<RisRecord> =
    KRisIO.process(this, dispatchers)

/**
 * Converts the RISFile lines provided by the [Reader] as receiver into a stream of RisRecords.
 * May throw an [IOException] if the reader fails to deliver lines or a [KRisException]
 * if the lines cannot be parsed successfully.
 */
@JvmOverloads
public fun Reader.processToStream(dispatchers: DispatcherProvider = DefaultDispatcherProvider): Stream<RisRecord> =
    KRisIO.processToStream(this, dispatchers)

/**
 * Converts the RISFile lines in the [File] provided as receiver into a stream of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@JvmOverloads
public fun File.processToStream(dispatchers: DispatcherProvider = DefaultDispatcherProvider): Stream<RisRecord> =
    KRisIO.processToStream(this, dispatchers)

/**
 * Converts the RISFile lines from the file with the path provided as receiver into a stream of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@JvmOverloads
public fun String.processToStream(dispatchers: DispatcherProvider = DefaultDispatcherProvider): Stream<RisRecord> =
    KRisIO.processToStream(this, dispatchers)

/**
 * Converts the RISFile lines provided by the [InputStream] as receiver into a stream of RisRecords.
 * May throw an [IOException] if the stream cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@JvmOverloads
public fun InputStream.processToStream(dispatchers: DispatcherProvider = DefaultDispatcherProvider): Stream<RisRecord> =
    KRisIO.processToStream(this, dispatchers)
