package com.app.jeferson.filmez;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
import com.app.jeferson.filmez.util.ConnectionChecker;
import com.app.jeferson.filmez.util.Log;
import com.app.jeferson.filmez.util.Snackbar;
import com.squareup.picasso.Picasso;

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
    private ImageView bgheader;
    private ProgressBar progress;
    private TextView txtReviews;
    private TextView txtAwards;
    private TextView txtPlot;
    private TextView txtWriter;
    private TextView txtActors;
    private TextView txtProductions;
    private TextView txtSite;
    private TextView txtCountry;
    private Button btnControl;

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
        bgheader = (ImageView)findViewById( R.id.bgheader );
        progress = (ProgressBar)findViewById( R.id.progress );
        txtReviews = (TextView)findViewById( R.id.txt_reviews );
        txtAwards = (TextView)findViewById( R.id.txt_awards );
        txtPlot = (TextView)findViewById( R.id.txt_plot );
        txtWriter = (TextView)findViewById( R.id.txt_writer );
        txtActors = (TextView)findViewById( R.id.txt_actors );
        txtProductions = (TextView)findViewById( R.id.txt_productions );
        txtSite = (TextView)findViewById( R.id.txt_site );
        txtCountry = (TextView)findViewById( R.id.txt_country );
        btnControl = (Button)findViewById( R.id.btn_control );

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
                if(ConnectionChecker.checkConnection(MovieDetailActivity.this)) {
                    doRequest(movie_received.getImdbID());
                }
            }
        }

    }

    private void populate(MovieDetailModel movie) {
        final String str_empty = "N/A";
        collapsingToolbar.setTitle(movie.getTitle());
        if(inMyList){
            fabSave.setVisibility(View.GONE);
            fabDelete.setVisibility(View.VISIBLE);

        }else{
            fabSave.setVisibility(View.VISIBLE);
            fabDelete.setVisibility(View.GONE);
        }
        btnControl();
        Picasso.with(MovieDetailActivity.this)
                .load(movie.getPoster())
                .error(R.drawable.ic_poster_standart)
                .into(imgPoster, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        progressImg.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progressImg.setVisibility(View.GONE);
                    }
                });

        txtTitle.setText(movie.getTitle());
        String director = "Por: "+ movie.getDirector();
        txtDirector.setText(director);
        String runtime = "Duração: "+ movie.getRuntime();
        txtDuration.setText(runtime);
        String genre = "Gênero: "+ movie.getGenre();
        txtGenre.setText(genre);
        String released = "Lançamento: "+ movie.getReleased();
        txtReleased.setText(released);
        String language = "Idioma: "+ movie.getLanguage();
        txtLanguage.setText(language);
        String imdb        = (!movie.getImdbRating().equals(str_empty)?"IMDB: " + movie.getImdbRating() +"/10 - "+ movie.getImdbVotes() + " votos \n":"");
        String rottenUsers = (!movie.getTomatoUserRating().equals(str_empty))?"Rotten usuários: " + movie.getTomatoUserRating() +"/5 - "+ movie.getTomatoUserReviews() + " votos \n":"";
        String rotten      = (!movie.getTomatoRating().equals(str_empty))?"Rotten críticos: " + movie.getTomatoRating() +"/10 - "+ movie.getTomatoReviews() + " votos \n":"";
        String metascore   = (!movie.getMetascore().equals(str_empty))?"Metascore: " + movie.getMetascore() +"/100":"";
        String reviews = imdb + rotten + rottenUsers + metascore;
        txtReviews.setText(reviews);
        txtAwards.setText(movie.getAwards().equals(str_empty)?"Nenhuma informação disponível.":movie.getAwards());
        txtPlot.setText(movie.getPlot().equals(str_empty)?"Nenhuma informação disponível.":movie.getPlot());

        if(movie.getWriter().equals(str_empty)){
            txtWriter.setVisibility(View.GONE);
        }else{
            String writter = "Escrito por: "+ movie.getWriter();
            txtWriter.setText(writter);
        }
        if(movie.getActors().equals(str_empty)){
            txtActors.setVisibility(View.GONE);
        }else{
            String actors = "Elenco: "+ movie.getActors();
            txtActors.setText(actors);
        }
        if(movie.getProduction().equals(str_empty)){
            txtProductions.setVisibility(View.GONE);
        }else{
            String production = "Produzido por "+ movie.getProduction();
            txtProductions.setText(production);
        }
        if(movie.getWebsite().equals(str_empty)){
            txtSite.setVisibility(View.GONE);
        }else{
            txtSite.setText(movie.getWebsite());
        }

        if(movie.getCountry().equals(str_empty)){
            txtCountry.setVisibility(View.GONE);
        }else{
            txtCountry.setText(movie.getCountry());
        }

    }

    public void btnControl(){
        if(inMyList){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                btnControl.setBackground(ContextCompat.getDrawable(MovieDetailActivity.this,R.drawable.rounded_register_button_selector_red));
            }else{
                btnControl.setBackgroundDrawable(ContextCompat.getDrawable(MovieDetailActivity.this,R.drawable.rounded_register_button_selector_red));
            }
            String str_remove = "Remover filme";
            btnControl.setText(str_remove);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                btnControl.setBackground(ContextCompat.getDrawable(MovieDetailActivity.this,R.drawable.rounded_register_button_selector_green));
            }else{
                btnControl.setBackgroundDrawable(ContextCompat.getDrawable(MovieDetailActivity.this,R.drawable.rounded_register_button_selector_green));
            }
            String str_add = "Cadastrar filme";
            btnControl.setText(str_add);
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
                    realmController.persistMovie(movie, new Date());
                    fabSave.setVisibility(View.GONE);
                    fabDelete.setVisibility(View.VISIBLE);
                    inMyList = true;
                    btnControl();

                }

            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(movie != null){
                    remove(movie.getTitle(), movie.getImdbID());
                }
            }
        });

        btnControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inMyList){
                    if(movie != null){
                        remove(movie.getTitle(), movie.getImdbID());
                    }
                }else{
                    if(movie != null) {
                        realmController.persistMovie(movie, new Date());
                        fabSave.setVisibility(View.GONE);
                        fabDelete.setVisibility(View.VISIBLE);
                        inMyList = true;
                        btnControl();

                    }
                }


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
                inMyList = false;
                finish();
//                fabSave.setVisibility(View.VISIBLE);
//                fabDelete.setVisibility(View.GONE);
//                btnControl();


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
        progress.setVisibility(View.VISIBLE);
        retrofit.client().interceptors().add(new LoggingInterceptor());
        Call<MovieDetailModel> call = apiService.searchMovieDetail(params[0]);
        call.enqueue(new Callback<MovieDetailModel>() {

            @Override
            public void onResponse(final Response<MovieDetailModel> response, Retrofit retrofit) {
                try {
                    progress.setVisibility(View.GONE);
                    movie = response.body();
                    if(movie !=null){
                        inMyList = false;
                        populate(movie);
                    }


                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                    progress.setVisibility(View.GONE);
                    Snackbar.make(MovieDetailActivity.this,getString(R.string.connection_fail) );
                }

            }
            @Override
            public void onFailure(Throwable t) {
                try {
                    progress.setVisibility(View.GONE);
                    Snackbar.make(MovieDetailActivity.this,getString(R.string.connection_fail) );
                }catch (Exception e){
                    e.getMessage();
                }

            }
        });
    }
}




