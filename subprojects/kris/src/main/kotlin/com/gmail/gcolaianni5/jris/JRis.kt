@file:Suppress("unused")

package com.gmail.gcolaianni5.jris

import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.asObservable
import kotlin.math.min

private const val TAG_SEPARATOR = "  - "
private const val INT_INTERMEDIATE = 1000

private val TAG_LENGTH = RisTag.ER.name.length
private val START_INDEX_VALUE = TAG_LENGTH + TAG_SEPARATOR.length

private val LINE_SEPARATOR = System.getProperty("line.separator")

private data class TagAccessor(
    val tag: RisTag,
    val setInto: (RisRecord, Any?) -> Unit,
    val getFrom: (RisRecord) -> Any?
)

class JRisException(message: String) : Throwable(message)

/**
 * `RIS` format processor and builder. It is capable of
 *
 * * processing lines of RIS files (Strings), converting them to [RisRecord]s
 * * building well formatted RIS files from [RisRecord]s.
 *
 * The Jris class works in non-blocking manner as default.
 * Extension functions provide blocking alternatives for ease of use both from Kotlin or Java.
 *
 * @author Gianluca Colaianni -- g.colaianni5@gmail.com
 * @author Urs Joss - urs.joss@gmx.ch
 */
@ExperimentalCoroutinesApi
object JRis {

    @Suppress("MaxLineLength", "MaximumLineLength")
    private val tagAccessors: Set<TagAccessor> = setOf(
        TagAccessor(RisTag.TY, { r, v -> r.type = v as RisType }, { r: RisRecord -> r.type }),
        TagAccessor(RisTag.A1, { r, v -> r.firstAuthors.add(v as String) }, { r: RisRecord -> r.firstAuthors }),
        TagAccessor(RisTag.A2, { r, v -> r.secondaryAuthors.add(v as String) }, { r: RisRecord -> r.secondaryAuthors }),
        TagAccessor(RisTag.A3, { r, v -> r.tertiaryAuthors.add(v as String) }, { r: RisRecord -> r.tertiaryAuthors }),
        TagAccessor(RisTag.A4, { r, v -> r.subsidiaryAuthors.add(v as String) }, { r: RisRecord -> r.subsidiaryAuthors }),
        TagAccessor(RisTag.AB, { r, v -> r.abstr = v as String }, { r: RisRecord -> r.abstr }),
        TagAccessor(RisTag.AD, { r, v -> r.authorAddress = v as String }, { r: RisRecord -> r.authorAddress }),
        TagAccessor(RisTag.AN, { r, v -> r.accessionNumber = v as String }, { r: RisRecord -> r.accessionNumber }),
        TagAccessor(RisTag.AU, { r, v -> r.authors.add(v as String) }, { r: RisRecord -> r.authors }),
        TagAccessor(RisTag.AV, { r, v -> r.archivesLocation = v as String? }, { r: RisRecord -> r.archivesLocation }),
        TagAccessor(RisTag.BT, { r, v -> r.bt = v as String? }, { r: RisRecord -> r.bt }),
        TagAccessor(RisTag.C1, { r, v -> r.custom1 = v as String? }, { r: RisRecord -> r.custom1 }),
        TagAccessor(RisTag.C2, { r, v -> r.custom2 = v as String? }, { r: RisRecord -> r.custom2 }),
        TagAccessor(RisTag.C3, { r, v -> r.custom3 = v as String? }, { r: RisRecord -> r.custom3 }),
        TagAccessor(RisTag.C4, { r, v -> r.custom4 = v as String? }, { r: RisRecord -> r.custom4 }),
        TagAccessor(RisTag.C5, { r, v -> r.custom5 = v as String? }, { r: RisRecord -> r.custom5 }),
        TagAccessor(RisTag.C6, { r, v -> r.custom6 = v as String? }, { r: RisRecord -> r.custom6 }),
        TagAccessor(RisTag.C7, { r, v -> r.custom7 = v as String? }, { r: RisRecord -> r.custom7 }),
        TagAccessor(RisTag.C8, { r, v -> r.custom8 = v as String? }, { r: RisRecord -> r.custom8 }),
        TagAccessor(RisTag.CA, { r, v -> r.caption = v as String? }, { r: RisRecord -> r.caption }),
        TagAccessor(RisTag.CN, { r, v -> r.callNumber = v as String? }, { r: RisRecord -> r.callNumber }),
        TagAccessor(RisTag.CP, { r, v -> r.cp = v as String? }, { r: RisRecord -> r.cp }),
        TagAccessor(RisTag.CT, { r, v -> r.unpublishedReferenceTitle = v as String? }, { r: RisRecord -> r.unpublishedReferenceTitle }),
        TagAccessor(RisTag.CY, { r, v -> r.placePublished = v as String? }, { r: RisRecord -> r.placePublished }),
        TagAccessor(RisTag.DA, { r, v -> r.date = v as String? }, { r: RisRecord -> r.date }),
        TagAccessor(RisTag.DB, { r, v -> r.databaseName = v as String? }, { r: RisRecord -> r.databaseName }),
        TagAccessor(RisTag.DO, { r, v -> r.doi = v as String? }, { r: RisRecord -> r.doi }),
        TagAccessor(RisTag.DP, { r, v -> r.databaseProvider = v as String? }, { r: RisRecord -> r.databaseProvider }),
        TagAccessor(RisTag.ED, { r, v -> r.editor = v as String? }, { r: RisRecord -> r.editor }),
        TagAccessor(RisTag.EP, { r, v -> r.endPage = v as String? }, { r: RisRecord -> r.endPage }),
        TagAccessor(RisTag.ET, { r, v -> r.edition = v as String? }, { r: RisRecord -> r.edition }),
        TagAccessor(RisTag.ID, { r, v -> r.referenceId = v as String? }, { r: RisRecord -> r.referenceId }),
        TagAccessor(RisTag.IS, { r, v -> r.issue = v as String? }, { r: RisRecord -> r.issue }),
        TagAccessor(RisTag.J1, { r, v -> r.periodicalNameUserAbbrevation = v as String? }, { r: RisRecord -> r.periodicalNameUserAbbrevation }),
        TagAccessor(RisTag.J2, { r, v -> r.alternativeTitle = v as String? }, { r: RisRecord -> r.alternativeTitle }),
        TagAccessor(RisTag.JA, { r, v -> r.periodicalNameStandardAbbrevation = v as String? }, { r: RisRecord -> r.periodicalNameStandardAbbrevation }),
        TagAccessor(RisTag.JF, { r, v -> r.periodicalNameFullFormatJF = v as String? }, { r: RisRecord -> r.periodicalNameFullFormatJF }),
        TagAccessor(RisTag.JO, { r, v -> r.periodicalNameFullFormatJO = v as String? }, { r: RisRecord -> r.periodicalNameFullFormatJO }),
        TagAccessor(RisTag.KW, { r, v -> r.keywords.add(v as String) }, { r: RisRecord -> r.keywords }),
        TagAccessor(RisTag.L1, { r, v -> r.pdfLinks.add(v as String) }, { r: RisRecord -> r.pdfLinks }),
        TagAccessor(RisTag.L2, { r, v -> r.fullTextLinks.add(v as String) }, { r: RisRecord -> r.fullTextLinks }),
        TagAccessor(RisTag.L3, { r, v -> r.relatedRecords.add(v as String) }, { r: RisRecord -> r.relatedRecords }),
        TagAccessor(RisTag.L4, { r, v -> r.images.add(v as String) }, { r: RisRecord -> r.images }),
        TagAccessor(RisTag.LA, { r, v -> r.language = v as String? }, { r: RisRecord -> r.language }),
        TagAccessor(RisTag.LB, { r, v -> r.label = v as String? }, { r: RisRecord -> r.label }),
        TagAccessor(RisTag.LK, { r, v -> r.websiteLink = v as String? }, { r: RisRecord -> r.websiteLink }),
        TagAccessor(RisTag.M1, { r, v -> r.number = v as Long? }, { r: RisRecord -> r.number }),
        TagAccessor(RisTag.M2, { r, v -> r.miscellaneous2 = v as String? }, { r: RisRecord -> r.miscellaneous2 }),
        TagAccessor(RisTag.M3, { r, v -> r.typeOfWork = v as String? }, { r: RisRecord -> r.typeOfWork }),
        TagAccessor(RisTag.N1, { r, v -> r.notes = v as String? }, { r: RisRecord -> r.notes }),
        TagAccessor(RisTag.N2, { r, v -> r.abstr2 = v as String? }, { r: RisRecord -> r.abstr2 }),
        TagAccessor(RisTag.NV, { r, v -> r.abstr2 = v as String? }, { r: RisRecord -> r.abstr2 }),
        TagAccessor(RisTag.OP, { r, v -> r.originalPublication = v as String? }, { r: RisRecord -> r.originalPublication }),
        TagAccessor(RisTag.PB, { r, v -> r.publisher = v as String? }, { r: RisRecord -> r.publisher }),
        TagAccessor(RisTag.PP, { r, v -> r.publishingPlace = v as String? }, { r: RisRecord -> r.publishingPlace }),
        TagAccessor(RisTag.PY, { r, v -> r.publicationYear = v as String? }, { r: RisRecord -> r.publicationYear }),
        TagAccessor(RisTag.RI, { r, v -> r.reviewedItem = v as String? }, { r: RisRecord -> r.reviewedItem }),
        TagAccessor(RisTag.RN, { r, v -> r.researchNotes = v as String? }, { r: RisRecord -> r.researchNotes }),
        TagAccessor(RisTag.RP, { r, v -> r.reprintEdition = v as String? }, { r: RisRecord -> r.reprintEdition }),
        TagAccessor(RisTag.SE, { r, v -> r.section = v as String? }, { r: RisRecord -> r.section }),
        TagAccessor(RisTag.SN, { r, v -> r.isbnIssn = v as String? }, { r: RisRecord -> r.isbnIssn }),
        TagAccessor(RisTag.SP, { r, v -> r.startPage = v as String? }, { r: RisRecord -> r.startPage }),
        TagAccessor(RisTag.ST, { r, v -> r.shortTitle = v as String? }, { r: RisRecord -> r.shortTitle }),
        TagAccessor(RisTag.T1, { r, v -> r.primaryTitle = v as String? }, { r: RisRecord -> r.primaryTitle }),
        TagAccessor(RisTag.T2, { r, v -> r.secondaryTitle = v as String? }, { r: RisRecord -> r.secondaryTitle }),
        TagAccessor(RisTag.T3, { r, v -> r.tertiaryTitle = v as String? }, { r: RisRecord -> r.tertiaryTitle }),
        TagAccessor(RisTag.TA, { r, v -> r.translatedAuthor = v as String? }, { r: RisRecord -> r.translatedAuthor }),
        TagAccessor(RisTag.TI, { r, v -> r.title = v as String? }, { r: RisRecord -> r.title }),
        TagAccessor(RisTag.TT, { r, v -> r.translatedTitle = v as String? }, { r: RisRecord -> r.translatedTitle }),
        TagAccessor(RisTag.U1, { r, v -> r.userDefinable1 = v as String? }, { r: RisRecord -> r.userDefinable1 }),
        TagAccessor(RisTag.U2, { r, v -> r.userDefinable2 = v as String? }, { r: RisRecord -> r.userDefinable2 }),
        TagAccessor(RisTag.U3, { r, v -> r.userDefinable3 = v as String? }, { r: RisRecord -> r.userDefinable3 }),
        TagAccessor(RisTag.U4, { r, v -> r.userDefinable4 = v as String? }, { r: RisRecord -> r.userDefinable4 }),
        TagAccessor(RisTag.U5, { r, v -> r.userDefinable5 = v as String? }, { r: RisRecord -> r.userDefinable5 }),
        TagAccessor(RisTag.UR, { r, v -> r.url = v as String? }, { r: RisRecord -> r.url }),
        TagAccessor(RisTag.VL, { r, v -> r.volumeNumber = v as String? }, { r: RisRecord -> r.volumeNumber }),
        TagAccessor(RisTag.VO, { r, v -> r.publisherStandardNumber = v as String? }, { r: RisRecord -> r.publisherStandardNumber }),
        TagAccessor(RisTag.Y1, { r, v -> r.primaryDate = v as String? }, { r: RisRecord -> r.primaryDate }),
        TagAccessor(RisTag.Y2, { r, v -> r.accessDate = v as String? }, { r: RisRecord -> r.accessDate })
    )

    private val tag2accessor: Map<String, TagAccessor> = tagAccessors.map { it.tag.name to it }.toMap()

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

        val tagAccessor = line.findTagAccessorGiven(previousTag)
        val tag = tagAccessor.tag
        tagAccessor.setInto(this, tag.typeSafeValueFrom(line))
        return tag
    }

    /**
     * Try to parse the tag name from the line and return the [TagAccessor] if it is defined.
     * We try to fall back to [RisTag.AB], especially if the previous tag was [RisTag.AB]
     */
    private fun String.findTagAccessorGiven(previousTag: RisTag?): TagAccessor {
        val tagName: String = substring(0, TAG_LENGTH)
        val tag = if (tagName in tag2accessor) RisTag.valueOf(tagName)
        else when (previousTag) {
            RisTag.AB -> previousTag
            else -> throw JRisException("Unable to parse tag '$tagName'")
        }
        return tag2accessor[tag.name] ?: tag2accessor.getValue(RisTag.AB.name)
    }

    private fun RisTag.typeSafeValueFrom(line: String): Any? {
        val rawValue: String = line.substring(START_INDEX_VALUE).trim()
        return when (kClass) {
            RisType::class -> RisType.valueOf(rawValue)
            Integer::class -> Integer.valueOf(rawValue)
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
        fun TagAccessor.withValue(value: Any): String = "$tag$TAG_SEPARATOR$value$LINE_SEPARATOR"
        val sortMap = sort.withIndex().associate { RisTag.valueOf(it.value) to it.index }.toMap()
        var firstRecord = true
        recordFlow.collect { risRecord ->
            if (!firstRecord) emit(LINE_SEPARATOR)
            firstRecord = false
            tagAccessors.sortedWith(sortMap.toComparator()).forEach { tagAccessor ->
                tagAccessor.getFrom(risRecord)?.let { recordValue: Any ->
                    when (recordValue) {
                        is List<*> -> recordValue.forEach { listValue ->
                            emit(tagAccessor.withValue(listValue as String))
                        }
                        is String -> emit(tagAccessor.withValue(recordValue.truncatedTo(tagAccessor.tag.maxLength)))
                        else -> emit(tagAccessor.withValue(recordValue))
                    }
                }
            }
            emit("${RisTag.ER.name}$TAG_SEPARATOR$LINE_SEPARATOR")
        }
    }

    /**
     * Sorting
     *
     * * first by fixOrder (where specified), guaranteeing e.g. TY is first and ER is last
     * * then by explicit order as provided to the build call
     * * lastly by tag name
     */
    private fun Map<RisTag, Int>.toComparator(): Comparator<TagAccessor> =
        compareBy({ it.tag.requiredOrder }, { this[it.tag] ?: INT_INTERMEDIATE }, { it.tag.name })


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
