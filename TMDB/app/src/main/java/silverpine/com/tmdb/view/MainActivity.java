package silverpine.com.tmdb.view;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import silverpine.com.tmdb.R;
import silverpine.com.tmdb.adapter.MovieAdapter;
import silverpine.com.tmdb.databinding.ActivityMainBinding;
import silverpine.com.tmdb.model.Movie;
import silverpine.com.tmdb.viewmodel.MainActivityViewModel;


public class MainActivity extends AppCompatActivity {

    private PagedList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        getSupportActionBar().setTitle(mainActivityViewModel.getMovieQueryTypeTitle());

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getMovies();

        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(() -> getMovies());
    }

    private void getMovies() {
        mainActivityViewModel.getMoviesPagedList().observe(this, moviesFromLiveData -> {
            movies = moviesFromLiveData;
            showOnRecyclerView();
        });
    }

    private void showOnRecyclerView() {
        int span = 2;

        recyclerView = activityMainBinding.rvMovies;
        adapter = new MovieAdapter(this);
        adapter.submitList(movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            span = 4;
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this,span));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}
