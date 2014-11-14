package com.algorithms;


import org.junit.Test;

/**
 * Binary tree height test.
 */
public class BinaryTreeHeightTest {

	@Test
	public void testSimpleCase() {
		Node rootNode = new Node();
		Node leftNode = new Node();
		Node rightNode = new Node();

		rootNode.right = rightNode;
		rootNode.left = leftNode;

		assert 1 == height(rootNode);
	}

	@Test
	public void testMoreComplexCase() {
		Node rootNode = new Node();
		Node leftNode = new Node();
		Node rightNode1 = new Node();
		Node rightNode2 = new Node();
		Node rightNode3 = new Node();

		rootNode.left = leftNode;
		rootNode.right = rightNode1;
		rightNode1.right = rightNode2;
		rightNode2.right = rightNode3;

		assert 3 == height(rootNode);
	}



	int height (Node node) {
		if (node == null) {
			return -1;
		} else {
			return Math.max(1+height(node.left), 1+height(node.right));
		}
	}

	class Node {
		Node left;
		Node right;
		Node value;

		@Override
		public boolean equals (Object other) {
			return this.value == ((Node) other).value;
		}
	}

}
