package lesson7.project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson7.project.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "9V85gUM1U2cJ9L2UOwiRfusjFPm6Ofg5";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final String LANGUAGE = "ru-ru";
    private static final String API_LANGUAGE = "language";
    private static final String METRIC = "true";
    private static final String API_METRIC = "metric";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private  DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException, SQLException {
        switch (period) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(API_LANGUAGE, LANGUAGE)
                        .addQueryParameter(API_METRIC, METRIC)
                        .build();
                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastResponse.body().string();
                //System.out.println(weatherResponse);

                JsonNode jsonNode = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0);

                String dateOfForecast = jsonNode.at("/Date").asText();
                String minTemperature = jsonNode.at("/Temperature/Minimum/Value").asText();
                Double maxTemperature = jsonNode.at("/Temperature/Maximum/Value").asDouble();
                System.out.println("Погода в городе: " + selectedCity + ", на дату:" + dateOfForecast + ", градусов поцельсию:" + maxTemperature);

                dataBaseRepository.saveWeatherToDataBase(new Weather(selectedCity,dateOfForecast,maxTemperature));



                break;
            case FIVE_DAYS:
                HttpUrl httpUrl1 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(API_LANGUAGE, LANGUAGE)
                        .addQueryParameter(API_METRIC, METRIC)
                        .build();
                Request request1 = new Request.Builder()
                        .url(httpUrl1)
                        .build();

                Response fiveDaysForecastResponse = okHttpClient.newCall(request1).execute();
                String weatherResponse1 = fiveDaysForecastResponse.body().string();
                //System.out.println(weatherResponse1);

                JsonNode jsonNode1Day = objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(0);

                String dateOfForecast1 = jsonNode1Day.at("/Date").asText();
                String minTemperature1 = jsonNode1Day.at("/Temperature/Minimum/Value").asText();
                Double maxTemperature1 = jsonNode1Day.at("/Temperature/Maximum/Value").asDouble();
                System.out.println("Погода в городе " + selectedCity + ", на дату:" + dateOfForecast1 + ", градусов поцельсию:" + maxTemperature1);


                JsonNode jsonNode2Day = objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(1);

                String dateOfForecast2 = jsonNode2Day.at("/Date").asText();
                String minTemperature2 = jsonNode2Day.at("/Temperature/Minimum/Value").asText();
                Double maxTemperature2 = jsonNode2Day.at("/Temperature/Maximum/Value").asDouble();
                System.out.println("Погода в городе " + selectedCity + ", на дату:" + dateOfForecast2 + ", градусов поцельсию:" + maxTemperature2);


                JsonNode jsonNode3Day = objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(2);

                String dateOfForecast3 = jsonNode3Day.at("/Date").asText();
                String minTemperature3 = jsonNode3Day.at("/Temperature/Minimum/Value").asText();
                Double maxTemperature3 = jsonNode3Day.at("/Temperature/Maximum/Value").asDouble();
                System.out.println("Погода в городе " + selectedCity + ", на дату:" + dateOfForecast3 + ", градусов поцельсию:" + maxTemperature3);


                JsonNode jsonNode4Day = objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(3);

                String dateOfForecast4 = jsonNode4Day.at("/Date").asText();
                String minTemperature4 = jsonNode4Day.at("/Temperature/Minimum/Value").asText();
                Double maxTemperature4 = jsonNode4Day.at("/Temperature/Maximum/Value").asDouble();
                System.out.println("Погода в городе " + selectedCity + ", на дату:" + dateOfForecast4 + ", градусов поцельсию:" + maxTemperature4);


                JsonNode jsonNode5Day = objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(4);

                String dateOfForecast5 = jsonNode5Day.at("/Date").asText();
                String minTemperature5 = jsonNode5Day.at("/Temperature/Minimum/Value").asText();
                Double maxTemperature5 = jsonNode5Day.at("/Temperature/Maximum/Value").asDouble();
                System.out.println("Погода в городе " + selectedCity + ", на дату:" + dateOfForecast5 + ", градусов поцельсию:" + maxTemperature5);

               // dataBaseRepository.saveWeatherToDataBase(new List<Weather>()); ???? не получается

                break;
        }

    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepository.getSavedToDBWeather();
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;

    }
}