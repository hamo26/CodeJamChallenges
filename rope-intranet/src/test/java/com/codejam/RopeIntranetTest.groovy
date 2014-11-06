//
// Generated from archetype; please customize.
//

package com.codejam

import org.junit.Test

/**
 * Tests {@link RopeIntranet}.
 */
class RopeIntranetTest {
    def ropeIntranet = new RopeIntranet()

	@Test
	void ensureNumberOfIntersectionIsReturnedCorrectly() {
        assert 2 == ropeIntranet.calculateIntersections([[4,4], [3,5], [2,1], [1,2]])
    }
}
