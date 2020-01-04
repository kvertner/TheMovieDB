package silverpine.com.tmdb.model;

import android.app.Application;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import silverpine.com.tmdb.R;
import silverpine.com.tmdb.service.MovieDataService;
import silverpine.com.tmdb.service.RetrofitInstance;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private MovieDataService movieDataService;
    private Application application;

    public MovieDataSource(Application application) {
        this.movieDataService = RetrofitInstance.getService();
        this.application = application;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {

       // Call<MovieDBResponse> call = movieDataService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key), 1);
        Call<MovieDBResponse> call = movieDataService.getUpComingMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key), 1, "US");

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();

                if (movieDBResponse != null) {
                    ArrayList<Movie> movies;
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();

                    callback.onResult(movies, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {

        Call<MovieDBResponse> call = movieDataService.getUpComingMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key), params.key, "US");

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();

                if (movieDBResponse != null) {
                    ArrayList<Movie> movies;
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();

                    callback.onResult(movies,  params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }
}
