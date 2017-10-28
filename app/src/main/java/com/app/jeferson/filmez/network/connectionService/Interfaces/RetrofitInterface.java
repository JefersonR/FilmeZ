package com.app.jeferson.filmez.network.connectionService.Interfaces;


import com.app.jeferson.filmez.network.connectionService.NetworkSetup;
import com.app.jeferson.filmez.util.ArrayAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jeferson on 22/11/15.
 */
public interface RetrofitInterface {
    //Define uma interface para conexão com o servidro.
    final String BASE_URL = "http://www.omdbapi.com";


    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .registerTypeAdapterFactory(new ArrayAdapterFactory())
            .create();

    Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(NetworkSetup.getClient3())
            .build();
    EndpointInterface apiService2 = retrofit2.create(EndpointInterface.class);

}
