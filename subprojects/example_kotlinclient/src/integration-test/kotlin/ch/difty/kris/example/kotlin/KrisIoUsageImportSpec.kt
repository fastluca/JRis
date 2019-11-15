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
object KrisIoUsageImportSpec : Spek({

    describe("importing from file") {
        val file by memoized { File.createTempFile("kris1", null, null) }

        beforeEachTest {
            file.build(listOf(RisRecord(type = RisType.JOUR)))
            file.deleteOnExit()
        }

        it("can read from File") {
            file.process() shouldHaveSize 1
        }

        it("can read from Reader") {
            file.bufferedReader().process() shouldHaveSize 1
        }

        it("can read from stream") {
            file.inputStream().process() shouldHaveSize 1
        }

        it("can read from path") {
            file.path.process() shouldHaveSize 1
        }
    }
})
