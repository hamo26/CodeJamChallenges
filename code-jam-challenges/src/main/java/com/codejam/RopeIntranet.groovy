//
// Generated from archetype; please customize.
//

package com.codejam

/**
 * Rope Intranet.
 */
class RopeIntranet
{
    def calculateIntersections(def pointPairs) {
        def leftHeights = []
		def rightHeights = []


		pointPairs.each{ pointPair ->
			leftHeights.add(pointPair[0])
			rightHeights.add(pointPair[1])
		}

		Collections.reverse(rightHeights.sort())

		int intersectionCount = 0;
		for (def intersection : pointPairs) {
			intersectionCount+=(Collections.binarySearch(rightHeights, intersection[1])-Math.abs(Collections.binarySearch(rightHeights, intersection[0])))
		}

		return intersectionCount;
    }
}
