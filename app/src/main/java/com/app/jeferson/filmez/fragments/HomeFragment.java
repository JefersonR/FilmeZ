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
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.jeferson.filmez.MainActivity;
import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.connectionFactory.RetrofitInterface;
import com.app.jeferson.filmez.providers.MySuggestionProvider;
import com.app.jeferson.filmez.util.ActivityStartProperties;
import com.app.jeferson.filmez.util.Log;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by jeferson on 22/11/15.
 */
public class HomeFragment extends Fragment implements ActivityStartProperties, RetrofitInterface {
    //Ui Elements
    private ImageView voiceSearch;
    private  Menu menu;
    private SearchView searchView;
    private TextView txtSearch;
    private View view;
    //Atributtes

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

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                Log.e("TESTE", "ALOOOO : " + newText);

                return false;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                String suggestion = getSuggestion(position);
                Log.e("SUGGESTION", suggestion);
                searchView.setQuery(suggestion, false);
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



}
