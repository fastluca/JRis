package com.gmail.gcolaianni5.jris.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;

import com.gmail.gcolaianni5.jris.bean.Record;
import com.gmail.gcolaianni5.jris.exception.JRisException;
import org.junit.jupiter.api.Test;

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

  /**
   * The list of expected tags was copied from https://en.wikipedia.org/wiki/RIS_(file_format) (version 2019-09-03 15:36)
   * and modified to pass the current implementation (-> prepare the failing test).
   * The following elements are not implemented: AD, ET, NV, RI, TT. but in addition we have "AA" and "R1" (typo!).
   */
  @Test
  void assertTypes() throws NoSuchFieldException, IllegalAccessException {
    String[] expectedTags = {
        "TY", "A1", "A2", "A3", "A4", "AB", "AD", "AN", "AU", "AV", "BT", "C1", "C2", "C3", "C4", "C5",
        "C6", "C7", "C8", "CA", "CN", "CP", "CT", "CY", "DA", "DB", "DO", "DP", "ED", "EP", "ET", "ID", "IS", "J1",
        "J2", "JA", "JF", "JO", "KW", "L1", "L2", "L3", "L4", "LA", "LB", "LK", "M1", "M2", "M3", "N1", "N2", "NV",
        "OP", "PB", "PP", "PY", "RI", "RN", "RP", "SE", "SN", "SP", "ST", "T1", "T2", "T3", "TA", "TI", "TT", "U1",
        "U2", "U3", "U4", "U5", "UR", "VL", "VO", "Y1", "Y2"
    };

    JRis jris = new JRis();
    Field f = jris.getClass().getDeclaredField("TAG_METHOD_DICTIONARY");
    f.setAccessible(true);

    @SuppressWarnings("unchecked")
    LinkedHashMap<String, MethodTypeMapping> dictionary = (LinkedHashMap) f.get(jris);

    assertThat(dictionary.keySet()).containsExactlyInAnyOrder(expectedTags);
  }
}
