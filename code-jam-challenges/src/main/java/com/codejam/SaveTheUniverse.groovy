package com.codejam

/**
 * Prisoners.
 */
class SaveTheUniverse {
    static void main(String[] args) {
        def testInput = new File(args[0]).newReader()
        def testOutput = new File('output').newWriter()
        def caseNumber = 1
        def numCases = testInput.readLine() as int
        while (caseNumber <= numCases) {
            def numSearchEngines = testInput.readLine() as int
            def searchEngines = []

            (0..<numSearchEngines).each {
                searchEngines.add(testInput.readLine())
            }

            def queries = []
            def numQueries = testInput.readLine() as int
            (0..<numQueries).each { queries.add(testInput.readLine()) }

            int i = 0
            int switches = 0
            while (true) {
                if (numQueries == 0) break
                int maxRange = 0
                searchEngines.each { searchEngine ->
                    int searchEngineRange = 0
                    int j = i
                    while (j < numQueries && queries.get(j) != searchEngine) {
                        searchEngineRange += 1
                        j += 1
                    }
                    maxRange = Math.max(searchEngineRange, maxRange)
                }
                i += maxRange

                if (i >= numQueries) {
                    break
                }

                switches += 1
            }


            def result = "Case #${caseNumber}: ${switches}"
            System.out.println(result)
            testOutput.writeLine(result)

            caseNumber++
        }

        testOutput.close()
    }
}
