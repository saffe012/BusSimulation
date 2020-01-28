import java.io.*;
import java.util.Random;

/**
 * Runs simulation.
 * Keeps track of some stats.
 *
 * Anatomy of scheduling: PriorityQueue[Segments(Queue[Events])]
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class BusRouteSim {
	static double peopleInBus = 0; // number of people that entered a bus
	static int totalPeopleSys = 0; // number of passengers that are created and added to a line
	public static PriorityQueue agenda = new PriorityQueue(); // priority queue to hold all events

	/**
	 * Gets current time of simulation.
	 */
	public static double getCurrentTime() {
		return agenda.getCurrentTime();
	}

	/**
	 * Gets current time of simulation.=
	 * @param stop Stop as int that bus event will occur
	 * @param direction Direction bus is travelling
	 */
	public static void addBusEvent(int stop, String direction) {
		Bus bus = new Bus();
		BusEvent BE = new BusEvent(bus, stop, direction);
		agenda.add(BE, 10);
	}

  /**
	 * Populates a BusEvent for each bus in the simulation
	 */
	public static void populateInitialBusEvents() {
    Random random = new Random();
    int direction;
    int stop;

    // randomly places buses on route in random directions to start
    for (int i = 0; i < Constants.NUM_BUSES; i++) {
  		direction = random.nextInt(2);
      stop = random.nextInt(Constants.BUS_STOPS.length);
      addBusEvent(stop, Constants.DIRECTIONS[direction]);
    }
	}

  /**
	 * Populates a PassengerEvent for each bus stop in the simulation
	 */
  public static void populateInitialPassengerEvents() {
    // creates then adds passenger events to queue
		for (int i = 0; i < Constants.BUS_STOPS.length; i++) {
			agenda.add(new PassengerEvent(i,Constants.BUS_STOPS[i]), 10);
		}
	}

	/**
	 * Prints stats and writes them to text file if specified.
	 */
	public static void writeStats() {
		// displays stats
		System.out.println("Number of buses: " + Constants.NUM_BUSES);
		System.out.println("Size of bus: " + Constants.BUS_SIZE);
		System.out.println("MPG of bus: " + Constants.BUS_MPG);
		System.out.println("Time of simulation: " + Constants.SIMULATION_TIME_LENGTH);
		System.out.println("Average passenger interval: " + Constants.PASSENGER_INTERVAL);
		System.out.println("Number of people who got on a bus: " + (peopleInBus));
		System.out.println("Total number of people who got in line: " + totalPeopleSys);
		Stats.displayStats(null);

		//Used to export stats to text file
		if (Constants.WRITE_STATS_TO_TXT) {
			try (FileWriter fw = new FileWriter(Constants.TEXT_FILE_NAME, true);
			     BufferedWriter bw = new BufferedWriter(fw);
			     PrintWriter printer = new PrintWriter(bw)) {
				printer.println("Number of buses: " + Constants.NUM_BUSES);
				printer.println("Size of bus: " + Constants.BUS_SIZE);
				printer.println("MPG of bus: " + Constants.BUS_MPG);
				printer.println("Time of simulation: " + Constants.SIMULATION_TIME_LENGTH);
				printer.println("Average passenger interval: " + Constants.PASSENGER_INTERVAL);
				printer.println("Number of people who got on a bus: " + (peopleInBus));
				printer.println("Total number of people who got in line: " + totalPeopleSys);
				Stats.displayStats(printer);
				printer.println('\n');
				printer.close();
				System.out.println("Statistics saved to: " + Constants.TEXT_FILE_NAME);
			}
			catch (IOException e) {
				System.out.println("Failed to write stats to file.");
			}
		}
	}

	/**
	 * Runs simulation by adding a BusEvent for each bus to agenda and adding a PassengerEvent
	 * to the agenda for each stop.
	 * A while loop executes the items in the agenda until time runs out.
	 * The constants (BUS_SIZE, NUM_BUSES, SIMULATION_TIME_LENGTH, PASSENGER_INTERVAL, BUS_MPG) can be manipulated in Constatns.java to create the desired simulation.
	 * Stats are displayed when simulation is complete.
	 */
	public static void main(String[] args) {

    populateInitialBusEvents();
  	populateInitialPassengerEvents();

		// runs until simulation time is exhausted
		while ((agenda.getCurrentTime() <= Constants.SIMULATION_TIME_LENGTH) && (!agenda.isEmpty())) {
			agenda.remove().run();
		}

		writeStats();
	}
}
