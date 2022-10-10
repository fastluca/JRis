package ch.difty.kris.usage;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;
import java.util.Collections;
import java.util.List;

import ch.difty.kris.KRisIO;
import ch.difty.kris.domain.RisRecord;
import ch.difty.kris.domain.RisType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KRisIOUsageImportTest {

    private File file;

    private final List<RisRecord> records = Collections.singletonList(new RisRecord.Builder()
        .type(RisType.JOUR)
        .build());

    @BeforeEach
    void setUp() throws IOException {
        // create sample file to import
        file = File.createTempFile("kris1", null, null);
        file.deleteOnExit();
        KRisIO.export(records, file);
    }

    @Test
    void canImportFromFile() throws IOException {
        assertThat(KRisIO.process(file)).hasSize(1);
    }

    @Test
    void canImportFromReader() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        assertThat(KRisIO.process(reader)).hasSize(1);
    }

    @Test
    void canImportFromStream() throws IOException {
        InputStream stream = new FileInputStream(file);
        assertThat(KRisIO.process(stream)).hasSize(1);
    }

    @Test
    void canImportFromPath() throws IOException {
        String path = file.getPath();
        assertThat(KRisIO.process(path)).hasSize(1);
    }
}
