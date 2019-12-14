package com.gmail.gcolaianni5.jris.example.java;

import static org.assertj.core.api.Assertions.assertThat;

import com.gmail.gcolaianni5.jris.RisRecord;
import com.gmail.gcolaianni5.jris.RisType;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

class RisRecordBuilderTest {

    private final RisRecord risRecord = new RisRecord.Builder()
        .type(RisType.BOOK)
        .firstAuthors(Lists.list("firstAuthor1", "firstAuthor2"))
        .secondaryAuthors(Lists.list("secondaryAuthor1", "secondaryAuthor2"))
        .tertiaryAuthors(Lists.list("tertiaryAuthor1", "tertiaryAuthor2"))
        .subsidiaryAuthors(Lists.list("subsidiaryAuthor1", "subsidiaryAuthor2"))
        .authors(Lists.list("author1", "author2"))
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
        .keywords(Lists.list("keyword2", "keyword2"))
        .pdfLinks(Lists.list("pdfLink1", "pdfLink2"))
        .fullTextLinks(Lists.list("fullTextLink1", "fullTextLink2"))
        .relatedRecords(Lists.list("relatedRecord1", "relatedRecord2"))
        .images(Lists.list("image1", "image2"))
        .language("language")
        .label("label")
        .websiteLink("websiteLink")
        .number(1L)
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
        .accessDate("2019-01-01")
        .build();

    @Test
    void canFillAllFieldsIntoDataClassAppropriately() {
        assertThat(risRecord.getType()).isEqualTo(RisType.BOOK);
        assertThat(risRecord.getFirstAuthors()).containsExactly("firstAuthor1", "firstAuthor2");
        assertThat(risRecord.getSecondaryAuthors()).containsExactly("secondaryAuthor1", "secondaryAuthor2");
        assertThat(risRecord.getTertiaryAuthors()).containsExactly("tertiaryAuthor1", "tertiaryAuthor2");
        assertThat(risRecord.getSubsidiaryAuthors()).containsExactly("subsidiaryAuthor1", "subsidiaryAuthor2");
        assertThat(risRecord.getAuthors()).containsExactly("author1", "author2");
        assertThat(risRecord.getAbstr()).isEqualTo("abstr");
        assertThat(risRecord.getAuthorAddress()).isEqualTo("authorAddress");
        assertThat(risRecord.getAccessionNumber()).isEqualTo("accessionNumber");
        assertThat(risRecord.getArchivesLocation()).isEqualTo("archivesLocation");
        assertThat(risRecord.getBt()).isEqualTo("bt");
        assertThat(risRecord.getCustom1()).isEqualTo("custom1");
        assertThat(risRecord.getCustom2()).isEqualTo("custom2");
        assertThat(risRecord.getCustom3()).isEqualTo("custom3");
        assertThat(risRecord.getCustom4()).isEqualTo("custom4");
        assertThat(risRecord.getCustom5()).isEqualTo("custom5");
        assertThat(risRecord.getCustom6()).isEqualTo("custom6");
        assertThat(risRecord.getCustom7()).isEqualTo("custom7");
        assertThat(risRecord.getCustom8()).isEqualTo("custom8");
        assertThat(risRecord.getCaption()).isEqualTo("caption");
        assertThat(risRecord.getCallNumber()).isEqualTo("callNumber");
        assertThat(risRecord.getCp()).isEqualTo("cp");
        assertThat(risRecord.getUnpublishedReferenceTitle()).isEqualTo("unpublishedReferenceTitle");
        assertThat(risRecord.getPlacePublished()).isEqualTo("placePublished");
        assertThat(risRecord.getDate()).isEqualTo("date");
        assertThat(risRecord.getDatabaseName()).isEqualTo("databaseName");
        assertThat(risRecord.getDoi()).isEqualTo("doi");
        assertThat(risRecord.getDatabaseProvider()).isEqualTo("databaseProvider");
        assertThat(risRecord.getEditor()).isEqualTo("editor");
        assertThat(risRecord.getEndPage()).isEqualTo("endPage");
        assertThat(risRecord.getEdition()).isEqualTo("edition");
        assertThat(risRecord.getReferenceId()).isEqualTo("referenceId");
        assertThat(risRecord.getIssue()).isEqualTo("issue");
        assertThat(risRecord.getPeriodicalNameUserAbbrevation()).isEqualTo("periodicalNameUserAbbrevation");
        assertThat(risRecord.getAlternativeTitle()).isEqualTo("alternativeTitle");
        assertThat(risRecord.getPeriodicalNameStandardAbbrevation()).isEqualTo("periodicalNameStandardAbbrevation");
        assertThat(risRecord.getPeriodicalNameFullFormatJF()).isEqualTo("periodicalNameFullFormatJF");
        assertThat(risRecord.getPeriodicalNameFullFormatJO()).isEqualTo("periodicalNameFullFormatJO");
        assertThat(risRecord.getKeywords()).containsExactly("keyword2", "keyword2");
        assertThat(risRecord.getPdfLinks()).containsExactly("pdfLink1", "pdfLink2");
        assertThat(risRecord.getFullTextLinks()).containsExactly("fullTextLink1", "fullTextLink2");
        assertThat(risRecord.getRelatedRecords()).containsExactly("relatedRecord1", "relatedRecord2");
        assertThat(risRecord.getImages()).containsExactly("image1", "image2");
        assertThat(risRecord.getLanguage()).isEqualTo("language");
        assertThat(risRecord.getLabel()).isEqualTo("label");
        assertThat(risRecord.getWebsiteLink()).isEqualTo("websiteLink");
        assertThat(risRecord.getNumber()).isEqualTo(1L);
        assertThat(risRecord.getMiscellaneous2()).isEqualTo("miscellaneous2");
        assertThat(risRecord.getTypeOfWork()).isEqualTo("typeOfWork");
        assertThat(risRecord.getNotes()).isEqualTo("notes");
        assertThat(risRecord.getAbstr2()).isEqualTo("abstr2");
        assertThat(risRecord.getNumberOfVolumes()).isEqualTo("numberOfVolumes");
        assertThat(risRecord.getOriginalPublication()).isEqualTo("originalPublication");
        assertThat(risRecord.getPublisher()).isEqualTo("publisher");
        assertThat(risRecord.getPublishingPlace()).isEqualTo("publishingPlace");
        assertThat(risRecord.getPublicationYear()).isEqualTo("publicationYear");
        assertThat(risRecord.getReviewedItem()).isEqualTo("reviewedItem");
        assertThat(risRecord.getResearchNotes()).isEqualTo("researchNotes");
        assertThat(risRecord.getReprintEdition()).isEqualTo("reprintEdition");
        assertThat(risRecord.getSection()).isEqualTo("section");
        assertThat(risRecord.getIsbnIssn()).isEqualTo("isbnIssn");
        assertThat(risRecord.getStartPage()).isEqualTo("startPage");
        assertThat(risRecord.getShortTitle()).isEqualTo("shortTitle");
        assertThat(risRecord.getPrimaryTitle()).isEqualTo("primaryTitle");
        assertThat(risRecord.getSecondaryTitle()).isEqualTo("secondaryTitle");
        assertThat(risRecord.getTertiaryTitle()).isEqualTo("tertiaryTitle");
        assertThat(risRecord.getTranslatedAuthor()).isEqualTo("translatedAuthor");
        assertThat(risRecord.getTitle()).isEqualTo("title");
        assertThat(risRecord.getTranslatedTitle()).isEqualTo("translatedTitle");
        assertThat(risRecord.getUserDefinable1()).isEqualTo("userDefinable1");
        assertThat(risRecord.getUserDefinable2()).isEqualTo("userDefinable2");
        assertThat(risRecord.getUserDefinable3()).isEqualTo("userDefinable3");
        assertThat(risRecord.getUserDefinable4()).isEqualTo("userDefinable4");
        assertThat(risRecord.getUserDefinable5()).isEqualTo("userDefinable5");
        assertThat(risRecord.getUrl()).isEqualTo("url");
        assertThat(risRecord.getVolumeNumber()).isEqualTo("volumeNumber");
        assertThat(risRecord.getPublisherStandardNumber()).isEqualTo("publisherStandardNumber");
        assertThat(risRecord.getPrimaryDate()).isEqualTo("primaryDate");
        assertThat(risRecord.getAccessDate()).isEqualTo("2019-01-01");
    }

}
