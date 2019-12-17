package com.gmail.gcolaianni5.jris.usage;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;
import java.util.Collections;
import java.util.List;

import com.gmail.gcolaianni5.jris.JRisIO;
import com.gmail.gcolaianni5.jris.domain.RisRecord;
import com.gmail.gcolaianni5.jris.domain.RisType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JRisIOUsageExportTest {

    private File file;

    private List<RisRecord> records = Collections.singletonList(new RisRecord.Builder()
        .type(RisType.JOUR)
        .build());

    @BeforeEach
    void setUp() throws IOException {
        // create sample file to import
        file = File.createTempFile("jris2", null, null);
        file.deleteOnExit();
    }

    @Test
    void canExportToFile() throws Exception {
        JRisIO.export(records, file);
        assertThat(JRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToWriter() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        JRisIO.export(records, writer);
        assertThat(JRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToStream() throws Exception {
        OutputStream stream = new FileOutputStream(file);
        JRisIO.export(records, stream);
        assertThat(JRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToPath() throws Exception {
        String path = file.getPath();
        JRisIO.export(records, path);
        assertThat(JRisIO.process(file)).hasSize(records.size());
    }
}
