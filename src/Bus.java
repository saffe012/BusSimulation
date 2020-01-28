import java.util.Random;

/**
 * Created for each of the number of buses in the simulation.
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class Bus {

	Passenger[] passengers; // List of Passengers holds the passengers of the bus

	/**
	 * Instantiates a bus of the correct size.
	 */
	public Bus() {
		passengers = new Passenger[Constants.BUS_SIZE];
	}

	/**
	 * Adds passenger to the array if bus is not full
	 * @param p Passenger to be added
	 * @return boolean Returns true if Passenger was added
	 */
	public boolean addPassengerToBus(Passenger p) {
		for (int i = 0; i < passengers.length; i++) {
			if (passengers[i] == null) {  // place passenger at first empty spot in array
				passengers[i] = p;
				return true;
			}
		}
		return false; // passenger was not added
	}

	/**
	 * Removes all passengers from the bus if the passengers destination is the stop
	 * @param stop The current stop represented as an integer
	 * @return Passenger[] Returns list of Passengers that got off bus at stop
	 */
	public Passenger[] removePassengersFromBus(int stop) {
		Passenger[] off = new Passenger[Constants.BUS_SIZE];
		int count = 0;
		for (int i = 0; i < passengers.length; i++) {  // cycle through array looking at each passenger's stop
			if (passengers[i] != null) {  // passenger exists at location
				if (passengers[i].getDropoffStop() == stop) {  // passenger's stop matches bus's stop
					off[count] = passengers[i];
					count++;
					passengers[i] = null;
				}
			}
		}
		Passenger[] newOff = new Passenger[count];
		for (int j = 0; j < newOff.length; j++) {  // create an array of the correct size of passengers that got off bus
			newOff[j] = off[j];
		}
		return newOff;
	}

	/**
	 * Checks to see if bus is at capacity
	 * @return boolean Returns True if bus is full
	 */
	public boolean isFull() {
		for (int i = 0; i < passengers.length; i++) {
			if (passengers[i] == null) {  // if any value is empty bus is not full
				return false;
			}
		}
		return true; // bus is full
	}
}
