package com.gmail.gcolaianni5.jris.example.kotlin

import com.gmail.gcolaianni5.jris.RisRecord
import com.gmail.gcolaianni5.jris.RisType
import com.gmail.gcolaianni5.jris.accept
import com.gmail.gcolaianni5.jris.process
import kotlinx.coroutines.InternalCoroutinesApi
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

@Suppress("SpellCheckingInspection")
@InternalCoroutinesApi
object JRisIoUsageImportSpec : Spek({

    describe("importing from file") {
        val file by memoized {
            File.createTempFile("jris1", null, null).also {
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
