/**
* Lab 6 for CS106 
*
* The methods for creating/maintaining the Array Heap class
*
* @author: Neha Thumu 
* @version: April 30, 2021
*/

package polling.treesheaps;

import java.util.ArrayList;

public class ArrayHeap<E extends Comparable<E>> implements PriorityQueue<E> {
	ArrayList<E> tree;

	/**
	 * constructor for ArrayHeap class. Initializes tree
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public ArrayHeap() {
		tree = new ArrayList<>();
	}

	/**
	 * constructor for PollingData class. Creates heap based on existing
	 * arraylist of data
	 * 
	 * @param ArrayList[] array (made from existing arraylist)
	 * @return N/A
	 */
	public ArrayHeap(ArrayList<E> array) {
		tree = array;
		for (int i = 0; i < array.size(); i++) {
			bubbleUp(i);
		}
	}

	/**
	 * inserting an element to the array heap
	 * 
	 * @param E element (type dependent on input)
	 * @return N/A
	 */
	@Override
	public void insert(E element) {
		// adding to the end of the array
		tree.add(element);

		// initialized the first child for bubbleUp
		int childIndex = tree.size() - 1;

		bubbleUp(childIndex);

	}

	/**
	 * used to re-heapify tree after an element is inserted
	 * 
	 * @param int childIndex
	 * @return N/A
	 */
	private void bubbleUp(int childIndex) {

		while (childIndex > 0) {
			int parentIndex = getParentIndex(childIndex);

			// if child greater than parent, swap
			if (tree.get(childIndex).compareTo(tree.get(parentIndex)) > 0) {
				swap(childIndex, parentIndex);
				childIndex = parentIndex;
				parentIndex = getParentIndex(childIndex);

			} else {
				// if child is in right position, stop the loop
				break;
			}

		}
	}

	/**
	 * returns the max element of the array heap
	 * 
	 * @param N/A
	 * @return the max element of the array heap
	 */
	@Override
	public E max() {
		if (isEmpty()) {
			return null;
		} else {
			return tree.get(0);
		}
	}

	/**
	 * returns and removes the max element of the array heap. Then uses
	 * bubbleDown to re-heapify the heap
	 * 
	 * @param N/A
	 * @return the max element of the array heap
	 */
	@Override
	public E removeMax() {

		if (isEmpty()) {
			return null;
		}

		E output = tree.get(0);

		// if table only had one element, clearing table
		if (tree.size() == 1) {
			tree.clear();
			return output;

		} else {
			// last index in first position
			tree.set(0, tree.get(tree.size() - 1));
			tree.remove(tree.size() - 1);

			bubbleDown(tree.size());

		}
		return output;
	}

	/**
	 * used to re-heapify a heap after the max element (root) is removed
	 * 
	 * @param int endIndex (size of tree)
	 * @return N/A
	 */
	private void bubbleDown(int treeSize) {

		int currentIndex = 0;
		int indexCompareTo = 0;

		// runs as long as the there aren't children left
		while (leftChild(currentIndex) != currentIndex
				&& leftChild(currentIndex) < treeSize) {

			// checks if right child is present
			if (rightChild(currentIndex) != currentIndex
					&& rightChild(currentIndex) < treeSize) {

				// checking if left or right child is greater
				// greater value is the one that will be compared w/ parent
				if (tree.get(rightChild(currentIndex))
						.compareTo(tree.get(leftChild(currentIndex))) > 0) {
					indexCompareTo = rightChild(currentIndex);
				} else {
					indexCompareTo = leftChild(currentIndex);
				}
			}

			// if there's no right child, compare with left child
			else {
				indexCompareTo = leftChild(currentIndex);
			}

			// compare parent with the child in question then swap if child
			// greater than parent
			if (tree.get(currentIndex)
					.compareTo(tree.get(indexCompareTo)) < 0) {
				swap(indexCompareTo, currentIndex);

				currentIndex = indexCompareTo;

			} else {
				// everything in the right place so end loop
				break;
			}
		}
	}

	/**
	 * sorting the heap from least to greatest
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public void sort() {
		int size = size();

		for (int i = 0; i < size; i++) {
			swap(0, size - i - 1);
			bubbleDown(size - i - 1);
		}
	}

	/**
	 * returns the size of the array heap
	 * 
	 * @param N/A
	 * @return int size of array heap
	 */
	@Override
	public int size() {
		return tree.size();
	}

	/**
	 * says if tree is empty or not
	 * 
	 * @param N/A
	 * @return boolean depending on if tree is empty
	 */
	@Override
	public boolean isEmpty() {
		return tree.size() == 0;
	}

	/**
	 * swapping the child and parent elements. So child becomes parent and
	 * parent becomes child
	 * 
	 * @param int child (index), int parent (index)
	 * @return N/A
	 */
	private void swap(int child, int parent) {
		E currentElement = tree.get(child);

		tree.set(child, tree.get(parent));
		tree.set(parent, currentElement);
	}

	/**
	 * returns the parent of a given child
	 * 
	 * @param int childIndex
	 * @return parent's index as an int
	 */
	private int getParentIndex(int childIndex) {
		if (childIndex == 0) {
			return 0;
		} else {
			return (childIndex - 1) / 2;
		}
	}

	/**
	 * returns the leftChild of the given parent (if parent has one)
	 * 
	 * @param int parentIndex
	 * @return leftChild or the input (if no leftChild)
	 */
	private int leftChild(int parentIndex) {
		if ((2 * parentIndex) + 1 < size()) {
			return (2 * parentIndex) + 1;
		} else {
			return parentIndex;
		}
	}

	/**
	 * returns the rightChild of the given parent (if parent has one)
	 * 
	 * @param int parentIndex
	 * @return rightChild or the input (if no rightChild)
	 */
	private int rightChild(int parentIndex) {
		if ((2 * parentIndex) + 2 < size()) {
			return (2 * parentIndex) + 2;
		} else {
			return parentIndex;
		}
	}

	/**
	 * returns the elements of the arrayHeap in order (by row- first row is
	 * parent and each following are children)
	 * 
	 * @param N/A
	 * @return String output (elements of arrayHeap by row in string form)
	 */
	@Override
	public String toString() {
		String output = "";
		int index = 0;

		int depth = (int) (Math.log(size()) / Math.log(2)) + 1;

		for (int i = 0; i < depth; i++) {
			index = (int) (Math.pow(2, i) - 1);
			for (int j = index; j < index + Math.pow(2, i); j++) {
				if (j < size()) {
					output += tree.get(j) + " ";
				}
			}
			output += "\n";
		}

		return output;

	}

}
