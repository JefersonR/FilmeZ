package com.app.jeferson.filmez;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.jeferson.filmez.fragments.AboutFragment;
import com.app.jeferson.filmez.fragments.HomeFragment;
import com.app.jeferson.filmez.fragments.MyMoviesFragment;
import com.app.jeferson.filmez.util.ActivityStartProperties;

public class MainActivity extends AppCompatActivity implements ActivityStartProperties {

    //UI Elements
    private Toolbar toolbar;
    private Toolbar toolbarSearch;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RelativeLayout relativeToolbar;

    //Fragments
    private HomeFragment homefragment;
    private MyMoviesFragment myMoviesFragment;
    private AboutFragment aboutFragment;

    //Defining Variables
    private final int TIME_EFFECT = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLayout();
        setProperties();
        listeners();
    }

    private void controlFragments(boolean isHome){
        if(isHome) {
            relativeToolbar.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.GONE);

        }else{
            relativeToolbar.setVisibility(View.GONE);
            toolbar.setVisibility(View.VISIBLE);

        }
    }

    private void controlFragments(boolean addFragment, boolean isHome){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(isHome) {
            if (homefragment == null) {
                homefragment = new HomeFragment();
            }
            fragmentTransaction.replace(R.id.frame, homefragment);
            fragmentTransaction.commit();
            relativeToolbar.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.GONE);

        }else{
            relativeToolbar.setVisibility(View.GONE);
            toolbar.setVisibility(View.VISIBLE);

        }
    }


    public void setActionBarDrawerToggle(Toolbar toolbar) {

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }




    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment_byID = fm.findFragmentById(R.id.frame);
        if(!fragment_byID.equals(homefragment)){
            controlFragments(true,true);
        } else{
            super.onBackPressed();
        }
    }

    @Override
    public void setLayout() {

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Meus filmes");
        }

        toolbarSearch = (Toolbar) findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbarSearch);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("");
        }
        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        relativeToolbar = (RelativeLayout) findViewById(R.id.relative_toolbar);
    }

    @Override
    public void setProperties() {
        controlFragments(true,true);

        drawerLayout.openDrawer(GravityCompat.START);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawerLayout.closeDrawers();
            }
        }, TIME_EFFECT);

    }

    @Override
    public void listeners() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    //Replacing the main content with right fragment;
                    case R.id.home:
                        controlFragments(true, true);
                        return true;

                    case R.id.my_movies:
                        if (myMoviesFragment == null) {
                            myMoviesFragment = new MyMoviesFragment();
                        }

                        fragmentTransaction.replace(R.id.frame, myMoviesFragment);
                        fragmentTransaction.commit();
                        controlFragments(false);

                        return true;
                    case R.id.about:
                        if (aboutFragment == null) {
                            aboutFragment = new AboutFragment();
                        }

                        fragmentTransaction.replace(R.id.frame, aboutFragment);
                        fragmentTransaction.commit();
                        controlFragments(false);

                        return true;
                    default:
                        controlFragments(true, true);
                        return true;

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            for (android.support.v4.app.Fragment fragment : getSupportFragmentManager().getFragments()) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }catch(Exception e){
            e.getMessage();
        }
    }


}
