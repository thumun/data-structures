package stacksqueues;

@SuppressWarnings("serial")
public class FullQueueException extends RuntimeException {
	public FullQueueException() {
		super("Queue is full.");
	}
}