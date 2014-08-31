package com.codejam

import static java.lang.Math.ceil
import static java.lang.Math.log

/**
 * LoadTesting.
 */
class LoadTesting
{
    static def calculateLoad(lowerBound, upperBound, factor) {
        if ((upperBound / factor) == lowerBound) { return 0 }

        int factors = 0
        for (long i = lowerBound; i < upperBound; i*=factor) {
            factors++;
        }

        return ceil(log(factors)/log(2)) as int
    }

    static void main(String[] args) {

        def testInput = new File(args[0]).newReader()
        def testOutput = new File('output').newWriter()
        def caseNumber = 1
        def numCases = testInput.readLine() as int

        while (caseNumber <= numCases) {
            def splitEntry = testInput.readLine().split()
            testOutput.writeLine("Case #${caseNumber}: ${calculateLoad(splitEntry[0].toInteger(), splitEntry[1].toInteger(), splitEntry[2].toInteger())}")
            caseNumber++
        }

        testOutput.close()
    }
}
