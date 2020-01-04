package silverpine.com.tmdb.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import silverpine.com.tmdb.model.MovieDBResponse;

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page, @Query("region") String region);

    @GET("movie/now_playing")
    Call<MovieDBResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieDBResponse> getNowPlayingMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page, @Query("region") String region);

    @GET("movie/top_rated")
    Call<MovieDBResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieDBResponse> getTopRatedMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page, @Query("region") String region);

    @GET("movie/upcoming")
    Call<MovieDBResponse> getUpComingMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MovieDBResponse> getUpComingMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page, @Query("region") String region);
}
