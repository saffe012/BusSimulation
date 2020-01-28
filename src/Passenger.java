import java.util.Random;

/**
 * Created for each passenger added to a line in the bus route sim
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class Passenger {

	int pickup; // the stop the passenger was picked up at
	int dropoff; // the stop the passenger will be dropped off at
	String direction; // the most efficient direction for the passenger to travel
	double arrivalTime; // time the passenger was placed in the bus line

	/**
	 * Gets the passengers pickup locations
	 * @return int pickup stop
	 */
	public int getPickupStop() {
		return pickup;
	}

	/**
	 * Gets destination of passenger
	 * @return int dropoff stop
	 */
	public int getDropoffStop() {
		return dropoff;
	}

	/**
	 * Gets the time they got in line at the bus station
	 * @return double time passenger was placed in the bus line
	 */
	public double getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Instantiates a passenger at the given stop and randomly chooses a destination and sets them in the logical direction
	 * @param time Time the passenger was placed in the bus line
	 * @param pickupFloor The passengers pickup locations
	 */
	public Passenger(double time, int pickupStop) {
		arrivalTime = time;
		pickup = pickupStop;
		Random random = new Random();
		dropoff = Constants.STOP_PROBABILTY_ARRAY[random.nextInt(Constants.STOP_PROBABILTY_ARRAY.length)]; // randomly chooses drop off location
		while (dropoff == pickup) {  // drop off cannot be same as pickup
			dropoff = Constants.STOP_PROBABILTY_ARRAY[random.nextInt(Constants.STOP_PROBABILTY_ARRAY.length)];
		}

		// sets most logical direction
		if (dropoff > pickup) {
			direction = Constants.EAST;
		}
		else {
			direction = Constants.WEST;
		}
	}
}
