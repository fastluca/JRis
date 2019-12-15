package com.gmail.gcolaianni5.jris

import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

/**
 * Dummy test to get a test output for the jacoco report
 */
internal class JRisIODummyTest {

    @Test
    fun canAccessJRisIO() {
        JRisIO.shouldNotBeNull()
    }
}
