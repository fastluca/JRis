package ch.difty.kris.example.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.gmail.gcolaianni5.jris.JRis;
import com.gmail.gcolaianni5.jris.RisRecord;
import com.gmail.gcolaianni5.jris.RisType;
import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

class KrisUsageTest {

    //@formatter:off
    @SuppressWarnings("SpellCheckingInspection") private final List<String> risLines = Arrays.asList(
        "TY  - JOUR",
        "AU  - Shannon, Claude E.",
        "PY  - 1948/07//",
        "TI  - A Mathematical Theory of Communication",
        "T2  - Bell System Technical Journal",
        "SP  - 379",
        "EP  - 423",
        "VL  - 27",
        "ER  - ",
        "TY  - JOUR",
        "TI  - Die Grundlage der allgemeinen Relativitätstheorie",
        "AU  - Einstein, Albert",
        "PY  - 1916",
        "SP  - 769",
        "EP  - 822",
        "JO  - Annalen der Physik",
        "VL  - 49",
        "ER  -"
    );
    //@formatter:on

    @Test
    void whenProcessingRisLinesAsList_willReturnListOfRisRecords() {
        final List<RisRecord> risRecords = JRis.processList(risLines);
        assertThat(risRecords).hasSize(2);
    }

    @Test
    void whenProcessingRisLinesAsObservable_willReturnObservableOfRisRecords() {
        final List<RisRecord> risRecords = new ArrayList<>();

        final Observable<String> observable = Observable.fromIterable(risLines);

        JRis
            .processObservables(observable)
            .blockingSubscribe(risRecords::add);

        assertThat(risRecords).hasSize(2);
    }

    private final RisRecord risRecord = new RisRecord.Builder()
        .type(RisType.JOUR)
        .authors(Collections.singletonList("Shannon, Claude E."))
        .publicationYear("1948/07//")
        .title("A Mathematical Theory of Communication")
        .secondaryTitle("Bell System Technical Journal")
        .startPage("379")
        .endPage("423")
        .volumeNumber("27")
        .build();

    private final List<RisRecord> risRecords = Collections.singletonList(risRecord);

    private final int expectedLineCount = 9;

    @Test
    void whenProcessingRisRecordsAsList_willReturnListOfRisLines() {
        final List<String> risLines = JRis.buildFromList(risRecords);
        assertThat(risLines).hasSize(expectedLineCount);
    }

    @Test
    void whenProcessingRisRecordsAsObservable_willReturnObservableOfString() {
        final List<String> risLines = new ArrayList<>();

        final Observable<RisRecord> observable = Observable.fromIterable(risRecords);

        JRis
            .exportObservable(observable)
            .blockingSubscribe(risLines::add);

        assertThat(risLines).hasSize(expectedLineCount);
    }
}
