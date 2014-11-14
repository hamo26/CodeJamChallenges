//
// Generated from archetype; please customize.
//

package com.ea

import org.junit.Test

/**
 * permute string.
 */
class PermuteString
{
    void permuteString(def prefix, def chunks) {
        if (chunks.size() == 1) {
            System.out.println(prefix + chunks.join())
        } else {
            for (def chunk : chunks) {
                permuteString(prefix + chunk, chunks.minus(chunk))
            }
        }
    }

    @Test
    void ensureSimpleStringCanBePermuted() {
        permuteString("", ['a', 'b', 'c', 'd'])
    }
}
