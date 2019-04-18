import java.io.*;

/**
 * Created by Matthew on 11/16/2016.
 */
//saffe012


//Runs simulation
//Keeps track of some stats
public class BusRouteSim {

    static double peopleInBus = 0;//number of people that entered a bus

    static int totalPeopleSys = 0;//number of passengers that are created and added to a line

    public static PQ agenda = new PQ();//priority queue to hold all events

    public static Stop F1 = new Stop("University Ave and 27th Street SE");
    public static Stop F2 = new Stop("Raymond Ave Station");
    public static Stop F3 = new Stop("University Ave and Fairview Ave");
    public static Stop F4 = new Stop("University Ave and Snelling Ave");
    public static Stop F5 = new Stop("University Ave and Lexington Parkway");
    public static Stop F6 = new Stop("University Ave and Dale Street");
    public static Stop F7 = new Stop("University Ave and Marion Street");
    public static Stop F8 = new Stop("Cedar Street and 5th Street");
    public static Stop F9 = new Stop("Minnesota Street and 4th Street");
    public static Stop F10 = new Stop("Union Depot");


    public static Stop[] stops = {null, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10};

    //gets current time of simulation
    public static double getCurrentTime() {
        return agenda.getCurrentTime();
    }

    //Runs simulation by adding correct number of BusEvents to agenda and adding a PassengerEvent
    //to the agenda for each stop.
    //A while loop executes the items in the agenda until time runs out.
    //The 4 variables (busSize, numBuses, time, interval) can be manipulated to create the desired simulation.
    //Stats are displayed when simulation is complete
    public static void main(String[] args){

        int busSize = 40; // bus size
        int numBuses = 10; // number of buses
        int time = 18000; // time to run
        int interval = 480; //load

        if (numBuses > 10) {
            for (int i = 0; i < 9; i++) {
                Bus bus = new Bus(busSize);
                BusEvent BE = new BusEvent(bus, i+1, "e");
                agenda.add(BE, 10);
            }
            for (int i = 10; i > 9 - (numBuses-10); i--) {
                Bus bus = new Bus(busSize);
                BusEvent BE = new BusEvent(bus, i, "w");
                agenda.add(BE, 10);
            }
        }else{
            for (int i = 0; i < numBuses; i++) {
                Bus bus = new Bus(busSize);
                BusEvent BE = new BusEvent(bus, i+1, "e");
                agenda.add(BE, 10);
            }
        }


        //creates then adds passenger events to queue
        PassengerEvent PE1 = new PassengerEvent(1,F1,interval);
        PassengerEvent PE2 = new PassengerEvent(2,F2,interval);
        PassengerEvent PE3 = new PassengerEvent(3,F3,interval);
        PassengerEvent PE4 = new PassengerEvent(4,F4,interval);
        PassengerEvent PE5 = new PassengerEvent(5,F5,interval);
        PassengerEvent PE6 = new PassengerEvent(6,F6,interval);
        PassengerEvent PE7 = new PassengerEvent(7,F7,interval);
        PassengerEvent PE8 = new PassengerEvent(8,F8,interval);
        PassengerEvent PE9 = new PassengerEvent(9,F9,interval);
        PassengerEvent PE10 = new PassengerEvent(10,F10,interval);
        agenda.add(PE1, 10);
        agenda.add(PE2, 10);
        agenda.add(PE3, 10);
        agenda.add(PE4, 10);
        agenda.add(PE5, 10);
        agenda.add(PE6, 10);
        agenda.add(PE7, 10);
        agenda.add(PE8, 10);
        agenda.add(PE9, 10);
        agenda.add(PE10, 10);

        //runs until simulation time is exhausted
        while ((agenda.getCurrentTime() <= time)&&(!agenda.isEmpty())) {
            agenda.remove().run();
        }

        //displays stats
        System.out.println("Number of buses: " + numBuses);
        System.out.println("Size of bus: " + busSize);
        System.out.println("Passenger load: " + interval);
        System.out.println("Number of people who got on a bus: " + (peopleInBus));
        System.out.println("Total number of people who got in line: " + totalPeopleSys);
        Stats.displayStats(null);


        //Used to export stats to text file
        /*
        try(FileWriter fw = new FileWriter("Project4_Data.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter p = new PrintWriter(bw)){
            p.println("Number of buses: " + numBuses);
            p.println("Size of bus: " + busSize);
            p.println("Time of simulation: " + time);
            p.println("Passenger load: " + interval);
            Stats.displayStats(p);
            p.println('\n');
            p.close();
        }
        catch (IOException e) {
            System.out.println("nope");
        }
        */


    }
}
