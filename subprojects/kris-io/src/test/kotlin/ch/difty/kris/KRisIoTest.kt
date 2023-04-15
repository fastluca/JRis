package ch.difty.kris

import ch.difty.kris.domain.RisRecord
import ch.difty.kris.domain.RisType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Test
import java.io.Writer

internal class KRisIoTest {

    @Test
    internal fun givenTwoRisRecords_when_exporting_areWrittenTwoWriter() {
        val records = listOf(
            RisRecord(type = RisType.BOOK, authors = mutableListOf("Bond J."), publicationYear = "1922"),
            RisRecord(type = RisType.JOUR, authors = mutableListOf("No Dr."), publicationYear = "1962"),
        )
        val writer = mockk<Writer> {
            every { write(any<String>()) } returns Unit
            every { close() } returns Unit
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        KRisIO.export(records, writer = writer, dispatchers = TestDispatcherProvider())

        verify { writer.write("TY  - BOOK\n") }
        verify { writer.write("AU  - Bond J.\n") }
        verify { writer.write("PY  - 1922\n") }
        verify { writer.write("TY  - JOUR\n") }
        verify { writer.write("AU  - No Dr.\n") }
        verify { writer.write("PY  - 1962\n") }
        verify { writer.write("ER  - \n") }
    }
}
