package ch.difty.kris

import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.Reader

/**
 * Converts the RISFile lines provided by the reader as receiver into a list of RisRecords.
 * May throw an [IOException] if the reader fails to deliver lines or a [KRisException]
 * if the lines cannot be parsed successfully.
 */
@ExperimentalCoroutinesApi
fun Reader.process() = KRisIO.process(this)

/**
 * Converts the RISFile lines in the [File] provided as receiver into a list of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully.
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@ExperimentalCoroutinesApi
fun File.process() = KRisIO.process(this)

/**
 * Converts the RISFile lines from the file with the path provided as receiver into a list of RisRecords.
 * May throw an [IOException] if the file cannot be read successfully.
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@ExperimentalCoroutinesApi
fun String.process() = KRisIO.process(this)

/**
 * Converts the RISFile lines provided by the [InputStream] as receiver  into a list of RisRecords.
 * May throw an [IOException] if the stream cannot be read successfully.
 * or a [KRisException] if the lines cannot be parsed successfully.
 */
@ExperimentalCoroutinesApi
fun InputStream.process() = KRisIO.process(this)
