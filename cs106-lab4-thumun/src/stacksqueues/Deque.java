package stacksqueues;

/**
 * Interface for Queues.
 * @param <E> the type of data in the stack
 */
public interface Deque<E> {
	
	void addFirst(E element) throws FullQueueException;
	
	void addLast(E element) throws FullQueueException;
	
	E removeFirst() throws EmptyQueueException;
	
	E removeLast() throws EmptyQueueException;
	
	E first() throws EmptyQueueException;
	
	E last() throws EmptyQueueException;
	
	int size();
	
	boolean isEmpty();
}