/**
 * Created for each stop.
 * Holds a queue for passengers going eastbound and westbound.
 *
 * @author  Matt Saffert
 * @version 1.0
 * @since   11/16/2016
 */

public class Stop {

	String name; // Holds name of the stop
	public static Queue eastBound = new Queue(); // passengers waiting in line travelling east bound
	public static Queue westBound = new Queue(); // passengers waiting in line travelling west bound

	/**
	 * Instantiates new Stop with name.
	 * @param name Name of stop
	 */
	public Stop(String name) {
		this.name = name;
	}

	/**
	 * Instantiates new Stop with name.
	 * @return Queue Queue of passengers waiting at stop that are east bound
	 */
	public Queue getEastBound() {
		return eastBound;
	}

	/**
	 * Instantiates new Stop with name.
	 * @return Queue Queue of passengers waiting at stop that are west bound
	 */
	public Queue getWestBound() {
		return westBound;
	}
}
