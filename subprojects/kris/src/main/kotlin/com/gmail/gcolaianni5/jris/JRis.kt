@file:Suppress("unused", "TooManyFunctions", "SpellCheckingInspection")

package com.gmail.gcolaianni5.jris

import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.asObservable
import kotlin.math.min

private const val TAG_SEPARATOR = "  - "
private const val INT_INTERMEDIATE = 1000

private val TAG_LENGTH = RisTag.ER.name.length
private val START_INDEX_VALUE = TAG_LENGTH + TAG_SEPARATOR.length

private val LINE_SEPARATOR = System.getProperty("line.separator")

class JRisException(message: String) : Throwable(message)

/**
 * `RIS` format processor and builder. It is capable of
 *
 * * processing lines of RIS files (Strings), converting them to [RisRecord]s
 * * building well formatted RIS files from [RisRecord]s.
 *
 * The JRis class works in non-blocking manner as default.
 * Extension functions provide blocking alternatives for ease of use both from Kotlin or Java.
 *
 * @author Gianluca Colaianni -- g.colaianni5@gmail.com
 * @author Urs Joss - urs.joss@gmx.ch
 */
@ExperimentalCoroutinesApi
object JRis {

//region:process - RISFile lines -> RisRecords

    /**
     * Converts a flow of Strings (representing lines in a RIS file) into a flow of [RisRecord]s.
     * May throw a [JRisException] if the line flow cannot be parsed successfully.
     */
    fun process(lineFlow: Flow<String>): Flow<RisRecord> = flow {
        var record = RisRecord()
        var previousTag: RisTag? = null

        lineFlow.filterNotNull()
            .filterNot { line -> line.isEmpty() }
            .collect { line ->
                if (line.isEndOfRecord()) {
                    emit(record)
                    record = RisRecord()
                } else {
                    previousTag = record.fillFrom(line, previousTag)
                }
            }
    }

    private fun String.isEndOfRecord() = startsWith(RisTag.ER.name)

    /**
     * Fills the tag and the respective value into the [RisRecord] (receiver).
     * Returns the tag as context for parsing the next line.
     */
    private fun RisRecord.fillFrom(line: String, previousTag: RisTag?): RisTag? {
        if (line.isEmpty() || line.length <= START_INDEX_VALUE + 1)
            return previousTag

        val tagName = line.substring(0, TAG_LENGTH)
        val tag = RisTag.fromName(tagName)
            ?: if (previousTag == RisTag.AB) RisTag.AB
            else throw JRisException("Unable to parse tag '$tagName'")
        tag.setInto(this, tag.typeSafeValueFrom(line))
        return tag
    }

    private fun RisTag.typeSafeValueFrom(line: String): Any? {
        val rawValue: String = line.substring(START_INDEX_VALUE).trim()
        return when (kClass) {
            RisType::class -> RisType.valueOf(rawValue)
            Long::class -> rawValue.toLong()
            else -> rawValue.truncatedTo(maxLength)
        }
    }

    private fun String.truncatedTo(maxLength: Int?): String = when {
        maxLength != null -> substring(0, min(maxLength, length))
        else -> this
    }

    /**
     * Converts an observable of Strings (representing lines in a RIS file) into an observable of [RisRecord]s
     * in non-blocking manner. May throw [JRisException] if the line flow cannot be parsed successfully.
     */
    @JvmStatic
    fun processObservables(risLineObservable: Observable<String>): Observable<RisRecord> =
        process(risLineObservable.asFlow()).asObservable()

    /**
     * Converts a list of Strings (representing lines in a RIS file) into a list of [RisRecord]s in blocking manner.
     * May throw a [JRisException] if the line flow cannot be parsed successfully.
     */
    @JvmStatic
    fun processList(risLines: List<String>): List<RisRecord> = runBlocking { process(risLines.asFlow()).toList() }

//endregion

//region:build - or RisRecords -> RISFile lines

    /**
     * Converts a flow of [RisRecord]s into a flow of [String]s in RIS file format.
     * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
     */
    fun build(recordFlow: Flow<RisRecord>, sort: List<String> = emptyList()): Flow<String> = flow {
        fun RisTag.withValue(value: Any): String = "$this$TAG_SEPARATOR$value$LINE_SEPARATOR"
        val sortMap = sort.withIndex().associate { RisTag.valueOf(it.value) to it.index }.toMap()
        var firstRecord = true
        recordFlow.collect { risRecord ->
            if (!firstRecord) emit(LINE_SEPARATOR)
            firstRecord = false
            RisTag.values().sortedWith(sortMap.toComparator()).forEach { tag ->
                tag.getFrom(risRecord)?.let { recordValue: Any ->
                    when (recordValue) {
                        is List<*> -> recordValue.forEach { listValue ->
                            emit(tag.withValue(listValue as String))
                        }
                        is String -> emit(tag.withValue(recordValue.truncatedTo(tag.maxLength)))
                        else -> emit(tag.withValue(recordValue))
                    }
                }
            }
        }
    }

    /**
     * Sorting
     *
     * * first by fixOrder (where specified), guaranteeing e.g. TY is first and ER is last
     * * then by explicit order as provided to the build call
     * * lastly by tag name
     */
    private fun Map<RisTag, Int>.toComparator(): Comparator<RisTag> =
        compareBy({ it.requiredOrder }, { this[it] ?: INT_INTERMEDIATE }, { it.name })


    /**
     * Converts an observable of [RisRecord]s into an observable of [String]s in RIS file format.
     * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
     */
    @JvmStatic
    @JvmOverloads
    fun exportObservable(observable: Observable<RisRecord>, sort: List<String> = emptyList()): Observable<String> =
        build(observable.asFlow(), sort).asObservable()

    /**
     * Converts a list of [RisRecord]s into a list of [String]s in RIS file format in blocking manner.
     * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
     */
    @JvmStatic
    @JvmOverloads
    fun buildFromList(risRecords: List<RisRecord>, sort: List<String> = emptyList()): List<String> =
        runBlocking { build(risRecords.asFlow(), sort).toList() }

//endregion
}
