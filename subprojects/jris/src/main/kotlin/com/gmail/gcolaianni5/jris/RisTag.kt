package com.gmail.gcolaianni5.jris

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
enum class RisTag(
    val description: String,
    val maxLength: Int? = null,
    val setInto: (RisRecord, Any?) -> Unit,
    val getFrom: (RisRecord) -> Any?,
    internal val kClass: KClass<*> = String::class,
    internal val requiredOrder: Int = 1000

) {
    TY(
        description = "Type of reference",
        kClass = RisType::class,
        requiredOrder = 0,
        setInto = { r, v -> r.type = v as RisType },
        getFrom = { r: RisRecord -> r.type }
    ), // must be first per record
    A1(
        description = "First Author",
        setInto = { r, v -> r.firstAuthors.add(v as String) },
        getFrom = { r: RisRecord -> r.firstAuthors }
    ),
    A2(
        description = "Secondary Author",
        kClass = List::class,
        setInto = { r, v -> r.secondaryAuthors.add(v as String) },
        getFrom = { r: RisRecord -> r.secondaryAuthors }
    ),
    A3(
        description = "Tertiary Author",
        kClass = List::class,
        setInto = { r, v -> r.tertiaryAuthors.add(v as String) },
        getFrom = { r: RisRecord -> r.tertiaryAuthors }
    ),
    A4(
        description = "Subsidiary Author",
        kClass = List::class,
        setInto = { r, v -> r.subsidiaryAuthors.add(v as String) },
        getFrom = { r: RisRecord -> r.subsidiaryAuthors }
    ),
    AB(
        description = "Abstract",
        setInto = { r, v -> r.abstr = v as String },
        getFrom = { r: RisRecord -> r.abstr }
    ),
    AD(
        description = "Author Address",
        setInto = { r, v -> r.authorAddress = v as String },
        getFrom = { r: RisRecord -> r.authorAddress }
    ),
    AN(
        description = "Accession Number",
        setInto = { r, v -> r.accessionNumber = v as String },
        getFrom = { r: RisRecord -> r.accessionNumber }
    ),
    AU(
        description = "Author",
        kClass = List::class,
        setInto = { r, v -> r.authors.add(v as String) },
        getFrom = { r: RisRecord -> r.authors }
    ),
    AV(
        description = "Location in Archives",
        setInto = { r, v -> r.archivesLocation = v as String? },
        getFrom = { r: RisRecord -> r.archivesLocation }
    ),
    // This field maps to T2 for all reference types except for Whole Book and Unpublished Work references.
    BT(
        description =
        "This field maps to T2 for all reference types except for Whole Book and Unpublished Work references.",
        setInto = { r, v -> r.bt = v as String? },
        getFrom = { r: RisRecord -> r.bt }
    ),
    C1(
        description = "Custom 1",
        setInto = { r, v -> r.custom1 = v as String? },
        getFrom = { r: RisRecord -> r.custom1 }
    ),
    C2(
        description = "Custom 2",
        setInto = { r, v -> r.custom2 = v as String? },
        getFrom = { r: RisRecord -> r.custom2 }
    ),
    C3(
        description = "Custom 3",
        setInto = { r, v -> r.custom3 = v as String? },
        getFrom = { r: RisRecord -> r.custom3 }
    ),
    C4(
        description = "Custom 4",
        setInto = { r, v -> r.custom4 = v as String? },
        getFrom = { r: RisRecord -> r.custom4 }
    ),
    C5(
        description = "Custom 5",
        setInto = { r, v -> r.custom5 = v as String? },
        getFrom = { r: RisRecord -> r.custom5 }
    ),
    C6(
        description = "Custom 6",
        setInto = { r, v -> r.custom6 = v as String? },
        getFrom = { r: RisRecord -> r.custom6 }
    ),
    C7(
        description = "Custom 7",
        setInto = { r, v -> r.custom7 = v as String? },
        getFrom = { r: RisRecord -> r.custom7 }
    ),
    C8(
        description = "Custom 8",
        setInto = { r, v -> r.custom8 = v as String? },
        getFrom = { r: RisRecord -> r.custom8 }
    ),
    CA(
        description = "Caption",
        setInto = { r, v -> r.caption = v as String? },
        getFrom = { r: RisRecord -> r.caption }
    ),
    CN(
        description = "Call Number",
        setInto = { r, v -> r.callNumber = v as String? },
        getFrom = { r: RisRecord -> r.callNumber }
    ),
    CP(
        description = "This field can contain alphanumeric characters.",
        setInto = { r, v -> r.cp = v as String? },
        getFrom = { r: RisRecord -> r.cp }
    ),
    CT(
        description = "Title of unpublished reference",
        setInto = { r, v -> r.unpublishedReferenceTitle = v as String? },
        getFrom = { r: RisRecord -> r.unpublishedReferenceTitle }
    ),
    CY(
        description = "Place Published",
        setInto = { r, v -> r.placePublished = v as String? },
        getFrom = { r: RisRecord -> r.placePublished }
    ),
    DA(
        description = "Date",
        setInto = { r, v -> r.date = v as String? },
        getFrom = { r: RisRecord -> r.date }
    ),
    DB(
        description = "Name of Database",
        setInto = { r, v -> r.databaseName = v as String? },
        getFrom = { r: RisRecord -> r.databaseName }
    ),
    DO(
        description = "DOI",
        setInto = { r, v -> r.doi = v as String? },
        getFrom = { r: RisRecord -> r.doi }
    ),
    DP(
        description = "Database Provider",
        setInto = { r, v -> r.databaseProvider = v as String? },
        getFrom = { r: RisRecord -> r.databaseProvider }
    ),
    ED(
        description = "Editor",
        setInto = { r, v -> r.editor = v as String? },
        getFrom = { r: RisRecord -> r.editor }
    ),
    EP(
        description = "End Page",
        setInto = { r, v -> r.endPage = v as String? },
        getFrom = { r: RisRecord -> r.endPage }
    ),
    ET(
        description = "Edition",
        setInto = { r, v -> r.edition = v as String? },
        getFrom = { r: RisRecord -> r.edition }
    ),
    ID(
        description = "Reference ID",
        setInto = { r, v -> r.referenceId = v as String? },
        getFrom = { r: RisRecord -> r.referenceId }
    ),
    IS(
        description = "Issue number",
        setInto = { r, v -> r.issue = v as String? },
        getFrom = { r: RisRecord -> r.issue }
    ),
    J1(
        description = "Periodical name: user abbreviation 1.",
        setInto = { r, v -> r.periodicalNameUserAbbrevation = v as String? },
        getFrom = { r: RisRecord -> r.periodicalNameUserAbbrevation },
        maxLength = 255
    ),
    // (this field is used for the abbreviated title of a book or journal name, the latter mapped to T2)
    J2(
        description = "Alternate Title",
        setInto = { r, v -> r.alternativeTitle = v as String? },
        getFrom = { r: RisRecord -> r.alternativeTitle }
    ),
    // This is the periodical in which the article was (or is to be, in the case of in-press references) published.
    JA(
        description = "Periodical name: standard abbreviation.",
        setInto = { r, v -> r.periodicalNameStandardAbbrevation = v as String? },
        getFrom = { r: RisRecord -> r.periodicalNameStandardAbbrevation },
        maxLength = 255
    ),
    JF(
        description = "Journal/Periodical name: full format.",
        setInto = { r, v -> r.periodicalNameFullFormatJF = v as String? },
        getFrom = { r: RisRecord -> r.periodicalNameFullFormatJF },
        maxLength = 255
    ),
    JO(
        description = "Journal/Periodical name: full format.",
        setInto = { r, v -> r.periodicalNameFullFormatJO = v as String? },
        getFrom = { r: RisRecord -> r.periodicalNameFullFormatJO },
        maxLength = 255
    ),
    KW(
        description = "Keywords",
        setInto = { r, v -> r.keywords.add(v as String) },
        getFrom = { r: RisRecord -> r.keywords },
        kClass = List::class
    ),
    // URL addresses can be entered individually, one per tag or
    // multiple addresses can be entered on one line using a semi-colon as a separator.
    L1(
        description = "Link to PDF.",
        setInto = { r, v -> r.pdfLinks.add(v as String) },
        getFrom = { r: RisRecord -> r.pdfLinks },
        kClass = List::class
    ),
    // URL addresses can be entered individually, one per tag or
    // multiple addresses can be entered on one line using a semi-colon as a separator.
    L2(
        description = "Link to Full-text.",
        setInto = { r, v -> r.fullTextLinks.add(v as String) },
        getFrom = { r: RisRecord -> r.fullTextLinks },
        kClass = List::class
    ),
    L3(
        description = "Related Records.",
        setInto = { r, v -> r.relatedRecords.add(v as String) },
        getFrom = { r: RisRecord -> r.relatedRecords },
        kClass = List::class
    ),
    L4(
        description = "Image(s).",
        setInto = { r, v -> r.images.add(v as String) },
        getFrom = { r: RisRecord -> r.images },
        kClass = List::class
    ),
    LA(
        description = "Language",
        setInto = { r, v -> r.language = v as String? },
        getFrom = { r: RisRecord -> r.language }
    ),
    LB(
        description = "Label",
        setInto = { r, v -> r.label = v as String? },
        getFrom = { r: RisRecord -> r.label }
    ),
    LK(
        description = "Website Link",
        setInto = { r, v -> r.websiteLink = v as String? },
        getFrom = { r: RisRecord -> r.websiteLink }
    ),
    M1(
        description = "Number",
        setInto = { r, v -> r.number = v as Long? },
        getFrom = { r: RisRecord -> r.number },
        kClass = Long::class
    ),
    M2(
        description = "Miscellaneous 2.",
        setInto = { r, v -> r.miscellaneous2 = v as String? },
        getFrom = { r: RisRecord -> r.miscellaneous2 }
    ),
    M3(
        description = "Type of Work",
        setInto = { r, v -> r.typeOfWork = v as String? },
        getFrom = { r: RisRecord -> r.typeOfWork }
    ),
    N1(
        description = "Notes",
        setInto = { r, v -> r.notes = v as String? },
        getFrom = { r: RisRecord -> r.notes }
    ),
    //  This is a free text field and can contain alphanumeric characters.
    //  There is no practical length limit to this field.
    N2(
        description = "Abstract.",
        setInto = { r, v -> r.abstr2 = v as String? },
        getFrom = { r: RisRecord -> r.abstr2 }
    ),
    NV(
        description = "Number of Volumes",
        setInto = { r, v -> r.numberOfVolumes = v as String? },
        getFrom = { r: RisRecord -> r.numberOfVolumes }
    ),
    OP(
        description = "Original Publication",
        setInto = { r, v -> r.originalPublication = v as String? },
        getFrom = { r: RisRecord -> r.originalPublication }
    ),
    PB(
        description = "Publisher",
        setInto = { r, v -> r.publisher = v as String? },
        getFrom = { r: RisRecord -> r.publisher }
    ),
    PP(
        description = "Publishing Place",
        setInto = { r, v -> r.publishingPlace = v as String? },
        getFrom = { r: RisRecord -> r.publishingPlace }
    ),
    PY(
        description = "Publication year (YYYY)",
        setInto = { r, v -> r.publicationYear = v as String? },
        getFrom = { r: RisRecord -> r.publicationYear }
    ),
    RI(
        description = "Reviewed Item",
        setInto = { r, v -> r.reviewedItem = v as String? },
        getFrom = { r: RisRecord -> r.reviewedItem }
    ),
    RN(
        description = "Research Notes",
        setInto = { r, v -> r.researchNotes = v as String? },
        getFrom = { r: RisRecord -> r.researchNotes }
    ),
    RP(
        description = "Reprint Edition",
        setInto = { r, v -> r.reprintEdition = v as String? },
        getFrom = { r: RisRecord -> r.reprintEdition }
    ),
    SE(
        description = "Section",
        setInto = { r, v -> r.section = v as String? },
        getFrom = { r: RisRecord -> r.section }
    ),
    SN(
        description = "ISBN/ISSN",
        setInto = { r, v -> r.isbnIssn = v as String? },
        getFrom = { r: RisRecord -> r.isbnIssn }
    ),
    SP(
        description = "Start Page",
        setInto = { r, v -> r.startPage = v as String? },
        getFrom = { r: RisRecord -> r.startPage }
    ),
    ST(
        description = "Short Title",
        setInto = { r, v -> r.shortTitle = v as String? },
        getFrom = { r: RisRecord -> r.shortTitle }
    ),
    T1(
        description = "Primary Title",
        setInto = { r, v -> r.primaryTitle = v as String? },
        getFrom = { r: RisRecord -> r.primaryTitle }
    ),
    T2(
        description = "Secondary Title (journal title, if applicable)",
        setInto = { r, v -> r.secondaryTitle = v as String? },
        getFrom = { r: RisRecord -> r.secondaryTitle }
    ),
    T3(
        description = "Tertiary Title",
        setInto = { r, v -> r.tertiaryTitle = v as String? },
        getFrom = { r: RisRecord -> r.tertiaryTitle }
    ),
    TA(
        description = "Translated Author",
        setInto = { r, v -> r.translatedAuthor = v as String? },
        getFrom = { r: RisRecord -> r.translatedAuthor }
    ),
    TI(
        description = "Title",
        setInto = { r, v -> r.title = v as String? },
        getFrom = { r: RisRecord -> r.title }
    ),
    TT(
        description = "Translated Title",
        setInto = { r, v -> r.translatedTitle = v as String? },
        getFrom = { r: RisRecord -> r.translatedTitle }
    ),
    U1(
        description = "User definable 1.",
        setInto = { r, v -> r.userDefinable1 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable1 }
    ),
    U2(
        description = "User definable 2.",
        setInto = { r, v -> r.userDefinable2 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable2 }
    ),
    U3(
        description = "User definable 3.",
        setInto = { r, v -> r.userDefinable3 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable3 }
    ),
    U4(
        description = "User definable 4.",
        setInto = { r, v -> r.userDefinable4 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable4 }
    ),
    U5(
        description = "User definable 5.",
        setInto = { r, v -> r.userDefinable5 = v as String? },
        getFrom = { r: RisRecord -> r.userDefinable5 }
    ),
    UR(
        description = "URL",
        setInto = { r, v -> r.url = v as String? },
        getFrom = { r: RisRecord -> r.url }
    ),
    VL(
        description = "Volume number",
        setInto = { r, v -> r.volumeNumber = v as String? },
        getFrom = { r: RisRecord -> r.volumeNumber }
    ),
    VO(
        description = "Published Standard number",
        setInto = { r, v -> r.publisherStandardNumber = v as String? },
        getFrom = { r: RisRecord -> r.publisherStandardNumber }
    ),
    Y1(
        description = "Primary Date",
        setInto = { r, v -> r.primaryDate = v as String? },
        getFrom = { r: RisRecord -> r.primaryDate }
    ),
    Y2(
        description = "Access Date",
        setInto = { r, v -> r.accessDate = v as String? },
        getFrom = { r: RisRecord -> r.accessDate }
    ),
    ER(
        description = "End of Reference",
        requiredOrder = Integer.MAX_VALUE,
        setInto = { _, _ -> Unit },
        getFrom = { "" }
    ), // must be last per record
    ;

    companion object {
        fun fromName(tagName: String) = values().firstOrNull { it.name == tagName }
    }
}
