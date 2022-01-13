/** 
 * This class is used to create a linked stack
 * 
 * There are methods are the different functions of the linked stack
 * 
 * */

package stacksqueues;

import java.util.EmptyStackException;

public class LinkedStack<E> implements Stack<E> {
	private Node<E> header;
	private int size = 0;

	/**
	 * constructor for Linked Stack: initializes header as null and size as zero
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public LinkedStack() {
		header = null;
		size = 0;
	}

	/**
	 * adds an element to the linked stack and increments size (this element is
	 * added to the top of the stack)
	 * 
	 * @param N/A
	 * @return N/A
	 */
	@Override
	public void push(E element) {
		header = new Node<>(element, header);
		size += 1;
	}

	/**
	 * removes and returns the topmost element of the linked stack and decreases
	 * the size
	 * 
	 * @param N/A
	 * @return topmost element in the stack
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			Node<E> temp = header; // header is the last element added/first in
									// stack
			header = header.getNext();
			size -= 1;
			return temp.getElement();
		}
	}

	/**
	 * returns the topmost element of the linked stack
	 * 
	 * @param N/A
	 * @return topmost element in the stack
	 */
	@Override
	public E peek() throws EmptyStackException { // like pop but not removed
													// from stack
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return header.getElement();
		}
	}

	/**
	 * returns the size of the linked stack
	 * 
	 * @param N/A
	 * @return size as an integer
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * checks if the stack is empty by checking if size is zero
	 * 
	 * @param N/A
	 * @return boolean depending on if size is zero
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * returns the content of the stack in the form of a string
	 * 
	 * @param N/A
	 * @return elements of the stack as strings separated by commas
	 */
	@Override
	public String toString() {
		LinkedStack<E> tempStack = new LinkedStack<E>();
		Node<E> tempHeader = header;
		String output = "(";

		if (isEmpty()) {
			output += " Nothing in the stack ";

		} else {

			while (size != tempStack.size) {
				tempStack.push(tempHeader.getElement());
				tempHeader = tempHeader.getNext();
			}

			while (tempStack.size != 1) {
				output += tempStack.pop() + ", ";

			}
		}
		output += tempStack.pop() + ")";
		return output;
	}

}