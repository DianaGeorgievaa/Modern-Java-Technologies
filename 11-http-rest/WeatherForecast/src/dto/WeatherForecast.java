package dto;

public class WeatherForecast {

    private double latitude;
    private double longitude;
    private String timezone;
    private DataPoint currently;
    private DataBlock hourly;
    private DataBlock daily;

    public WeatherForecast(double latitude, double longitude, String timezone) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public DataPoint getCurrently() {
        return currently;
    }

    public DataBlock getHourly() {
        return hourly;
    }

    public DataBlock getDaily() {
        return daily;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone='" + timezone + '\'' +
                ", currently=" + currently.toString() +
                ", hourly=" + hourly.toString() +
                ", daily=" + daily.toString() +
                '}';
    }
}
