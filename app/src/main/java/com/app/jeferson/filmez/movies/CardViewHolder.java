package com.app.jeferson.filmez.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.jeferson.filmez.R;

/**
 * Created by Jeferson on 14/05/2016.
 */
public class CardViewHolder extends RecyclerView.ViewHolder {
    public RelativeLayout layoutPoster;
    public ProgressBar progressImg;
    public ImageView imgPoster;
    public LinearLayout layoutInfo;
    public TextView txtTitle;
    public TextView txtYear;
    public ImageView imgForward;
    public ImageView imgSave;
    public ImageView imgDelete;
    public ProgressBar progress;
    public CardView card;

    public CardViewHolder(View itemView) {
        super(itemView);
        
        layoutPoster = (RelativeLayout)itemView.findViewById( R.id.layout_poster );
        progressImg = (ProgressBar)itemView.findViewById( R.id.progress_img );
        imgPoster = (ImageView)itemView.findViewById( R.id.img_poster );
        layoutInfo = (LinearLayout)itemView.findViewById( R.id.layout_info );
        txtTitle = (TextView)itemView.findViewById( R.id.txt_title );
        txtYear = (TextView)itemView.findViewById( R.id.txt_year );
        imgForward = (ImageView)itemView.findViewById( R.id.img_forward );
        imgSave = (ImageView)itemView.findViewById( R.id.img_save );
        imgDelete = (ImageView)itemView.findViewById( R.id.img_delete );
        progress = (ProgressBar)itemView.findViewById( R.id.progress );
        card = (CardView)itemView.findViewById( R.id.card );

    }
}





