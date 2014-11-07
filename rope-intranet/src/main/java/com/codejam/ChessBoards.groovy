//
// Generated from archetype; please customize.
//

package com.codejam

/**
 * Rope Intranet.
 */
class ChessBoards {
	static def cutChessBoards(char[][] inputMatrix) {
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
			def previousSizeCoordinates = chessBoardSizes.get(size - 1)

			if (previousSizeCoordinates.isEmpty()) {
				break
			}

			chessBoardSizes.put(size, new LinkedHashSet())

			def currentSizeSet = chessBoardSizes.get(size)

			previousSizeCoordinates.each { pair ->
				if (isSquareOfSize(size, pair, inputMatrix)) {
					currentSizeSet.add(pair)
					true
				} else {
					false
				}
			}


		}

		chessBoardSizes.reverseEach { boardSize, coordinateSet ->
			coordinateSet.removeAll { pair ->
				if (canCut(pair, boardSize, inputMatrix)) {
					cut(pair, boardSize, inputMatrix)
					false
				} else {
					true
				}
			}
		}

		return chessBoardSizes;
	}

	static boolean canCut(def pair, def boardSize, char[][] inputMatrix) {
		for (int i = pair[0]; i < pair[0] + boardSize; i++) {
			for (int j = pair[1]; j < pair[1] + boardSize; j++) {
				if (inputMatrix[i][j] == '2') {
					return false
				}
			}
		}

		return true
	}

	static void cut(def pair, def boardSize, char[][] inputMatrix) {
		for (int i = pair[0]; i < pair[0] + boardSize; i++) {
			for (int j = pair[1]; j < pair[1] + boardSize; j++) {
				inputMatrix[i][j] = '2'
			}
		}
	}

	static boolean isSquareOfSize(int size, def coordinatePair, char[][] inputMatrix) {
		if (coordinatePair[0] + size > inputMatrix.size() || coordinatePair[1] + size > inputMatrix[0].size()) {
			return false
		}

		int rightIndex = coordinatePair[1] + size - 1
		int bottomIndex = coordinatePair[0] + size - 1

		for (int i = coordinatePair[0]; i < bottomIndex + 1; i++) {
			if (!(inputMatrix[i][rightIndex - 1] as int ^ inputMatrix[i][rightIndex] as int)) {
				return false
			}
		}

		for (int j = coordinatePair[1]; j < rightIndex + 1; j++) {
			if (!(inputMatrix[bottomIndex-1][j] as int ^ inputMatrix[bottomIndex][j] as int)) {
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
			char[][] boardInput = new char[rows][columns]

			for (int i = 0; i < rows; i++) {
				def hexValues = testInput.readLine().toCharArray()
				def rowValues = []
				hexValues.each { hexValue ->
					rowValues.addAll(
							String.format("%4s", Integer.toBinaryString(Integer.parseInt(hexValue.toString(), 16))).replace(' ', '0').toCharArray()
					)
				}
				boardInput[i] = rowValues
			}

			System.out.println("On case#${caseNumber}")

			def chessSizes = cutChessBoards(boardInput)
			def filteredChessSizes = chessSizes.findAll { it.value.size() > 0 }
			testOutput.writeLine("Case #${caseNumber}: ${filteredChessSizes.size()}")

			filteredChessSizes.reverseEach { chessSize, boards ->
				testOutput.writeLine("${chessSize} ${boards.size()}")
			}


			caseNumber++
		}

		testOutput.close()
	}
}
