/** 
 * This class is used to create a deque
 * 
 * There are methods are the different functions of the deque. 
 * 
 * addFirst adds an element to the beginning of the deque, addLast adds to the end 
 * of the deque, removeFirst removes the first element from the deque, removeLast removes the
 * last element from the deque, first returns the first element from the deque, last returns
 * the last element from the deque, size returns the size of the deque, isEmpty checks 
 * if the deque is empty, isFull checks if the deque is full, and toString returns the 
 * content of the deque in the form of a string 
 */

package stacksqueues;

public class ArrayDeque<E> implements Deque<E> {
	private E[] nodeArray;
	private int size = 0;
	private int firstIndex = 0;
	private int capacity = 1000;

	/**
	 * constructor for deque: initializes size as zero, capacity as 1000, and
	 * nodeArray as an array of type E (based on input) with length capacity
	 * 
	 * @param N/A
	 * @return N/A
	 */
	@SuppressWarnings("unchecked")
	public ArrayDeque() {
		nodeArray = (E[]) new Object[capacity];
		size = 0;
	}

	/**
	 * constructor for deque: length based on input initializes size as zero,
	 * capacity, and nodeArray as an array of type E (based on input) with
	 * length capacity
	 * 
	 * @param capacity (the length of the array)
	 * @return N/A
	 */
	@SuppressWarnings("unchecked")
	public ArrayDeque(int capacity) {
		nodeArray = (E[]) new Object[capacity];
		size = 0;
		this.capacity = capacity;
	}

	/**
	 * adds element to the beginning of the deque and increments size
	 * 
	 * @param element (type based on input)
	 * @return N/A
	 */
	@Override
	public void addFirst(E element) throws FullQueueException {
		int currentIndex = 0;

		if (isFull()) {
			throw new FullQueueException();
		} else {

			// for the first item added
			if (firstIndex == 0 && isEmpty()) {
				currentIndex = 0;

				// making circular (in case firstindex 0th position in array)
			} else if (firstIndex == 0) {
				// added the end of array but before first index
				currentIndex = capacity - 1;
			} else {
				currentIndex = firstIndex - 1;
			}

			firstIndex = currentIndex;
			nodeArray[currentIndex] = element;
			size += 1;
		}

	}

	/**
	 * adds element to the end of the deque and increments size
	 * 
	 * @param element (type based on input)
	 * @return N/A
	 */
	@Override
	public void addLast(E element) throws FullQueueException {
		if (isFull()) {
			throw new FullQueueException();
		} else {
			// in case firstIndex + size greater than capacity, modulus will
			// make sure added to beginning of array (circular structure)
			int currentIndex = (firstIndex + size) % capacity;
			nodeArray[currentIndex] = element;
			size += 1;
		}
	}

	/**
	 * removes and returns the first element of the deque and decreases size
	 * 
	 * @param N/A
	 * @return the first node in the deque (type based on input)
	 */
	@Override
	public E removeFirst() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}

		E first = nodeArray[firstIndex];
		// re-using idea from addLast
		firstIndex = (firstIndex + 1) % capacity;
		size--;
		return first;
	}

	/**
	 * removes and returns the last element of the deque and decreases size
	 * 
	 * @param N/A
	 * @return the last node in the deque (type based on input)
	 */
	@Override
	public E removeLast() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		// re-using idea from addLast
		int lastIndex = (firstIndex + size - 1) % capacity;
		size--;
		return nodeArray[lastIndex];
	}

	/**
	 * returns the first element of the deque
	 * 
	 * @param N/A
	 * @return the first node in the deque (type based on input)
	 */
	@Override
	public E first() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}

		return nodeArray[firstIndex];
	}

	/**
	 * returns the last element of the deque
	 * 
	 * @param N/A
	 * @return the last node in the deque (type based on input)
	 */
	@Override
	public E last() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		int lastIndex = (firstIndex + size - 1) % capacity;
		return nodeArray[lastIndex];
	}

	/**
	 * returns the size of the deque
	 * 
	 * @param N/A
	 * @return size of the deque
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * checks if deque is empty by checking if size is zero
	 * 
	 * @param N/A
	 * @return boolean depending if size is 0
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * checks if deque is full by comparing size with capacity
	 * 
	 * @param N/A
	 * @return boolean depending if size equal to capacity
	 */
	public boolean isFull() {
		return size == capacity;
	}

	/**
	 * returns content of ArrayDeque in form of string
	 * 
	 * @param N/A
	 * @return string output [content in deque]
	 */
	@Override
	public String toString() {
		String output = "(";

		if (isEmpty()) {
			output += " Nothing in the stack ";

		} else {

			for (int i = 0; i < size; i++) {
				int index = (firstIndex + i) % capacity;
				output += nodeArray[index] + ", ";
			}
		}
		output = output.substring(0, output.length() - 2);
		output += ")";
		return output;
	}

}
