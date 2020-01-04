package silverpine.com.tmdb.viewmodel;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import silverpine.com.tmdb.R;
import silverpine.com.tmdb.model.Movie;
import silverpine.com.tmdb.model.MovieDataSource;
import silverpine.com.tmdb.model.MovieDataSourceFactory;

public class MainActivityViewModel extends AndroidViewModel {
    LiveData<MovieDataSource> movieDatSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviesPagedList;
    private MovieQueryType selectedMovieQuery = MovieQueryType.NOW_PLAYING;
    private Application application;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;

        MovieDataSourceFactory factory = new MovieDataSourceFactory(application);
        movieDatSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        moviesPagedList = (new LivePagedListBuilder<Long, Movie>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }

    public MovieQueryType getSelectedMovieQuery() {
        return selectedMovieQuery;
    }

    public void setSelectedMovieQuery(MovieQueryType selectedMovieQuery) {
        this.selectedMovieQuery = selectedMovieQuery;
    }

    public String getMovieQueryTypeTitle() {
        switch (selectedMovieQuery) {
            case POPULAR:
                return application.getString(R.string.popular_title);
            case UPCOMING:
                return application.getString(R.string.upcoming_title);
            case TOP_RATED:
                return application.getString(R.string.top_rated_title);
            case NOW_PLAYING:
                return application.getString(R.string.now_playing_title);
            default:
                return "Unknown Movie Query Type";
        }
    }

    public enum MovieQueryType {
        NOW_PLAYING,
        POPULAR,
        TOP_RATED,
        UPCOMING
    }
}
