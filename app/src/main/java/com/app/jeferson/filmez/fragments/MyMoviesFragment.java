package com.app.jeferson.filmez.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.jeferson.filmez.MainActivity;
import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.movies.CardViewItems;
import com.app.jeferson.filmez.movies.CardViewRecyclerAdapter;
import com.app.jeferson.filmez.movies.MovieDetailModel;
import com.app.jeferson.filmez.realm.RealmController;
import com.app.jeferson.filmez.util.ActivityStartProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by jeferson on 22/11/15.
 */
public class MyMoviesFragment extends Fragment implements ActivityStartProperties {
    //Ui Elements
    private Menu menu;
    private View view;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView txtNothing;
    private CoordinatorLayout coordinator;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    //Atributtes
    private ArrayList<CardViewItems.Search> items;
    private CardViewItems.Search cardViewListItem;
    private int sort = 2;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        } catch (InflateException e) {
            e.getMessage();
        }
        setHasOptionsMenu(true);
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
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.container);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerList);
        progressBar = (ProgressBar)   view.findViewById(R.id.progressBar);
        txtNothing =  (TextView)         view.findViewById(R.id.txt_nothing_movie);
        coordinator =  (CoordinatorLayout)  view.findViewById(R.id.coordinator);
    }

    @Override
    public void setProperties() {
        configureToolbar();
        items = new ArrayList<CardViewItems.Search>();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


    }

    @Override
    public void listeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new   SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyMovies();
            }

        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getMyMovies();
    }



    private void configureToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if(activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle("Meus filmes");
            ((MainActivity) getActivity()).setActionBarDrawerToggle(toolbar);
        }
    }

    public void getMyMovies(){
        items.clear();
        List<MovieDetailModel> myMovies = new RealmController(getActivity()).getMovieDetailModels();
        if(myMovies != null && !myMovies.isEmpty() ){
            for(MovieDetailModel data : myMovies){
                items.add(new CardViewItems.Search(data.getTitle(), data.getYear(),data.getImdbID(),data.getType(),data.getPoster()));
            }
        }
        if(items.isEmpty()){
            txtNothing.setVisibility(View.VISIBLE);
        }else{
            txtNothing.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(new CardViewRecyclerAdapter(items,true));
        mSwipeRefreshLayout.setRefreshing(false);
        if(sort != 2){
            sortList(sort);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_filter_movies, menu);
        super.onCreateOptionsMenu(menu,inflater);
        this.menu = menu;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_name:
                if (items != null && !items.isEmpty()) {
                    sortList(0);
                }
                return true;
            case R.id.sort_year:
                if (items != null && !items.isEmpty()) {
                    sortList(1);
                }
                return true;

            case R.id.sort_insert:
                if (items != null && !items.isEmpty()) {
                    sortList(2);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sortList(int position) {
        sort = position;
        switch (position) {
            case 0:
                Collections.sort(items, new Comparator<CardViewItems.Search>() {
                    @Override
                    public int compare(CardViewItems.Search movies, CardViewItems.Search movies2) {
                        return movies.getTitle().compareTo(movies2.getTitle());
                    }
                });

                recyclerView.setAdapter(null);
                recyclerView.setAdapter(new CardViewRecyclerAdapter(items,true));
                break;
            case 1:
                Collections.sort(items, new Comparator<CardViewItems.Search>() {
                    @Override
                    public int compare(CardViewItems.Search movies, CardViewItems.Search movies2) {
                        return movies.getYear().compareTo(movies2.getYear());
                    }
                });

                recyclerView.setAdapter(null);
                recyclerView.setAdapter(new CardViewRecyclerAdapter(items,true));
                break;

            case 2:
                getMyMovies();
                break;
            default:
                break;
        }
    }


}
