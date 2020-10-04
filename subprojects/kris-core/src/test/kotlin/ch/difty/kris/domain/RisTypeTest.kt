package ch.difty.kris.domain

import org.junit.jupiter.api.Test

@Suppress("SpellCheckingInspection")
internal class RisTypeTest {

    @Test
    @Suppress("LongMethod")
    fun description() {
        RisType.values().map { it.description }.containsAll(
            listOf(
                "Abstract",
                "Audiovisual material",
                "Aggregated Database",
                "Ancient Text",
                "Art Work",
                "Bill",
                "Blog",
                "Whole book",
                "Case",
                "Book chapter",
                "Chart",
                "Classical Work",
                "Computer program",
                "Conference proceeding",
                "Conference paper",
                "Catalog",
                "Data file",
                "Online Database",
                "Dictionary",
                "Electronic Book",
                "Electronic Book Section",
                "Edited Book",
                "Electronic Article",
                "Web Page",
                "Encyclopedia",
                "Equation",
                "Figure",
                "Generic",
                "Government Document",
                "Grant",
                "Hearing",
                "Internet Communication",
                "In Press",
                "Journal (full)",
                "Journal",
                "Legal Rule or Regulation",
                "Manuscript",
                "Map",
                "Magazine article",
                "Motion picture",
                "Online Multimedia",
                "Music score",
                "Newspaper",
                "Pamphlet",
                "Patent",
                "Personal communication",
                "Report",
                "Serial publication",
                "Slide",
                "Sound recording",
                "Standard",
                "Statute",
                "Thesis/Dissertation",
                "Unenacted Bill",
                "Unpublished work",
                "Video recording"
            )
        )
    }
}
