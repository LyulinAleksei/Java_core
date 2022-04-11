package lesson6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AccuweatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/25123
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

                final String PROTOKOL = "https";
                final String BASE_HOST = "dataservice.accuweather.com";
                final String FORECASTS = "forecasts";
                final String VERSION = "v1";
                final String DAILY = "daily";
                final String ONE_DAY = "1day";
                final String API_KEY = "9V85gUM1U2cJ9L2UOwiRfusjFPm6Ofg5";
                final String API_KEY_QUERY_PARAM = "apikey";
                final String CITY = "25123";
                final String LANGUAGE = "&ru-ru";
                final String API_LANGUAGE = "language";
                final String METRIC = "true";
                final String API_METRIC = "metric";

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(FORECASTS)
                .addPathSegment(VERSION)
                .addPathSegment(DAILY)
                .addPathSegment(ONE_DAY)
                .addPathSegment(CITY)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter(API_LANGUAGE,LANGUAGE)
                .addQueryParameter(API_METRIC,METRIC)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();

        Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
        String weatherResponse = Objects.requireNonNull(oneDayForecastResponse.body()).string();

        System.out.println(oneDayForecastResponse.code());
        System.out.println(oneDayForecastResponse.headers());
        System.out.println(weatherResponse);

    }
}

