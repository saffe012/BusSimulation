import java.util.Random;

/**
 * Created for each of the ten stops. Controls what time intervals passengers are added to the line at the station.
 * Rescheduled every time a passenger is added to a line.
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class PassengerEvent implements Event {

	int stop; // the integer value of the stop connected to this PassengerEvent
	Stop station; // the stop connected to this PassengerEvent
	int interval = Constants.PASSENGER_INTERVAL; // the base time between passengers getting in line

	/**
	 * Instantiates the event with the correct stop and stop lines, as well as, given load for simulation
	 * @param stop the integer value of the stop connected to this PassengerEvent
	 * @param station the stop connected to this PassengerEvent
	 * @param interval the base time between passengers getting in line
	 */
	public PassengerEvent(int stop, Stop station) {
		this.stop = stop;
		this.station = station;
	}

  /**
	 * Creates passenger and adds it to the correct line,
   * then adds the Event to the agenda after calculating
   * the interval at which the passenger should enter the line.
   * Gathers data regarding the total people added to the simulation.
	 */
	public void run() {
		double arrivalTime = BusRouteSim.getCurrentTime();
		Passenger passenger = new Passenger(arrivalTime, stop);

    // add passenger to correct waiting line
		if (passenger.direction.equals(Constants.WEST)) {
			station.getWestBound().add(passenger);
		}
    else {
			station.getEastBound().add(passenger);
		}

		BusRouteSim.totalPeopleSys = BusRouteSim.totalPeopleSys + 1;
		int time = interval;
		Random random = new Random();
		int frac = Constants.ARRIVAL_INTERVAL_VARIABILITY[random.nextInt(Constants.ARRIVAL_INTERVAL_VARIABILITY.length)];

		switch (frac) {  // adds variability to the arrival interval of passengers
  		case (75):
  			time = (int) (interval + (.75 * interval));
  			break;
  		case (50):
  			time = (int) (interval + (.50 * interval));
  			break;
  		case (20):
  			time = (int) (interval + (.20 * interval));
  			break;
  		case (0):
  			time = interval;
  			break;
  		case (-20):
  			time = (int) (interval - (.20 * interval));
  			break;
  		case (-50):
  			time = (int) (interval - (.50 * interval));
  			break;
  		case (-75):
  			time = (int) (interval - (.75 * interval));
  			break;
		}

		if (stop >= 8) { // downtown stops are more popular so people arrive at them faster
			time -= 30;
		}

		BusRouteSim.agenda.add(this, time); // add the event to the priority queue
	}
}
