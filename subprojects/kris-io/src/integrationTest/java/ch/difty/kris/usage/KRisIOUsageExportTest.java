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

@SuppressWarnings("SpellCheckingInspection")
class KRisIOUsageExportTest {

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
        KRisIO.export(records, file);
        assertThat(KRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToWriter() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        KRisIO.export(records, writer);
        assertThat(KRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToStream() throws Exception {
        OutputStream stream = new FileOutputStream(file);
        KRisIO.export(records, stream);
        assertThat(KRisIO.process(file)).hasSize(records.size());
    }

    @Test
    void canExportToPath() throws Exception {
        String path = file.getPath();
        KRisIO.export(records, path);
        assertThat(KRisIO.process(file)).hasSize(records.size());
    }
}
