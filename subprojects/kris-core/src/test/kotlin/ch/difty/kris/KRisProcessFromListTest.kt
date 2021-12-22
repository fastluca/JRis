@file:Suppress("SpellCheckingInspection")

package ch.difty.kris

import ch.difty.kris.domain.RisRecord
import ch.difty.kris.domain.RisType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KRisProcessFromListTest {

    private val risRecord = RisRecord(
        type = RisType.BOOK,
        firstAuthors = mutableListOf("firstAuthor1", "firstAuthor2"),
        secondaryAuthors = mutableListOf("secondaryAuthor1", "secondaryAuthor2"),
        tertiaryAuthors = mutableListOf("tertiaryAuthor1", "tertiaryAuthor2"),
        subsidiaryAuthors = mutableListOf("subsidiaryAuthor1", "subsidiaryAuthor2"),
        authors = mutableListOf("author1", "author2"),
        abstr = "abstr",
        authorAddress = "authorAddress",
        accessionNumber = "accessionNumber",
        archivesLocation = "archivesLocation",
        bt = "bt",
        custom1 = "custom1",
        custom2 = "custom2",
        custom3 = "custom3",
        custom4 = "custom4",
        custom5 = "custom5",
        custom6 = "custom6",
        custom7 = "custom7",
        custom8 = "custom8",
        caption = "caption",
        callNumber = "callNumber",
        cp = "cp",
        unpublishedReferenceTitle = "unpublishedReferenceTitle",
        placePublished = "placePublished",
        date = "date",
        databaseName = "databaseName",
        doi = "doi",
        databaseProvider = "databaseProvider",
        editor = "editor",
        endPage = "endPage",
        edition = "edition",
        referenceId = "referenceId",
        issue = "issue",
        periodicalNameUserAbbrevation = "periodicalNameUserAbbrevation",
        alternativeTitle = "alternativeTitle",
        periodicalNameStandardAbbrevation = "periodicalNameStandardAbbrevation",
        periodicalNameFullFormatJF = "periodicalNameFullFormatJF",
        periodicalNameFullFormatJO = "periodicalNameFullFormatJO",
        keywords = mutableListOf("keyword2", "keyword2"),
        pdfLinks = mutableListOf("pdfLink1", "pdfLink2"),
        fullTextLinks = mutableListOf("fullTextLink1", "fullTextLink2"),
        relatedRecords = mutableListOf("relatedRecord1", "relatedRecord2"),
        images = mutableListOf("image1", "image2"),
        language = "language",
        label = "label",
        websiteLink = "websiteLink",
        number = 1L,
        miscellaneous2 = "miscellaneous2",
        typeOfWork = "typeOfWork",
        notes = "notes",
        abstr2 = "abstr2",
        numberOfVolumes = "numberOfVolumes",
        originalPublication = "originalPublication",
        publisher = "publisher",
        publishingPlace = "publishingPlace",
        publicationYear = "publicationYear",
        reviewedItem = "reviewedItem",
        researchNotes = "researchNotes",
        reprintEdition = "reprintEdition",
        section = "section",
        isbnIssn = "isbnIssn",
        startPage = "startPage",
        shortTitle = "shortTitle",
        primaryTitle = "primaryTitle",
        secondaryTitle = "secondaryTitle",
        tertiaryTitle = "tertiaryTitle",
        translatedAuthor = "translatedAuthor",
        title = "title",
        translatedTitle = "translatedTitle",
        userDefinable1 = "userDefinable1",
        userDefinable2 = "userDefinable2",
        userDefinable3 = "userDefinable3",
        userDefinable4 = "userDefinable4",
        userDefinable5 = "userDefinable5",
        url = "url",
        volumeNumber = "volumeNumber",
        publisherStandardNumber = "publisherStandardNumber",
        primaryDate = "primaryDate",
        accessDate = "2019-01-01"
    )

    private val expected =
        """TY  - BOOK
        |A1  - firstAuthor1
        |A1  - firstAuthor2
        |A2  - secondaryAuthor1
        |A2  - secondaryAuthor2
        |A3  - tertiaryAuthor1
        |A3  - tertiaryAuthor2
        |A4  - subsidiaryAuthor1
        |A4  - subsidiaryAuthor2
        |AB  - abstr
        |AD  - authorAddress
        |AN  - accessionNumber
        |AU  - author1
        |AU  - author2
        |AV  - archivesLocation
        |BT  - bt
        |C1  - custom1
        |C2  - custom2
        |C3  - custom3
        |C4  - custom4
        |C5  - custom5
        |C6  - custom6
        |C7  - custom7
        |C8  - custom8
        |CA  - caption
        |CN  - callNumber
        |CP  - cp
        |CT  - unpublishedReferenceTitle
        |CY  - placePublished
        |DA  - date
        |DB  - databaseName
        |DO  - doi
        |DP  - databaseProvider
        |ED  - editor
        |EP  - endPage
        |ET  - edition
        |ID  - referenceId
        |IS  - issue
        |J1  - periodicalNameUserAbbrevation
        |J2  - alternativeTitle
        |JA  - periodicalNameStandardAbbrevation
        |JF  - periodicalNameFullFormatJF
        |JO  - periodicalNameFullFormatJO
        |KW  - keyword2
        |KW  - keyword2
        |L1  - pdfLink1
        |L1  - pdfLink2
        |L2  - fullTextLink1
        |L2  - fullTextLink2
        |L3  - relatedRecord1
        |L3  - relatedRecord2
        |L4  - image1
        |L4  - image2
        |LA  - language
        |LB  - label
        |LK  - websiteLink
        |M1  - 1
        |M2  - miscellaneous2
        |M3  - typeOfWork
        |N1  - notes
        |N2  - abstr2
        |NV  - numberOfVolumes
        |OP  - originalPublication
        |PB  - publisher
        |PP  - publishingPlace
        |PY  - publicationYear
        |RI  - reviewedItem
        |RN  - researchNotes
        |RP  - reprintEdition
        |SE  - section
        |SN  - isbnIssn
        |SP  - startPage
        |ST  - shortTitle
        |T1  - primaryTitle
        |T2  - secondaryTitle
        |T3  - tertiaryTitle
        |TA  - translatedAuthor
        |TI  - title
        |TT  - translatedTitle
        |U1  - userDefinable1
        |U2  - userDefinable2
        |U3  - userDefinable3
        |U4  - userDefinable4
        |U5  - userDefinable5
        |UR  - url
        |VL  - volumeNumber
        |VO  - publisherStandardNumber
        |Y1  - primaryDate
        |Y2  - 2019-01-01
        |ER  - 
        |""".trimMargin()

    @Suppress("S100")
    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `can build risRecord with all fields`() {
        val lines = KRis.buildFromList(listOf(risRecord))
        lines.joinToString("") shouldBeEqualTo expected
    }
}
