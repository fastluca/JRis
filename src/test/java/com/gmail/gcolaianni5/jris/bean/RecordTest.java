package com.gmail.gcolaianni5.jris.bean;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.util.LinkedList;

public class RecordTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: Record */
  // Test written by Diffblue Cover.
  @Test
  public void addAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String author = "3";

    // Act
    objectUnderTest.addAuthor(author);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getAuthors());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addFirstAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String firstAuthor = "3";

    // Act
    objectUnderTest.addFirstAuthor(firstAuthor);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getFirstAuthors());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addFullTextLinkInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String fullTextLink = "3";

    // Act
    objectUnderTest.addFullTextLink(fullTextLink);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getFullTextLinks());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addImageInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String image = "3";

    // Act
    objectUnderTest.addImage(image);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getImages());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addKeywordInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String keyword = "3";

    // Act
    objectUnderTest.addKeyword(keyword);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getKeywords());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addPdfLinkInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String pdfLink = "3";

    // Act
    objectUnderTest.addPdfLink(pdfLink);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getPdfLinks());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addRelatedRecordInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String relatedRecord = "3";

    // Act
    objectUnderTest.addRelatedRecord(relatedRecord);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getRelatedRecords());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addSecondaryAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String secondaryAuthor = "3";

    // Act
    objectUnderTest.addSecondaryAuthor(secondaryAuthor);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getSecondaryAuthors());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addSubsidiaryAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String subsidiaryAuthor = "3";

    // Act
    objectUnderTest.addSubsidiaryAuthor(subsidiaryAuthor);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getSubsidiaryAuthors());
  }

  // Test written by Diffblue Cover.
  @Test
  public void addTertiaryAuthorInputNotNullOutputVoid() {

    // Arrange
    final Record objectUnderTest = new Record();
    final String tertiaryAuthor = "3";

    // Act
    objectUnderTest.addTertiaryAuthor(tertiaryAuthor);

    // Assert side effects
    final LinkedList<String> linkedList = new LinkedList<String>();
    linkedList.add("3");
    Assert.assertEquals(linkedList, objectUnderTest.getTertiaryAuthors());
  }
}
