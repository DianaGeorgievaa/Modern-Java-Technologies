package dto;

public class Geocode {

    private double lat;
    private double lon;

    public Geocode(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Geocode{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
