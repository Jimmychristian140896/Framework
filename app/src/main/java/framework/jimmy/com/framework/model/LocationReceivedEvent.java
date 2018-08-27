package framework.jimmy.com.framework.model;

public class LocationReceivedEvent {
    public double longitude;
    public double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public LocationReceivedEvent(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
