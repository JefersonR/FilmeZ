package com.app.jeferson.filmez;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.jeferson.filmez.connectionFactory.LoggingInterceptor;
import com.app.jeferson.filmez.connectionFactory.RetrofitInterface;
import com.app.jeferson.filmez.movies.CardViewItems;
import com.app.jeferson.filmez.movies.MovieDetailModel;
import com.app.jeferson.filmez.realm.RealmController;
import com.app.jeferson.filmez.util.ActivityStartProperties;
import com.app.jeferson.filmez.util.Log;
import com.app.jeferson.filmez.util.Snackbar;

import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MovieDetailActivity extends AppCompatActivity implements ActivityStartProperties, RetrofitInterface{
    //UI elements
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fabSave;
    private FloatingActionButton fabDelete;
    private RelativeLayout layoutPoster;
    private ProgressBar progressImg;
    private ImageView imgPoster;
    private LinearLayout layoutInfo;
    private TextView txtTitle;
    private TextView txtDirector;
    private TextView txtDuration;
    private TextView txtGenre;
    private TextView txtReleased;
    private TextView txtLanguage;

    //Attributes
    private MovieDetailModel movie;
    private boolean inMyList = false;
    private RealmController realmController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setLayout();
        setProperties();
        listeners();

    }

    @Override
    public void setLayout() {
        toolbar =  (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        fabSave = (FloatingActionButton) findViewById(R.id.float_save);
        fabDelete = (FloatingActionButton) findViewById(R.id.float_delete);
        layoutPoster = (RelativeLayout)findViewById( R.id.layout_poster );
        progressImg = (ProgressBar)findViewById( R.id.progress_img );
        imgPoster = (ImageView)findViewById( R.id.img_poster );
        layoutInfo = (LinearLayout)findViewById( R.id.layout_info );
        txtTitle = (TextView)findViewById( R.id.txt_title );
        txtDirector = (TextView)findViewById( R.id.txt_director );
        txtDuration = (TextView)findViewById( R.id.txt_duration );
        txtGenre = (TextView)findViewById( R.id.txt_genre );
        txtReleased = (TextView)findViewById( R.id.txt_released );
        txtLanguage = (TextView)findViewById( R.id.txt_language );
    }

    @Override
    public void setProperties() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        realmController = new RealmController(MovieDetailActivity.this);
        if(getIntent().hasExtra("MOVIE")){
            CardViewItems.Search movie_received = (CardViewItems.Search) getIntent().getSerializableExtra("MOVIE");
            try{
                movie = realmController.getMovieDetailModel(movie_received.getImdbID());
            }catch(Exception e){
                e.getMessage();
            }

            if(movie != null){
                inMyList = true;
                populate(movie);

            }else{
                doRequest(movie_received.getImdbID());
            }
        }

    }

    private void populate(MovieDetailModel movie) {
        collapsingToolbar.setTitle(movie.getTitle());
        if(inMyList){
            fabSave.setVisibility(View.GONE);
            fabDelete.setVisibility(View.VISIBLE);

        }else{
            fabSave.setVisibility(View.VISIBLE);
            fabDelete.setVisibility(View.GONE);

        }

    }

    @Override
    public void listeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(movie != null) {
                    movie.setDate(new Date());
                    realmController.persistMovie(movie);
                    fabSave.setVisibility(View.GONE);
                    fabDelete.setVisibility(View.VISIBLE);
                }

            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(movie.getTitle(), movie.getImdbID());
            }
        });

    }

    private void remove(final String title, final String movieID) {

        AlertDialog alerta;
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MovieDetailActivity.this);
        //define o titulo
        builder.setTitle("Remover filme");
        //define a mensagem
        builder.setMessage("Tem certeza que deseja remover " + title + " da sua lista?");


        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                realmController.deleteMovieDetailModel(movieID);
                fabSave.setVisibility(View.VISIBLE);
                fabDelete.setVisibility(View.GONE);

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


    @Override
    public void doRequest() {

    }

    @Override
    public void doRequest(String... params) {
//        progressBar.setVisibility(View.VISIBLE);
        retrofit.client().interceptors().add(new LoggingInterceptor());
        Call<MovieDetailModel> call = apiService.searchMovieDetail(params[0]);
        call.enqueue(new Callback<MovieDetailModel>() {

            @Override
            public void onResponse(final Response<MovieDetailModel> response, Retrofit retrofit) {
                try {
//                    progressBar.setVisibility(View.GONE);

                    movie = response.body();
                    if(movie !=null){
                        inMyList = false;
                        populate(movie);
                    }


                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
//                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(MovieDetailActivity.this,getString(R.string.connection_fail) );
                }

            }
            @Override
            public void onFailure(Throwable t) {
                try {
//                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(MovieDetailActivity.this,getString(R.string.connection_fail) );
                }catch (Exception e){
                    e.getMessage();
                }

            }
        });
    }
}

