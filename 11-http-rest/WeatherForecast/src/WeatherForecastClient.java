import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.DataPoint;
import dto.Geocode;
import dto.WeatherForecast;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;

public class WeatherForecastClient {

    private HttpClient client;
    private String secretKey;
    private String token;

    public WeatherForecastClient(HttpClient client, String secretKey, String token) {
        this.client = client;
        this.secretKey = secretKey;
        this.token = token;
    }

    /**
     * Fetches the weather forecast for the specified location.
     *
     * @return the forecast
     */
    public WeatherForecast getForecast(String location) throws IOException, InterruptedException {
        location = location.replace(" ", "%20");
        String locationURL = getConstructedLocationURL(location);
        Geocode geocode = getGeocode(locationURL);
        String weatherURL = getConstructedWeatherURL(geocode);

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(weatherURL)).build();
        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (httpResponse.statusCode() != 200) {
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(httpResponse.body(), WeatherForecast.class);
    }

    /**
     * Fetches the current weather for the specified location.
     *
     * @return the current weather
     */
    public DataPoint getCurrent(String location) throws IOException, InterruptedException {
        return getForecast(location).getCurrently();
    }

    /**
     * Fetches the hourly weather forecast
     *
     * @return the hourly weather forecast
     */
    public Collection<DataPoint> getHourlyForecast(String location) throws IOException, InterruptedException {
        return getForecast(location).getHourly().getData();
    }

    /**
     * Fetches the weekly weather forecast
     *
     * @return the weekly weather forecast
     */
    public Collection<DataPoint> getWeeklyForecast(String location) throws IOException, InterruptedException {
        return getForecast(location).getDaily().getData();
    }

    private String getConstructedLocationURL(String location) {
        return "https://eu1.locationiq.com/v1/search.php?key=" + token + "&q=" + location + "&format=json";
    }

    private String getConstructedWeatherURL(Geocode geocode) {
        return "https://api.darksky.net/forecast/" + secretKey + "/" + geocode.getLat() + "," + geocode.getLon() + "?units=si&lang=bg";
    }

    private Geocode getGeocode(String url) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (httpResponse.statusCode() != 200) {
            return null;
        }

        Gson gson = new Gson();
        List<Geocode> geocodes = gson.fromJson(httpResponse.body(), new TypeToken<List<Geocode>>() {
        }.getType());

        return geocodes.get(0);
    }
}