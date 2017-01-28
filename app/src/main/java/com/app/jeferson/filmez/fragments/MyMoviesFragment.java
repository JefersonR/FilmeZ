package com.app.jeferson.filmez.fragments;

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
import com.app.jeferson.filmez.connectionFactory.RetrofitInterface;
import com.app.jeferson.filmez.movies.CardViewItems;
import com.app.jeferson.filmez.util.ActivityStartProperties;
import com.app.jeferson.filmez.util.ConnectionChecker;

import java.util.ArrayList;


/**
 * Created by jeferson on 22/11/15.
 */
public class MyMoviesFragment extends Fragment implements ActivityStartProperties, RetrofitInterface {
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
    private ArrayList<CardViewItems> items;
    private CardViewItems cardViewListItem;



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
        items = new ArrayList<CardViewItems>();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        if(ConnectionChecker.checkConnection(getActivity())){
            doRequest();
        }
    }

    @Override
    public void listeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new   SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRequest();
            }

        });

    }


    @Override
    public void doRequest() {
//        progressBar.setVisibility(View.VISIBLE);
//        retrofit.client().interceptors().add(new LoggingInterceptor());
//        Call<ActivitiesFeedModel> call = apiService.getRides(INT_ZERO, maxResuts, newDateFormat.format(new Date()),true, session.getString(token));
//        call.enqueue(new Callback<ActivitiesFeedModel>() {
//
//            @Override
//            public void onResponse(Response<ActivitiesFeedModel> response, Retrofit retrofit) {
//                try {
//                    items.clear();
//                    txtNothing.setVisibility(View.GONE);
//                    progressBar.setVisibility(View.GONE);
//                    if(response.isSuccess() ){
//                        if(response.body().getData() != null && !response.body().getData().isEmpty()){
//                            for(ActivitiesFeedModel.Datum data : response.body().getData()){
//                                items.add(new CardViewItems(data));
//                            }
//                            recyclerView.setAdapter(new CardViewRecyclerAdapter(items,getActivity()));
//
//                        }else{
//                            txtNothing.setVisibility(View.VISIBLE);
//                        }
//                    }else{
//                        txtNothing.setVisibility(View.VISIBLE);
//                        switch (response.code()){
//                            default:
//                                Snackbar.make(coordinator,error.errorMessage(response.errorBody().string()));
//                                break;
//                            case REQUEST_EXPIRED:
//                                ExpiredConnection.expired(response.errorBody().string(),getActivity());
//                                break;
//                        }
//                    }
//
//                } catch (Exception e) {
//                    Log.e("ERROR", e.getMessage());
//                    progressBar.setVisibility(View.GONE);
//                    txtNothing.setVisibility(View.VISIBLE);
//                    Snackbar.make(coordinator,getString(R.string.connection_fail) );
//                }
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//            @Override
//            public void onFailure(Throwable t) {
//                try {
//                    progressBar.setVisibility(View.GONE);
//                    txtNothing.setVisibility(View.VISIBLE);
//                    Snackbar.make(coordinator, getActivity().getString(R.string.connection_fail));
//                }catch (Exception e){
//                    e.getMessage();
//                }
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });

    }

    @Override
    public void doRequest(String... params) {

    }

}
