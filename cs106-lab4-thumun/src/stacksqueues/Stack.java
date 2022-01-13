package stacksqueues;

import java.util.EmptyStackException;

/**
 * Interface for Stacks.
 * @param <E> the type of data in the stack
 */
public interface Stack<E> {
	
	void push(E element);
	
	E pop() throws EmptyStackException;
	
	E peek() throws EmptyStackException;
	
	int size();
	
	boolean isEmpty();
}
