package ch.difty.kris.example.java;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class KrisUsageTest {

    @Test
    void canParse() {
        //@formatter:off
        List<String> singleEntryRis = Arrays.asList(
            "TY  - JOUR",
            "AU  - Shannon, Claude E.",
            "PY  - 1948/07//",
            "TI  - A Mathematical Theory of Communication",
            "T2  - Bell System Technical Journal",
            "SP  - 379",
            "EP  - 423",
            "VL  - 27",
            "ER  - "
        );
        //@formatter:on

        //List<RisRecord> records = JRis.INSTANCE.parse(singleEntryRis)
        // TODO develop API
    }
}
