package ch.difty.kris.domain

import kotlin.reflect.KClass

/**
 * The enum defines all available Tags that may be used in the RIS format.
 *
 * A [description] outlines the purpose of the tag, the [maxLength] property
 * defines the maximum number of characters (for Strings) - a `null` value indicates
 * either no restriction or a non-String class (as specified in [kClass]).
 * The [requiredOrder] property defines the first level of sorting when writing RIS
 * files. The specification does not enforce any particular order except for `TY`
 * to be the first one and `ER` to be the last tag in a RIS record. All other tags
 * share the same value, resulting in either being sorted by tag name alphabetically
 * or by a custom sort order specified by the caller.
 *
 * The class was composed from information available on
 * [Wikipedia](https://en.wikipedia.org/wiki/RIS_(file_format)).
 */
@Suppress("unused", "SpellCheckingInspection")
public enum class RisTag(
    internal val description: String,
    internal val maxLength: Int? = null,
    internal val setInto: (RisRecord, Any?) -> Unit,
    internal val getFrom: (RisRecord) -> Any?,
    internal val kClass: KClass<*> = String::class,
    internal val requiredOrder: Int = 1000,
) {
    /** Type of reference (must be the first tag) */
    TY(
        description = "Type of reference",
        kClass = RisType::class,
        requiredOrder = 0,
        setInto = { r, v -> r.type = v as RisType },
        getFrom = { r: RisRecord -> r.type }
    ),

    /** Primary Authors (each author on its own line preceded by the A1 tag) */
    A1(
        description = "First Author",
        setInto = { r, v -> r.firstAuthors.add(v as String) },
        getFrom = { r: RisRecord -> r.firstAuthors }
    ),

    /** Secondary Authors (each author on its own line preceded by the A2 tag) */
    A2(
        description = "Secondary Author",
        kClass = List::class,
        setInto = { r, v -> r.secondaryAuthors.add(v as String) },
        getFrom = { r: RisRecord -> r.secondaryAuthors }
    ),

    /** Tertiary Authors (each author on its own line preceded by the A3 tag) */
    A3(
        description = "Tertiary Author",
        kClass = List::class,
        setInto = { r, v -> r.tertiaryAuthors.add(v as String) },
        getFrom = { r: RisRecord -> r.tertiaryAuthors }
    ),

    /** Subsidiary Authors (each author on its own line preceded by the A4 tag) */
    A4(
        description = "Subsidiary Author",
        kClass = List::class,
        setInto = { r, v -> r.subsidiaryAuthors.add(v as String) },
        getFrom = { r: RisRecord -> r.subsidiaryAuthors }
    ),

    /** Abstract */
    AB(
        description = "Abstract",
        setInto = { r, v -> r.abstr = v as String },
        getFrom = { r: RisRecord -> r.abstr }
    ),

    /** Author Address */
    AD(
        description = "Author Address",
        setInto = { r, v -> r.authorAddress = v as String },
        getFrom = { r: RisRecord -> r.authorAddress }
    ),

    /** Accession Number */
    AN(
        description = "Accession Number",
        setInto = { r, v -> r.accessionNumber = v as String },
        getFrom = { r: RisRecord -> r.accessionNumber }
    ),

    /** Author (each author on its own line preceded by the AU tag) */
    AU(
        description = "Author",
        kClass = List::class,
        setInto = { r, v -> r.authors.add(v as String) },
        getFrom = { r: RisRecord -> r.authors }
    ),

    /** Location in Archives */
    AV(
        description = "Location in Archives",
        setInto = { r, v -> r.archivesLocation = v as String? },
        getFrom = { r: RisRecord -> r.archivesLocation }
    ),

    /**
     * This field maps to T2 for all reference types except for Whole Book and Unpublished Work references.
     * It can contain alphanumeric characters. There is no practical limit to the length of this field.
     */
    BT(
        description =
        "This field maps to T2 for all reference types except for Whole Book and Unpublished Work references.",
        setInto = { r, v -> r.bt = v as String? },
        getFrom = { r: RisRecord -> r.bt }
    ),

    /** Custom 1 */
    C1(
        description = "Custom 1",
        setInto = { r, v -> r.custom1 = v as String? },
        getFrom = { r: RisRecord -> r.custom1 }
    ),

    /** Custom 2 */
    C2(
        description = "Custom 2",
        setInto = { r, v -> r.custom2 = v as String? },
        getFrom = { r: RisRecord -> r.custom2 }
    ),

    /** Custom 3 */
    C3(
        description = "Custom 3",
        setInto = { r, v -> r.custom3 = v as String? },
        getFrom = { r: RisRecord -> r.custom3 }
    ),

    /** Custom 4 */
    C4(
        description = "Custom 4",
        setInto = { r, v -> r.custom4 = v as String? },
        getFrom = { r: RisRecord -> r.custom4 }
    ),

    /** Custom 5 */
    C5(
        description = "Custom 5",
        setInto = { r, v -> r.custom5 = v as String? },
        getFrom = { r: RisRecord -> r.custom5 }
    ),

    /** Custom 6 */
    C6(
        description = "Custom 6",
        setInto = { r, v -> r.custom6 = v as String? },
        getFrom = { r: RisRecord -> r.custom6 }
    ),

    /** Custom 7 */
    C7(
        description = "Custom 7",
        setInto = { r, v -> r.custom7 = v as String? },
        getFrom = { r: RisRecord -> r.custom7 }
    ),

    /** Custom 8 */
    C8(
        description = "Custom 8",
        setInto = { r, v -> r.custom8 = v as String? },
        getFrom = { r: RisRecord -> r.custom8 }
    ),

    /** Caption */
    CA(
        description = "Caption",
        setInto = { r, v -> r.caption = v as String? },
        getFrom = { r: RisRecord -> r.caption }
    ),

    /** Call Number */
    CN(
        description = "Call Number",
        setInto = { r, v -> r.callNumber = v as String? },
        getFrom = { r: RisRecord -> r.callNumber }
    ),

    /** This field can contain alphanumeric characters. There is no practical limit to the length of this field. */
    CP(
        description = "This field can contain alphanumeric characters.",
        setInto = { r, v -> r.cp = v as String? },
        getFrom = { r: RisRecord -> r.cp }
    ),

    /** Title of unpublished reference */
    CT(
        description = "Title of unpublished reference",
        setInto = { r, v -> r.unpublishedReferenceTitle = v as String? },
        getFrom = { r: RisRecord -> r.unpublishedReferenceTitle }
    ),

    /** Place Published */
    CY(
        description = "Place Published",
        setInto = { r, v -> r.placePublished = v as String? },
        getFrom = { r: RisRecord -> r.placePublished }
    ),

    /** Date */
    DA(
        description = "Date",
        setInto = { r, v -> r.date = v as String? },
        getFrom = { r: RisRecord -> r.date }
    ),

    /** Name of Database */
    DB(
        description = "Name of Database",
        setInto = { r, v -> r.databaseName = v as String? },
        getFrom = { r: RisRecord -> r.databaseName }
    ),

    /** DOI */
    DO(
        description = "DOI",
        setInto = { r, v -> r.doi = v as String? },
        getFrom = { r: RisRecord -> r.doi }
    ),

    /** Database Provider */
    DP(
        description = "Database Provider",
        setInto = { r, v -> r.databaseProvider = v as String? },
        getFrom = { r: RisRecord -> r.databaseProvider }
    ),

    /** Editor */
    ED(
        description = "Editor",
        setInto = { r, v -> r.editor = v as String? },
        getFrom = { r: RisRecord -> r.editor }
    ),

    /** End Page */
    EP(
        description = "End Page",
        setInto = { r, v -> r.endPage = v as String? },
        getFrom = { r: RisRecord -> r.endPage }
    ),

    /** Edition */
    ET(
        description = "Edition",
        setInto = { r, v -> r.edition = v as String? },
        getFrom = { r: RisRecord -> r.edition }
    ),

    /** Reference ID */
    ID(
        description = "Reference ID",
        setInto = { r, v -> r.referenceId = v as String? },
        getFrom = { r: RisRecord -> r.referenceId }
    ),

    /** Issue number */
    IS(
        description = "Issue number",
        setInto = { r, v -> r.issue = v as String? },
        getFrom = { r: RisRecord -> r.issue }
    ),

    /** Periodical name: user abbreviation 1. This is an alphanumeric field of up to 255 characters. */
    J1(
        description = "Periodical name: user abbreviation 1.",
        setInto = { r, v -> r.periodicalNameUserAbbrevation = v as String? },
        getFrom = { r: RisRecord -> r.periodicalNameUserAbbrevation },
        maxLength = 255
    ),

    /**
     * Alternate Title (this field is used for the abbreviated title of a book or journal name, the latter mapped to T2)
     **/
    J2(
        description = "Alternate Title",
        setInto = { r, v -> r.alternativeTitle = v as String? },
        getFrom = { r: RisRecord -> r.alternativeTitle }
    ),

    /**
     * Periodical name: standard abbreviation.
     * This is the periodical in which the article was (or is to be, in the case of in-press references) published.
     * This is an alphanumeric field of up to 255 characters.
     */
    JA(
        description = "Periodical name: standard abbreviation.",
        setInto = { r, v -> r.periodicalNameStandardAbbrevation = v as String? },
        getFrom = { r: RisRecord -> r.periodicalNameStandardAbbrevation },
        maxLength = 255
    ),

    /** Journal/Periodical name: full format. This is an alphanumeric field of up to 255 characters. */
    JF(
        description = "Journal/Periodical name: full format.",
        setInto = { r, v -> r.periodicalNameFullFormatJF = v as String? },
        getFrom = { r: RisRecord -> r.periodicalNameFullFormatJF },
        maxLength = 255
    ),

    /** Journal/Periodical name: full format. This is an alphanumeric field of up to 255 characters. */
    JO(
        description = "Journal/Periodical name: full format.",
        setInto = { r, v -> r.periodicalNameFullFormatJO = v as String? },
        getFrom = { r: RisRecord -> r.periodicalNameFullFormatJO },
        maxLength = 255
    ),

    /** Keywords (keywords should be entered each on its own line preceded by the tag) */
    KW(
        description = "Keywords",
        setInto = { r, v -> r.keywords.add(v as String) },
        getFrom = { r: RisRecord -> r.keywords },
        kClass = List::class
    ),

    /**
     * Link to PDF. There is no practical limit to the length of this field.
     * URL addresses can be entered individually, one per tag or multiple addresses
     * can be entered on one line using a semi-colon as a separator.
     */
    L1(
        description = "Link to PDF.",
        setInto = { r, v -> r.pdfLinks.add(v as String) },
        getFrom = { r: RisRecord -> r.pdfLinks },
        kClass = List::class
    ),

    /**
     * Link to Full-text. There is no practical limit to the length of this field.
     * URL addresses can be entered individually, one per tag or multiple addresses
     * can be entered on one line using a semi-colon as a separator.
     */
    L2(
        description = "Link to Full-text.",
        setInto = { r, v -> r.fullTextLinks.add(v as String) },
        getFrom = { r: RisRecord -> r.fullTextLinks },
        kClass = List::class
    ),

    /** Related Records. There is no practical limit to the length of this field. */
    L3(
        description = "Related Records.",
        setInto = { r, v -> r.relatedRecords.add(v as String) },
        getFrom = { r: RisRecord -> r.relatedRecords },
        kClass = List::class
    ),

    /** Image(s). There is no practical limit to the length of this field. */
    L4(
        description = "Image(s).",
        setInto = { r, v -> r.images.add(v as String) },
        getFrom = { r: RisRecord -> r.images },
        kClass = List::class
    ),

    /** Language */
    LA(
        description = "Language",
        setInto = { r, v -> r.language = v as String? },
        getFrom = { r: RisRecord -> r.language }
    ),

    /** Label */
    LB(
        description = "Label",
        setInto = { r, v -> r.label = v as String? },
        getFrom = { r: RisRecord -> r.label }
    ),

    /** Website Link */
    LK(
        description = "Website Link",
        setInto = { r, v -> r.websiteLink = v as String? },
        getFrom = { r: RisRecord -> r.websiteLink }
    ),

    /** Number */
    M1(
        description = "Number",
        setInto = { r, v -> r.number = v as Long? },
        getFrom = { r: RisRecord -> r.number },
        kClass = Long::class
    ),

    /** Miscellaneous 2. This is an alphanumeric field and there is no practical limit to the length of this field. */
    M2(
        description = "Miscellaneous 2.",
        setInto = { r, v -> r.miscellaneous2 = v as String? },
        getFrom = { r: RisRecord -> r.miscellaneous2 }
    ),

    /** Type of Work */
    M3(
        description = "Type of Work",
        setInto = { r, v -> r.typeOfWork = v as String? },
        getFrom = { r: RisRecord -> r.typeOfWork }
    ),

    /** Notes */
    N1(
        description = "Notes",
        setInto = { r, v -> r.notes = v as String? },
        getFrom = { r: RisRecord -> r.notes }
    ),

    /**
     * Abstract. This is a free text field and can contain alphanumeric characters.
     * There is no practical length limit to this field.
     */
    N2(
        description = "Abstract.",
        setInto = { r, v -> r.abstr2 = v as String? },
        getFrom = { r: RisRecord -> r.abstr2 }
    ),

    /** Number of Volumes */
    NV(
        description = "Number of Volumes",
        setInto = { r, v -> r.numberOfVolumes = v as String? },
        getFrom = { r: RisRecord -> r.numberOfVolumes }
    ),

    /** Original Publication */
    OP(
        description = "Original Publication",
        setInto = { r, v -> r.originalPublication = v as String? },
        getFrom = { r: RisRecord -> r.originalPublication }
    ),

    /** Publisher */
    PB(
        description = "Publisher",
        setInto = { r, v -> r.publisher = v as String? },
        getFrom = { r: RisRecord -> r.publisher }
    ),

    /** Publishing Place */
    PP(
        description = "Publishing Place",
        setInto = { r, v -> r.publishingPlace = v as String? },
        getFrom = { r: RisRecord -> r.publishingPlace }
    ),

    /** Publication year (YYYY) */
    PY(
        description = "Publication year (YYYY)",
        setInto = { r, v -> r.publicationYear = v as String? },
        getFrom = { r: RisRecord -> r.publicationYear }
    ),

    /** Reviewed Item */
    RI(
        description = "Reviewed Item",
        setInto = { r, v -> r.reviewedItem = v as String? },
        getFrom = { r: RisRecord -> r.reviewedItem }
    ),

    /** Research Notes */
    RN(
        description = "Research Notes",
        setInto = { r, v -> r.researchNotes = v as String? },
        getFrom = { r: RisRecord -> r.researchNotes }
    ),

    /** Reprint Edition */
    RP(
        description = "Reprint Edition",
        setInto = { r, v -> r.reprintEdition = v as String? },
        getFrom = { r: RisRecord -> r.reprintEdition }
    ),

    /** Section */
    SE(
        description = "Section",
        setInto = { r, v -> r.section = v as String? },
        getFrom = { r: RisRecord -> r.section }
    ),

    /** ISBN/ISSN */
    SN(
        description = "ISBN/ISSN",
        setInto = { r, v -> r.isbnIssn = v as String? },
        getFrom = { r: RisRecord -> r.isbnIssn }
    ),

    /** Start Page */
    SP(
        description = "Start Page",
        setInto = { r, v -> r.startPage = v as String? },
        getFrom = { r: RisRecord -> r.startPage }
    ),

    /** Short Title */
    ST(
        description = "Short Title",
        setInto = { r, v -> r.shortTitle = v as String? },
        getFrom = { r: RisRecord -> r.shortTitle }
    ),

    /** Primary Title */
    T1(
        description = "Primary Title",
        setInto = { r, v -> r.primaryTitle = v as String? },
        getFrom = { r: RisRecord -> r.primaryTitle }
    ),

    /** Secondary Title (journal title, if applicable) */
    T2(
        description = "Secondary Title (journal title, if applicable)",
        setInto = { r, v -> r.secondaryTitle = v as String? },
        getFrom = { r: RisRecord -> r.secondaryTitle }
    ),

    /** Tertiary Title */
    T3(
        description = "Tertiary Title",
        setInto = { r, v -> r.tertiaryTitle = v as String? },
        getFrom = { r: RisRecord -> r.tertiaryTitle }
    ),

    /** Translated Author */
    TA(
        description = "Translated Author",
        setInto = { r, v -> r.translatedAuthor = v as String? },
        getFrom = { r: RisRecord -> r.translatedAuthor }
    ),

    /** Title */
    TI(
        description = "Title",
        setInto = { r, v -> r.title = v as String? },
        getFrom = { r: RisRecord -> r.title }
    ),

    /** Translated Title */
    TT(
        description = "Translated Title",
        setInto = { r, v -> r.translatedTitle = v as String? },
        getFrom = { r: RisRecord -> r.translatedTitle }
    ),

    /** User definable 1. This is an alphanumeric field and there is no practical limit to the length of this field. */
    U1(
        description = "User definable 1.",
        setInto = { r, v -> r.userDefinable1 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable1 }
    ),

    /** User definable 2. This is an alphanumeric field and there is no practical limit to the length of this field. */
    U2(
        description = "User definable 2.",
        setInto = { r, v -> r.userDefinable2 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable2 }
    ),

    /** User definable 3. This is an alphanumeric field and there is no practical limit to the length of this field. */
    U3(
        description = "User definable 3.",
        setInto = { r, v -> r.userDefinable3 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable3 }
    ),

    /** User definable 4. This is an alphanumeric field and there is no practical limit to the length of this field. */
    U4(
        description = "User definable 4.",
        setInto = { r, v -> r.userDefinable4 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable4 }
    ),

    /** User definable 5. This is an alphanumeric field and there is no practical limit to the length of this field. */
    U5(
        description = "User definable 5.",
        setInto = { r, v -> r.userDefinable5 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable5 }
    ),

    /** URL */
    UR(
        description = "URL",
        setInto = { r, v -> r.url = v as String? },
        getFrom = { r: RisRecord -> r.url }
    ),

    /** Volume number */
    VL(
        description = "Volume number",
        setInto = { r, v -> r.volumeNumber = v as String? },
        getFrom = { r: RisRecord -> r.volumeNumber }
    ),

    /** Published Standard number */
    VO(
        description = "Published Standard number",
        setInto = { r, v -> r.publisherStandardNumber = v as String? },
        getFrom = { r: RisRecord -> r.publisherStandardNumber }
    ),

    /** Primary Date */
    Y1(
        description = "Primary Date",
        setInto = { r, v -> r.primaryDate = v as String? },
        getFrom = { r: RisRecord -> r.primaryDate }
    ),

    /** Access Date */
    Y2(
        description = "Access Date",
        setInto = { r, v -> r.accessDate = v as String? },
        getFrom = { r: RisRecord -> r.accessDate }
    ),

    /** End of Reference (must be empty and the last tag) */
    ER(
        description = "End of Reference",
        requiredOrder = Integer.MAX_VALUE,
        setInto = { _, _ -> },
        getFrom = { "" }
    ),
    ;

    public companion object {
        public fun fromName(tagName: String): RisTag? = values().firstOrNull { it.name == tagName }
    }
}
