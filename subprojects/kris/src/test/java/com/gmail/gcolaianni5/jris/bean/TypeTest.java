package com.gmail.gcolaianni5.jris.bean;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TypeTest {

  /* testedClasses: Type */
  // Test written by Diffblue Cover.
  @Test
  void valueOfInputNotNullOutputIllegalArgumentException() {

    // Arrange
    final String name = "a,b,c";

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
        Type.valueOf(name);
    });

    // Method is not expected to return due to exception thrown
  }
}
