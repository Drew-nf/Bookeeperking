package com.example.bookkeepingking;

import LocalDatabase.Employee;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class RetroFitClass {

    private static Retrofit instance;

    private final static String baseUrl = "Bookkeepingking-env.eba-sfnkjwg6.us-east-2.elasticbeanstalk.com";

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public interface BookKeepingService {
      @POST("/fetchSIT")
        Call<Employee> fetchSIT(@Body Employee requestBody);
    }
}
