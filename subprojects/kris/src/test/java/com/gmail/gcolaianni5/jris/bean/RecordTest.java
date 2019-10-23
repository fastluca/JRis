package com.gmail.gcolaianni5.jris.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class RecordTest {

  /* testedClasses: Record */
  // Test written by Diffblue Cover.
  @Test
  void addAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String author = "3";

    // Act
    objectUnderTest.addAuthor(author);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getAuthors());
  }

  // Test written by Diffblue Cover.
  @Test
  void addFirstAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String firstAuthor = "3";

    // Act
    objectUnderTest.addFirstAuthor(firstAuthor);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getFirstAuthors());
  }

  // Test written by Diffblue Cover.
  @Test
  void addFullTextLinkInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String fullTextLink = "3";

    // Act
    objectUnderTest.addFullTextLink(fullTextLink);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getFullTextLinks());
  }

  // Test written by Diffblue Cover.
  @Test
  void addImageInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String image = "3";

    // Act
    objectUnderTest.addImage(image);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getImages());
  }

  // Test written by Diffblue Cover.
  @Test
  void addKeywordInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String keyword = "3";

    // Act
    objectUnderTest.addKeyword(keyword);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getKeywords());
  }

  // Test written by Diffblue Cover.
  @Test
  void addPdfLinkInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String pdfLink = "3";

    // Act
    objectUnderTest.addPdfLink(pdfLink);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getPdfLinks());
  }

  // Test written by Diffblue Cover.
  @Test
  void addRelatedRecordInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String relatedRecord = "3";

    // Act
    objectUnderTest.addRelatedRecord(relatedRecord);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getRelatedRecords());
  }

  // Test written by Diffblue Cover.
  @Test
  void addSecondaryAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String secondaryAuthor = "3";

    // Act
    objectUnderTest.addSecondaryAuthor(secondaryAuthor);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getSecondaryAuthors());
  }

  // Test written by Diffblue Cover.
  @Test
  void addSubsidiaryAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String subsidiaryAuthor = "3";

    // Act
    objectUnderTest.addSubsidiaryAuthor(subsidiaryAuthor);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getSubsidiaryAuthors());
  }

  // Test written by Diffblue Cover.
  @Test
  void addTertiaryAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String tertiaryAuthor = "3";

    // Act
    objectUnderTest.addTertiaryAuthor(tertiaryAuthor);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<>();
    linkedList.add("3");
    assertEquals(linkedList, objectUnderTest.getTertiaryAuthors());
  }
}
