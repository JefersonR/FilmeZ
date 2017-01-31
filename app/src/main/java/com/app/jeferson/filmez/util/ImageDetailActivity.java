package com.app.jeferson.filmez.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.jeferson.filmez.R;
import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity implements ActivityStartProperties {
    private Toolbar toolbar;
    private ImageView imgPoster;
    private ProgressBar progress;
    private TextView txtTitle;
    private String picture;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        setLayout();
        setProperties();
        listeners();
    }

    @Override
    public void setLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgPoster = (ImageView) findViewById(R.id.img_poster);
        progress = (ProgressBar) findViewById(R.id.progress);
        txtTitle = (TextView) findViewById(R.id.txt_title);
    }

    @Override
    public void setProperties() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

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
    public void listeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}