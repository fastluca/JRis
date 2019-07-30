package com.gmail.gcolaianni5.jris.engine;

import com.gmail.gcolaianni5.jris.bean.Record;
import com.gmail.gcolaianni5.jris.exception.JRisException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JRisTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: JRis */
  // Test written by Diffblue Cover.
  @Test
  public void buildInput1NullOutputJRisException() throws IOException, JRisException {

    // Arrange
    final ArrayList<Record> records = new ArrayList<Record>();
    records.add(null);
    final Writer writer = null;

    // Act
    thrown.expect(JRisException.class);
    JRis.build(records, writer);

    // Method is not expected to return due to exception thrown
  }

  // Test written by Diffblue Cover.
  @Test
  public void buildInputNullNullOutputNullPointerException() throws JRisException, IOException {

    // Arrange
    final List<Record> records = null;
    final OutputStream out = null;

    // Act
    thrown.expect(NullPointerException.class);
    JRis.build(records, out);

    // Method is not expected to return due to exception thrown
  }
}
