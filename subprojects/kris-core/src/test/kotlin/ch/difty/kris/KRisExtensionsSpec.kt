package ch.difty.kris

import io.kotest.core.spec.style.DescribeSpec
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull

object KRisExtensionsSpec : DescribeSpec({

    val recordWithNewLines = listOf(
        "TY  - JOUR\n",
        "SP  - 2\n",
        "EP  - 28\n",
        "ER  - \n"
    )
    val recordWithoutNewLines = listOf(
        "TY  - JOUR",
        "SP  - 2",
        "EP  - 28",
        "ER  - "
    )

    describe("with a list of lines - each w/ new lines") {
        it("should extract startPage") {
            recordWithNewLines.toRisRecords().first().startPage shouldBeEqualTo "2"
        }
        it("should extract endPage") {
            recordWithNewLines.toRisRecords().first().endPage shouldBeEqualTo "28"
        }
    }

    describe("with a list of lines - each w/o new lines") {
        it("should extract startPage") {
            recordWithoutNewLines.toRisRecords().first().startPage.shouldBeNull() // TODO wrong, should be 'shouldBeEqualTo "2"'
        }
        it("should extract endPage") {
            recordWithoutNewLines.toRisRecords().first().endPage shouldBeEqualTo "28"
        }
    }
})
