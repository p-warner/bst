package test;

public class BinarySearchTree<K, V> implements BinarySearchTreeI<K, V> {
	private class Node<K, V> {
		public K key;
		public V value;
		public Node<K, V> left, right;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = right = null;
		}
	} // INNER CLASS

	private Node<K, V> root; // globally scoped root var

	BinarySearchTree() { // bst constructor
		root = null;
	}

	private Node<K, V> searchtree(Node<K, V> curr, K key) {
		if (curr == null) {
			return null;
		}
		if (((Comparable) key).compareTo(curr.key) == 0) {
			return curr;

		}
		if (((Comparable) key).compareTo(curr.key) < 0) {
			return searchtree(curr.left, key);
		}
		return searchtree(curr.right, key);

	}

	public boolean contains(K key) {
		Node<K, V> tmp = searchtree(root, key);

		if (tmp == null) {
			return false;
		}
		return true;
	}

	public V get(K key) {
		Node<K, V> tmp = searchtree(root, key);
		if (tmp == null) {
			return null;
		}
		return tmp.value;
	}

	public void printinorder() {
		inOrderTraversal(root);
		System.out.print("\n");

	}

	private void inOrderTraversal(Node<K, V> curr) {
		if (curr != null) {
			inOrderTraversal(curr.left);
			System.out.print(curr.key + " ");
			inOrderTraversal(curr.right);

		}
	}

	private Node<K, V> minimumKey(Node<K, V> curr) {
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}

	private Node<K, V> add(Node<K, V> curr, K key, V value) {
		if (curr == null)
			return new Node<K, V>(key, value);
		if (((Comparable) key).compareTo(curr.key) < 0)
			curr.left = add(curr.left, key, value);
		else if (((Comparable) key).compareTo(curr.key) > 0)
			curr.right = add(curr.right, key, value);

		return curr;

	}

	@Override
	public void add(K key, V value) {
		root = add(root, key, value);

	}

	private void delete(Node<K, V> node, K key) {
		Node<K, V> parent = null;
		Node<K, V> curr = node;
		// setup pointers
		while (curr != null && ((Comparable) key).compareTo(curr.key) != 0) {
			parent = curr;
			// left or right decision
			if (((Comparable) key).compareTo(curr.key) < 0) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		if (curr == null) {
			return;
		}
		// three cases
		if (curr.left == null && curr.right == null) {
			// is leaf
			if (curr == root) {
				root = null;
			} else {
				if (parent.left == curr) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			}
		} else if (curr.left != null && curr.right != null) {
			// two children
			Node<K, V> successor = minimumKey(curr.right);

			K successorKey = successor.key;
			V successorValue = successor.value;

			delete(root, successorKey);
			curr.key = successorKey;
			curr.value = successorValue;
		} else {
			// one child
			Node<K, V> child = (curr.left == null) ? curr.right : curr.left;
			if (curr == root) {
				root = child;
			} else {
				if (curr == parent.left) {
					parent.left = child;
				} else {
					parent.right = child;
				}
			}

		}
	}

	@Override
	public void remove(K key) {
		delete(root, key);
	}
}
