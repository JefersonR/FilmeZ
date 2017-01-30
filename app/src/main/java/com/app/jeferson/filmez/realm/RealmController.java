package com.app.jeferson.filmez.realm;

import android.content.Context;

import com.app.jeferson.filmez.movies.MovieDetailModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Jeferson on 29/01/2017.
 */

public class RealmController {

    private RealmConfiguration realmConfig;
    private Realm realm;

    public RealmController(Context context) {
        realmConfig = new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);
    }

    public Realm getRealm() {
        return realm;
    }

    //clear all objects from MovieDetailModel.class
    public void clearAll() {

        realm.beginTransaction();
        realm.delete(MovieDetailModel.class);
        realm.commitTransaction();
    }

    //find all objects in the MovieDetailModel.class
    public RealmResults<MovieDetailModel> getMovieDetailModels() {

        return realm.where(MovieDetailModel.class).findAll();
    }


    public RealmResults<MovieDetailModel> getMoviesByDate() {

        return realm.where(MovieDetailModel.class)
                .findAllSorted("date",Sort.DESCENDING);
    }



    //query a single item with the given id
    public MovieDetailModel getMovieDetailModel(String id) {
        return realm.where(MovieDetailModel.class).equalTo("imdbID", id).findFirst();
    }

    //delete
    public boolean deleteMovieDetailModel(String id) {
        try {
            MovieDetailModel movieDetailModel = realm.where(MovieDetailModel.class).equalTo("imdbID", id).findFirst();
            realm.beginTransaction();
            movieDetailModel.deleteFromRealm();
            realm.commitTransaction();
            return true;
        }catch(Exception e){
            return false;
        }

    }

    public boolean persistMovie(MovieDetailModel movieDetailModel){

        realm.beginTransaction();
        realm.copyToRealm(movieDetailModel);
        realm.commitTransaction();
        return true;
    }

    //check if MovieDetailModel.class is empty
    public boolean hasMovieDetailModels() {

        return !realm.where(MovieDetailModel.class).findAll().isEmpty();
    }

    //query example
    public RealmResults<MovieDetailModel> queryedMovieDetailModels() {

        return realm.where(MovieDetailModel.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();

    }
}