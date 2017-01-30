package com.app.jeferson.filmez.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.movies.CardViewItems;
import com.app.jeferson.filmez.movies.CardViewRecyclerAdapter;
import com.app.jeferson.filmez.movies.MovieDetailModel;
import com.app.jeferson.filmez.realm.RealmController;
import com.app.jeferson.filmez.util.ActivityStartProperties;

import java.util.ArrayList;
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
    private Toolbar toolbar;

    //Atributtes
    private ArrayList<CardViewItems.Search> items;
    private CardViewItems.Search cardViewListItem;



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
        txtNothing =  (TextView)         view.findViewById(R.id.txt_nothing);
        coordinator =  (CoordinatorLayout)  view.findViewById(R.id.coordinator);
    }

    @Override
    public void setProperties() {
        items = new ArrayList<CardViewItems.Search>();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        getMyMovies();

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

    public void getMyMovies(){
        new loadMovies().execute();
    }

    private class loadMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            items.clear();
            List<MovieDetailModel> myMovies = new RealmController(getActivity()).getMovieDetailModels();
            if(myMovies != null && !myMovies.isEmpty() ){
                for(MovieDetailModel data : myMovies){
                    items.add(new CardViewItems.Search(data.getTitle(), data.getYear(),data.getImdbID(),data.getType(),data.getPoster()));
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            recyclerView.setAdapter(new CardViewRecyclerAdapter(items,true));
            mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }




}
