/**
 * BST for storing generics. Functions like a Map but is a linked tree.
 * By making it Map-like with K, V pairs we make the BST much more flexible.
 * 
 * @author pwarner@pct.edu
 *
 * @param <K>
 * @param <V>
 */
public class BinarySearchTree<K, V> implements BinarySearchTreeI<K, V> {
    
	/**
	 * INNERCLASS, CONSTRUCTOR, ROOT GLOBAL
	 */
	private class Node<K, V> { 
        public K key;
        public V value;
        public Node<K, V> left, right; 
   
        public Node(K key, V obj){ 
            this.key = key; 
            this.value = obj;
            left = right = null; 
        } 
    }
    
    //Globally scoped root for entry into the tree from all methods in the class.
    private Node<K, V> root; 
    
    BinarySearchTree(){ 
        root = null; 
    }
    
    /**
     * ADD
     */
    @Override
    public void add(K key, V obj)  { 
        root = add(root, key, obj); 
    }
    
    private Node<K, V> add(Node<K, V> curr, K key, V value) { 
        //Check bounds
        if (curr == null) { 
            return new Node<K, V>(key, value);
        } 
        
        if(((Comparable) key).compareTo(curr.key) < 0) {
            curr.left = add(curr.left, key, value);
        }else if (((Comparable) key).compareTo(curr.key) > 0) {
            curr.right = add(curr.right, key, value); 
        }
        
        return curr; 
    }
    
    
    /**
     * CONTAINS
     */
    private Node<K, V> searchTree(Node<K, V> curr, K key)  {  
    	if (curr == null)
            return null;
        
        if(((Comparable) key).compareTo(curr.key) == 0)
        	return curr;
        
        if (((Comparable) key).compareTo(curr.key) < 0) 
            return searchTree(curr.left, key);
        
        return searchTree(curr.right, key); 
    }
    
    @Override
    public boolean contains(K key) {
    	Node<K, V> temp = searchTree(root, key);
    	
    	if(temp != null) 
    		return true;
    	
    	return false;
    }
    
    /**
     * GET
     */
    @Override
    public V get(K key)  { 
        Node<K, V> temp = searchTree(root, key); 
        
        if (temp != null) {
        	//printChildren(temp);
            return temp.value;//found, return the value of the node
        }else{
            return null;
        }
    } 
    
    public void printInorder() { 
        inorderTraversal(root); 
        System.out.println("\n");
    } 
   
    private void inorderTraversal(Node<K, V> root) { 
        if (root != null) { 
        	inorderTraversal(root.left); 
            System.out.print(root.key + " "); 
            inorderTraversal(root.right); 
        } 
    } 
    
    private Node<K, V> minimumKey(Node<K, V> curr){
		while (curr.left != null) {
			curr = curr.left;
		}
		
		return curr;
	}
    
    /**
     * REMOVE 
     */
    @Override
    public void remove(K key) {
    	System.out.println("Removing " + key);
        delete(root, key); 
    }
    
    private void delete(Node<K, V> root, K key){
    	//Remember walking trough a linked list with two pointers? 
    	//This is the same thing
		Node<K, V> parent = null;
		Node<K, V> curr = root;

		// search key in BST and set its parent pointer
		while (curr != null && ((Comparable) key).compareTo(curr.key) != 0){
			parent = curr;

			if(((Comparable) key).compareTo(curr.key) < 0) {
				curr = curr.left;
			}else{
				curr = curr.right;
			}
		}
		
		if (curr == null){
			return;//Key not found...
		}
		
		//Pointers are setup at this point.

		//1- node is a leaf
		if (curr.left == null && curr.right == null){
			//System.out.println("Delete leaf.");
			// if node to be deleted is not a root node, then set its
			// parent left/right child to null
			if (curr == root) {
				root = null;
			}else {
				if (parent.left == curr) {
					parent.left = null;
				}else{
					parent.right = null;
				}
			}
		}else if(curr.left != null && curr.right != null){
			//2- node to be deleted has two children
			//System.out.println("Two children");
			// find its in-order successor node
			Node<K, V> successor  = minimumKey(curr.right);

			// store successor K, V
			K successorKey = successor.key;
			V successorValue = successor.value;

			// recursively delete the successor. Note that the successor
			// will have at-most one child (right child)
			delete(root, successorKey);

			// Copy the value of successor to current node
			curr.key = successorKey;
			curr.value = successorValue;
		}else{
			//3- node to be deleted has only one child
			//System.out.println("One child.");
			// find child node
			Node<K, V> child = (curr.left == null) ? curr.right : curr.left ;

			// if node to be deleted is not a root node, then set its parent
			// to its child
			if (curr == root){
				// if node to be deleted is root node, then set the root to child
				root = child;
			}else{
				if(curr == parent.left){
					parent.left = child;
				}else{
					parent.right = child;
				}
			}
		}

		return;
    }
     
    /**
     * OPTIONAL
     * 
     */
    private void printChildren(Node<K, V> root) {
    	if(root.left != null) {
    		System.out.println("Left- " + root.left.key);
    	}else {
    		System.out.println("Left- null");
    	}
    	
    	if(root.right != null) {
    		System.out.println("Right- " + root.right.key);
    	}else {
    		System.out.println("Right- null");
    	}
    }
}
