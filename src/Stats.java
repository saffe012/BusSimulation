import java.io.File;
import java.io.PrintWriter;

/**
 * Created by Matthew on 11/20/2016.
 */

//Gathers useful stats while BusRouteSim runs
public class Stats {

    static int busCount = 0;
    static int totalPassAvg = 0;
    static int maxBusOccupancy = 0;
    static double totalTravelTimeAvg = 0;
    static int totalPeopleDelivered = 0;
    static double minTravelTime = 18000;
    static double maxTravelTime = 0;

    //Keeps stats on the quantity of people in the buses
    public static void updateBusPassAvg(int passengers){
        if (passengers > maxBusOccupancy){
            maxBusOccupancy = passengers;
        }
        totalPassAvg += passengers; //sum of passengers on all buses everytime one leaves a station
        busCount += 1; // increments everytime a bus leaves a station
    }

    //Keeps stats used to calculate average travel time
    public static void updateAverageTravelTime(double time ){
        if (time > maxTravelTime){
            maxTravelTime = time;
        }
        if (time < minTravelTime){
            minTravelTime = time;
        }
        totalTravelTimeAvg += time;
    }

    //Keeps track of how many people arrive at their destination
    public static void updateArrived(int people){
        totalPeopleDelivered += people;
    }


    //prints stats
    public static void displayStats(PrintWriter p) {


        System.out.println("Average bus occupancy: " + (totalPassAvg / busCount));
        System.out.println("Max bus occupancy: " + maxBusOccupancy);
        System.out.println("Average Passenger Miles Per Gallon: " + (Bus.mpg*(totalPassAvg / busCount)));
        System.out.println("Average travel time: " + (totalTravelTimeAvg / totalPeopleDelivered));
        System.out.println("Max travel time: " + (maxTravelTime));
        System.out.println("Min travel time: " + (minTravelTime));


        //Used to export stats to text file
        /*
        p.println("Average bus occupancy: " + (totalPassAvg / busCount));
        p.println("Max bus occupancy: " + maxBusOccupancy);
        p.println("Average Passenger Miles Per Gallon: " + (Bus.mpg*(totalPassAvg / busCount)));
        p.println("Average travel time: " + (totalTravelTimeAvg / totalPeopleDelivered));
        p.println("Max travel time: " + (maxTravelTime));
        p.println("Min travel time: " + (minTravelTime));
        */
    }
}
