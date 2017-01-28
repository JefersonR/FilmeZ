package com.app.jeferson.filmez.connectionFactory;

import com.app.jeferson.filmez.movies.CardViewItems;
import com.app.jeferson.filmez.movies.MovieDetail;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jeferson on 22/11/15.
 */
public interface EndpointInterface {
    /*
    Request method and URL specified in the annotation
    Callback for the parsed response is the last parameter
    */
    //Search movies
    @GET("/?type=movie&r=json")
    Call<CardViewItems> searchMovie(@Query("s") String search);

    //Get details
    @GET("/?type=movie&plot=full&r=json&tomatoes=true")
    Call<MovieDetail> searchMovieDetail(@Query("i") String movieID);

}