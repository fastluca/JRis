package ch.difty.kris.example.kotlin

import com.gmail.gcolaianni5.jris.JRis
import com.gmail.gcolaianni5.jris.RisRecord
import com.gmail.gcolaianni5.jris.RisType
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object KrisUsageSpec : Spek({

    describe("with RIS file as list of strings representing") {
        // example from wikipedia (https://en.wikipedia.org/wiki/RIS_(file_format))
        val lines = listOf(
            "TY  - JOUR",
            "AU  - Shannon, Claude E.",
            "PY  - 1948/07//",
            "TI  - A Mathematical Theory of Communication",
            "T2  - Bell System Technical Journal",
            "SP  - 379",
            "EP  - 423",
            "VL  - 27",
            "ER  - "
        )

        it("can parse lines as sequence returning list") {
            JRis.parse(lines.asSequence()) shouldHaveSize 1
        }

        // TODO develop API for parsing
    }

    describe("with RisRecord") {
        val risRecord = RisRecord(
            type = RisType.JOUR,
            authors = mutableListOf("Shannon, Claude E."),
            publicationYear = "1948/07//",
            title = "A Mathematical Theory of Communication",
            secondaryTitle = "Bell System Technical Journal",
            startPage = "379",
            endPage = "423",
            volumeNumber = " 27"
        )

        val lines by memoized { JRis.build(records = listOf(risRecord)) }
        it("can convert risRecord to a string") {
            lines shouldEqual """TY  - JOUR
                                |AU  - Shannon, Claude E.
                                |EP  - 423
                                |PY  - 1948/07//
                                |SP  - 379
                                |T2  - Bell System Technical Journal
                                |TI  - A Mathematical Theory of Communication
                                |VL  -  27
                                |ER  - 
                                |""".trimMargin()
        }
    }
})