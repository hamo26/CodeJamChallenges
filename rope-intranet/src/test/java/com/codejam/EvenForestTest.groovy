package com.codejam

import org.junit.Test

/**
 * Even Forest.
 */
class EvenForestTest {

	class EventForest {
		private final def activeSet
		private final def adjacencyList

		EventForest(final def adjacencyList) {
			this.activeSet = adjacencyList.keySet()
			this.adjacencyList = adjacencyList;

			relaxAdjacencyList();
		}

		def countCuts() {
			int numNodes = adjacencyList.size()
			int subTreeSize = 2
			int cuts = 0;

			while (numNodes > subTreeSize) {
				for (node in adjacencyList) {
					if (1 + node.value.size() == subTreeSize) {
						numNodes -= subTreeSize
						cuts++;
					}
				}

				subTreeSize *= 2;
			}

			cuts
		}

		def relaxAdjacencyList() {
			for (node in adjacencyList) {
				def relaxedNodeValues = []
				for (int childNode : node.value) {
					def stack = new Stack<>()
					stack.push(childNode)

					while (!stack.isEmpty()) {
						def poppedNode = stack.pop()
						relaxedNodeValues.add(poppedNode)
						for (int childChildNode : adjacencyList[poppedNode]) {
							stack.push(childChildNode)
						}
					}
				}

				node.value = relaxedNodeValues
			}
		}
	}

	@Test
	void "Test sample input"() {
		assert 2 == new EventForest([1:[2,3,6] as Set,
									 2:[7,5] as Set,
									 3:[4] as Set,
									 4:[] as Set,
									 5:[] as Set,
									 6:[8] as Set,
									 7:[] as Set,
									 8:[9,10] as Set,
									 9:[] as Set,
									 10:[] as Set]).countCuts()
	}

	@Test
	void "Test 4 cuts"() {
		assert 2 == new EventForest([1:[2,3,6] as Set,
									 2:[7,5] as Set,
									 3:[4] as Set,
									 4:[12] as Set,
									 5:[] as Set,
									 6:[8] as Set,
									 7:[11] as Set,
									 8:[9,10] as Set,
									 9:[] as Set,
									 10:[] as Set,
									 11:[] as Set,
									 12:[] as Set]).countCuts()
	}

	@Test
	void "Test 0 cuts"() {
		assert 0 == new EventForest([1:[2,3,6] as Set]).countCuts()
	}

	@Test
	void "Test 1 cut"() {
		assert 1 == new EventForest([1: [2] as Set,
									 2: [3] as Set,
									 4: [1] as Set]).countCuts()
	}
}
