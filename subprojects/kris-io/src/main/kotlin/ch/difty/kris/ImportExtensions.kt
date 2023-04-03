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
public fun Reader.process(): List<RisRecord> = KRisIO.process(this)

/**
 * Converts the RISFile lines in the [File] provided as receiver into a list of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
public fun File.process(): List<RisRecord> = KRisIO.process(this)

/**
 * Converts the RISFile lines from the file with the path provided as receiver into a list of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
public fun String.process(): List<RisRecord> = KRisIO.process(this)

/**
 * Converts the RISFile lines provided by the [InputStream] as receiver  into a list of RisRecords.
 * May throw an [IOException] if the stream cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
public fun InputStream.process(): List<RisRecord> = KRisIO.process(this)

/**
 * Converts the RISFile lines provided by the [Reader] as receiver into a stream of RisRecords.
 * May throw an [IOException] if the reader fails to deliver lines or a [KRisException]
 * if the lines cannot be parsed successfully.
 */

public fun Reader.processToStream(): Stream<RisRecord> = KRisIO.processToStream(this)

/**
 * Converts the RISFile lines in the [File] provided as receiver into a stream of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
public fun File.processToStream(): Stream<RisRecord> = KRisIO.processToStream(this)

/**
 * Converts the RISFile lines from the file with the path provided as receiver into a stream of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
public fun String.processToStream(): Stream<RisRecord> = KRisIO.processToStream(this)

/**
 * Converts the RISFile lines provided by the [InputStream] as receiver into a stream of RisRecords.
 * May throw an [IOException] if the stream cannot be read successfully,
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
public fun InputStream.processToStream(): Stream<RisRecord> = KRisIO.processToStream(this)
