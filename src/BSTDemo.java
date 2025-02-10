import java.util.Random;

public class BSTDemo {

	public static void main(String[] args) {
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();
		
		System.out.println("Adding 50");
		bst.add(50, "My own!");
		System.out.println("Adding 100");
		bst.add(100, "My own!");
		System.out.println("Adding 25");
		bst.add(25, "My own!");
		
		bst.printInorder();
		
		//Test removal of leaves
		System.out.println("Remove(25) leaf");
		bst.remove(25);
		bst.printInorder();
		
		System.out.println("Remove(100) leaf");
		bst.remove(100);
		bst.printInorder();
		
		System.out.println("Adding 100");
		bst.add(100, "My own!");
		System.out.println("Adding 25");
		bst.add(25, "My own!");
		System.out.println("Adding 110");
		bst.add(110, "My own!");
		System.out.println("Adding 90");
		bst.add(90, "My own!");
		System.out.println("Adding 12");
		bst.add(12, "My own!");
		System.out.println("Adding 28");
		bst.add(28, "My own!");
		
		
		//Test removal with 1 child left
		System.out.println("Adding 9, left child of 12.");
		bst.add(9, "Payload");
		
		bst.printInorder();
		
		System.out.println("Remove(12)");
		bst.remove(12);
		bst.printInorder();
		
		//Test removal of 1 child with right
		System.out.println("Adding 10, right child of 9.");
		bst.add(10, "Payload");
		
		bst.printInorder();
		
		System.out.println("Remove(9)");
		bst.remove(9);
		bst.printInorder();
		
		//Test removal of node with 2 children
		System.out.println("remove(25)");
		bst.remove(25);
		bst.printInorder();
		
		//print Children
		//bst.get(28);
		
		System.out.println("remove(100)");
		bst.remove(100);
		bst.printInorder();
		System.out.println("90s children");
		bst.get(90);
		
		System.out.println("110s children");
		bst.get(110);
		
		System.out.println("50s children");
		bst.get(50);
		
		//Test removing the root
		System.out.println("Remove 50 (the root)");
		bst.remove(50);
		bst.printInorder();
		
		
		//Test search
		bst.contains(90);
		
		
		
		//Pretend we work at a company that has millions of items in inventory.
		//We want to provide fast access to all items in the inventory
		//Each item is an inventory object
		//Use a tree to solve the problem
		
		//hash a unique component of the object
		//use the hash as a key into our BST!
		
		//Congrats, you just thought of a way to implement a database
		
		//Create a hash to use as keys!
		System.out.println(hash("hello"));
		System.out.println(hash("bye"));
		System.out.println(hash("Phil"));
		System.out.println(hash("Warner"));
		
		//Caveats 
		//what happens when you enter the same key?
			//always put it on the right/left?
			//same idea as the Map ADT, chaining is a great idea here
		
	}
	
	/**
	 * Simple hash to turn a string into an int.
	 * 
	 * @param str
	 * @return
	 */
	private static int hash(String str) {
		int hash = 0;
		for (int i = 0; i < str.length(); i++) {
		      char c = str.charAt(i);
		      hash = ((hash << 5) - hash) + c;
		      hash = hash & hash; // Convert to 32bit integer
	    }
		
		return hash;
	}

}
