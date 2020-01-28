import java.io.File;
import java.io.PrintWriter;

/**
 * Gathers useful stats while BusRouteSim runs
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class Stats {

	static double busCount = 0; // number of stops that every bus makes
	static double totalPassAvg = 0; // total sum of passengers every time a bus leaves the station
	static int maxBusOccupancy = 0; // the highest number of people that a bus held in the simulation
	static double totalTravelTimeAvg = 0; // total travel time of people that got delivered
	static int totalPeopleDelivered = 0; // total numer of people that were delivered
	static double minTravelTime = Constants.SIMULATION_TIME_LENGTH; // travel time of the passenger that got delivered the fastest
	static double maxTravelTime = 0; // travel time of the passenger that got delivered the slowest

	/**
	 * Keeps stats on the quantity of people in the buses
	 * @param passengers Number of passengers on the bus currently
	 */
	public static void updateBusPassAvg(int passengers) {
		if (passengers > maxBusOccupancy) {
			maxBusOccupancy = passengers;
		}
		totalPassAvg += passengers; // sum of passengers on all buses everytime one leaves a station
		busCount += 1; // increments everytime a bus leaves a station
	}

	/**
	 * Keeps stats used to calculate average travel time
	 * @param time Time it took for a passenger to be delivered
	 */
	public static void updateAverageTravelTime(double time) {
		if (time > maxTravelTime) {
			maxTravelTime = time;
		}
		if (time < minTravelTime) {
			minTravelTime = time;
		}
		totalTravelTimeAvg += time;
	}

	/**
	 * Keeps track of how many people arrive at their destination
	 * @param people Number of people delivered to their location at each stop
	 */
	public static void updateArrived(int people) {
		totalPeopleDelivered += people;
	}


	/**
	 * Prints stats
	 * @param printer object to write to a text file.
	 */
	public static void displayStats(PrintWriter printer) {

		System.out.println("Average bus occupancy: " + (totalPassAvg / busCount));
		System.out.println("Max bus occupancy: " + maxBusOccupancy);
		System.out.println("Average Passenger Miles Per Gallon: " + (Constants.BUS_MPG*(totalPassAvg / busCount)));
		System.out.println("Average travel time: " + (totalTravelTimeAvg / totalPeopleDelivered));
		System.out.println("Max travel time: " + (maxTravelTime));
		System.out.println("Min travel time: " + (minTravelTime));


		// Used to export stats to text file
		if (Constants.WRITE_STATS_TO_TXT) {
		   printer.println("Average bus occupancy: " + (totalPassAvg / busCount));
		   printer.println("Max bus occupancy: " + maxBusOccupancy);
		   printer.println("Average Passenger Miles Per Gallon: " + (Constants.BUS_MPG*(totalPassAvg / busCount)));
		   printer.println("Average travel time: " + (totalTravelTimeAvg / totalPeopleDelivered));
		   printer.println("Max travel time: " + (maxTravelTime));
		   printer.println("Min travel time: " + (minTravelTime));
		 }
	}
}
