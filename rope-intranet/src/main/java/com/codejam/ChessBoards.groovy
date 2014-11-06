//
// Generated from archetype; please customize.
//

package com.codejam

/**
 * Rope Intranet.
 */
class ChessBoards
{
    static def cutChessBoards(char [][] inputMatrix) {
        def chessBoardSizes = [:]
        chessBoardSizes.put(1, new LinkedHashSet())

        //add all coordinates as candidates of size 1 chessboards.
        for (int i = 0; i < inputMatrix.size(); i++) {
            for (int j = 0; j < inputMatrix[i].size(); j++) {
                chessBoardSizes.get(1).add([i, j])
            }

        }

        //start with size 2 and move up until no larger boards can be built
        for (int size = 2; size < inputMatrix[0].size(); size++) {
            def previousSizeCoordinates = chessBoardSizes.get(size-1)

            if (previousSizeCoordinates.isEmpty()) { break }

            chessBoardSizes.put(size, new LinkedHashSet())

            def currentSizeSet = chessBoardSizes.get(size)

            previousSizeCoordinates.removeAll { pair ->
                if (isSquareOfSize(size, pair, inputMatrix)) {
                    currentSizeSet.add(pair)
                    true
                } else {
                    false
                }
            }

            previousSizeCoordinates.removeAll { pair -> hasOverLap(size, pair, currentSizeSet) }
        }


        return chessBoardSizes;
	}

    static boolean hasOverLap(int size, def previousSizePair, def currentSizeSet) {
        currentSizeSet.any { currentSizePair -> rectOverlap(size, previousSizePair, currentSizePair) }

    }

    static boolean valueInRange(int value, int min, int max){ return (value >= min) && (value <= max); }

    static boolean rectOverlap(int size, def previousSizePair, def currentSizePair) {
        boolean xOverlap = valueInRange(previousSizePair[0], currentSizePair[0], currentSizePair[0] + size) ||
                valueInRange(currentSizePair[0], previousSizePair[0], previousSizePair[0]+ size - 1);

        boolean yOverlap = valueInRange(previousSizePair[1], currentSizePair[1], currentSizePair[1] + size) ||
                valueInRange(currentSizePair[1], previousSizePair[1], previousSizePair[1]+ size - 1);

        return xOverlap && yOverlap;
    }

    static boolean isSquareOfSize(int size, def coordinatePair, char[][] inputMatrix) {
        if (coordinatePair[0] + size > inputMatrix.size() || coordinatePair[1] + size > inputMatrix[0].size()) {
            return false
        }

        int rightIndex = coordinatePair[1] + size - 1
        if (inputMatrix[coordinatePair[0]][rightIndex-1] as int ^ inputMatrix[coordinatePair[0]][rightIndex] as int) {
            for (int i = coordinatePair[0]; i < coordinatePair[0] + size -1; i++) {
                if (!xor(inputMatrix[i][coordinatePair[1]..rightIndex], inputMatrix[i+1][coordinatePair[1]..rightIndex])) {
                    return false
                }
            }

            return true
        } else {
            return false
        }
    }

    static boolean xor (def a, def b )
    {
        for ( int i=0; i<a.size() ; i++ )
        {
            if (!(a[i] as int ^ b[i] as int)) {
                return false
            }
        }
        return true
    }

	static void main(String[] args) {
		def testInput = new File(args[0]).newReader()
		def testOutput = new File('output').newWriter()
		def caseNumber = 1
		def numCases = testInput.readLine() as int
		while (caseNumber <= numCases) {
			def dimensions = testInput.readLine().split()

			def rows = dimensions[0].toInteger()
			def columns = dimensions[1].toInteger()
			char [][] boardInput = new char [rows][columns]

			for (int i = 0; i<rows; i++) {
				def hexValues = testInput.readLine().toCharArray()
				def rowValues = []
				hexValues.each{ hexValue ->
					rowValues.addAll(
							String.format("%4s", Integer.toBinaryString(Integer.parseInt(hexValue.toString(), 16))).replace(' ', '0').toCharArray()
					)
				}
				boardInput[i] = rowValues
			}

			def chessSizes = cutChessBoards(boardInput)
			testOutput.writeLine("Case #${caseNumber}: ${chessSizes.size()}")
			chessSizes.each { size, numBoards -> testOutput.writeLine("${size} ${numBoards}")}
			caseNumber++
		}
		testOutput.close()
	}
}
