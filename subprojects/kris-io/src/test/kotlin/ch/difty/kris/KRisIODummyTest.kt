package ch.difty.kris

import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

/**
 * Dummy test to get a test output for the jacoco report
 */
internal class KRisIODummyTest {

    @Test
    fun canAccessKRisIO() {
        KRisIO.shouldNotBeNull()
    }
}
