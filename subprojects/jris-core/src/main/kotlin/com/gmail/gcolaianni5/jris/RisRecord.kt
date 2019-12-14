package com.gmail.gcolaianni5.jris

/**
 * A single RIS record. It contains all the allowed tag from RIS format.
 *
 * @author Gianluca Colaianni -- g.colaianni5@gmail.com
 * @version 1.0
 * @since 22 apr 2017
 */
@Suppress("ParameterListWrapping", "SpellCheckingInspection", "TooManyFunctions")
data class RisRecord(

    /** TY */
    var type: RisType? = null,

    /** A1 */
    val firstAuthors: MutableList<String> = mutableListOf(),

    /** A2 */
    val secondaryAuthors: MutableList<String> = mutableListOf(),

    /** A3 */
    val tertiaryAuthors: MutableList<String> = mutableListOf(),

    /** A4 */
    val subsidiaryAuthors: MutableList<String> = mutableListOf(),

    /** AU */
    val authors: MutableList<String> = mutableListOf(),

    /** AB */
    var abstr: String? = null,

    /** AD */
    var authorAddress: String? = null,

    /** AN */
    var accessionNumber: String? = null,

    /** AV */
    var archivesLocation: String? = null,

    /** BT */
    var bt: String? = null,

    /** C1 */
    var custom1: String? = null,

    /** C2 */
    var custom2: String? = null,

    /** C3 */
    var custom3: String? = null,

    /** C4 */
    var custom4: String? = null,

    /** C5 */
    var custom5: String? = null,

    /** C6 */
    var custom6: String? = null,

    /** C7 */
    var custom7: String? = null,

    /** C8 */
    var custom8: String? = null,

    /** CA */
    var caption: String? = null,

    /** CN */
    var callNumber: String? = null,

    /** CP */
    var cp: String? = null,

    /** CT */
    var unpublishedReferenceTitle: String? = null,

    /** CY */
    var placePublished: String? = null,

    /** DA */
    var date: String? = null,

    /** DB */
    var databaseName: String? = null,

    /** DO */
    var doi: String? = null,

    /** DP */
    var databaseProvider: String? = null,

    /** ED */
    var editor: String? = null,

    /** EP */
    var endPage: String? = null,

    /** ED */
    var edition: String? = null,

    /** ID */
    var referenceId: String? = null,

    /** IS */
    var issue: String? = null,

    /**
     * J1. Max 255 characters.
     */
    var periodicalNameUserAbbrevation: String? = null,

    /**
     * J2. This field is used for the abbreviated title of a book or journal name, the latter mapped to T2.
     */
    var alternativeTitle: String? = null,

    /**
     * JA. This is the periodical in which the article was (or is to be, in the case of in-press references) published.
     *     This is an alphanumeric field of up to 255 characters.
     */
    var periodicalNameStandardAbbrevation: String? = null,

    /**
     * JF. Journal/Periodical name: full format. This is an alphanumeric field of up to 255 characters.
     */
    var periodicalNameFullFormatJF: String? = null,

    /**
     * JO. Journal/Periodical name: full format. This is an alphanumeric field of up to 255 characters.
     */
    var periodicalNameFullFormatJO: String? = null,

    /**
     * KW
     */
    val keywords: MutableList<String> = mutableListOf(),

    /**
     * L1. There is no practical limit to the length of this field. URL addresses can be entered individually,
     *     one per tag or multiple addresses can be entered on one line using a semi-colon as a separator.
     */
    val pdfLinks: MutableList<String> = mutableListOf(),

    /**
     * L2. Link to Full-text. There is no practical limit to the length of this field.
     *     URL addresses can be entered individually, one per tag or multiple addresses
     *     can be entered on one line using a semi-colon as a separator.
     */
    val fullTextLinks: MutableList<String> = mutableListOf(),

    /**
     * L3. Related Records. There is no practical limit to the length of this field.
     */
    val relatedRecords: MutableList<String> = mutableListOf(),

    /**
     * L4. Image(s). There is no practical limit to the length of this field.
     */
    val images: MutableList<String> = mutableListOf(),

    /** LA */
    var language: String? = null,

    /** LB */
    var label: String? = null,

    /** LK */
    var websiteLink: String? = null,

    /** M1 */
    var number: Long? = null,

    /**
     * M2. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    var miscellaneous2: String? = null,

    /** M3 */
    var typeOfWork: String? = null,

    /** N1 */
    var notes: String? = null,

    /**
     * N2. Abstract. This is a free text field and can contain alphanumeric characters.
     *     There is no practical length limit to this field.
     */
    var abstr2: String? = null,

    /**
     * NV.
     */
    var numberOfVolumes: String? = null,

    /** OP */
    var originalPublication: String? = null,

    /** PB */
    var publisher: String? = null,

    /** PP */
    var publishingPlace: String? = null,

    /**
     * PY. Publication year (YYYY/MM/DD).
     */
    var publicationYear: String? = null,

    /** RI */
    var reviewedItem: String? = null,

    /** RN */
    var researchNotes: String? = null,

    /** RP */
    var reprintEdition: String? = null,

    /** SE */
    var section: String? = null,

    /** SN */
    var isbnIssn: String? = null,

    /** SP - String? instead of Int? for DistillerSr format */
    var startPage: String? = null,

    /** ST */
    var shortTitle: String? = null,

    /** T1 */
    var primaryTitle: String? = null,

    /** T2 */
    var secondaryTitle: String? = null,

    /** T3 */
    var tertiaryTitle: String? = null,

    /** TA */
    var translatedAuthor: String? = null,

    /** TI */
    var title: String? = null,

    /** TT */
    var translatedTitle: String? = null,

    /**
     * U1. User definable 1. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    var userDefinable1: String? = null,

    /**
     * U. User definable 2. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    var userDefinable2: String? = null,

    /**
     * U3. User definable 3. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    var userDefinable3: String? = null,

    /**
     * U4. User definable 4. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    var userDefinable4: String? = null,

    /**
     * U5. User definable 5. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    var userDefinable5: String? = null,

    /** UR */
    var url: String? = null,

    /** VL */
    var volumeNumber: String? = null,

    /** VO */
    var publisherStandardNumber: String? = null,

    /** Y1 */
    var primaryDate: String? = null,

    /** Y2 */
    var accessDate: String? = null

) {
    // This whole bloating builder is only necessary for Java inter-operability.
    class Builder {
        private var type: RisType? = null
        private val firstAuthors: MutableList<String> = mutableListOf()
        private val secondaryAuthors: MutableList<String> = mutableListOf()
        private val tertiaryAuthors: MutableList<String> = mutableListOf()
        private val subsidiaryAuthors: MutableList<String> = mutableListOf()
        private var authors: MutableList<String> = mutableListOf()
        private var abstr: String? = null
        private var authorAddress: String? = null
        private var accessionNumber: String? = null
        private var archivesLocation: String? = null
        private var bt: String? = null
        private var custom1: String? = null
        private var custom2: String? = null
        private var custom3: String? = null
        private var custom4: String? = null
        private var custom5: String? = null
        private var custom6: String? = null
        private var custom7: String? = null
        private var custom8: String? = null
        private var caption: String? = null
        private var callNumber: String? = null
        private var cp: String? = null
        private var unpublishedReferenceTitle: String? = null
        private var placePublished: String? = null
        private var date: String? = null
        private var databaseName: String? = null
        private var doi: String? = null
        private var databaseProvider: String? = null
        private var editor: String? = null
        private var endPage: String? = null
        private var edition: String? = null
        private var referenceId: String? = null
        private var issue: String? = null
        private var periodicalNameUserAbbrevation: String? = null
        private var alternativeTitle: String? = null
        private var periodicalNameStandardAbbrevation: String? = null
        private var periodicalNameFullFormatJF: String? = null
        private var periodicalNameFullFormatJO: String? = null
        private val keywords: MutableList<String> = mutableListOf()
        private val pdfLinks: MutableList<String> = mutableListOf()
        private val fullTextLinks: MutableList<String> = mutableListOf()
        private val relatedRecords: MutableList<String> = mutableListOf()
        private val images: MutableList<String> = mutableListOf()
        private var language: String? = null
        private var label: String? = null
        private var websiteLink: String? = null
        private var number: Long? = null
        private var miscellaneous2: String? = null
        private var typeOfWork: String? = null
        private var notes: String? = null
        private var abstr2: String? = null
        private var numberOfVolumes: String? = null
        private var originalPublication: String? = null
        private var publisher: String? = null
        private var publishingPlace: String? = null
        private var publicationYear: String? = null
        private var reviewedItem: String? = null
        private var researchNotes: String? = null
        private var reprintEdition: String? = null
        private var section: String? = null
        private var isbnIssn: String? = null
        private var startPage: String? = null
        private var shortTitle: String? = null
        private var primaryTitle: String? = null
        private var secondaryTitle: String? = null
        private var tertiaryTitle: String? = null
        private var translatedAuthor: String? = null
        private var title: String? = null
        private var translatedTitle: String? = null
        private var userDefinable1: String? = null
        private var userDefinable2: String? = null
        private var userDefinable3: String? = null
        private var userDefinable4: String? = null
        private var userDefinable5: String? = null
        private var url: String? = null
        private var volumeNumber: String? = null
        private var publisherStandardNumber: String? = null
        private var primaryDate: String? = null
        private var accessDate: String? = null

        fun type(type: RisType?) = apply { this.type = type }
        fun firstAuthors(firstAuthors: List<String>) =
            apply { this.firstAuthors.clear(); this.firstAuthors.addAll(firstAuthors) }

        fun secondaryAuthors(secondaryAuthors: List<String>) =
            apply { this.secondaryAuthors.clear(); this.secondaryAuthors.addAll(secondaryAuthors) }

        fun tertiaryAuthors(tertiaryAuthors: List<String>) =
            apply { this.tertiaryAuthors.clear(); this.tertiaryAuthors.addAll(tertiaryAuthors) }

        fun subsidiaryAuthors(subsidiaryAuthors: List<String>) =
            apply { this.subsidiaryAuthors.clear(); this.subsidiaryAuthors.addAll(subsidiaryAuthors) }

        fun authors(authors: List<String>) = apply { this.authors.clear(); this.authors.addAll(authors) }
        fun abstr(abstr: String?) = apply { this.abstr = abstr }
        fun authorAddress(authorAddress: String?) = apply { this.authorAddress = authorAddress }
        fun accessionNumber(accessionNumber: String?) = apply { this.accessionNumber = accessionNumber }
        fun archivesLocation(archivesLocation: String?) = apply { this.archivesLocation = archivesLocation }
        fun bt(bt: String?) = apply { this.bt = bt }
        fun custom1(custom1: String?) = apply { this.custom1 = custom1 }
        fun custom2(custom2: String?) = apply { this.custom2 = custom2 }
        fun custom3(custom3: String?) = apply { this.custom3 = custom3 }
        fun custom4(custom4: String?) = apply { this.custom4 = custom4 }
        fun custom5(custom5: String?) = apply { this.custom5 = custom5 }
        fun custom6(custom6: String?) = apply { this.custom6 = custom6 }
        fun custom7(custom7: String?) = apply { this.custom7 = custom7 }
        fun custom8(custom8: String?) = apply { this.custom8 = custom8 }
        fun caption(caption: String?) = apply { this.caption = caption }
        fun callNumber(callNumber: String?) = apply { this.callNumber = callNumber }
        fun cp(cp: String?) = apply { this.cp = cp }
        fun unpublishedReferenceTitle(unpublishedReferenceTitle: String?) =
            apply { this.unpublishedReferenceTitle = unpublishedReferenceTitle }

        fun placePublished(placePublished: String?) = apply { this.placePublished = placePublished }
        fun date(date: String?) = apply { this.date = date }
        fun databaseName(databaseName: String?) = apply { this.databaseName = databaseName }
        fun doi(doi: String?) = apply { this.doi = doi }
        fun databaseProvider(databaseProvider: String?) = apply { this.databaseProvider = databaseProvider }
        fun editor(editor: String?) = apply { this.editor = editor }
        fun endPage(endPage: String?) = apply { this.endPage = endPage }
        fun edition(edition: String?) = apply { this.edition = edition }
        fun referenceId(referenceId: String?) = apply { this.referenceId = referenceId }
        fun issue(issue: String?) = apply { this.issue = issue }
        fun periodicalNameUserAbbrevation(periodicalNameUserAbbrevation: String?) =
            apply { this.periodicalNameUserAbbrevation = periodicalNameUserAbbrevation }

        fun alternativeTitle(alternativeTitle: String?) = apply { this.alternativeTitle = alternativeTitle }
        fun periodicalNameStandardAbbrevation(periodicalNameStandardAbbrevation: String?) =
            apply { this.periodicalNameStandardAbbrevation = periodicalNameStandardAbbrevation }

        fun periodicalNameFullFormatJF(periodicalNameFullFormatJF: String?) =
            apply { this.periodicalNameFullFormatJF = periodicalNameFullFormatJF }

        fun periodicalNameFullFormatJO(periodicalNameFullFormatJO: String?) =
            apply { this.periodicalNameFullFormatJO = periodicalNameFullFormatJO }

        fun keywords(keywords: List<String>) = apply { this.keywords.clear(); this.keywords.addAll(keywords) }
        fun pdfLinks(pdfLinks: List<String>) = apply { this.pdfLinks.clear(); this.pdfLinks.addAll(pdfLinks) }
        fun fullTextLinks(fullTextLinks: List<String>) =
            apply { this.fullTextLinks.clear(); this.fullTextLinks.addAll(fullTextLinks) }

        fun relatedRecords(relatedRecords: List<String>) =
            apply { this.relatedRecords.clear(); this.relatedRecords.addAll(relatedRecords) }

        fun images(images: List<String>) = apply { this.images.clear(); this.images.addAll(images) }
        fun language(language: String?) = apply { this.language = language }
        fun label(label: String?) = apply { this.label = label }
        fun websiteLink(websiteLink: String?) = apply { this.websiteLink = websiteLink }
        fun number(number: Long?) = apply { this.number = number }
        fun miscellaneous2(miscellaneous2: String?) = apply { this.miscellaneous2 = miscellaneous2 }
        fun typeOfWork(typeOfWork: String?) = apply { this.typeOfWork = typeOfWork }
        fun notes(notes: String?) = apply { this.notes = notes }
        fun abstr2(abstr2: String?) = apply { this.abstr2 = abstr2 }
        fun numberOfVolumes(numberOfVolumes: String?) = apply { this.numberOfVolumes = numberOfVolumes }
        fun originalPublication(originalPublication: String?) =
            apply { this.originalPublication = originalPublication }

        fun publisher(publisher: String?) = apply { this.publisher = publisher }
        fun publishingPlace(publishingPlace: String?) = apply { this.publishingPlace = publishingPlace }
        fun publicationYear(publicationYear: String?) = apply { this.publicationYear = publicationYear }
        fun reviewedItem(reviewedItem: String?) = apply { this.reviewedItem = reviewedItem }
        fun researchNotes(researchNotes: String?) = apply { this.researchNotes = researchNotes }
        fun reprintEdition(reprintEdition: String?) = apply { this.reprintEdition = reprintEdition }
        fun section(section: String?) = apply { this.section = section }
        fun isbnIssn(isbnIssn: String?) = apply { this.isbnIssn = isbnIssn }
        fun startPage(startPage: String?) = apply { this.startPage = startPage }
        fun shortTitle(shortTitle: String?) = apply { this.shortTitle = shortTitle }
        fun primaryTitle(primaryTitle: String?) = apply { this.primaryTitle = primaryTitle }
        fun secondaryTitle(secondaryTitle: String?) = apply { this.secondaryTitle = secondaryTitle }
        fun tertiaryTitle(tertiaryTitle: String?) = apply { this.tertiaryTitle = tertiaryTitle }
        fun translatedAuthor(translatedAuthor: String?) = apply { this.translatedAuthor = translatedAuthor }
        fun title(title: String?) = apply { this.title = title }
        fun translatedTitle(translatedTitle: String?) = apply { this.translatedTitle = translatedTitle }
        fun userDefinable1(userDefinable1: String?) = apply { this.userDefinable1 = userDefinable1 }
        fun userDefinable2(userDefinable2: String?) = apply { this.userDefinable2 = userDefinable2 }
        fun userDefinable3(userDefinable3: String?) = apply { this.userDefinable3 = userDefinable3 }
        fun userDefinable4(userDefinable4: String?) = apply { this.userDefinable4 = userDefinable4 }
        fun userDefinable5(userDefinable5: String?) = apply { this.userDefinable5 = userDefinable5 }
        fun url(url: String?) = apply { this.url = url }
        fun volumeNumber(volumeNumber: String?) = apply { this.volumeNumber = volumeNumber }
        fun publisherStandardNumber(publisherStandardNumber: String?) =
            apply { this.publisherStandardNumber = publisherStandardNumber }

        fun primaryDate(primaryDate: String?) = apply { this.primaryDate = primaryDate }
        fun accessDate(accessDate: String?) = apply { this.accessDate = accessDate }
        @Suppress("LongMethod")
        fun build(): RisRecord = RisRecord(type,
            firstAuthors,
            secondaryAuthors,
            tertiaryAuthors,
            subsidiaryAuthors,
            authors,
            abstr,
            authorAddress,
            accessionNumber,
            archivesLocation,
            bt,
            custom1,
            custom2,
            custom3,
            custom4,
            custom5,
            custom6,
            custom7,
            custom8,
            caption,
            callNumber,
            cp,
            unpublishedReferenceTitle,
            placePublished,
            date,
            databaseName,
            doi,
            databaseProvider,
            editor,
            endPage,
            edition,
            referenceId,
            issue,
            periodicalNameUserAbbrevation,
            alternativeTitle,
            periodicalNameStandardAbbrevation,
            periodicalNameFullFormatJF,
            periodicalNameFullFormatJO,
            keywords,
            pdfLinks,
            fullTextLinks,
            relatedRecords,
            images,
            language,
            label,
            websiteLink,
            number,
            miscellaneous2,
            typeOfWork,
            notes,
            abstr2,
            numberOfVolumes,
            originalPublication,
            publisher,
            publishingPlace,
            publicationYear,
            reviewedItem,
            researchNotes,
            reprintEdition,
            section,
            isbnIssn,
            startPage,
            shortTitle,
            primaryTitle,
            secondaryTitle,
            tertiaryTitle,
            translatedAuthor,
            title,
            translatedTitle,
            userDefinable1,
            userDefinable2,
            userDefinable3,
            userDefinable4,
            userDefinable5,
            url,
            volumeNumber,
            publisherStandardNumber,
            primaryDate,
            accessDate
        )
    }
}
