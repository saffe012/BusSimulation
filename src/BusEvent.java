/**
 * Created for every bus in the simulation.
 * Reschedules every time a bus 'leaves' a station.
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class BusEvent implements Event {
	Bus bus; // Bus tied to this BusEvent
	int offStop; // stop the bus is currently at
	String direction; // direction of the bus ("e" or "w")
	int time = 180; // how long the bus will be at the stop in seconds
	int stopTime = 0; // time to be added to time depending on how many apssengers get on or off

	/**
	 * Instantiates an event of a given bus at a given stop going in a given direction.
	 * @param bus Bus connected to event
	 * @param stop stop the bus is currently at
	 * @param direction direction of the bus ("e" or "w")
	 */
	public BusEvent(Bus bus, int stop, String direction) {
		this.bus = bus;
		offStop = stop;
		this.direction = direction;
	}

	/**
	 * Handles bus stop event for east bound direction.
	 */
	public void handleEastBound() {
		while (!bus.isFull() && Constants.BUS_STOPS[offStop].getEastBound().length() > 0) {  // keep loading people on bus until bus is full or no more people in line
			Passenger newPassenger = (Passenger) Constants.BUS_STOPS[offStop].getEastBound().remove(); // first person in queue at stop
			bus.addPassengerToBus(newPassenger); // add passenger to bus
			BusRouteSim.peopleInBus += 1;
			stopTime += 3;
		}
		if (offStop == Constants.BUS_STOPS.length-1) {  // end of line so switch directions
			offStop -= 1;
			direction = Constants.WEST;
		}
		else {  // set next stop
			offStop += 1;
		}
	}

	/**
	 * Handles bus stop event for west bound direction.
	 */
	public void handleWestBound() {
		while (!bus.isFull() && Constants.BUS_STOPS[offStop].getWestBound().length() > 0) {  // keep loading people on bus until bus is full or no more people in line
			Passenger newPassenger = (Passenger) Constants.BUS_STOPS[offStop].getWestBound().remove(); // first person in queue at stop
			bus.addPassengerToBus(newPassenger); // add passenger to bus
			BusRouteSim.peopleInBus += 1;
			stopTime += 3;
		}
		if (offStop == 0) {  // end of line so switch directions
			offStop += 1;
			direction = Constants.EAST;
		}
		else {  // set next stop
			offStop -= 1;
		}
	}

	/**
	 * First removes passengers from the bus that need to get off.
	 * Then, passengers are added from the correct stop queue until the the queue length is zero or the bus is full.
	 * Wait time at the station is calculated then the bus is added to the agenda again according to the calculated wait time.
	 * Stats.java is called throughout to gather data.
	 */
	public void run() {
		double curSimTime = BusRouteSim.getCurrentTime();
		Passenger[] passengers = bus.removePassengersFromBus(offStop); // remove passengers whose destination is offStop
		stopTime += passengers.length * 2;
		Stats.updateArrived(passengers.length);

		if (direction.equals(Constants.EAST)) { // bus is eastbound
			handleEastBound();
		}
		else {  // bus is westbound
			handleWestBound();
		}

		if (stopTime > 15) {  // stop will take at least 15 seconds whether anyone gets on or get off
			time += stopTime;
		}
		else {
			time += 15;
		}

		int num_passengers = 0;
		int offTime = 0;

		for (int i = 0; i < bus.passengers.length; i++) {  // calculates number off people on bus after stop activities
			if (bus.passengers[i] != null) {
				num_passengers += 1;
			}
		}

		for (int j = 0; j < passengers.length; j++) {  // helps calculates average travel time for all passengers for Stats
			offTime += 2;
			double totalTime = (curSimTime + offTime) - passengers[j].getArrivalTime();
			Stats.updateAverageTravelTime(totalTime);
		}

		Stats.updateBusPassAvg(num_passengers); // update Stats
		BusRouteSim.agenda.add(this, time); // re-add this event to the Priority Queue
	}
}
