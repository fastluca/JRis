package com.gmail.gcolaianni5.jris.implementation

import com.gmail.gcolaianni5.jris.TAG_SEPARATOR
import com.gmail.gcolaianni5.jris.domain.RisRecord
import com.gmail.gcolaianni5.jris.domain.RisTag
import com.gmail.gcolaianni5.jris.truncatedTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

private const val INT_INTERMEDIATE = 1000

private val LINE_SEPARATOR = System.getProperty("line.separator")

internal object JRisExport {

    /**
     * Converts a flow of [RisRecord]s into a flow of [String]s in RIS file format.
     * Optionally accepts a list of names of [RisTag]s defining a sort order for the [RisTag]s in the file.
     */
    internal fun build(recordFlow: Flow<RisRecord>, sort: List<String> = emptyList()): Flow<String> = flow {
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

}
