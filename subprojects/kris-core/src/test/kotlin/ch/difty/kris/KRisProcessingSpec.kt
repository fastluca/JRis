package ch.difty.kris

import ch.difty.kris.domain.RisType
import io.kotest.core.spec.style.DescribeSpec
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.coInvoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldThrow

@Suppress("unused", "SpellCheckingInspection", "S1192")
object KRisProcessingSpec : DescribeSpec({

    describe("with RIS file as list of strings") {
        val type = "JOUR"
        val author = "Shannon, Claude E."
        val pubYear = "1948/07//"
        val title = "A Mathematical Theory of Communication"
        val journalTitle = "Bell System Technical Journal"
        val startPage = "379"
        val endPage = "423"
        val volume = "27"

        // example from wikipedia (https://en.wikipedia.org/wiki/RIS_(file_format))
        val lines = listOf(
            "TY  - $type",
            "AU  - $author",
            "PY  - $pubYear",
            "TI  - $title",
            "T2  - $journalTitle",
            "SP  - $startPage",
            "EP  - $endPage",
            "VL  - $volume",
            "ER  - "
        )
        describe("representing a single record") {
            val risRecords = runBlocking { KRis.process(lines.asFlow()).toList() }

            it("should be parsed into one single RisRecord") { risRecords shouldHaveSize 1 }
            it("should have the reference type $type") { risRecords.first().type shouldBeEqualTo RisType.JOUR }
            it("should have single author") { risRecords.first().authors shouldHaveSize 1 }
            it("should have author $author") { risRecords.first().authors.first() shouldBeEqualTo author }
            it("should have publication year $pubYear") { risRecords.first().publicationYear shouldBeEqualTo pubYear }
            it("should have title $title") { risRecords.first().title shouldBeEqualTo title }
            it("should have secondary/journal title $journalTitle") { risRecords.first().secondaryTitle shouldBeEqualTo journalTitle }
            it("should have start page $startPage") { risRecords.first().startPage shouldBeEqualTo startPage }
            it("should have end page $endPage") { risRecords.first().endPage shouldBeEqualTo endPage }
            it("should have volume $volume") { risRecords.first().volumeNumber shouldBeEqualTo volume }
        }

        describe("representing two records") {
            val twoRecordLines = lines + lines
            it("should be parsed into one single RisRecord") {
                runBlocking { KRis.process(twoRecordLines.asFlow()).toList() shouldHaveSize 2 }
            }
        }
    }

    describe("with invalid ris tags") {
        val lines = listOf(
            "XX  - flkjsdf",
            "XX  - skjd"
        )

        it("should throw") {
            coInvoking { KRis.process(lines.asFlow()).toList() } shouldThrow KRisException::class
        }
    }

    describe("with RIS file with other fields") {
        val type = "JOUR"
        val number = 1999L
        val abstract = "abstr line 1"
        val abstract2 = "abstr line 2"

        describe("with number and abstract") {
            val lines = listOf(
                "TY  - $type",
                "M1  - $number",
                "AB  - $abstract",
                "ER  - "
            )
            val risRecords = runBlocking { KRis.process(lines.asFlow()).toList() }

            it("should be parsed into one single RisRecord") { risRecords shouldHaveSize 1 }
            it("should have the reference type $type") { risRecords.first().type shouldBeEqualTo RisType.JOUR }
            it("should have number $number") { risRecords.first().number shouldBeEqualTo number }
            it("should have abstract $abstract") { risRecords.first().abstr shouldBeEqualTo abstract }
        }

        describe("with number, abstract and some invalid tag thereafter") {
            val lines = listOf(
                "TY  - $type",
                "",
                "abcdefg",
                "AB  - $abstract",
                "XX  - $abstract2",
                "ER  - "
            )
            val risRecords = runBlocking { KRis.process(lines.asFlow()).toList() }

            it("should be parsed into one single RisRecord") { risRecords shouldHaveSize 1 }
            it("should have the reference type $type") { risRecords.first().type shouldBeEqualTo RisType.JOUR }
            it("should have abstract $abstract2") { risRecords.first().abstr shouldBeEqualTo abstract2 }
        }
    }
})
