//
// Generated from archetype; please customize.
//

package com.codejam

import org.junit.Test

/**
 * Tests for the {@link RopeIntranet} class.
 */
class RopeIntranetTest
{
    @Test
    void ensureNumberOfIntersectionsCalculatedCorrectly() {
        assert 2 == new  RopeIntranet().calculateIntersections([[0,3], [2,2], [1,1]])
    }
}
