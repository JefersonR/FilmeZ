package com.app.jeferson.filmez.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.connectionFactory.RetrofitInterface;
import com.app.jeferson.filmez.util.ActivityStartProperties;


/**
 * Created by jeferson on 22/11/15.
 */
public class AboutFragment extends Fragment implements ActivityStartProperties, RetrofitInterface {
    //Ui Elements


    //Atributtes
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setLayout();
        setProperties();
        listeners();

    }

    @Override
    public void setLayout() {
        // spinner element
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
    }

    @Override
    public void setProperties() {

    }

    @Override
    public void listeners() {

    }


    @Override
    public void doRequest() {


    }

    @Override
    public void doRequest(String... params) {

    }

}
