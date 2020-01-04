package silverpine.com.tmdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import silverpine.com.tmdb.R;
import silverpine.com.tmdb.databinding.MovieListItemBinding;
import silverpine.com.tmdb.model.Movie;
import silverpine.com.tmdb.view.MovieActivity;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {
    private Context context;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);

        holder.movieListItemBinding.setMovie(movie);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(view -> {
                int pos = getAdapterPosition();
                Movie movie = getItem(pos);

                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            });

        }
    }
}
