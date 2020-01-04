package silverpine.com.tmdb.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import silverpine.com.tmdb.R;
import silverpine.com.tmdb.databinding.ActivityMovieBinding;
import silverpine.com.tmdb.model.Movie;

public class MovieActivity extends AppCompatActivity {
    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");

            activityMovieBinding.setMovie(movie);
        }
    }
}
