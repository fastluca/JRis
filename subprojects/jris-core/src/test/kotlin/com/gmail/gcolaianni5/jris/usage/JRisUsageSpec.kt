package com.gmail.gcolaianni5.jris.usage

import com.gmail.gcolaianni5.jris.JRis
import com.gmail.gcolaianni5.jris.domain.RisRecord
import com.gmail.gcolaianni5.jris.domain.RisType
import com.gmail.gcolaianni5.jris.toRisLines
import com.gmail.gcolaianni5.jris.toRisRecords
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

/**
 * Specification how to use JRis from kotlin
 */
@ExperimentalCoroutinesApi
@FlowPreview
@Suppress("SpellCheckingInspection", "unused")
@InternalCoroutinesApi
object JRisUsageSpec : Spek({

    describe("with list of strings representing two RIS records") {
        // example from wikipedia (https://en.wikipedia.org/wiki/RIS_(file_format)
        // and https://de.wikipedia.org/wiki/RIS_(Dateiformat))
        val risLines: List<String> = listOf(
            "TY  - JOUR",
            "AU  - Shannon, Claude E.",
            "PY  - 1948/07//",
            "TI  - A Mathematical Theory of Communication",
            "T2  - Bell System Technical Journal",
            "SP  - 379",
            "EP  - 423",
            "VL  - 27",
            "ER  - ",
            "TY  - JOUR",
            "TI  - Die Grundlage der allgemeinen Relativitätstheorie",
            "AU  - Einstein, Albert",
            "PY  - 1916",
            "SP  - 769",
            "EP  - 822",
            "JO  - Annalen der Physik",
            "VL  - 49",
            "ER  - "
        )


        it("can be passed to a static method returning a list of RisRecords (blocking)") {
            JRis.processList(risLines) shouldHaveSize 2
        }

        describe("converted to Flow") {
            val flowOfRisLines: Flow<String> = risLines.asFlow()

            it("can be passed to a flow operator returning a flow of RisRecords (non-blocking)") {
                runBlocking {
                    flowOfRisLines
                        .toRisRecords()
                        .toList()
                        .shouldHaveSize(2)
                }
            }
        }

        describe("converted to a Sequence") {
            val sequenceOfRisLines: Sequence<String> = risLines.asSequence()

            it("can be passed to a sequence operator returning a sequence of RisRecords (blocking)") {
                sequenceOfRisLines
                    .toRisRecords()
                    .toList()
                    .shouldHaveSize(2)
            }
        }
    }

    describe("with a list with a single RisRecord") {
        val risRecord = RisRecord(
            type = RisType.JOUR,
            authors = mutableListOf("Shannon, Claude E."),
            publicationYear = "1948/07//",
            title = "A Mathematical Theory of Communication",
            secondaryTitle = "Bell System Technical Journal",
            startPage = "379",
            endPage = "423",
            volumeNumber = "27"
        )
        val expectedLinesInFile = 9 // including ER (End of Record)

        val risRecords = listOf(risRecord)

        it("can be passed to a static method returning a list of Strings (blocking)") {
            JRis.buildFromList(risRecords) shouldHaveSize 9
        }

        describe("converted to Flow") {
            val flowOfRisRecords: Flow<RisRecord> = risRecords.asFlow()

            it("can be passed to a flow operator returning a flow of Strings (non-blocking)") {
                runBlocking {
                    flowOfRisRecords
                        .toRisLines()
                        .toList()
                        .shouldHaveSize(expectedLinesInFile)
                }
            }
        }

        describe("converted to a Sequence") {
            val sequenceOfRisRecords: Sequence<RisRecord> = risRecords.asSequence()

            it("can be passed to a sequence operator returning a sequence of Strings (blocking)") {
                sequenceOfRisRecords
                    .toRisLines()
                    .toList()
                    .shouldHaveSize(expectedLinesInFile)
            }
        }

        it("can convert risRecord to a string") {
            risRecords.toRisLines().joinToString(separator = "") shouldEqual """TY  - JOUR
                                |AU  - Shannon, Claude E.
                                |EP  - 423
                                |PY  - 1948/07//
                                |SP  - 379
                                |T2  - Bell System Technical Journal
                                |TI  - A Mathematical Theory of Communication
                                |VL  - 27
                                |ER  - 
                                |""".trimMargin()
        }
    }
})
