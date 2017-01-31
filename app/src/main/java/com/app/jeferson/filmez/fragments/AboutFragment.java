package com.app.jeferson.filmez.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jeferson.filmez.MainActivity;
import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.util.ActivityStartProperties;


/**
 * Created by jeferson on 22/11/15.
 */
public class AboutFragment extends Fragment implements ActivityStartProperties {
    //Ui Elements


    //Atributtes
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_about, container, false);
        } catch (InflateException e) {
            e.getMessage();
        }
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

    }

    @Override
    public void setProperties() {
        configureToolbar();
    }

    @Override
    public void listeners() {

    }

    private void configureToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if(activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle("Sobre");
            ((MainActivity) getActivity()).setActionBarDrawerToggle(toolbar);
        }
    }
}
