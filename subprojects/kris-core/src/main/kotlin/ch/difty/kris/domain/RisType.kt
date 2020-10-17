package ch.difty.kris.domain

/**
 * Types of References - as used in [RisTag] `TY`.
 *
 * The class was composed from information available on
 * [Wikipedia](https://en.wikipedia.org/wiki/RIS_(file_format)).
 */
@Suppress("unused", "SpellCheckingInspection")
public enum class RisType(public val description: String) {
    /** Abstract */
    ABST("Abstract"),

    /** Audiovisual material */
    ADVS("Audiovisual material"),

    /** Aggregated Database */
    AGGR("Aggregated Database"),

    /** Ancient Text */
    ANCIENT("Ancient Text"),

    /** Art Work */
    ART("Art Work"),

    /** Bill */
    BILL("Bill"),

    /** Blog */
    BLOG("Blog"),

    /** Whole book */
    BOOK("Whole book"),

    /** Case */
    CASE("Case"),

    /** Book chapter */
    CHAP("Book chapter"),

    /** Chart */
    CHART("Chart"),

    /** Classical Work */
    CLSWK("Classical Work"),

    /** Computer program */
    COMP("Computer program"),

    /** Conference proceeding */
    CONF("Conference proceeding"),

    /** Conference paper */
    CPAPER("Conference paper"),

    /** Catalog */
    CTLG("Catalog"),

    /** Data file */
    DATA("Data file"),

    /** Online Database */
    DBASE("Online Database"),

    /** Dictionary */
    DICT("Dictionary"),

    /** Electronic Book */
    EBOOK("Electronic Book"),

    /** Electronic Book Section */
    ECHAP("Electronic Book Section"),

    /** Edited Book */
    EDBOOK("Edited Book"),

    /** Electronic Article */
    EJOUR("Electronic Article"),

    /** Web Page */
    ELEC("Web Page"),

    /** Encyclopedia */
    ENCYC("Encyclopedia"),

    /** Equation */
    EQUA("Equation"),

    /** Figure */
    FIGURE("Figure"),

    /** Generic */
    GEN("Generic"),

    /** Government Document */
    GOVDOC("Government Document"),

    /** Grant */
    GRANT("Grant"),

    /** Hearing */
    HEAR("Hearing"),

    /** Internet Communication */
    ICOMM("Internet Communication"),

    /** In Press */
    INPR("In Press"),

    /** Journal (full) */
    JFULL("Journal (full)"),

    /** Journal */
    JOUR("Journal"),

    /** Legal Rule or Regulation */
    LEGAL("Legal Rule or Regulation"),

    /** Manuscript */
    MANSCPT("Manuscript"),

    /** Map */
    MAP("Map"),

    /** Magazine article */
    MGZN("Magazine article"),

    /** Motion picture */
    MPCT("Motion picture"),

    /** Online Multimedia */
    MULTI("Online Multimedia"),

    /** Music score */
    MUSIC("Music score"),

    /** Newspaper */
    NEWS("Newspaper"),

    /** Pamphlet */
    PAMP("Pamphlet"),

    /** Patent */
    PAT("Patent"),

    /** Personal communication */
    PCOMM("Personal communication"),

    /** Report */
    RPRT("Report"),

    /** Serial publication */
    SER("Serial publication"),

    /** Slide */
    SLIDE("Slide"),

    /** Sound recording */
    SOUND("Sound recording"),

    /** Standard */
    STAND("Standard"),

    /** Statute */
    STAT("Statute"),

    /** Thesis/Dissertation */
    THES("Thesis/Dissertation"),

    /** Unenacted Bill */
    UNBILL("Unenacted Bill"),

    /** Unpublished work */
    UNPB("Unpublished work"),

    /** Video recording */
    VIDEO("Video recording")
}
