package com.app.jeferson.filmez.ui.fragments;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.bases.BaseFragment;
import com.app.jeferson.filmez.ui.activities.MainActivity;


/**
 * Created by jeferson on 22/11/15.
 */
public class AboutFragment extends BaseFragment {
    @Override
    protected void setLayout(View view) {

    }

    @Override
    protected void startProperties() {
        configureToolbar();
    }

    @Override
    protected void defineListeners() {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_about;
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
