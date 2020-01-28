/**
 * Queue implementation using an Array
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class Queue {

	private Object[] queue; // the Array that represents the queue
	private int size; // number of items in the array
	private int front; // first element
	private int rear; // last element

	/**
	 * Instantiates a queue
	 */
	public Queue() {
		queue = new Object[0];
	}

	/**
	 * Instantiates a queue of a length
	 * @param initLength the length of the queue
	 */
	public Queue(int initLength) {
		if (initLength < 0)
			throw new IllegalArgumentException("capacity requested is negative");
		queue = new Object[initLength];
	}

	/**
	 * adds an object obj to a queue placing it in the order of arrival
	 * relative to other items added to the queue--first in, first out
	 * (FIFO)
	 * @param obj Object to be added to the queue
	 */
	public void add(Object obj) {

		if (queue.length == 0) {  // array non-existant, create it and insert first object
			queue = new Object[1];
			size = 1;
			queue[0] = obj;
		}
		else if (size == 0)  {  // adding to empty queue
			rear = 0;
			front = 0;
			size = 1;
			queue[0] = obj;
		}
		else  {  // general case: array exists and non-empty
			if (size == queue.length) {  // allocate bigger array if needed
				Object[] new_queue = new Object[2 * queue.length + 1];
				if (front <= rear)  // queue has not wrapped, so make simple copy to new space
					System.arraycopy(queue, front, new_queue, 0, size);
				else if (front > rear) {  // queue has wrapped, so copy in two chunks
					System.arraycopy(queue, front, new_queue, 0, queue.length - front);
					System.arraycopy(queue, 0, new_queue, queue.length - front, rear + 1);
					front = 0;
					rear = size - 1;
				}
				queue = new_queue;
			}

			rear = (rear + 1) % queue.length; // index of first empty space in queue
			queue[rear] = obj; // places event at first empty space in queue
			size++;

		}
	}

	/**
	 * removes and returns the object placed in a queue prior
	 * to any other items presently in the queue
	 * @return Object First element in queue
	 */
	public Object remove() {

		if (size == 0)
			return null;

		Object obj = queue[front];
		front = (front + 1) % queue.length;
		size--;
		return obj;
	}

	/**
	 * returns the integer quantity of items currently present in the
	 * queue
	 * @return int Length of the queue
	 */
	public int length() {
		return size;
	}
}
