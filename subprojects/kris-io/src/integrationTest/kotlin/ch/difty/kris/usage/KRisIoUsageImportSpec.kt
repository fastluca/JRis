package ch.difty.kris.usage

import ch.difty.kris.accept
import ch.difty.kris.domain.RisRecord
import ch.difty.kris.domain.RisType
import ch.difty.kris.process
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

@ExperimentalCoroutinesApi
@Suppress("SpellCheckingInspection")
@InternalCoroutinesApi
object KRisIoUsageImportSpec : Spek({

    describe("importing from file") {
        val file by memoized {
            File.createTempFile("kris1", null, null).also {
                it.accept(listOf(RisRecord(type = RisType.JOUR)))
                it.deleteOnExit()
            }
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
