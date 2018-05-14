package hci.univie.ac.at.dogtastic;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Jasmin on 14.05.2018.
 */

public class RoutesData {

    String startdestination;
    String endDestination;
    LatLng startPoint;
    LatLng endPoint;
    String distance;
    String time;
    //String dogID;
    //String userID;

    RoutesData(String startdestination, String endDestination, LatLng startPoint, LatLng endPoint, String distance, String time){
        this.startdestination=startdestination;
        this.endDestination=endDestination;
        this.startPoint=startPoint;
        this.endPoint=endPoint;
        this.distance=distance;
        this.time=time;
    }


    public String getStartdestination() {
        return startdestination;
    }

    public void setStartdestination(String startdestination) {
        this.startdestination = startdestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public LatLng getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(LatLng startPoint) {
        this.startPoint = startPoint;
    }

    public LatLng getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(LatLng endPoint) {
        this.endPoint = endPoint;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
