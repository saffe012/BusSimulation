import java.util.Random;

/**
 * Created by Matthew on 11/16/2016.
 */

//Created for each of the number of buses in the simulation.
//List of Passengers holds the passengers of the bus
public class Bus {
    int size;
    Passenger[] passengers;
    static int mpg;

    //instantiates a bus of the correct size
    public Bus(int size){
        this.size = size;
         passengers = new Passenger[size];
        if (size == 40){
            mpg = 6;
        }else{
            mpg = 4;
        }
    }

    //adds passenger to the array if bus is not full
    public boolean addPassenger(Passenger p){

        for (int i = 0; i < passengers.length; i++){
            if (passengers[i] == null){
                passengers[i] = p;
                return true;
            }
        }
        return false;
    }

    //removes all passengers from the bus if the passengers destination is the stop
    public Passenger[] removePassengersAtStop(int stop){
        Passenger[] off = new Passenger[size];
        int count = 0;
        for(int i = 0; i < passengers.length; i++){
            if (passengers[i] != null) {
                if (passengers[i].getDropoffStop() == stop) {
                    off[count] = passengers[i];
                    count++;
                    passengers[i] = null;
                }
            }
        }
        Passenger[] newOff = new Passenger[count];
        for(int j = 0; j < newOff.length; j++){
            newOff[j] = off[j];
        }
        return newOff;
    }

    //checks to see if bus is at capacity
    public boolean isFull(){
        for (int i = 0; i < passengers.length; i++){
            if (passengers[i] == null){
                return false;
            }
        }
        return true;
    }
}
