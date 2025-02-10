package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BstTest {

	@Test
	void testGeneral() {
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
		
		bst.add(3, "THREE");
		bst.add(5, "FIVE");
		bst.add(2, "TWO");
		bst.add(4, "FOUR");
		bst.add(1, "ONE");
		
		System.out.println("Print inorder");
		//bst.printInorder();
	}
	
	@Test
	void testAdd() {
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
		
		bst.add(3, "THREE");
		bst.add(5, "FIVE");
		bst.add(2, "TWO");
		bst.add(4, "FOUR");
		bst.add(1, "ONE");
	}
	
	@Test
	void testContains() {
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
		
		bst.add(3, "THREE");
		bst.add(5, "FIVE");
		bst.add(2, "TWO");
		bst.add(4, "FOUR");
		bst.add(1, "ONE");
		
		assertTrue(bst.contains(1));
		assertTrue(bst.contains(2));
		assertTrue(bst.contains(3));
		assertTrue(bst.contains(4));
		assertTrue(bst.contains(5));
		
		assertFalse(bst.contains(0));
		assertFalse(bst.contains(6));
	}
	
	@Test
	void testRemove() {
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
		
		bst.add(3, "THREE");
		bst.add(5, "FIVE");
		bst.add(2, "TWO");
		bst.add(4, "FOUR");
		bst.add(1, "ONE");
		
		bst.remove(1);
		assertFalse(bst.contains(1));
		
		bst.remove(4);
		assertFalse(bst.contains(4));
		
		bst.remove(6);
		assertFalse(bst.contains(6));
	}
	
	@Test
	void testGet() {
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
		
		bst.add(3, "THREE");
		bst.add(5, "FIVE");
		bst.add(2, "TWO");
		bst.add(4, "FOUR");
		bst.add(1, "ONE");
		
		System.out.println(bst.get(1));
		assertEquals("ONE", bst.get(1));
		assertEquals("FOUR", bst.get(4));
		assertNull(bst.get(6));
	}
}
