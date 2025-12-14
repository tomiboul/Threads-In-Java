package tomiboul;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;

public class Runner implements Runnable {
    private String name;
    private int defaultSpeed;
    private int currentSpeed;
    private int time = 0;
    private int distance = 0;

    private String itinerary;
    public final ArrayList<String> listOfItinerary = (ArrayList<String>) List.of("up", "flat", "up","down", "flat");

    public Runner(String name, int defaultSpeed) {
        assert defaultSpeed > 0;
        assert name.isEmpty() == false;

        this.name = name;
        this.defaultSpeed = defaultSpeed;
        this.currentSpeed = defaultSpeed;
    }




    @Override
    public void run(){
        while (true) {
            try {
                System.out.println("oui");

            } catch (Exception _) {}
        }
    }

    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getDefaultSpeed() {
        return defaultSpeed;
    }
    public void setDefaultSpeed(int defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }
    public String getName() {
        return name;
    }
}
