package com.gmail.gcolaianni5.jris.bean;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class TypeTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: Type */
  // Test written by Diffblue Cover.
  @Test
  public void valueOfInputNotNullOutputIllegalArgumentException() {

    // Arrange
    final String name = "a,b,c";

    // Act
    thrown.expect(IllegalArgumentException.class);
    Type.valueOf(name);

    // Method is not expected to return due to exception thrown
  }
}
