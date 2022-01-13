package polling.treesheaps;

/**
 * Interface for BinaryTrees.
 * @param <E> the type of data in the tree
 */
public interface BinaryTree<E extends Comparable<E>> {

	void insert(E element);
	
	E getRootElement();
	
	int size();
	
	boolean isEmpty();
	
	String toStringInOrder();
	
	String toStringPreOrder();
	
	String toStringPostOrder();	
}
