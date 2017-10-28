package com.app.jeferson.filmez.network.connectionService.Interfaces;


import com.app.jeferson.filmez.movies.CardViewItems;
import com.app.jeferson.filmez.movies.MovieDetailModel;

import retrofit2.Call;

/**
 * Created by jeferson on 22/11/15.
 */
public interface EndpointInterface {
    final String search_movie = "/?type=movie&r=json";
    final String search_movie_detail = "/?type=movie&plot=full&r=json&tomatoes=true";
    /*
    Request method and URL specified in the annotation
    Callback for the parsed response is the last parameter
    */
    //Search movies
    @retrofit.http.GET(search_movie)
    Call<CardViewItems> searchMovie(@retrofit.http.Query("s") String search);

    //Get details
    @retrofit.http.GET(search_movie_detail)
    Call<MovieDetailModel> searchMovieDetail(@retrofit.http.Query("i") String movieID);

}

