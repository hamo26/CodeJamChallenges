package com.codejam

/**
 * RopeIntranet.
 */
class RopeIntranet
{
    static def calculateIntersections(def pointPairs) {
        def count = 0
        for (int i = 0; i < pointPairs.size(); i++) {
            for (int j = i + 1; j < pointPairs.size(); j++) {
                if ((pointPairs[i][0] < pointPairs[j][0] && pointPairs[i][1] > pointPairs[j][1]) ||
                        (pointPairs[i][0] > pointPairs[j][0] && pointPairs[i][1] < pointPairs[j][1])) {
                     count++;
                }
            }
        }
        return count;
    }

    static void main(String[] args) {

        def testInput = new File(args[0]).newReader()
        def testOutput = new File('output').newWriter()
        def caseNumber = 1
        def numCases = testInput.readLine().toInteger()

        while (caseNumber <= numCases) {
            def testInputs = testInput.readLine().toInteger()
            def pairPoints = []
            (1..testInputs).each {
                def splitEntry = testInput.readLine().split()
                pairPoints.add([splitEntry[0].toInteger(), splitEntry[1].toInteger()])
            }

            testOutput.writeLine("Case #${caseNumber}: ${calculateIntersections(pairPoints)}")
            caseNumber++
        }

        testOutput.close()
    }
}
