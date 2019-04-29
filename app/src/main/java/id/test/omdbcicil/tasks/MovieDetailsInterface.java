package id.test.omdbcicil.tasks;

import id.test.omdbcicil.models.MovieDetails;

public interface MovieDetailsInterface {
    interface View {
        void showMovie(MovieDetails movie);

        void onFailed(String message);
    }

    interface Presenter {
        void getMovie(String imdbID);
    }
}
