package com.app.jeferson.filmez.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.util.Constants;
import com.app.jeferson.filmez.util.Log;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jeferson on 11/05/2016.
 */
public class CardViewRecyclerAdapter extends RecyclerView.Adapter<CardViewHolder> implements Constants {

    private List<CardViewItems.Search> cardViewListItems;
    private Context context;
    private int typeChat = 0;
    private int cid = 0;
    private boolean privacy = false;
    private int current = 0;


    public CardViewRecyclerAdapter(List<CardViewItems.Search> palettes) {
        this.cardViewListItems = new ArrayList<CardViewItems.Search>();
        this.cardViewListItems.addAll(palettes);
        privacy = false;

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


}