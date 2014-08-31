package com.codejam

import org.junit.Test

/**
 * Tests for the {@link LoadTesting} class.
 */
class LoadTestingTest
{
    @Test
    void ensureNumberOfLoadTestsCalculatedCorrectly() {
        assert 2 == LoadTesting.calculateLoad(50, 700, 2)
        assert 0 == LoadTesting.calculateLoad(19, 57, 3)
        assert 4 == LoadTesting.calculateLoad(1, 1000, 2)
        assert 2 == LoadTesting.calculateLoad(24, 97, 2)
    }
}
