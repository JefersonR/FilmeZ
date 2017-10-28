package com.app.jeferson.filmez.network.connectionService;

import android.content.Context;
import android.widget.ProgressBar;

import com.app.jeferson.filmez.movies.CardViewItems;
import com.app.jeferson.filmez.movies.MovieDetailModel;
import com.app.jeferson.filmez.network.connectionService.Interfaces.EndpointInterface;
import com.app.jeferson.filmez.network.connectionService.Interfaces.OnSucess;
import com.app.jeferson.filmez.network.connectionService.Interfaces.RetrofitInterface;
import com.google.gson.Gson;

import java.io.Serializable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Jeferson on 29/04/2017.
 */

public class Presenter implements RetrofitInterface, Serializable {

    private Context context;
    private Retrofit retrofit;
    private Retrofit retrofit6;
    private EndpointInterface apiService;
    private EndpointInterface apiService6;
    private Gson mGson;

    final String search_movie = "/?type=movie&r=json";
    final String search_movie_detail = "/?type=movie&plot=full&r=json&tomatoes=true";

    public Presenter(Context context) {
        mGson = gson;
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(NetworkSetup.getClient())
                .build();

        apiService = retrofit.create(EndpointInterface.class);

    }

    public Gson getmGson() {
        return mGson;
    }

    public void searchMovie(String search, OnSucess onSucessListener, ProgressBar progressBar) {
        new GenericRestCallBack<CardViewItems>().request(context, apiService.searchMovie(search), onSucessListener, progressBar);

    }
    public void searchMovieDetail(String search, OnSucess onSucessListener, ProgressBar progressBar) {
        new GenericRestCallBack<MovieDetailModel>().request(context, apiService.searchMovieDetail(search), onSucessListener, progressBar);

    }

}


