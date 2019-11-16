package ch.difty.kris.example.kotlin

import com.gmail.gcolaianni5.jris.RisRecord
import com.gmail.gcolaianni5.jris.RisType
import com.gmail.gcolaianni5.jris.build
import com.gmail.gcolaianni5.jris.process
import kotlinx.coroutines.InternalCoroutinesApi
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

@Suppress("SpellCheckingInspection")
@InternalCoroutinesApi
object KrisIoUsageExportSpec : Spek({

    describe("exporting into file") {
        val file by memoized { File.createTempFile("kris2", null, null).also { it.deleteOnExit() } }
        val records = listOf(RisRecord(type = RisType.ABST))

        it("can write to File") {
            file.build(records)
            records.assertRecordsWereWrittenTo(file)
        }

        it("can write to writer") {
            val bufferedWriter = file.bufferedWriter()
            bufferedWriter.build(records)
            records.assertRecordsWereWrittenTo(file)
        }

        it("can write to stream") {
            val outputStream = file.outputStream()
            outputStream.build(records)
            records.assertRecordsWereWrittenTo(file)
        }

        it("can read from path") {
            val path = file.path
            path.build(records)
            records.assertRecordsWereWrittenTo(file)
        }
    }
})

private fun List<RisRecord>.assertRecordsWereWrittenTo(file: File) {
    file.path.process() shouldHaveSize size
}
