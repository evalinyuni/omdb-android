package id.test.omdbcicil.tasks;

import java.util.ArrayList;
import id.test.omdbcicil.models.MovieList;


public interface MovieListInterface {
    interface View {
        void setMovieList(ArrayList<MovieList.Movie> movieList);

        void updateUi(Boolean isLoading);

        void runLayoutAnimation();

        void onSuccess();

        void onFailed(String message);
    }

    interface Presenter {
        void getMoviesList(String title);

        void getMoreMovies();
    }
}
