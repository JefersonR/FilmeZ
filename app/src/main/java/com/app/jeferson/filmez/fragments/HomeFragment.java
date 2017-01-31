package com.app.jeferson.filmez.fragments;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.jeferson.filmez.MainActivity;
import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.connectionFactory.LoggingInterceptor;
import com.app.jeferson.filmez.connectionFactory.RetrofitInterface;
import com.app.jeferson.filmez.movies.CardViewItems;
import com.app.jeferson.filmez.movies.CardViewRecyclerAdapter;
import com.app.jeferson.filmez.movies.MovieDetailModel;
import com.app.jeferson.filmez.providers.MySuggestionProvider;
import com.app.jeferson.filmez.realm.RealmController;
import com.app.jeferson.filmez.util.ActivityStartProperties;
import com.app.jeferson.filmez.util.ConnectionChecker;
import com.app.jeferson.filmez.util.Log;
import com.app.jeferson.filmez.util.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by jeferson on 22/11/15.
 */
public class HomeFragment extends Fragment implements ActivityStartProperties, RetrofitInterface {
    //Ui Elements
    private  Menu menu;
    private SearchView searchView;
    private TextView txtSearch;
    private View view;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView txtNothing;
    private CoordinatorLayout coordinator;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout layoutWelcome;
    private LinearLayout layoutFirst;

    //Atributtes
    private ArrayList<CardViewItems.Search> items;
    private CardViewItems cardViewListItem;
    private String strSearch = "";
    private final int REQ_CODE_SPEECH_INPUT = 100;


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

        setHasOptionsMenu(true);
        setLayout();
        setProperties();
        listeners();

    }

    @Override
    public void setLayout() {
        txtSearch =(TextView)view.getRootView().findViewById(R.id.txt_search);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.container);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerList);
        progressBar = (ProgressBar)   view.findViewById(R.id.progressBar);
        txtNothing =  (TextView)         view.findViewById(R.id.txt_nothing);
        coordinator =  (CoordinatorLayout)  view.findViewById(R.id.coordinator);
        layoutWelcome =  (LinearLayout)  view.findViewById(R.id.layout_welcome);
        layoutFirst =  (LinearLayout)  view.findViewById(R.id.layout_first);

    }

    @Override
    public void setProperties() {
        items = new ArrayList<CardViewItems.Search>();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        configureToolbar();
        getLastItens();

    }

    private void getLastItens(){
        RealmController realm = new RealmController(getActivity());
        if(realm.hasMovieDetailModels()) {
            layoutWelcome.setVisibility(View.VISIBLE);
            layoutFirst.setVisibility(View.GONE);
            List<MovieDetailModel> myMovies = realm.getMoviesByDate();
            items.clear();
            if (myMovies != null && !myMovies.isEmpty()) {
                for (MovieDetailModel data : myMovies) {
                    items.add(new CardViewItems.Search(data.getTitle(), data.getYear(), data.getImdbID(), data.getType(), data.getPoster()));
                }
            }
            recyclerView.setAdapter(null);
            recyclerView.setAdapter(new CardViewRecyclerAdapter(items));
        }else{
            items.clear();
            recyclerView.setAdapter(null);
            recyclerView.setAdapter(new CardViewRecyclerAdapter(items));
            layoutFirst.setVisibility(View.VISIBLE);
            layoutWelcome.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ConnectionChecker.checkConnection(getActivity())) {
            if (!strSearch.isEmpty()){
                doRequest(strSearch);
            }else{
                getLastItens();
            }
        }
    }

    @Override
    public void listeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new   SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(ConnectionChecker.checkConnection(getActivity())) {
                    if (!strSearch.isEmpty()) {
                        doRequest(strSearch);
                    } else {
                        getLastItens();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }

            }

        });

    }

    @Override
    public void onStop() {
        super.onStop();
        strSearch = "";
    }



    @Override
    public void doRequest() {


    }

    @Override
    public void doRequest(String... params) {
        progressBar.setVisibility(View.VISIBLE);
        layoutWelcome.setVisibility(View.GONE);
        layoutFirst.setVisibility(View.GONE);
        items.clear();
        recyclerView.setAdapter(null);
        retrofit.client().interceptors().add(new LoggingInterceptor());
        Call<CardViewItems> call = apiService.searchMovie(params[0]);
        call.enqueue(new Callback<CardViewItems>() {

            @Override
            public void onResponse(Response<CardViewItems> response, Retrofit retrofit) {
                try {

                    txtNothing.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    if(response.isSuccess() ){
                        if(response.body().getSearch() != null && !response.body().getSearch().isEmpty()){
                            for(CardViewItems.Search data : response.body().getSearch()){
                                items.add(data);
                            }
                            recyclerView.setAdapter(new CardViewRecyclerAdapter(items));

                        }else{
                            txtNothing.setVisibility(View.VISIBLE);
                        }
                    }else{
                        Snackbar.make(coordinator,getString(R.string.connection_fail) );
                    }

                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    txtNothing.setVisibility(View.VISIBLE);
                    Snackbar.make(coordinator,getString(R.string.connection_fail) );
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Throwable t) {
                try {
                    progressBar.setVisibility(View.GONE);
                    txtNothing.setVisibility(View.VISIBLE);
                    Snackbar.make(coordinator, getActivity().getString(R.string.connection_fail));
                }catch (Exception e){
                    e.getMessage();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
        this.menu = menu;

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.listsearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        ImageView magImage = (ImageView)searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);

        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.TRANSPARENT);
        magImage.setVisibility(View.GONE);
        magImage.setImageDrawable(null);

        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("QUERY", query);

                SearchRecentSuggestions suggestions = new SearchRecentSuggestions(getActivity(),
                        MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
                suggestions.saveRecentQuery(query, null);


                if(ConnectionChecker.checkConnection(getActivity())){
                    hideKeyboard();
                    strSearch = query;
                    doRequest(strSearch);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                String suggestion = getSuggestion(position);
                Log.e("SUGGESTION", suggestion);
                searchView.setQuery(suggestion, false);
                if(ConnectionChecker.checkConnection(getActivity())){
                    hideKeyboard();
                    strSearch = suggestion;
                    doRequest(strSearch);
                }
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                // Your code here
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.listsearch),new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                menu.findItem(R.id.action_voice).setVisible(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                menu.findItem(R.id.action_voice).setVisible(true);
                return true;
            }
        });



        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                menu.findItem(R.id.listsearch).expandActionView();  ;
                searchView.setIconifiedByDefault(false);
                searchView.setFocusable(true);
                searchView.setIconified(false);
                searchView.requestFocusFromTouch();

            }

        });
    }

    private String getSuggestion(int position) {
        Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
        return cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_voice:
                promptSpeechInput();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Log.e("ERROR", a.getMessage());
        }
    }
    /**
     * Receiving speech input
     * */
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == MainActivity.RESULT_OK && data   != null) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    android.util.Log.e("fala", result.get(0));
                    if (!result.get(0).isEmpty()){
                        if (menu != null && searchView != null) {
                            menu.findItem(R.id.listsearch).expandActionView();
                            searchView.setIconifiedByDefault(false);
                            searchView.setFocusable(true);
                            searchView.setIconified(false);
                            searchView.requestFocusFromTouch();
                            searchView.setQuery(result.get(0), false);
                        }
                    }
                }
                break;
            }
        }
    }

    public void hideKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private void configureToolbar(){

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_search);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if(activity.getSupportActionBar() != null) {
            ((MainActivity) getActivity()).setActionBarDrawerToggle(toolbar);
        }
    }

}
