package com.codejam

/**
 * Prisoners.
 */
class Prisoners {
	static void main(String[] args) {
		def testInput = new File(args[0]).newReader()
		def testOutput = new File('output').newWriter()
		def caseNumber = 1
		def numCases = testInput.readLine() as int
		while (caseNumber <= numCases) {
			def prisonerInfo = testInput.readLine().split()
            def numPrisonCells = prisonerInfo[0].toInteger()
            def prisonersToRelease = testInput.readLine().split()

			System.out.println("On case#${caseNumber}")

			testOutput.writeLine("Case #${caseNumber}: ${calcBribeCoins(numPrisonCells, new HashSet(Arrays.asList(prisonersToRelease)))}")

			caseNumber++
		}

		testOutput.close()
	}

    static def calcBribeCoins(int numPrisonCells, def prisonersToRelease) {

        //Dynamic programming approach. We want the minimum number of bribes required for a range of prison cells.
        // Given a cell, the minimum number of bribes required is the (range - 1) + MinBribes(current_prisoner + 1, n) + (1, current_prisoner -1)
        // We can memoize ranges and optimal bribe values when found.
        def cache = [:]

        def minBribes = calcBribes(1, numPrisonCells, prisonersToRelease, cache)

        return minBribes

    }

    static def calcBribes(def startingIndex, def endIndex, def prisonersToRelease, def cache) {
        def prisonersToConsider = []

        for (int i = startingIndex; i <= endIndex; i++) {
            if (prisonersToRelease.contains(String.valueOf(i))) {
                prisonersToConsider.add(i)
            }
        }

        if (prisonersToConsider.isEmpty()) {
            cache.put([startingIndex, endIndex], 0)
            return 0
        }

        def minBribeValue = prisonersToConsider.collect { prisonerIndex ->
            def (optimalRightBribes, optimalLeftBribes) = [0, 0]

            if (prisonerIndex + 1 < endIndex) {
                optimalRightBribes = cache.containsKey([prisonerIndex + 1, endIndex]) ? cache.get([prisonerIndex + 1, endIndex]) :
                        calcBribes(prisonerIndex +1, endIndex, prisonersToRelease, cache)
            }

            if (prisonerIndex - 1 > startingIndex) {
                optimalLeftBribes = cache.containsKey([startingIndex, prisonerIndex - 1]) ? cache.get([startingIndex, prisonerIndex - 1]) :
                        calcBribes(startingIndex, prisonerIndex - 1, prisonersToRelease, cache)
            }


            return (endIndex - startingIndex) + optimalRightBribes + optimalLeftBribes
        }.min()

        cache.put([startingIndex, endIndex], minBribeValue)

        return minBribeValue
    }
}
