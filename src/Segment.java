/**
 * Segment class used in priority queue (PriorityQueue.java)
 * Uses queue class Queue.java
 * Similar to node in linked list. Holds Queue and time as data and next segment
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class Segment {

	private double time; // time associatied with segment
	private Queue queue; // queue of events
	private Segment next; // next Segement associated with current Segment

	/**
	 * Instantiates a new Segment
	 * @param time Time associatied with segment
	 */
	public Segment(double time) {
		this.time = time;
		queue = new Queue();
		next = null;
	}

	/**
	 * Gets time associatied with segment
	 * @return double Time associatied with segment
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Gets the queue of events
	 * @return Queue Returns queue of events
	 */
	public Queue getEvents() {
		return queue;
	}

	/**
	 * Gets the next segemnt
	 * @return Segment Returns queue of events
	 */
	public Segment getNext() {
		return next;
	}

	/**
	 * Sets the next segemnt
	 * @param nextSegment sets the next segment
	 */
	public void setNext(Segment nextSegment) {
		next = nextSegment;
	}
}
