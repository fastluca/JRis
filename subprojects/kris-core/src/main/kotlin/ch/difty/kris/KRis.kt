@file:Suppress("unused", "TooManyFunctions", "SpellCheckingInspection")

package ch.difty.kris

import ch.difty.kris.domain.RisRecord
import ch.difty.kris.implementation.RisExport
import ch.difty.kris.implementation.RisImport
import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.asFlow
import kotlinx.coroutines.rx2.asObservable

class KRisException(message: String) : Throwable(message)

internal const val TAG_SEPARATOR = "  - "

/**
 * `RIS` format processor and builder. It is capable of
 *
 * * processing lines of RIS files (Strings), converting them to [RisRecord]s
 * * building well formatted RIS files from [RisRecord]s.
 *
 * The [KRis] class works in non-blocking manner as default.
 * Extension functions provide blocking alternatives for ease of use both from Kotlin or Java.
 *
 * @author Gianluca Colaianni -- g.colaianni5@gmail.com
 * @author Urs Joss - urs.joss@gmx.ch
 */
@Suppress("KDocUnresolvedReference")
@ExperimentalCoroutinesApi
object KRis {

//region:process - RISFile lines -> RisRecords

    /**
     * Converts a flow of Strings (representing lines in a RIS file) into a flow of [RisRecord]s.
     * May throw a [KRisException] if the line flow cannot be parsed successfully.
     */
    fun process(lineFlow: Flow<String>): Flow<RisRecord> = RisImport.process(lineFlow)

    /**
     * Converts an observable of Strings (representing lines in a RIS file) into an observable of [RisRecord]s
     * in non-blocking manner. May throw [KRisException] if the line flow cannot be parsed successfully.
     */
    @JvmStatic
    fun processObservables(risLineObservable: Observable<String>): Observable<RisRecord> =
        process(risLineObservable.asFlow()).asObservable()

    /**
     * Converts a list of Strings (representing lines in a RIS file) into a list of [RisRecord]s in blocking manner.
     * May throw a [KRisException] if the line flow cannot be parsed successfully.
     */
    @JvmStatic
    fun processList(risLines: List<String>): List<RisRecord> = runBlocking { process(risLines.asFlow()).toList() }

//endregion

//region:build - or RisRecords -> RISFile lines

    /**
     * Converts a flow of [RisRecord]s into a flow of [String]s in RIS file format.
     * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
     */
    fun build(recordFlow: Flow<RisRecord>, sort: List<String> = emptyList()): Flow<String> = RisExport.build(recordFlow, sort)

    /**
     * Converts a list of [RisRecord]s into a list of [String]s in RIS file format in blocking manner.
     * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
     */
    @JvmStatic
    @JvmOverloads
    fun buildFromList(risRecords: List<RisRecord>, sort: List<String> = emptyList()): List<String> =
        runBlocking { build(risRecords.asFlow(), sort).toList() }

    /**
     * Converts an observable of [RisRecord]s into an observable of [String]s in RIS file format.
     * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
     */
    @JvmStatic
    @JvmOverloads
    fun exportObservable(observable: Observable<RisRecord>, sort: List<String> = emptyList()): Observable<String> =
        build(observable.asFlow(), sort).asObservable()

//endregion
}
