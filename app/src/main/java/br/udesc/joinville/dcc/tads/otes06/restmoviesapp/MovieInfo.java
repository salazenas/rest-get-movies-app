package br.udesc.joinville.dcc.tads.otes06.restmoviesapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);
        Intent previousMovie = getIntent();
        String previousId = previousMovie.getExtras().getString(("movieId"));
        LoadMovie loadMovie = new LoadMovie(previousId);
        loadMovie.execute();
    }

    class LoadMovie extends AsyncTask<Void, Void, Movie> {

        String movieId = "";

        LoadMovie(String id) {
            super();
            this.movieId = id;
        }

        @Override
        protected Movie doInBackground(Void... voids) {
            MoviesService mvs = new MoviesService();
            Movie movie = mvs.getById(this.movieId);
            return movie;
        }

        @Override
        protected void onPostExecute(Movie movie) {

            TextView title = findViewById(R.id.title);
            TextView duration = findViewById(R.id.duration);
            TextView cost = findViewById(R.id.cost);
            TextView year = findViewById(R.id.year);
            TextView director_id = findViewById(R.id.director);
            TextView currency = findViewById(R.id.currency);

            title.setText("Titulo: " + movie.getTitle());
            duration.setText("Duração: " + movie.getDuration());
            cost.setText("Custo: " + movie.getCost());
            year.setText("Ano: " + movie.getYear());
            director_id.setText("Id Diretor: " + movie.getDirector_id());
            currency.setText("Pais: " + movie.getCurrency());
        }
    }
}
