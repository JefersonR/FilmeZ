package com.app.jeferson.filmez.connectionFactory;

import com.app.jeferson.filmez.util.ArrayAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by jeferson on 22/11/15.
 */
public interface RetrofitInterface {
    //Define uma interface para conexão com o servidro.
    public static final String BASE_URL =  "http://www.omdbapi.com";
    public Gson gson = new GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .registerTypeAdapterFactory(new ArrayAdapterFactory())
            .create();

    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    public  EndpointInterface apiService =
            retrofit.create(EndpointInterface.class);


    public void doRequest();
    public void doRequest(String... params);

}
