package ch.difty.kris.usage

import ch.difty.kris.KRisIO
import ch.difty.kris.accept
import ch.difty.kris.domain.RisRecord
import ch.difty.kris.domain.RisType
import ch.difty.kris.process
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

@Suppress("SpellCheckingInspection", "unused")
object KRisIoUsageSpec : Spek({

    describe("importing from file") {
        val file by memoized {
            File.createTempFile("kris1", null, null).apply {
                accept(listOf(RisRecord(type = RisType.JOUR)))
                deleteOnExit()
            }
        }

        it("can read from File") {
            KRisIO.process(file) shouldHaveSize 1
        }

        it("can read from Path") {
            KRisIO.process(file.path) shouldHaveSize 1
        }

        it("can read from InputStream") {
            KRisIO.process(file.inputStream()) shouldHaveSize 1
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
            val records = listOf(
                RisRecord(
                    type = RisType.ABST,
                    typeOfWork = "tow",
                    abstr = "abstr",
                    language = "lang",
                    databaseProvider = "dp"
                )
            )

            it("can write to File") {
                file.accept(records)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to File with custom sort") {
                file.accept(records, customSort)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to writer") {
                file.bufferedWriter().accept(records)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to writer with custom sort") {
                file.bufferedWriter().accept(records, customSort)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to stream") {
                file.outputStream().accept(records)
                file.path.process() shouldHaveSize records.size
            }

            it("can write to stream with custom sort") {
                file.outputStream().accept(records, customSort)
                file.path.process() shouldHaveSize records.size
            }

            it("can read from path") {
                file.path.accept(records)
                file.path.process() shouldHaveSize records.size
            }

            it("can read from path with custom sort") {
                file.path.accept(records, customSort)
                file.path.process() shouldHaveSize records.size
            }

            describe("without explicit sort") {
                it("writes TY first, EL last and remainder alphabetically") {
                    file.path.accept(records)
                    val fileContent = file.readText()
                    fileContent shouldBeEqualTo """TY  - ABST
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
                    file.path.accept(records, listOf("M3", "AB"))
                    val fileContent = file.readText()
                    fileContent shouldBeEqualTo """TY  - ABST
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
