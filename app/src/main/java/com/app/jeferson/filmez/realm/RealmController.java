package com.app.jeferson.filmez.realm;

import android.content.Context;

import com.app.jeferson.filmez.movies.MovieDetailModel;

import java.util.Date;
import java.util.List;

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


    public List<MovieDetailModel> getMoviesByDate() {
        int limitStart = 0;
        int limitEnd   = 5;
        List<MovieDetailModel> list = realm.where(MovieDetailModel.class)
                .findAllSorted("date",Sort.DESCENDING);
        return list.subList(limitStart, (list.size() > limitEnd)?limitEnd:list.size());
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
            realm.close();
            return true;
        }catch(Exception e){
            return false;
        }

    }

    public boolean persistMovie(MovieDetailModel movieDetailModel, Date date){
        try {
            realm.beginTransaction();
            movieDetailModel.setDate(date);
            realm.copyToRealm(movieDetailModel);
            realm.commitTransaction();
            realm.close();
            return true;
        }catch(Exception e){
            e.getMessage();
        }
        return false;
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