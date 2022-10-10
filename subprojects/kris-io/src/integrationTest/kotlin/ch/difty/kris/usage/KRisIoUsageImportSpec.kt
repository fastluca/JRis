package ch.difty.kris.usage

import ch.difty.kris.accept
import ch.difty.kris.domain.RisRecord
import ch.difty.kris.domain.RisType
import ch.difty.kris.process
import io.kotest.core.spec.style.DescribeSpec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.amshove.kluent.shouldHaveSize
import java.io.File

@Suppress("unused")
object KRisIoUsageImportSpec : DescribeSpec({

    describe("importing from file") {
        val file = withContext(Dispatchers.IO) {
            File.createTempFile("kris1", null, null)
        }.also {
            it.accept(listOf(RisRecord(type = RisType.JOUR)))
            it.deleteOnExit()
        }

        it("can read from File") {
            file.process() shouldHaveSize 1
        }

        it("can read from Reader") {
            val bufferedReader = file.bufferedReader()
            bufferedReader.process() shouldHaveSize 1
        }

        it("can read from stream") {
            val inputStream = file.inputStream()
            inputStream.process() shouldHaveSize 1
        }

        it("can read from path") {
            val path = file.path
            path.process() shouldHaveSize 1
        }
    }
})
