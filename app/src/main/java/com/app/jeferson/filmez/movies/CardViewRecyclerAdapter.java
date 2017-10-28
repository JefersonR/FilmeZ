package com.app.jeferson.filmez.movies;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.network.connectionService.Interfaces.OnSucess;
import com.app.jeferson.filmez.network.connectionService.Presenter;
import com.app.jeferson.filmez.realm.RealmController;
import com.app.jeferson.filmez.ui.activities.ImageDetailActivity;
import com.app.jeferson.filmez.ui.activities.MovieDetailActivity;
import com.app.jeferson.filmez.util.ConnectionChecker;
import com.app.jeferson.filmez.util.Constants;
import com.app.jeferson.filmez.util.Log;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Jeferson on 11/05/2016.
 */
public class CardViewRecyclerAdapter extends RecyclerView.Adapter<CardViewHolder> implements Constants {

    private List<CardViewItems.Search> cardViewListItems;
    private Context context;
    private boolean isMyMovies = false;
    private RealmController realmController;

    public CardViewRecyclerAdapter(List<CardViewItems.Search> palettes) {
        this.cardViewListItems = new ArrayList<CardViewItems.Search>();
        this.cardViewListItems.addAll(palettes);
        isMyMovies = false;
    }

    public CardViewRecyclerAdapter(List<CardViewItems.Search> palettes, boolean isMyMovies) {
        this.cardViewListItems = new ArrayList<CardViewItems.Search>();
        this.cardViewListItems.addAll(palettes);
        this.isMyMovies = isMyMovies;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_listitem, viewGroup, false);
        context = viewGroup.getContext();
        realmController = new RealmController(context);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder cardViewListViewHolder,int i) {
        try {
            final CardViewItems.Search cardViewListItem = cardViewListItems.get(cardViewListViewHolder.getAdapterPosition());
            if (cardViewListItem != null) {

                if(realmController.getMovieDetailModel(cardViewListItem.getImdbID()) != null){
                    cardViewListViewHolder.imgSave.setVisibility(View.GONE);
                    cardViewListViewHolder.imgDelete.setVisibility(View.VISIBLE);
                }else{
                    cardViewListViewHolder.imgSave.setVisibility(View.VISIBLE);
                    cardViewListViewHolder.imgDelete.setVisibility(View.GONE);
                }

                cardViewListViewHolder.txtTitle.setText(cardViewListItem.getTitle());
                String strYear = cardViewListItem.getYear();
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
                            doRequestDetail(cardViewListItem.getImdbID(), cardViewListViewHolder.progress,cardViewListViewHolder.imgSave,cardViewListViewHolder.imgDelete);
                        }
                    }
                });

                cardViewListViewHolder.imgDelete.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if (realmController.getMovieDetailModel(cardViewListItem.getImdbID()) != null) {
                            remove(cardViewListItem.getTitle(), cardViewListItem.getImdbID(),cardViewListViewHolder.getAdapterPosition(),cardViewListViewHolder.imgSave,cardViewListViewHolder.imgDelete);
                        }

                    }
                });

                cardViewListViewHolder.imgPoster.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                      Intent intent = new Intent(context, ImageDetailActivity.class);
                        intent.putExtra("PHOTO",cardViewListItem.getPoster());
                        intent.putExtra("TITLE",cardViewListItem.getTitle());
                        context.startActivity(intent);

                    }
                });

                cardViewListViewHolder.imgForward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent(context, MovieDetailActivity.class);
                            intent.putExtra("MOVIE",cardViewListItem);
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
                            intent.putExtra("MOVIE",cardViewListItem);
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

    public void removeItem(int position) {

        cardViewListItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cardViewListItems.size());
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




    public void doRequestDetail(String movieID, final ProgressBar progressBar,final ImageView save, final ImageView delete) {
        progressBar.setVisibility(View.VISIBLE);
        new Presenter(context).searchMovieDetail(movieID, new OnSucess() {
            @Override
            public void onSucessResponse(retrofit2.Response response) {
                MovieDetailModel movieDetailModel = (MovieDetailModel)response.body();
                if(movieDetailModel != null){
                    realmController.persistMovie(movieDetailModel, new Date());
                    save.setVisibility(View.GONE);
                    delete.setVisibility(View.VISIBLE);
                }

            }
        },progressBar);

    }

    private void remove(final String title, final String movieID,final int position,final ImageView save, final ImageView delete) {


        AlertDialog alerta;
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //define o titulo
        builder.setTitle("Remover filme");
        //define a mensagem
        builder.setMessage("Tem certeza que deseja remover " + title + " da sua lista?");


        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                realmController.deleteMovieDetailModel(movieID);
                if(!isMyMovies) {
                    delete.setVisibility(View.GONE);
                    save.setVisibility(View.VISIBLE);
                }else{
                    removeItem(position);
                }
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}