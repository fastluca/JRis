package ch.difty.kris.example.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;
import java.util.Collections;
import java.util.List;

import com.gmail.gcolaianni5.jris.JRisIO;
import com.gmail.gcolaianni5.jris.RisRecord;
import com.gmail.gcolaianni5.jris.RisType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KrisIOUsageExportTest {

    private File file;

    private List<RisRecord> records = Collections.singletonList(new RisRecord.Builder()
        .type(RisType.JOUR)
        .build());

    @BeforeEach
    void setUp() throws IOException {
        // create sample file to import
        file = File.createTempFile("kris2", null, null);
        file.deleteOnExit();
    }

    @Test
    void canExportToFile() throws Exception {
        JRisIO.build(records, file);
        assertThat(JRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToWriter() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        JRisIO.build(records, writer);
        assertThat(JRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToStream() throws Exception {
        OutputStream stream = new FileOutputStream(file);
        JRisIO.build(records, stream);
        assertThat(JRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToPath() throws Exception {
        String path = file.getPath();
        JRisIO.build(records, path);
        assertThat(JRisIO.process(file)).hasSize(records.size());
    }
}
