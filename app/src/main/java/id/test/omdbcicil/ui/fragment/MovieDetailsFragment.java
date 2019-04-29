package id.test.omdbcicil.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.test.omdbcicil.R;
import id.test.omdbcicil.ext.Constants;
import id.test.omdbcicil.models.MovieDetails;
import id.test.omdbcicil.presenters.MovieDetailsPresenter;
import id.test.omdbcicil.tasks.MovieDetailsInterface;
import id.test.omdbcicil.ui.Utils.Snackbar;
import id.test.omdbcicil.ui.adapter.GenreAdapter;
import id.test.omdbcicil.ui.adapter.RatingAdapter;

public class MovieDetailsFragment extends Fragment implements MovieDetailsInterface.View{
    @BindView(R.id.tv_released)
    TextView tv_released;
    @BindView(R.id.tv_director) TextView tv_director;
    @BindView(R.id.tv_writer) TextView tv_writer;
    @BindView(R.id.tv_rated) TextView tv_rated;
    @BindView(R.id.tv_plot) TextView tv_plot;
    @BindView(R.id.rv_genre)
    RecyclerView rv_genre;
    @BindView(R.id.rv_ratings) RecyclerView rv_ratings;
    @BindView(R.id.sv_movie)
    ScrollView sv_movie;
    @BindView(R.id.pg_movie)
    ProgressBar pg_movie;
    private ArrayList<String> genres;
    private ArrayList<MovieDetails.Rating> ratings;

    public static MovieDetailsFragment newInstance(String imdbID) {
        Bundle args = new Bundle();
        args.putString(Constants.getImdbId(), imdbID);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment_movie = inflater.inflate(R.layout.fragment_movie_details, container, false);

        ButterKnife.bind(this, fragment_movie);
        genres = new ArrayList<>();
        ratings = new ArrayList<>();
        MovieDetailsInterface.Presenter presenter = new MovieDetailsPresenter(this);
        presenter.getMovie(Objects.requireNonNull(getArguments()).getString(Constants.getImdbId()));
        return fragment_movie;
    }

    @Override
    public void showMovie(MovieDetails movie) {
        tv_released.setText(String.format("%s: %s", getText(R.string.year), movie.getReleased()));
        tv_director.setText(String.format("%s: %s", getText(R.string.director), movie.getDirector()));
        tv_writer.setText(String.format("%s: %s", getText(R.string.writer), movie.getWriter()));
        tv_rated.setText(movie.getRated());
        tv_plot.setText(movie.getPlot());
        genres.addAll(movie.getGenre());
        ratings.addAll(movie.getRatings());
        initGenresRecyclerView();
        initRatingRecyclerView();
        updateUi(false);
    }

    public void initGenresRecyclerView() {
        rv_genre.setHasFixedSize(true);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        rv_genre.setLayoutManager(layoutManager);
        GenreAdapter adapter = new GenreAdapter(genres);
        rv_genre.setAdapter(adapter);
    }

    public void initRatingRecyclerView() {
        rv_ratings.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_ratings.setLayoutManager(linearLayoutManager);
        RatingAdapter adapter = new RatingAdapter(ratings);
        rv_ratings.setAdapter(adapter);
    }

    @Override
    public void onFailed(String message) {
        if (getActivity() != null) {
            Snackbar.Error(getActivity(), message);
        }
    }

    public void updateUi(boolean isLoading) {
        pg_movie.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        sv_movie.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }
}
