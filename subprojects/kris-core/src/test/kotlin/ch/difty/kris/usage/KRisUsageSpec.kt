package ch.difty.kris.usage

import ch.difty.kris.KRis
import ch.difty.kris.domain.RisRecord
import ch.difty.kris.domain.RisType
import ch.difty.kris.risTagNames
import ch.difty.kris.toRisLines
import ch.difty.kris.toRisRecords
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

/**
 * Specification how to use KRis from kotlin
 */
@Suppress("SpellCheckingInspection", "unused")
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class, InternalCoroutinesApi::class)
object KRisUsageSpec : Spek({

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
            KRis.processList(risLines) shouldHaveSize 2
        }

        it("can be converted using an extension function (blocking)") {
            risLines.toRisRecords() shouldHaveSize 2
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
            KRis.buildFromList(risRecords) shouldHaveSize 9
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
            risRecords.toRisLines().joinToString(separator = "") shouldBeEqualTo """TY  - JOUR
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

        it("can convert risRecord to a string - applying a sort") {
            risRecords.toRisLines(listOf("TI", "EP")).joinToString(separator = "") shouldBeEqualTo """TY  - JOUR
                                |TI  - A Mathematical Theory of Communication
                                |EP  - 423
                                |AU  - Shannon, Claude E.
                                |PY  - 1948/07//
                                |SP  - 379
                                |T2  - Bell System Technical Journal
                                |VL  - 27
                                |ER  - 
                                |""".trimMargin()
        }

        it("can get list of all RisTag names") {
            risTagNames.joinToString() shouldBeEqualTo
                "TY, A1, A2, A3, A4, AB, AD, AN, AU, AV, BT, C1, C2, C3, C4, C5, C6, C7, C8, CA, CN, CP, CT, CY, DA, DB, DO, DP, ED, EP, ET, " +
                "ID, IS, J1, J2, JA, JF, JO, KW, L1, L2, L3, L4, LA, LB, LK, M1, M2, M3, N1, N2, NV, OP, PB, PP, PY, RI, RN, RP, SE, SN, SP, " +
                "ST, T1, T2, T3, TA, TI, TT, U1, U2, U3, U4, U5, UR, VL, VO, Y1, Y2, ER"
        }

        it("can get list of all RisTag names via KRis") {
            KRis.risTagNames() shouldBeEqualTo risTagNames
        }
    }
})
