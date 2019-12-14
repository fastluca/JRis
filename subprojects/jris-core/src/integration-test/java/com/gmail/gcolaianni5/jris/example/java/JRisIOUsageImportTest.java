package com.gmail.gcolaianni5.jris.example.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;
import java.util.Collections;
import java.util.List;

import com.gmail.gcolaianni5.jris.JRisIO;
import com.gmail.gcolaianni5.jris.RisRecord;
import com.gmail.gcolaianni5.jris.RisType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JRisIOUsageImportTest {

    private File file;

    private List<RisRecord> records = Collections.singletonList(new RisRecord.Builder()
        .type(RisType.JOUR)
        .build());

    @BeforeEach
    void setUp() throws IOException {
        // create sample file to import
        file = File.createTempFile("jris1", null, null);
        file.deleteOnExit();
        JRisIO.export(records, file);
    }

    @Test
    void canImportFromFile() throws IOException {
        assertThat(JRisIO.process(file)).hasSize(1);
    }

    @Test
    void canImportFromReader() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        assertThat(JRisIO.process(reader)).hasSize(1);
    }

    @Test
    void canImportFromStream() throws IOException {
        InputStream stream = new FileInputStream(file);
        assertThat(JRisIO.process(stream)).hasSize(1);
    }

    @Test
    void canImportFromPath() throws IOException {
        String path = file.getPath();
        assertThat(JRisIO.process(path)).hasSize(1);
    }
}
