import java.util.Random;

/**
 * Created by Matthew on 11/15/2016.
 */

//created for each of the ten stops. Controls what time intervals passengers are added to the line at the station.
//rescheduled every time a passenger is added to a line
public class PassengerEvent implements Event {

    int stop;
    Stop station;
    int interval;
    int[] arrivalIntervals = {75,75,50,50,50,20,20,20,20,0,0,-20,-20,-20,-20,-50,-50,-50,-75,-75};

    //instantiates the event with the correct stop and stop lines, as well as, given load for simulation
    public PassengerEvent(int stop, Stop station, int interval){
        this.stop = stop;
        this.station = station;
        this.interval = interval;
    }

    //creates passenger and adds it to th correct line,
    // then adds it to the agenda after calculating
    // the interval at which the passenger should enter the line.
    //Gathers data regarding the total people added to the simulation
    public void run(){
        double arrivalTime = BusRouteSim.getCurrentTime();
        Passenger a = new Passenger(arrivalTime, stop);
        if(a.direction.equals("w")){
            station.getWestBound().add(a);
        }else {
            station.getEastBound().add(a);
        }
        BusRouteSim.totalPeopleSys = BusRouteSim.totalPeopleSys + 1;
        int frac;
        int time = interval;
        Random rn = new Random();
        frac = arrivalIntervals[rn.nextInt(20)];
        if(frac == 75){
            time = (int) (interval + (.75 * interval));
        }else if(frac == 50){
            time = (int) (interval + (.50 * interval));
        }else if(frac == 20){
            time = (int) (interval + (.20 * interval));
        }else if(frac == 0){
            time = interval;
        }else if(frac == -20){
            time = (int) (interval - (.20 * interval));
        }else if(frac == -50){
            time = (int) (interval - (.50 * interval));
        }else if(frac == -75){
            time = (int) (interval - (.75 * interval));
        }
        if(stop >= 8){
            time-=30;
        }
        BusRouteSim.agenda.add(this, time);
    }
}