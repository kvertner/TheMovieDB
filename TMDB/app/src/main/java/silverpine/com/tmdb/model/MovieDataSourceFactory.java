package silverpine.com.tmdb.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(Application application) {
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
