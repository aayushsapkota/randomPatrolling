package aayush.randompatrolling;

import android.renderscript.RenderScript;

import java.util.ArrayList;


public class SelectedLocation {

    private ArrayList<String> place = new ArrayList<>();

    private String name;
    private String longitude;
    private String latitude;
    private String minTimeToStay;
    private String maxTimeTOStay;
    private String priority;
    private String checkBackOn;

    public SelectedLocation() {
    }

    public void addPlaceInformation(String name, String longitude, String latitude,
                                    String minTimeToStay, String maxTimeTOStay, String priority, String checkBackOn) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxTimeTOStay = maxTimeTOStay;
        this.minTimeToStay = minTimeToStay;
        this.priority = priority;
        this.checkBackOn = checkBackOn;


        place.add(name);
        place.add(longitude);
        place.add(latitude);
        place.add(minTimeToStay);
        place.add(maxTimeTOStay);
        place.add(priority);
        place.add(checkBackOn);
    }

    public ArrayList<String> getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getMinTimeToStay() {
        return minTimeToStay;
    }

    public String getMaxTimeTOStay() {
        return maxTimeTOStay;
    }

    public String getPriority() {
        return priority;
    }

    public String getCheckBackOn() {
        return checkBackOn;
    }


}
