package ch.difty.kris.example.kotlin

import com.gmail.gcolaianni5.jris.JRisIO
import com.gmail.gcolaianni5.jris.RisRecord
import com.gmail.gcolaianni5.jris.RisType
import com.gmail.gcolaianni5.jris.build
import com.gmail.gcolaianni5.jris.process
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

@Suppress("SpellCheckingInspection", "unused")
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
object KrisIoUsageSpec : Spek({

    describe("importing from file") {
        val file by memoized {
            File.createTempFile("kris1", null, null).apply {
                build(listOf(RisRecord(type = RisType.JOUR)))
                deleteOnExit()
            }
        }

        it("can read from File") {
            JRisIO.process(file) shouldHaveSize 1
        }

        it("can read from Path") {
            JRisIO.process(file.path) shouldHaveSize 1
        }

        it("can read from InputStream") {
            JRisIO.process(file.inputStream()) shouldHaveSize 1
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

    describe("exporting into file") {
        val file by memoized { File.createTempFile("kris2", null, null).apply { deleteOnExit() } }
        val customSort = listOf("AB")

        describe("with fields in natural order") {
            val records = listOf(RisRecord(type = RisType.ABST, typeOfWork = "tow", abstr = "abstr", language = "lang", databaseProvider = "dp"))

            it("can write to File") {
                file.build(records)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to File with custom sort") {
                file.build(records, customSort)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to writer") {
                file.bufferedWriter().build(records)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to writer with custom sort") {
                file.bufferedWriter().build(records, customSort)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to stream") {
                file.outputStream().build(records)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to stream with custom sort") {
                file.outputStream().build(records, customSort)
                file.path.process() shouldHaveSize records.size
            }

            it("can read from path") {
                file.path.build(records)
                file.path.process() shouldHaveSize records.size
            }

            it("can read from path with custom sort") {
                file.path.build(records, customSort)
                file.path.process() shouldHaveSize records.size
            }

            describe("without explicit sort") {
                it("writes TY first, EL last and remainder alphabetically") {
                    file.path.build(records)
                    val fileContent = file.readText()
                    fileContent shouldEqual """TY  - ABST
                    |AB  - abstr
                    |DP  - dp
                    |LA  - lang
                    |M3  - tow
                    |ER  - 
                    |""".trimMargin()
                }
            }

            describe("with explicit sort") {
                it("writes TY first, EL last, then according to sort and remainder alphabetically") {
                    file.path.build(records, listOf("M3", "AB"))
                    val fileContent = file.readText()
                    fileContent shouldEqual """TY  - ABST
                    |M3  - tow
                    |AB  - abstr
                    |DP  - dp
                    |LA  - lang
                    |ER  - 
                    |""".trimMargin()
                }
            }
        }
    }
})

