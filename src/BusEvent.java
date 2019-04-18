/**
 * Created by Matthew on 11/16/2016.
 */

//created for every bus in the simulation.
//reschedules every time a bus 'leaves' a station.
public class BusEvent implements Event{

    Bus bus;
    int offStop;
    String direction;
    int time = 180;
    int stopTime = 0;

    //instantiates an event of a given bus at a given stop going in a given direction.
    public BusEvent(Bus bus, int stop, String direction){
        this.bus = bus;
        offStop = stop;
        this.direction = direction;
    }

    //First removes passengers from the bus that need to get off.
    //Then, passengers are added from the correct stop queue until the the queue length is zero or the bus is full
    //Wait time at the station is calculated then the bus is added to the agenda again according to the calculates wait time
    //Stats.java is called throughout to gather data.
    public void run(){
        double curSimTime = BusRouteSim.getCurrentTime();

        Passenger[] arr = bus.removePassengersAtStop(offStop);
        stopTime = stopTime + (arr.length * 2);
        Stats.updateArrived(arr.length);
        if (direction.equals("e")){
            for (int i = 0; i < BusRouteSim.stops[offStop].getEastBound().length(); i++) {
                if (!bus.isFull()) {
                    Passenger oldPass = (Passenger) BusRouteSim.stops[offStop].getEastBound().remove();
                    bus.addPassenger(oldPass);
                    BusRouteSim.peopleInBus = BusRouteSim.peopleInBus + 1;
                    stopTime = stopTime + 3;
                }
            }
            if (offStop == 10) {
                offStop = 9;
                direction = "w";
            } else {
                offStop = offStop + 1;
            }
        }else {
            for (int i = 0; i < BusRouteSim.stops[offStop].getWestBound().length(); i++) {
                if (!bus.isFull()) {
                    Passenger oldPass = (Passenger) BusRouteSim.stops[offStop].getWestBound().remove();
                    bus.addPassenger(oldPass);
                    BusRouteSim.peopleInBus = BusRouteSim.peopleInBus + 1;
                    stopTime = stopTime + 3;
                }
            }
            if (offStop == 1) {
                offStop = 2;
                direction = "e";
            } else {
                offStop = offStop - 1;
            }
        }
        if(stopTime > 15){
            time = time + stopTime;
        }else{
            time = time + 15;
        }
        int num_passengers = 0;
        int offTime = 0;
        for (int i = 0; i < bus.passengers.length; i++){
            if(bus.passengers[i] != null){
                num_passengers += 1;
            }
        }
        for(int j = 0; j < arr.length; j++){
            offTime = offTime + 2;
            double totalTime = (curSimTime + offTime) - arr[j].getArrivalTime();
            Stats.updateAverageTravelTime(totalTime);
        }
        Stats.updateBusPassAvg(num_passengers);
        BusRouteSim.agenda.add(this, time);
    }
}
