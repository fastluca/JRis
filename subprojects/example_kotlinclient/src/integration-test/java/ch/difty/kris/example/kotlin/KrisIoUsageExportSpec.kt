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
        val file by memoized { File.createTempFile("kris2", null, null) }
        val records = listOf(RisRecord(type = RisType.ABST))

        beforeEachTest {
            file.deleteOnExit()
        }

        it("can write to File") {
            file.build(records)
            file.path.process() shouldHaveSize records.size
        }

        it("can write to writer") {
            file.bufferedWriter().build(records)
            file.path.process() shouldHaveSize records.size
        }

        it("can write to stream") {
            file.outputStream().build(records)
            file.path.process() shouldHaveSize records.size
        }

        it("can read from path") {
            file.path.build(records)
            file.path.process() shouldHaveSize records.size
        }
    }
})
