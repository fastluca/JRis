package ch.difty.kris.domain

/**
 * A single RIS record. It contains all the allowed tag from RIS format.
 *
 * @author Gianluca Colaianni -- g.colaianni5@gmail.com
 * @version 1.0
 * @since 22 apr 2017
 */
@Suppress("ParameterListWrapping", "SpellCheckingInspection", "TooManyFunctions")
public data class RisRecord(

    /** TY */
    public var type: RisType? = null,

    /** A1 */
    public val firstAuthors: MutableList<String> = mutableListOf(),

    /** A2 */
    public val secondaryAuthors: MutableList<String> = mutableListOf(),

    /** A3 */
    public val tertiaryAuthors: MutableList<String> = mutableListOf(),

    /** A4 */
    public val subsidiaryAuthors: MutableList<String> = mutableListOf(),

    /** AU */
    public val authors: MutableList<String> = mutableListOf(),

    /** AB */
    public var abstr: String? = null,

    /** AD */
    public var authorAddress: String? = null,

    /** AN */
    public var accessionNumber: String? = null,

    /** AV */
    public var archivesLocation: String? = null,

    /** BT */
    public var bt: String? = null,

    /** C1 */
    public var custom1: String? = null,

    /** C2 */
    public var custom2: String? = null,

    /** C3 */
    public var custom3: String? = null,

    /** C4 */
    public var custom4: String? = null,

    /** C5 */
    public var custom5: String? = null,

    /** C6 */
    public var custom6: String? = null,

    /** C7 */
    public var custom7: String? = null,

    /** C8 */
    public var custom8: String? = null,

    /** CA */
    public var caption: String? = null,

    /** CN */
    public var callNumber: String? = null,

    /** CP */
    public var cp: String? = null,

    /** CT */
    public var unpublishedReferenceTitle: String? = null,

    /** CY */
    public var placePublished: String? = null,

    /** DA */
    public var date: String? = null,

    /** DB */
    public var databaseName: String? = null,

    /** DO */
    public var doi: String? = null,

    /** DP */
    public var databaseProvider: String? = null,

    /** ED */
    public var editor: String? = null,

    /** EP */
    public var endPage: String? = null,

    /** ED */
    public var edition: String? = null,

    /** ID */
    public var referenceId: String? = null,

    /** IS */
    public var issue: String? = null,

    /**
     * J1. Max 255 characters.
     */
    public var periodicalNameUserAbbrevation: String? = null,

    /**
     * J2. This field is used for the abbreviated title of a book or journal name, the latter mapped to T2.
     */
    public var alternativeTitle: String? = null,

    /**
     * JA. This is the periodical in which the article was (or is to be, in the case of in-press references) published.
     *     This is an alphanumeric field of up to 255 characters.
     */
    public var periodicalNameStandardAbbrevation: String? = null,

    /**
     * JF. Journal/Periodical name: full format. This is an alphanumeric field of up to 255 characters.
     */
    public var periodicalNameFullFormatJF: String? = null,

    /**
     * JO. Journal/Periodical name: full format. This is an alphanumeric field of up to 255 characters.
     */
    public var periodicalNameFullFormatJO: String? = null,

    /**
     * KW
     */
    public val keywords: MutableList<String> = mutableListOf(),

    /**
     * L1. There is no practical limit to the length of this field. URL addresses can be entered individually,
     *     one per tag or multiple addresses can be entered on one line using a semi-colon as a separator.
     */
    public val pdfLinks: MutableList<String> = mutableListOf(),

    /**
     * L2. Link to Full-text. There is no practical limit to the length of this field.
     *     URL addresses can be entered individually, one per tag or multiple addresses
     *     can be entered on one line using a semi-colon as a separator.
     */
    public val fullTextLinks: MutableList<String> = mutableListOf(),

    /**
     * L3. Related Records. There is no practical limit to the length of this field.
     */
    public val relatedRecords: MutableList<String> = mutableListOf(),

    /**
     * L4. Image(s). There is no practical limit to the length of this field.
     */
    public val images: MutableList<String> = mutableListOf(),

    /** LA */
    public var language: String? = null,

    /** LB */
    public var label: String? = null,

    /** LK */
    public var websiteLink: String? = null,

    /** M1 */
    public var number: Long? = null,

    /**
     * M2. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    public var miscellaneous2: String? = null,

    /** M3 */
    public var typeOfWork: String? = null,

    /** N1 */
    public var notes: String? = null,

    /**
     * N2. Abstract. This is a free text field and can contain alphanumeric characters.
     *     There is no practical length limit to this field.
     */
    public var abstr2: String? = null,

    /**
     * NV.
     */
    public var numberOfVolumes: String? = null,

    /** OP */
    public var originalPublication: String? = null,

    /** PB */
    public var publisher: String? = null,

    /** PP */
    public var publishingPlace: String? = null,

    /**
     * PY. Publication year (YYYY/MM/DD).
     */
    public var publicationYear: String? = null,

    /** RI */
    public var reviewedItem: String? = null,

    /** RN */
    public var researchNotes: String? = null,

    /** RP */
    public var reprintEdition: String? = null,

    /** SE */
    public var section: String? = null,

    /** SN */
    public var isbnIssn: String? = null,

    /** SP - String? instead of Int? for DistillerSr format */
    public var startPage: String? = null,

    /** ST */
    public var shortTitle: String? = null,

    /** T1 */
    public var primaryTitle: String? = null,

    /** T2 */
    public var secondaryTitle: String? = null,

    /** T3 */
    public var tertiaryTitle: String? = null,

    /** TA */
    public var translatedAuthor: String? = null,

    /** TI */
    public var title: String? = null,

    /** TT */
    public var translatedTitle: String? = null,

    /**
     * U1. User definable 1. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    public var userDefinable1: String? = null,

    /**
     * U. User definable 2. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    public var userDefinable2: String? = null,

    /**
     * U3. User definable 3. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    public var userDefinable3: String? = null,

    /**
     * U4. User definable 4. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    public var userDefinable4: String? = null,

    /**
     * U5. User definable 5. This is an alphanumeric field and there is no practical limit to the length of this field.
     */
    public var userDefinable5: String? = null,

    /** UR */
    public var url: String? = null,

    /** VL */
    public var volumeNumber: String? = null,

    /** VO */
    public var publisherStandardNumber: String? = null,

    /** Y1 */
    public var primaryDate: String? = null,

    /** Y2 */
    public var accessDate: String? = null,

    ) {
    // This whole bloating builder is only necessary for Java inter-operability.
    public class Builder {
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

        public fun type(type: RisType?): Builder = apply { this.type = type }
        public fun firstAuthors(firstAuthors: List<String>): Builder =
            apply { this.firstAuthors.clear(); this.firstAuthors.addAll(firstAuthors) }

        public fun secondaryAuthors(secondaryAuthors: List<String>): Builder =
            apply { this.secondaryAuthors.clear(); this.secondaryAuthors.addAll(secondaryAuthors) }

        public fun tertiaryAuthors(tertiaryAuthors: List<String>): Builder =
            apply { this.tertiaryAuthors.clear(); this.tertiaryAuthors.addAll(tertiaryAuthors) }

        public fun subsidiaryAuthors(subsidiaryAuthors: List<String>): Builder =
            apply { this.subsidiaryAuthors.clear(); this.subsidiaryAuthors.addAll(subsidiaryAuthors) }

        public fun authors(authors: List<String>): Builder = apply {
            this.authors.clear(); this.authors.addAll(authors)
        }

        public fun abstr(abstr: String?): Builder = apply { this.abstr = abstr }
        public fun authorAddress(authorAddress: String?): Builder = apply { this.authorAddress = authorAddress }
        public fun accessionNumber(accessionNumber: String?): Builder = apply { this.accessionNumber = accessionNumber }
        public fun archivesLocation(archivesLocation: String?): Builder = apply {
            this.archivesLocation = archivesLocation
        }

        public fun bt(bt: String?): Builder = apply { this.bt = bt }
        public fun custom1(custom1: String?): Builder = apply { this.custom1 = custom1 }
        public fun custom2(custom2: String?): Builder = apply { this.custom2 = custom2 }
        public fun custom3(custom3: String?): Builder = apply { this.custom3 = custom3 }
        public fun custom4(custom4: String?): Builder = apply { this.custom4 = custom4 }
        public fun custom5(custom5: String?): Builder = apply { this.custom5 = custom5 }
        public fun custom6(custom6: String?): Builder = apply { this.custom6 = custom6 }
        public fun custom7(custom7: String?): Builder = apply { this.custom7 = custom7 }
        public fun custom8(custom8: String?): Builder = apply { this.custom8 = custom8 }
        public fun caption(caption: String?): Builder = apply { this.caption = caption }
        public fun callNumber(callNumber: String?): Builder = apply { this.callNumber = callNumber }
        public fun cp(cp: String?): Builder = apply { this.cp = cp }
        public fun unpublishedReferenceTitle(unpublishedReferenceTitle: String?): Builder =
            apply { this.unpublishedReferenceTitle = unpublishedReferenceTitle }

        public fun placePublished(placePublished: String?): Builder = apply { this.placePublished = placePublished }
        public fun date(date: String?): Builder = apply { this.date = date }
        public fun databaseName(databaseName: String?): Builder = apply { this.databaseName = databaseName }
        public fun doi(doi: String?): Builder = apply { this.doi = doi }

        public fun databaseProvider(databaseProvider: String?): Builder = apply {
            this.databaseProvider = databaseProvider
        }

        public fun editor(editor: String?): Builder = apply { this.editor = editor }
        public fun endPage(endPage: String?): Builder = apply { this.endPage = endPage }
        public fun edition(edition: String?): Builder = apply { this.edition = edition }
        public fun referenceId(referenceId: String?): Builder = apply { this.referenceId = referenceId }
        public fun issue(issue: String?): Builder = apply { this.issue = issue }
        public fun periodicalNameUserAbbrevation(periodicalNameUserAbbrevation: String?): Builder =
            apply { this.periodicalNameUserAbbrevation = periodicalNameUserAbbrevation }

        public fun alternativeTitle(alternativeTitle: String?): Builder = apply {
            this.alternativeTitle = alternativeTitle
        }

        public fun periodicalNameStandardAbbrevation(periodicalNameStandardAbbrevation: String?): Builder =
            apply { this.periodicalNameStandardAbbrevation = periodicalNameStandardAbbrevation }

        public fun periodicalNameFullFormatJF(periodicalNameFullFormatJF: String?): Builder =
            apply { this.periodicalNameFullFormatJF = periodicalNameFullFormatJF }

        public fun periodicalNameFullFormatJO(periodicalNameFullFormatJO: String?): Builder =
            apply { this.periodicalNameFullFormatJO = periodicalNameFullFormatJO }

        public fun keywords(keywords: List<String>): Builder = apply {
            this.keywords.clear(); this.keywords.addAll(keywords)
        }

        public fun pdfLinks(pdfLinks: List<String>): Builder = apply {
            this.pdfLinks.clear(); this.pdfLinks.addAll(pdfLinks)
        }

        public fun fullTextLinks(fullTextLinks: List<String>): Builder =
            apply { this.fullTextLinks.clear(); this.fullTextLinks.addAll(fullTextLinks) }

        public fun relatedRecords(relatedRecords: List<String>): Builder =
            apply { this.relatedRecords.clear(); this.relatedRecords.addAll(relatedRecords) }

        public fun images(images: List<String>): Builder = apply { this.images.clear(); this.images.addAll(images) }
        public fun language(language: String?): Builder = apply { this.language = language }
        public fun label(label: String?): Builder = apply { this.label = label }
        public fun websiteLink(websiteLink: String?): Builder = apply { this.websiteLink = websiteLink }
        public fun number(number: Long?): Builder = apply { this.number = number }
        public fun miscellaneous2(miscellaneous2: String?): Builder = apply { this.miscellaneous2 = miscellaneous2 }
        public fun typeOfWork(typeOfWork: String?): Builder = apply { this.typeOfWork = typeOfWork }
        public fun notes(notes: String?): Builder = apply { this.notes = notes }
        public fun abstr2(abstr2: String?): Builder = apply { this.abstr2 = abstr2 }
        public fun numberOfVolumes(numberOfVolumes: String?): Builder = apply { this.numberOfVolumes = numberOfVolumes }
        public fun originalPublication(originalPublication: String?): Builder =
            apply { this.originalPublication = originalPublication }

        public fun publisher(publisher: String?): Builder = apply { this.publisher = publisher }
        public fun publishingPlace(publishingPlace: String?): Builder = apply { this.publishingPlace = publishingPlace }
        public fun publicationYear(publicationYear: String?): Builder = apply { this.publicationYear = publicationYear }
        public fun reviewedItem(reviewedItem: String?): Builder = apply { this.reviewedItem = reviewedItem }
        public fun researchNotes(researchNotes: String?): Builder = apply { this.researchNotes = researchNotes }
        public fun reprintEdition(reprintEdition: String?): Builder = apply { this.reprintEdition = reprintEdition }
        public fun section(section: String?): Builder = apply { this.section = section }
        public fun isbnIssn(isbnIssn: String?): Builder = apply { this.isbnIssn = isbnIssn }
        public fun startPage(startPage: String?): Builder = apply { this.startPage = startPage }
        public fun shortTitle(shortTitle: String?): Builder = apply { this.shortTitle = shortTitle }
        public fun primaryTitle(primaryTitle: String?): Builder = apply { this.primaryTitle = primaryTitle }
        public fun secondaryTitle(secondaryTitle: String?): Builder = apply { this.secondaryTitle = secondaryTitle }
        public fun tertiaryTitle(tertiaryTitle: String?): Builder = apply { this.tertiaryTitle = tertiaryTitle }
        public fun translatedAuthor(translatedAuthor: String?): Builder = apply {
            this.translatedAuthor = translatedAuthor
        }

        public fun title(title: String?): Builder = apply { this.title = title }
        public fun translatedTitle(translatedTitle: String?): Builder = apply { this.translatedTitle = translatedTitle }
        public fun userDefinable1(userDefinable1: String?): Builder = apply { this.userDefinable1 = userDefinable1 }
        public fun userDefinable2(userDefinable2: String?): Builder = apply { this.userDefinable2 = userDefinable2 }
        public fun userDefinable3(userDefinable3: String?): Builder = apply { this.userDefinable3 = userDefinable3 }
        public fun userDefinable4(userDefinable4: String?): Builder = apply { this.userDefinable4 = userDefinable4 }
        public fun userDefinable5(userDefinable5: String?): Builder = apply { this.userDefinable5 = userDefinable5 }
        public fun url(url: String?): Builder = apply { this.url = url }
        public fun volumeNumber(volumeNumber: String?): Builder = apply { this.volumeNumber = volumeNumber }
        public fun publisherStandardNumber(publisherStandardNumber: String?): Builder =
            apply { this.publisherStandardNumber = publisherStandardNumber }

        public fun primaryDate(primaryDate: String?): Builder = apply { this.primaryDate = primaryDate }
        public fun accessDate(accessDate: String?): Builder = apply { this.accessDate = accessDate }

        @Suppress("LongMethod")
        public fun build(): RisRecord = RisRecord(
            type,
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
