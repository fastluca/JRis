package com.gmail.gcolaianni5.jris.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.LinkedList

@Suppress("SpellCheckingInspection")
internal class RisRecordTest {

    /* testedClasses: RisRecord */
    // Test written by Diffblue Cover.
    @Test
    fun addAuthorInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val author = "3"

        // Act
        objectUnderTest.authors.add(author)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.authors)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addFirstAuthorInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val firstAuthor = "3"

        // Act
        objectUnderTest.firstAuthors.add(firstAuthor)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.firstAuthors)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addFullTextLinkInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val fullTextLink = "3"

        // Act
        objectUnderTest.fullTextLinks.add(fullTextLink)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.fullTextLinks)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addImageInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val image = "3"

        // Act
        objectUnderTest.images.add(image)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.images)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addKeywordInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val keyword = "3"

        // Act
        objectUnderTest.keywords.add(keyword)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.keywords)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addPdfLinkInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val pdfLink = "3"

        // Act
        objectUnderTest.pdfLinks.add(pdfLink)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.pdfLinks)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addRelatedRisRecordInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val relatedRisRecord = "3"

        // Act
        objectUnderTest.relatedRecords.add(relatedRisRecord)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.relatedRecords)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addSecondaryAuthorInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val secondaryAuthor = "3"

        // Act
        objectUnderTest.secondaryAuthors.add(secondaryAuthor)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.secondaryAuthors)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addSubsidiaryAuthorInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val subsidiaryAuthor = "3"

        // Act
        objectUnderTest.subsidiaryAuthors.add(subsidiaryAuthor)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.subsidiaryAuthors)
    }

    // Test written by Diffblue Cover.
    @Test
    fun addTertiaryAuthorInputNotNullOutputVoid() {

        // Arrange
        val objectUnderTest = RisRecord()
        val tertiaryAuthor = "3"

        // Act
        objectUnderTest.tertiaryAuthors.add(tertiaryAuthor)

        // Assert side effects
        val linkedList = LinkedList<String>()
        linkedList.add("3")
        assertEquals(linkedList, objectUnderTest.tertiaryAuthors)
    }
}
