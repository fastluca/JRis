package ch.difty.kris.guide;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.difty.kris.KRis;
import ch.difty.kris.KRisIO;
import ch.difty.kris.domain.RisRecord;
import ch.difty.kris.domain.RisType;
import io.reactivex.Observable;

@SuppressWarnings("ALL")
public class GuideExamples {

    //region export

    // tag::risRecords[]
    private final RisRecord record1 = new RisRecord.Builder()
        .type(RisType.JOUR)
        .authors(Arrays.asList("Shannon, Claude E."))
        .publicationYear("1948/07//")
        .title("A Mathematical Theory of Communication")
        .secondaryTitle("Bell System Technical Journal")
        .startPage("379")
        .endPage("423")
        .volumeNumber("27")
        .build();

    private final RisRecord record2 = new RisRecord.Builder()
        .type(RisType.JOUR)
        .authors(Arrays.asList("Turing, Alan Mathison"))
        .publicationYear("1948/07//")
        .periodicalNameFullFormatJO("Proc. of London Mathematical Society")
        .title("On computable numbers, with an application to the Entscheidungsproblem")
        .secondaryTitle("Bell System Technical Journal")
        .startPage("230")
        .endPage("265")
        .volumeNumber("47")
        .issue("1")
        .primaryDate("1937")
        .build();
    // end::risRecords[]

    void fromList() {
        // tag::fromList[]
        final List<RisRecord> records = Arrays.asList(record1, record2);

        List<String> lines = KRis.buildFromList(records);
        // end::fromList[]
    }

    void fromListCustomSort() {
        // tag::fromListCustomSort[]
        final List<RisRecord> records = Arrays.asList(record1, record2);
        final List<String> sort = Arrays.asList("SP", "EP", "T1");

        List<String> lines = KRis.buildFromList(records, sort);
        // end::fromListCustomSort[]
    }

    // tag::fromObservable[]
    void fromObservable() throws IOException {
        final List<RisRecord> records = Arrays.asList(record1, record2);
        final Observable<RisRecord> observable = Observable.fromIterable(records);

        final BufferedWriter writer = new BufferedWriter(new FileWriter("export.ris"));

        KRis
            .exportObservable(observable)
            .doFinally(() -> closeWriter(writer))
            .blockingSubscribe(writer::append);
    }

    private void closeWriter(final BufferedWriter writer) {
        try {
            writer.close();
        } catch (Exception ex) {
        }
    }
    // end::fromObservable[]

    void writerExport() throws IOException {
        // tag::writerExport[]
        final List<RisRecord> records = Arrays.asList(record1, record2);

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter("export.ris"))) {
            KRisIO.export(records, writer);
        }
        // end::writerExport[]
    }

    void fileExport() throws IOException {
        // tag::fileExport[]
        final List<RisRecord> records = Arrays.asList(record1, record2);

        final File file = new File("export.ris");

        KRisIO.export(records, file);
        // end::fileExport[]
    }

    void streamExport() throws IOException {
        // tag::streamExport[]
        final List<RisRecord> records = Arrays.asList(record1, record2);

        try (OutputStream s = new BufferedOutputStream(new FileOutputStream("export.ris"))) {
            KRisIO.export(records, s);
        }
        // end::streamExport[]
    }

    void pathExport() throws IOException {
        // tag::pathExport[]
        final List<RisRecord> records = Arrays.asList(record1, record2);

        KRisIO.export(records, "export.ris");
        // end::pathExport[]
    }

    //endregion

    //region import
    void processReader() throws IOException {
        // tag::processReader[]
        try (final BufferedReader reader = new BufferedReader(new FileReader("import.ris"))) {
            final List<RisRecord> records = KRisIO.process(reader);
        }
        // end::processReader[]
    }

    void processFile() throws IOException {
        // tag::processFile[]
        final File file = new File("import.ris");
        final List<RisRecord> records = KRisIO.process(file);
        // end::processFile[]
    }

    void processInputStream() throws IOException {
        // tag::processInputStream[]
        try (InputStream s = new BufferedInputStream(new FileInputStream("import.ris"))) {
            final List<RisRecord> records = KRisIO.process(s);
        }
        // end::processInputStream[]
    }

    void processPath() throws IOException {
        // tag::processPath[]
        final List<RisRecord> records = KRisIO.process("import.ris");
        // end::processPath[]
    }

    void passRisLinesAsList() throws IOException {
        // tag::passRisLinesAsList[]
        final List<String> lines = Arrays.asList(); // TODO
        List<RisRecord> records = KRis.processList(lines);
        // end::passRisLinesAsList[]
    }

    void processObservables() throws IOException {
        // tag::processObservables[]
        final List<RisRecord> risRecords = new ArrayList<>();
        final List<String> lines = new ArrayList<>();
        final Observable<String> observable = Observable.fromIterable(lines);

        KRis
            .processObservables(observable)
            .blockingSubscribe(risRecords::add);
        // end::processObservables[]
    }

    //endregion

    void getListOfRisTagsAsString() {
        // tag::getListOfRisTagsAsString[]
        List<String> namesOfAllRisTags = KRis.risTagNames();
        // end::getListOfRisTagsAsString[]
    }
}
