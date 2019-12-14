package com.gmail.gcolaianni5.jris.example.kotlin

import com.gmail.gcolaianni5.jris.RisRecord
import com.gmail.gcolaianni5.jris.RisType
import com.gmail.gcolaianni5.jris.accept
import com.gmail.gcolaianni5.jris.process
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

@ExperimentalCoroutinesApi
@Suppress("SpellCheckingInspection")
@InternalCoroutinesApi
object JRisIoUsageExportSpec : Spek({

    describe("exporting into file") {
        val file by memoized { File.createTempFile("jris2", null, null).also { it.deleteOnExit() } }
        val records = listOf(RisRecord(type = RisType.ABST))

        it("can write to File") {
            file.accept(records)
            records.assertRecordsWereWrittenTo(file)
        }

        it("can write to writer") {
            val bufferedWriter = file.bufferedWriter()
            bufferedWriter.accept(records)
            records.assertRecordsWereWrittenTo(file)
        }

        it("can write to stream") {
            val outputStream = file.outputStream()
            outputStream.accept(records)
            records.assertRecordsWereWrittenTo(file)
        }

        it("can read from path") {
            val path = file.path
            path.accept(records)
            records.assertRecordsWereWrittenTo(file)
        }
    }
})

@ExperimentalCoroutinesApi
private fun List<RisRecord>.assertRecordsWereWrittenTo(file: File) {
    file.path.process() shouldHaveSize size
}
