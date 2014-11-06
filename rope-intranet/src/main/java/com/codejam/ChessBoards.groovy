//
// Generated from archetype; please customize.
//

package com.codejam

/**
 * Rope Intranet.
 */
class ChessBoards
{
    def cutChessBoards(int[][] inputMatrix) {
        



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
