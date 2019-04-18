/**
 * Created by Matthew on 11/16/2016.
 */

//Created for each stop.
//holds a queue for passengers going eastbound and westbound
public class Stop {

    String name; //Holds name of stop
    public static Q2 eastBound = new Q2();
    public static Q2 westBound = new Q2();

    public Stop(String name){
        this.name = name;
    }

    public Q2 getEastBound(){
        return eastBound;
    }

    public Q2 getWestBound(){
        return westBound;
    }

}
