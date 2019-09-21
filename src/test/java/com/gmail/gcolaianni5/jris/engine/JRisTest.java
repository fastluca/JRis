package com.gmail.gcolaianni5.jris.engine;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gmail.gcolaianni5.jris.bean.Record;
import com.gmail.gcolaianni5.jris.exception.JRisException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

class JRisTest {

  /* testedClasses: JRis */
  // Test written by Diffblue Cover.
  @Test
  void buildInput1NullOutputJRisException() throws IOException, JRisException {

    // Arrange
    final ArrayList<Record> records = new ArrayList<Record>();
    records.add(null);
    final Writer writer = null;

    // Act
    assertThrows(JRisException.class, () -> {
      JRis.build(records, writer);
    });

    // Method is not expected to return due to exception thrown
  }

  // Test written by Diffblue Cover.
  @Test
  void buildInputNullNullOutputNullPointerException() throws JRisException, IOException {

    // Arrange
    final List<Record> records = null;
    final OutputStream out = null;

    // Act
    assertThrows(NullPointerException.class, () -> {
      JRis.build(records, out);
    });

    // Method is not expected to return due to exception thrown
  }
}
