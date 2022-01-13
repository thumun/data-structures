package polling.treesheaps;

/**
 * 
 * 
 * @author Avi Patel, Neha Thumu
 * @version April 14, 2021
 * @param <E>
 */

public class LinkedBinaryTree<E extends Comparable<E>>
		implements BinaryTree<E> {

	private E element;
	private LinkedBinaryTree<E> left;
	private LinkedBinaryTree<E> right;

	/**
	 * constructor for LinkedBinaryTree class. Sets element, right, and left
	 * variables
	 * 
	 * @param E element (type dependent on input)
	 * @return N/A
	 */
	public LinkedBinaryTree(E element) {
		this.element = element;
		this.left = null;
		this.right = null;
	}

	/**
	 * default constructor for LinkedBinaryTree class. Sets element, right, and
	 * left variables all as null
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public LinkedBinaryTree() {
		this(null);
	}

	/**
	 * sets the root as the input
	 * 
	 * @param E elem (type dependent on input)
	 * @return N/A
	 */
	public void setElement(E elem) {
		this.element = elem;
	}

	/**
	 * returning the left child
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public LinkedBinaryTree<E> getLeft() {
		return this.left;
	}

	/**
	 * returning the right child
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public LinkedBinaryTree<E> getRight() {
		return this.right;
	}

	/**
	 * sets 'this' 's left child as input
	 * 
	 * @param LinkedBinaryTree<E> n (object of LinkedBinaryTree)
	 * @return N/A
	 */
	public void setLeft(LinkedBinaryTree<E> n) {
		this.left = n;
	}

	/**
	 * sets 'this' 's right child as input
	 * 
	 * @param LinkedBinaryTree<E> n (object of LinkedBinaryTree)
	 * @return N/A
	 */
	public void setRight(LinkedBinaryTree<E> n) {
		this.right = n;
	}

	/**
	 * logic for actually adding an element to the LinkedBinaryTree<E>
	 * 
	 * @param E element (element in the tree)
	 * @return if tree empty, setting first element as input, less than: if
	 *         there is not already a child, element set as child to previous
	 *         (linked as previous.left); else insert recursively called to
	 *         create a new parent, greater than: if there is not already a
	 *         child; else insert recursively called to create a new parent,
	 *         element set as child to previous (linked as previous.right),
	 *         equal to: overwrites current element
	 */
	@Override
	public void insert(E element) {
		// if elements are the same --> "rewrite" the note
		if (this.isEmpty()) {
			this.element = element;
		} else if (element.compareTo(this.element) < 0) {
			if (this.left == null) {
				this.left = new LinkedBinaryTree<E>(element);
			} else {
				this.left.insert(this, element);
			}
		} else if (element.compareTo(this.element) == 0) {
			this.element = element;
		} else if (element.compareTo(this.element) > 0) {
			if (this.right == null) {
				this.right = new LinkedBinaryTree<E>(element);
			} else {
				this.right.insert(this, element);
			}
		}
	}

	/**
	 * logic for adding an element to the LinkedBinaryTree<E>. Done in a
	 * recursive fashion.
	 * 
	 * @param LinkedBinaryTree<E> n (the tree), E element (element in the tree)
	 * @return if input tree empty, creating a new tree that passes element-
	 *         making it the first element in the tree (base case), less than:
	 *         input added as the left child, greater than: input added as the
	 *         right child, equal to: input overwrites current node
	 */
	private LinkedBinaryTree<E> insert(LinkedBinaryTree<E> n, E element) {
		// base case is for first element of tree
		if (n == null) {
			return new LinkedBinaryTree<E>(element);
		}
		// if elements are the same --> "rewrite" the note

		if (element.compareTo(n.getRootElement()) < 0) {
			// add element to the left subtree
			n.setLeft(insert(n.getLeft(), element));
			return n;
		} else if (element.compareTo(n.getRootElement()) == 0) { // rewriting
																	// logic
			n.setElement(element);
			return n;
		} else {
			// add element to the right subtree
			n.setRight(insert(n.getRight(), element));
			return n;
		}
	}

	/**
	 * Determines the size of the LinkedBinaryTree<E>
	 * 
	 * @param N/A
	 * @return root element of binary tree
	 */
	@Override
	public E getRootElement() {
		return this.element;
	}

	/**
	 * Determines the size of the LinkedBinaryTree<E>
	 * 
	 * @param N/A
	 * @return the size of the LinkedBinaryTree<E>
	 */
	public int size() {
		return computeSize(this);
	}

	/**
	 * Determines the size of the LinkedBinaryTree<E> recursively
	 * 
	 * @param n: the input LinkedBinaryTree<E>
	 * @return the size, or number of nodes, of the LinkedBinaryTree<E>
	 */
	private int computeSize(LinkedBinaryTree<E> n) {
		if (this.getRootElement() == null || n == null) {
			return 0;
		} else {
			return 1 + computeSize(n.getLeft()) + computeSize(n.getRight());
		}
	}

	/**
	 * Determines if the LinkedBinaryTree<E> is empty
	 * 
	 * @param N/A
	 * @return true if the LinkedBinaryTree<E> is empty, else false
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.size() == 0;
	}

	/**
	 * the toString method for in-order (one of the ways to traverse through the
	 * LinkedBinaryTree<E>)
	 * 
	 * @param N/A
	 * @return the nodes printed in in-order format
	 */
	@Override
	public String toStringInOrder() {
		// TODO Auto-generated method stub
		return inOrder(this);
	}

	/**
	 * logic for adding elements in the in-order format. Done in a recursive
	 * fashion.
	 * 
	 * @param LinkedBinaryTree<E> n (object of LinkedBinaryTree<E>)
	 * @return empty string if empty (base case) or the elements in the tree in
	 *         in-order format
	 */
	private String inOrder(LinkedBinaryTree<E> n) {
		if (n == null) {
			return "";
		} else {
			return inOrder(n.getLeft()) + " " + n.getRootElement()
					+ inOrder(n.getRight());
		}
	}

	/**
	 * the toString method for pre-order (one of the ways to traverse through
	 * the LinkedBinaryTree<E>)
	 * 
	 * @param N/A
	 * @return the nodes printed in pre-order format
	 */
	@Override
	public String toStringPreOrder() {
		return preOrder(this);
	}

	/**
	 * logic for adding elements in the pre-order format. Done in a recursive
	 * fashion.
	 * 
	 * @param LinkedBinaryTree<E> n (object of LinkedBinaryTree<E>)
	 * @return empty string if empty (base case) or the elements in the tree in
	 *         pre-order format
	 */
	private String preOrder(LinkedBinaryTree<E> n) {
		if (n == null) {
			return "";
		} else {
			return n.getRootElement() + " " + preOrder(n.getLeft())
					+ preOrder(n.getRight());
		}
	}

	/**
	 * logic for adding elements in the post-order format. Done in a recursive
	 * fashion.
	 * 
	 * @param LinkedBinaryTree<E> n (object of LinkedBinaryTree<E>)
	 * @return empty string if empty (base case) or the elements in the tree in
	 *         post-order format
	 */
	private String postOrder(LinkedBinaryTree<E> n) {
		if (n == null) {
			return "";
		} else {
			return postOrder(n.getLeft()) + postOrder(n.getRight()) + " "
					+ n.getRootElement();
		}
	}

	/**
	 * the toString method for post-order (one of the ways to traverse through
	 * the LinkedBinaryTree<E>)
	 * 
	 * @param N/A
	 * @return the nodes printed in post-order format
	 */
	public String toStringPostOrder() {
		return postOrder(this);
	}

	/**
	 * the toString method for LinkedBinaryTree<E>
	 * 
	 * @param N/A
	 * @return the nodes printed in pre-order, in-order and post-order
	 */
	public String toString() {
		return "Tree:\nPre:\t" + this.toStringPreOrder().trim() + "\nIn:\t"
				+ this.toStringInOrder().trim() + "\nPost:\t"
				+ this.toStringPostOrder().trim();
	}
}
