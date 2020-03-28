@file:Suppress("SpellCheckingInspection")

package ch.difty.kris.domain

import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldContainAll
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

@Suppress("unused")
object RisRecordSpec : Spek({

    group("no values specified") {

        describe("given default record as data class") {
            val record = RisRecord()
            assertDefaultValues(record)
        }

        describe("given default record built with builder") {
            val record = RisRecord.Builder().build()
            assertDefaultValues(record)
        }

        describe("given record as data class with explicit null/empty values") {
            val record = RisRecord(
                type = null,
                firstAuthors = mutableListOf(),
                secondaryAuthors = mutableListOf(),
                tertiaryAuthors = mutableListOf(),
                subsidiaryAuthors = mutableListOf(),
                authors = mutableListOf(),
                abstr = null,
                authorAddress = null,
                accessionNumber = null,
                archivesLocation = null,
                bt = null,
                custom1 = null,
                custom2 = null,
                custom3 = null,
                custom4 = null,
                custom5 = null,
                custom6 = null,
                custom7 = null,
                custom8 = null,
                caption = null,
                callNumber = null,
                cp = null,
                unpublishedReferenceTitle = null,
                placePublished = null,
                date = null,
                databaseName = null,
                doi = null,
                databaseProvider = null,
                editor = null,
                endPage = null,
                edition = null,
                referenceId = null,
                issue = null,
                periodicalNameUserAbbrevation = null,
                alternativeTitle = null,
                periodicalNameStandardAbbrevation = null,
                periodicalNameFullFormatJF = null,
                periodicalNameFullFormatJO = null,
                keywords = mutableListOf(),
                pdfLinks = mutableListOf(),
                fullTextLinks = mutableListOf(),
                relatedRecords = mutableListOf(),
                images = mutableListOf(),
                language = null,
                label = null,
                websiteLink = null,
                number = null,
                miscellaneous2 = null,
                typeOfWork = null,
                notes = null,
                abstr2 = null,
                numberOfVolumes = null,
                originalPublication = null,
                publisher = null,
                publishingPlace = null,
                publicationYear = null,
                reviewedItem = null,
                researchNotes = null,
                reprintEdition = null,
                section = null,
                isbnIssn = null,
                startPage = null,
                shortTitle = null,
                primaryTitle = null,
                secondaryTitle = null,
                tertiaryTitle = null,
                translatedAuthor = null,
                title = null,
                translatedTitle = null,
                userDefinable1 = null,
                userDefinable2 = null,
                userDefinable3 = null,
                userDefinable4 = null,
                userDefinable5 = null,
                url = null,
                volumeNumber = null,
                publisherStandardNumber = null,
                primaryDate = null,
                accessDate = null
            )
            assertDefaultValues(record)
        }

    }

    group("with all values specified") {
        describe("given record as data class") {
            val record = RisRecord(
                type = RisType.ABST,
                firstAuthors = mutableListOf("fA1", "fA2"),
                secondaryAuthors = mutableListOf("sA1", "sA2"),
                tertiaryAuthors = mutableListOf("tA1", "tA2"),
                subsidiaryAuthors = mutableListOf("suA1", "suA2"),
                authors = mutableListOf("a1", "a2"),
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
                keywords = mutableListOf("k1", "k2"),
                pdfLinks = mutableListOf("pL1", "pL2"),
                fullTextLinks = mutableListOf("fTL1", "fTL2"),
                relatedRecords = mutableListOf("rR1", "rR2"),
                images = mutableListOf("i1", "i2"),
                language = "language",
                label = "label",
                websiteLink = "websiteLink",
                number = 1234,
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
                accessDate = "accessDate"
            )
            assertSpecifiedValues(record)
        }

        describe("given record built with builder") {
            val record = RisRecord.Builder()
                .type(RisType.ABST)
                .firstAuthors(mutableListOf("fA1", "fA2"))
                .secondaryAuthors(mutableListOf("sA1", "sA2"))
                .tertiaryAuthors(mutableListOf("tA1", "tA2"))
                .subsidiaryAuthors(mutableListOf("suA1", "suA2"))
                .authors(mutableListOf("a1", "a2"))
                .abstr("abstr")
                .authorAddress("authorAddress")
                .accessionNumber("accessionNumber")
                .archivesLocation("archivesLocation")
                .bt("bt")
                .custom1("custom1")
                .custom2("custom2")
                .custom3("custom3")
                .custom4("custom4")
                .custom5("custom5")
                .custom6("custom6")
                .custom7("custom7")
                .custom8("custom8")
                .caption("caption")
                .callNumber("callNumber")
                .cp("cp")
                .unpublishedReferenceTitle("unpublishedReferenceTitle")
                .placePublished("placePublished")
                .date("date")
                .databaseName("databaseName")
                .doi("doi")
                .databaseProvider("databaseProvider")
                .editor("editor")
                .endPage("endPage")
                .edition("edition")
                .referenceId("referenceId")
                .issue("issue")
                .periodicalNameUserAbbrevation("periodicalNameUserAbbrevation")
                .alternativeTitle("alternativeTitle")
                .periodicalNameStandardAbbrevation("periodicalNameStandardAbbrevation")
                .periodicalNameFullFormatJF("periodicalNameFullFormatJF")
                .periodicalNameFullFormatJO("periodicalNameFullFormatJO")
                .keywords(mutableListOf("k1", "k2"))
                .pdfLinks(mutableListOf("pL1", "pL2"))
                .fullTextLinks(mutableListOf("fTL1", "fTL2"))
                .relatedRecords(mutableListOf("rR1", "rR2"))
                .images(mutableListOf("i1", "i2"))
                .language("language")
                .label("label")
                .websiteLink("websiteLink")
                .number(1234)
                .miscellaneous2("miscellaneous2")
                .typeOfWork("typeOfWork")
                .notes("notes")
                .abstr2("abstr2")
                .numberOfVolumes("numberOfVolumes")
                .originalPublication("originalPublication")
                .publisher("publisher")
                .publishingPlace("publishingPlace")
                .publicationYear("publicationYear")
                .reviewedItem("reviewedItem")
                .researchNotes("researchNotes")
                .reprintEdition("reprintEdition")
                .section("section")
                .isbnIssn("isbnIssn")
                .startPage("startPage")
                .shortTitle("shortTitle")
                .primaryTitle("primaryTitle")
                .secondaryTitle("secondaryTitle")
                .tertiaryTitle("tertiaryTitle")
                .translatedAuthor("translatedAuthor")
                .title("title")
                .translatedTitle("translatedTitle")
                .userDefinable1("userDefinable1")
                .userDefinable2("userDefinable2")
                .userDefinable3("userDefinable3")
                .userDefinable4("userDefinable4")
                .userDefinable5("userDefinable5")
                .url("url")
                .volumeNumber("volumeNumber")
                .publisherStandardNumber("publisherStandardNumber")
                .primaryDate("primaryDate")
                .accessDate("accessDate")
                .build()
            assertSpecifiedValues(record)
        }
    }
})

@Suppress("LongMethod")
private fun Suite.assertDefaultValues(record: RisRecord) {
    mapOf(
        "type" to record.type,
        "abstr" to record.abstr,
        "authorAddress" to record.authorAddress,
        "accessionNumber" to record.accessionNumber,
        "archivesLocation" to record.archivesLocation,
        "bt" to record.bt,
        "custom1" to record.custom1,
        "custom2" to record.custom2,
        "custom3" to record.custom3,
        "custom4" to record.custom4,
        "custom5" to record.custom5,
        "custom6" to record.custom6,
        "custom7" to record.custom7,
        "custom8" to record.custom8,
        "caption" to record.caption,
        "callNumber" to record.callNumber,
        "cp" to record.cp,
        "unpublishedReferenceTitle" to record.unpublishedReferenceTitle,
        "placePublished" to record.placePublished,
        "date" to record.date,
        "databaseName" to record.databaseName,
        "doi" to record.doi,
        "databaseProvider" to record.databaseProvider,
        "editor" to record.editor,
        "endPage" to record.endPage,
        "edition" to record.edition,
        "referenceId" to record.referenceId,
        "issue" to record.issue,
        "periodicalNameUserAbbrevation" to record.periodicalNameUserAbbrevation,
        "alternativeTitle" to record.alternativeTitle,
        "periodicalNameStandardAbbrevation" to record.periodicalNameStandardAbbrevation,
        "periodicalNameFullFormatJF" to record.periodicalNameFullFormatJF,
        "periodicalNameFullFormatJO" to record.periodicalNameFullFormatJO,
        "language" to record.language,
        "label" to record.label,
        "websiteLink" to record.websiteLink,
        "number" to record.number,
        "miscellaneous2" to record.miscellaneous2,
        "typeOfWork" to record.typeOfWork,
        "notes" to record.notes,
        "abstr2" to record.abstr2,
        "numberOfVolumes" to record.numberOfVolumes,
        "originalPublication" to record.originalPublication,
        "publisher" to record.publisher,
        "publishingPlace" to record.publishingPlace,
        "publicationYear" to record.publicationYear,
        "reviewedItem" to record.reviewedItem,
        "researchNotes" to record.researchNotes,
        "reprintEdition" to record.reprintEdition,
        "section" to record.section,
        "isbnIssn" to record.isbnIssn,
        "startPage" to record.startPage,
        "shortTitle" to record.shortTitle,
        "primaryTitle" to record.primaryTitle,
        "secondaryTitle" to record.secondaryTitle,
        "tertiaryTitle" to record.tertiaryTitle,
        "translatedAuthor" to record.translatedAuthor,
        "title" to record.title,
        "translatedTitle" to record.translatedTitle,
        "userDefinable1" to record.userDefinable1,
        "userDefinable2" to record.userDefinable2,
        "userDefinable3" to record.userDefinable3,
        "userDefinable4" to record.userDefinable4,
        "userDefinable5" to record.userDefinable5,
        "url" to record.url,
        "volumeNumber" to record.volumeNumber,
        "publisherStandardNumber" to record.publisherStandardNumber,
        "primaryDate" to record.primaryDate,
        "accessDate" to record.accessDate
    ).forEach { (name, value) ->
        it("$name should be null") {
            value.shouldBeNull()
        }
    }

    mapOf(
        "firstAuthors" to record.firstAuthors,
        "secondaryAuthors" to record.secondaryAuthors,
        "tertiaryAuthors" to record.tertiaryAuthors,
        "subsidiaryAuthors" to record.subsidiaryAuthors,
        "authors" to record.authors,
        "keywords" to record.keywords,
        "pdfLinks" to record.pdfLinks,
        "fullTextLinks" to record.fullTextLinks,
        "relatedRecords" to record.relatedRecords,
        "images" to record.images
    ).forEach { (name, list) ->
        it("$name should be an empty list") {
            list.shouldBeEmpty()
        }
    }
}

@Suppress("LongMethod")
private fun Suite.assertSpecifiedValues(record: RisRecord) {
    mapOf(
        RisType.ABST.toString() to record.type,
        "abstr" to record.abstr,
        "authorAddress" to record.authorAddress,
        "accessionNumber" to record.accessionNumber,
        "archivesLocation" to record.archivesLocation,
        "bt" to record.bt,
        "custom1" to record.custom1,
        "custom2" to record.custom2,
        "custom3" to record.custom3,
        "custom4" to record.custom4,
        "custom5" to record.custom5,
        "custom6" to record.custom6,
        "custom7" to record.custom7,
        "custom8" to record.custom8,
        "caption" to record.caption,
        "callNumber" to record.callNumber,
        "cp" to record.cp,
        "unpublishedReferenceTitle" to record.unpublishedReferenceTitle,
        "placePublished" to record.placePublished,
        "date" to record.date,
        "databaseName" to record.databaseName,
        "doi" to record.doi,
        "databaseProvider" to record.databaseProvider,
        "editor" to record.editor,
        "endPage" to record.endPage,
        "edition" to record.edition,
        "referenceId" to record.referenceId,
        "issue" to record.issue,
        "periodicalNameUserAbbrevation" to record.periodicalNameUserAbbrevation,
        "alternativeTitle" to record.alternativeTitle,
        "periodicalNameStandardAbbrevation" to record.periodicalNameStandardAbbrevation,
        "periodicalNameFullFormatJF" to record.periodicalNameFullFormatJF,
        "periodicalNameFullFormatJO" to record.periodicalNameFullFormatJO,
        "language" to record.language,
        "label" to record.label,
        "websiteLink" to record.websiteLink,
        "1234" to record.number,
        "miscellaneous2" to record.miscellaneous2,
        "typeOfWork" to record.typeOfWork,
        "notes" to record.notes,
        "abstr2" to record.abstr2,
        "numberOfVolumes" to record.numberOfVolumes,
        "originalPublication" to record.originalPublication,
        "publisher" to record.publisher,
        "publishingPlace" to record.publishingPlace,
        "publicationYear" to record.publicationYear,
        "reviewedItem" to record.reviewedItem,
        "researchNotes" to record.researchNotes,
        "reprintEdition" to record.reprintEdition,
        "section" to record.section,
        "isbnIssn" to record.isbnIssn,
        "startPage" to record.startPage,
        "shortTitle" to record.shortTitle,
        "primaryTitle" to record.primaryTitle,
        "secondaryTitle" to record.secondaryTitle,
        "tertiaryTitle" to record.tertiaryTitle,
        "translatedAuthor" to record.translatedAuthor,
        "title" to record.title,
        "translatedTitle" to record.translatedTitle,
        "userDefinable1" to record.userDefinable1,
        "userDefinable2" to record.userDefinable2,
        "userDefinable3" to record.userDefinable3,
        "userDefinable4" to record.userDefinable4,
        "userDefinable5" to record.userDefinable5,
        "url" to record.url,
        "volumeNumber" to record.volumeNumber,
        "publisherStandardNumber" to record.publisherStandardNumber,
        "primaryDate" to record.primaryDate,
        "accessDate" to record.accessDate
    ).forEach { (value, field) ->
        it("should be $value") {
            field.toString() shouldBeEqualTo value
        }
    }

    mapOf(
        "fA" to record.firstAuthors,
        "sA" to record.secondaryAuthors,
        "tA" to record.tertiaryAuthors,
        "suA" to record.subsidiaryAuthors,
        "a" to record.authors,
        "k" to record.keywords,
        "pL" to record.pdfLinks,
        "fTL" to record.fullTextLinks,
        "rR" to record.relatedRecords,
        "i" to record.images
    ).forEach { (name, list) ->
        it("list values should have value ${name}1 and ${name}2") {
            list shouldContainAll arrayOf("${name}1", "${name}2")
        }
    }
}
