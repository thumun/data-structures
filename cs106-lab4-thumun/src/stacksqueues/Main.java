/**
* Lab 4 for CS106 
*
* This lab is creating and implementing deque and linked stack.  Also implemented postfix and infix. 
* 
*
* @author: Neha Thumu 
* @version: April 9, 2021
*/

package stacksqueues;

import java.util.EmptyStackException;

public class Main {

	public static void main(String[] args) {
		// tests for LinkedStack and ArrayDeque

		checkStackMethods();
		checkDequeMethods();

	}

	/**
	 * testing the methods and checking of creating the linked stack worked
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public static void checkDequeMethods() throws EmptyQueueException {
		int capacity = 5;
		ArrayDeque<String> testDeque = new ArrayDeque<String>(capacity);

		System.out.println(testDeque.isEmpty() == true);
		System.out.println(testDeque.size() == 0);

		for (int i = 0; i < capacity; i++) {
			testDeque.addLast(String.valueOf(i));
		}

		System.out.println(testDeque.isEmpty() == false);
		System.out.println(testDeque.size() == capacity);

		System.out.println(testDeque.removeFirst().equals("0"));
		System.out.println(testDeque.removeLast().equals("4"));
		testDeque.addFirst("test");
		testDeque.addFirst("test2");

		try {
			testDeque.addLast("hello"); // should throw exception
		} catch (FullQueueException e) {
			System.out.println("FullQueueException");
		}

		System.out.println(testDeque); // checking deque output
	}

	/**
	 * testing the methods and checking of creating the deque worked
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public static void checkStackMethods() throws EmptyStackException {
		try {
			LinkedStack<String> testStack = new LinkedStack<String>();

			System.out.println(testStack.isEmpty() == true);
			System.out.println(testStack.size() == 0);

			for (int i = 0; i < 20; i++) {
				testStack.push(String.valueOf(i));
			}

			System.out.println(testStack.isEmpty() == false);
			System.out.println(testStack.size() == 20);

			System.out.println(testStack.peek().equals("19"));
			System.out.println(testStack.size() == 20);

			System.out.println(testStack.pop().equals("19"));
			System.out.println(testStack.size() == 19);

			testStack.push("20");
			System.out.println(testStack.size() == 20);

			System.out.println(testStack); // checking the stack output

		} catch (EmptyStackException e) {
			System.out.println("EmptyStackException");
		}

	}
}
