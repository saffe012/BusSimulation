import java.util.Random;

/**
 * Created by Matthew on 11/15/2016.
 */

//Created for each passenger added to a line in the bus route sim
public class Passenger {

    int pickup;
    int dropoff;
    String direction;
    int[] dropoffArray = {1,2,3,4,5,6,7,8,8,9,9,10,10};
    double arrivalTime;

    public int getPickupStop(){
        return pickup;
    }

    //Gets destination of passenger
    public int getDropoffStop(){
        return dropoff;
    }

    //gets the time they got in line at the bus station
    public double getArrivalTime() { return arrivalTime; }

    //instantiates a passenger at the given stop and randomly chooses a destination and sets them in the logical direction
    public Passenger(double time, int pickupFloor){
        arrivalTime = time;
        pickup = pickupFloor;
        Random random = new Random();
        dropoff = dropoffArray[random.nextInt(13)];
        while (dropoff == pickup){
            dropoff = dropoffArray[random.nextInt(13)];
        }

        if (dropoff > pickup){
            direction = "e";
        }else{
            direction = "w";
        }
    }


}