package com.algorithms;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Swapping Fun.
 */
public class SwappingFunTest {

	@Test
	public void testSixElementAlternativeSwap(){
		int[] array = {1, 2, 3, 4, 5, 6};
		reArrange(array);

		assertTrue(Arrays.equals(new int [] {1, 4, 2, 5, 3, 6}, array));

	}

	@Test
	public void testTenElementAlternativeSwap() {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		reArrange(array);

		assertTrue(Arrays.equals(new int [] {1, 6, 2, 7, 3, 8, 4, 9, 5, 10}, array));
	}

	void reArrange(int [] targetList) {
		int numElements = targetList.length;
		for (int i = 1; i <= numElements /2 - 1; i++) {
			for (int j = 0; j < i; j++) {
				int tempValue = targetList[numElements / 2 - (i - 2 * j)];
				targetList[numElements / 2 - (i - 2 * j)] = targetList[numElements/ 2 - (i - 2*j -1)];
				targetList[numElements/ 2 - (i - 2*j -1)] = tempValue;
			}
		}
	}
}
