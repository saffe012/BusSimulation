/**
 * A Priority Queue is used for an agenda
 * Assumes that priorities are times and that new items cannot be
 * added at a time previous to current time; so you cannot go back in time.
 * Additions are always made at a time specified relative to current time
 * indicated by time stamp in first time segment (queue) in the priority queue.
 * Uses class Q1 (Queue class using linked list) and class Segment
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class PriorityQueue {

	private Segment seg; // front of linked list representing priority queue

	/**
	 * Instatiates a Priority Queue with a segment starting at time 0
	 */
	public PriorityQueue() {
		seg = new Segment(0);
	}

	/**
	 * Queues a new Event at the end of the queue
	 * @param event Event to add to priority queue
	 * @param time Number of seconds it takes event to occur
	 */
	public void add(Event event, double time) {

		time += getCurrentTime(); // event will occur in 'time' from now

		if (time < seg.getTime())  // attempting to schedule event after time already happened
			System.out.println("Error: trying to go back in time");
		else if (time == seg.getTime())  // time is same as current segment
			seg.getEvents().add(event);
		else { // search list for correct insertion point, then insert
			Segment trailer = seg;
      Segment pointer = seg.getNext();
			while (pointer != null && time > pointer.getTime()) {  //search
				pointer = pointer.getNext();
				trailer = trailer.getNext();
			}
			if (pointer != null && time == pointer.getTime())  // time of event matches the pointer Segment
				pointer.getEvents().add(event);
			else { // add new segment after trailer and before pointer
				Segment temp = new Segment(time);
				temp.getEvents().add(event);
				temp.setNext(pointer);
				trailer.setNext(temp);
			}
		}
	}

	/**
	 * Dequeues the first Event in line and returns that event
	 * @return Event
	 */
	public Event remove() {
		if (this.isEmpty()) {
			System.out.println("Error: removing from empty queue");
			return null;
		}
		else if (seg.getEvents().length() == 0) {
			seg = seg.getNext();
			return (Event) seg.getEvents().remove();
		}
		else
			return (Event) seg.getEvents().remove();
	}

	/**
	 * Checks if the queue is empty
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (seg.getEvents().length() == 0 && seg.getNext() == null);
	}

	/**
	 * Returns the priority associated with the top priority event in the
	 * priority queue
	 * @return double
	 */
	public double getCurrentTime() {
		return seg.getTime();
	}
}
