package com.app.jeferson.filmez.ui.activities;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.bases.BaseActivity;
import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends BaseActivity{
    private ImageView imgPoster;
    private ProgressBar progress;
    private TextView txtTitle;
    private String picture;
    private String title;


    @Override
    public void setLayout() {
        imgPoster = (ImageView) findViewById(R.id.img_poster);
        progress = (ProgressBar) findViewById(R.id.progress);
        txtTitle = (TextView) findViewById(R.id.txt_title);
    }

    @Override
    protected void startProperties() {
        setToolbar(R.id.toolbar);
        picture = getIntent().getStringExtra("PHOTO");
        title = getIntent().getStringExtra("TITLE");

        txtTitle.setText(title);
        Picasso.with(ImageDetailActivity.this)
                .load(picture)
                .error(R.drawable.ic_poster_standart)
                .into(imgPoster, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progress.setVisibility(View.GONE);
                    }
                });

    }

    @Override
    protected void defineListeners() {

    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_image_detail;
    }

    @Override
    protected Context getContext() {
        return ImageDetailActivity.this;
    }



}