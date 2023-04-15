package ch.difty.kris

import ch.difty.kris.domain.RisRecord
import java.io.File
import java.io.OutputStream
import java.io.Writer

/**
 * Converts a list of [RisRecord]s into a list of [String]s in RIS file format, dumping them into the
 * [Writer] provided as receiver. Optionally accepts a list of names of RisTags defining a sort order for
 * the RisTags in the file.
 */
@JvmOverloads
public fun Writer.accept(
    records: List<RisRecord>,
    sort: List<String> = emptyList(),
    dispatchers: DispatcherProvider = DefaultDispatcherProvider,
): Unit = KRisIO.export(records, sort, dispatchers, this)

/**
 * Converts a list of [RisRecord]s into a list of [String]s in RIS file format, writing them into the [File]
 * provided as receiver. Optionally accepts a list of names of RisTags defining a sort order for the RisTags
 * in the file.
 */
@JvmOverloads
public fun File.accept(
    records: List<RisRecord>,
    sort: List<String> = emptyList(),
    dispatchers: DispatcherProvider = DefaultDispatcherProvider,
): Unit = KRisIO.export(records, sort, dispatchers, this)

/**
 * Converts a list of [RisRecord]s into a list of [String]s in RIS file format, writing them into
 * the [OutputStream] provided as receiver. Optionally accepts a list of names of RisTags defining a sort order
 * for the RisTags in the file.
 */
@JvmOverloads
public fun OutputStream.accept(
    records: List<RisRecord>,
    sort: List<String> = emptyList(),
    dispatchers: DispatcherProvider = DefaultDispatcherProvider,
): Unit = KRisIO.export(records, sort, dispatchers, this)

/**
 * Converts a list of [RisRecord]s into a list of [String]s in RIS file format, writing them into file with
 * the path provided as the receiver, if possible.
 * Optionally accepts a list of names of RisTags defining a sort order for the RisTags in the file.
 */
@JvmOverloads
public fun String.accept(
    records: List<RisRecord>,
    sort: List<String> = emptyList(),
    dispatchers: DispatcherProvider = DefaultDispatcherProvider,
): Unit = KRisIO.export(records, sort, dispatchers, this)
