/**
 * Constants class to hold constants for the Bus Simulation
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public final class Constants {
  // Hide the constructor
	private Constants() {}

	public static Stop[] BUS_STOPS = // the different stops on the route
	{
		new Stop("University Ave and 27th Street SE"),
		new Stop("Raymond Ave Station"),
		new Stop("University Ave and Fairview Ave"),
		new Stop("University Ave and Snelling Ave"),
		new Stop("University Ave and Lexington Parkway"),
		new Stop("University Ave and Dale Street"),
		new Stop("University Ave and Marion Street"),
		new Stop("Cedar Street and 5th Street"),
		new Stop("Minnesota Street and 4th Street"),
		new Stop("Union Depot")
	};

  public static String[] DIRECTIONS = {"e", "w"};
  public static String EAST = "e"; // direction of travel
  public static String WEST = "w"; // direction of travel

  // array to help decide which stop the passenger should get off all. Higher probability for downtown stops (8,9,10).
  public static	int[] STOP_PROBABILTY_ARRAY = {1,2,3,4,5,6,7,8,8,9,9,10,10};

  // used to slighly randomize how often passengers get in line
  public static int[] ARRIVAL_INTERVAL_VARIABILITY = {75,75,50,50,50,20,20,20,20,0,0,-20,-20,-20,-20,-50,-50,-50,-75,-75};

  // variables to be changed to affect simulation
  public static int BUS_SIZE = 40; // number of passengers a bus can hold (40 or 60)
  public static int BUS_MPG = 6; // miles per gallon fuel efficiency of bus
  public static int NUM_BUSES = 10; // number of buses to be run in simulation
  public static int SIMULATION_TIME_LENGTH = 43200; // time to run simulation in seconds
  public static int PASSENGER_INTERVAL = 480; // how often a passenger will enter the line at a bus stop
  public static boolean WRITE_STATS_TO_TXT = false; // whether user wants stats written to text file
  public static String TEXT_FILE_NAME = ""; // filename of text file to write stats to.
}
