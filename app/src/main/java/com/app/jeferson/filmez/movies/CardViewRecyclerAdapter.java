package com.app.jeferson.filmez.movies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.jeferson.filmez.MovieDetailActivity;
import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.connectionFactory.LoggingInterceptor;
import com.app.jeferson.filmez.connectionFactory.RetrofitInterface;
import com.app.jeferson.filmez.util.ConnectionChecker;
import com.app.jeferson.filmez.util.Constants;
import com.app.jeferson.filmez.util.Log;
import com.app.jeferson.filmez.util.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by Jeferson on 11/05/2016.
 */
public class CardViewRecyclerAdapter extends RecyclerView.Adapter<CardViewHolder> implements Constants, RetrofitInterface {

    private List<CardViewItems.Search> cardViewListItems;
    private Context context;

    public CardViewRecyclerAdapter(List<CardViewItems.Search> palettes) {
        this.cardViewListItems = new ArrayList<CardViewItems.Search>();
        this.cardViewListItems.addAll(palettes);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_listitem, viewGroup, false);
        context = viewGroup.getContext();
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder cardViewListViewHolder,final int i) {
        try {
            final CardViewItems.Search cardViewListItem = cardViewListItems.get(i);
            if (cardViewListItem != null) {

                cardViewListViewHolder.txtTitle.setText(cardViewListItem.getTitle());
                String strYear = "("+ cardViewListItem.getYear()+")";
                cardViewListViewHolder.txtYear.setText(strYear);
                if(cardViewListItem.getPoster()!= null && !cardViewListItem.getPoster().isEmpty()) {
                    Picasso.with(context)
                            .load(cardViewListItem.getPoster())
                            .error(R.drawable.ic_poster_standart)
                            .into(cardViewListViewHolder.imgPoster, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    cardViewListViewHolder.progressImg.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError() {
                                    cardViewListViewHolder.progressImg.setVisibility(View.GONE);
                                }
                            });
                }else{
                    cardViewListViewHolder.progressImg.setVisibility(View.GONE);
                }


                cardViewListViewHolder.imgSave.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(ConnectionChecker.checkConnection((Activity) context)){
                            doRequestDetail(cardViewListItem.getImdbID(), cardViewListViewHolder.progress);
                        }
                    }
                });

                cardViewListViewHolder.imgForward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent(context, MovieDetailActivity.class);
                            context.startActivity(intent);
                            Activity activity = (Activity) context;
                            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } catch (Exception e) {
                            e.getMessage();
                        }

                    }
                });

                cardViewListViewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent(context, MovieDetailActivity.class);
                            context.startActivity(intent);
                            Activity activity = (Activity) context;
                            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } catch (Exception e) {
                            e.getMessage();
                        }

                    }
                });

            }
        }catch(Exception e){
            e.getMessage();
            Log.e("ERROR", e.getMessage() + "<");
        }


    }

    public List<CardViewItems.Search> getCardViewListItems() {
        return cardViewListItems;
    }

    public void setCardViewListItems(List<CardViewItems.Search> cardViewListItems) {
        this.cardViewListItems = cardViewListItems;
    }

    @Override
    public int getItemCount() {
        return cardViewListItems.size();
    }


    @Override
    public void doRequest() {

    }

    @Override
    public void doRequest(String... params) {

    }

    public void doRequestDetail(String movieID,final ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        retrofit.client().interceptors().add(new LoggingInterceptor());
        Call<MovieDetail> call = apiService.searchMovieDetail(movieID);
        call.enqueue(new Callback<MovieDetail>() {

            @Override
            public void onResponse(Response<MovieDetail> response, Retrofit retrofit) {
                try {
                    progressBar.setVisibility(View.GONE);

                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Snackbar.make((Activity) context,context.getString(R.string.connection_fail) );
                }

            }
            @Override
            public void onFailure(Throwable t) {
                try {
                    progressBar.setVisibility(View.GONE);
                    Snackbar.make((Activity) context,context.getString(R.string.connection_fail) );
                }catch (Exception e){
                    e.getMessage();
                }

            }
        });
    }
}