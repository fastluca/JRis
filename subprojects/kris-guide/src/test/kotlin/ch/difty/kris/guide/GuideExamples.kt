@file:Suppress("unused", "SpellCheckingInspection", "UNUSED_VARIABLE", "RedundantExplicitType")

package ch.difty.kris.guide

import ch.difty.kris.accept
import ch.difty.kris.domain.RisRecord
import ch.difty.kris.domain.RisType
import ch.difty.kris.process
import ch.difty.kris.toRisLines
import ch.difty.kris.toRisRecords
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream

//region records-file

// tag::risRecords[]
val record1 = RisRecord(
    type = RisType.JOUR,
    authors = mutableListOf("Shannon, Claude E."),
    publicationYear = "1948/07//",
    title = "A Mathematical Theory of Communication",
    secondaryTitle = "Bell System Technical Journal",
    startPage = "379",
    endPage = "423",
    volumeNumber = "27"
)
val record2 = RisRecord(
    type = RisType.JOUR,
    authors = mutableListOf("Turing, Alan Mathison"),
    publicationYear = "1948/07//",
    periodicalNameFullFormatJO = "Proc. of London Mathematical Society",
    title = "On computable numbers, with an application to the Entscheidungsproblem",
    secondaryTitle = "Bell System Technical Journal",
    startPage = "230",
    endPage = "265",
    volumeNumber = "47",
    issue = "1",
    primaryDate = "1937"
)
// end::risRecords[]

@ExperimentalCoroutinesApi
fun listToRisLines() {
    // tag::listToRisLines[]
    val lines: List<String> = listOf(record1, record2).toRisLines()
    // end::listToRisLines[]
}

@Suppress("UNUSED_VARIABLE")
@ExperimentalCoroutinesApi
fun listToRisLinesWithSort() {
    // tag::listToRisLinesWithSort[]
    val sort: List<String> = listOf("SP", "EP", "T1")
    val lines: List<String> = listOf(record1, record2).toRisLines(sort)
    // end::listToRisLinesWithSort[]
}


@ExperimentalCoroutinesApi
fun writingToFileManually() {
    // tag::writingToFileManually[]
    val lines: List<String> = listOf(record1, record2).toRisLines()

    // writing the lines to file, one of many possibilities
    val content: String = lines.joinToString(separator = "")
    File("export.ris").writeText(content)
    // end::writingToFileManually[]
}


@ExperimentalCoroutinesApi
fun writerAccept() {
    // tag::writerAccept[]
    val writer = File.createTempFile("export.ris", null, null).bufferedWriter()

    writer.accept(listOf(record1, record2))
    // end::writerAccept[]
}

@ExperimentalCoroutinesApi
fun fileAccept() {
    // tag::fileAccept[]
    val file = File.createTempFile("export.ris", null, null)

    file.accept(listOf(record1, record2))
    // end::fileAccept[]
}

@ExperimentalCoroutinesApi
fun streamAccept() {
    // tag::streamAccept[]
    val outputStream = File.createTempFile("export.ris", null, null).outputStream()

    outputStream.accept(listOf(record1, record2))
    // end::streamAccept[]
}

@ExperimentalCoroutinesApi
fun pathAccept() {
    // tag::pathAccept[]
    "export.ris".accept(listOf(record1, record2))
    // end::pathAccept[]
}


@Suppress("UNUSED_VARIABLE")
@ExperimentalCoroutinesApi
fun flowToRisLines() {
    // tag::flowToRisLines[]
    val flow = flowOf(record1, record2)

    val risLines: Flow<String> = flow.toRisLines()
    // end::flowToRisLines[]
}

@ExperimentalCoroutinesApi
fun pathAcceptFlow() {
    // tag::pathAcceptFlow[]
    val risLines: Flow<String> = flowOf(record1, record2).toRisLines()

    runBlocking {
        val content: String = risLines.toList().joinToString(separator = "")
        File("export.ris").writeText(content)
    }
    // end::pathAcceptFlow[]
}

//endregion

//region file-records

@ExperimentalCoroutinesApi
@Suppress("UNUSED_VARIABLE")
fun processReader() {
    // tag::processReader[]
    val bufferedReader: BufferedReader = File("import.ris").bufferedReader()

    val records: List<RisRecord> = bufferedReader.process()
    // end::processReader[]
}
//endregion

@ExperimentalCoroutinesApi
@Suppress("UNUSED_VARIABLE")
fun processFile() {
    // tag::processFile[]
    val file: File = File("import.ris")

    val records: List<RisRecord> = file.process()
    // end::processFile[]
}
//endregion


@ExperimentalCoroutinesApi
@Suppress("UNUSED_VARIABLE")
fun processInputStream() {
    // tag::processInputStream[]
    val inputStream: FileInputStream = File("import.ris").inputStream()

    val records: List<RisRecord> = inputStream.process()
    // end::processInputStream[]
}
//endregion

@ExperimentalCoroutinesApi
@Suppress("UNUSED_VARIABLE")
fun processPath() {
    // tag::processPath[]
    val records: List<RisRecord> = "import.ris".process()
    // end::processPath[]
}


@ExperimentalCoroutinesApi
@FlowPreview
@Suppress("UNUSED_VARIABLE", "UNREACHABLE_CODE")
fun passRisLinesAsList() {
    // tag::passRisLinesAsList[]
    val lines: List<String> = TODO()

    val records: List<RisRecord> = lines.toRisRecords()
    // end::passRisLinesAsList[]
}

@ExperimentalCoroutinesApi
@FlowPreview
@Suppress("UNUSED_VARIABLE", "UNREACHABLE_CODE")
fun passRisLinesAsSequence() {
    // tag::passRisLinesAsSequence[]
    val lineSequence: Sequence<String> = TODO()

    val records: Sequence<RisRecord> = lineSequence.toRisRecords()
    // end::passRisLinesAsSequence[]
}

@ExperimentalCoroutinesApi
@FlowPreview
@Suppress("UNUSED_VARIABLE", "UNREACHABLE_CODE")
fun passRisLinesAsFlow() {
    // tag::passRisLinesAsFlow[]
    val lineFlow: Flow<String> = TODO()

    val recordFlow: Flow<RisRecord> = lineFlow.toRisRecords()

    runBlocking {
        val records: List<RisRecord> = recordFlow.toList()
    }
    // end::passRisLinesAsFlow[]
}
//endregion
