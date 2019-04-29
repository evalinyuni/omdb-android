package id.test.omdbcicil.presenters;

import android.support.annotation.NonNull;

import java.util.HashMap;

import id.test.omdbcicil.api.MovieApiService;
import id.test.omdbcicil.ext.Constants;
import id.test.omdbcicil.models.MovieList;
import id.test.omdbcicil.tasks.MovieListInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListPresenter implements MovieListInterface.Presenter {

    private MovieListInterface.View view;
    private MovieApiService moviesApiService;
    private int page = 1;
    private int numPages = 2;
    private String query = "";
    private HashMap<String, String> params;
    private MovieList movieList;

    public MovieListPresenter(MovieListInterface.View view) {
        this.view = view;
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moviesApiService = retrofit.create(MovieApiService.class);
    }

    @Override
    public void getMoviesList(String title) {
        initPagination(title);
        initParams();
        Call<MovieList> call = moviesApiService.getMovieList(params);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(@NonNull Call<MovieList> call, @NonNull Response<MovieList> response) {
                movieList = response.body();
                if (response.isSuccessful() && movieList != null) {
                    setPagination(movieList);
                    if (page < numPages) {
                        movieList.getMovies().add(null);
                    }
                    view.setMovieList(movieList.getMovies());
                    view.runLayoutAnimation();
                    page++;
                    if(!"".equals(title)) {
                        view.onSuccess();
                    }
                } else {
                    view.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieList> call, @NonNull Throwable t) {
                view.onFailed(t.getMessage());
            }
        });
    }

    @Override
    public void getMoreMovies() {
        if (page <= numPages) {
            initParams();
            Call<MovieList> call = moviesApiService.getMovieList(params);
            call.enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(@NonNull Call<MovieList> call, @NonNull Response<MovieList> response) {
                    movieList = response.body();
                    if (response.isSuccessful() && movieList != null) {
                        page++;
                        if (page < numPages) {
                            movieList.getMovies().add(null);
                        }
                        view.setMovieList(movieList.getMovies());
                    } else {
                        view.onFailed(response.message());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MovieList> call, @NonNull Throwable t) {
                    view.onFailed(t.getMessage());
                }
            });
        }
    }

    private void initParams() {
        params = new HashMap<>();
        params.put(Constants.getParamSearch(), query);
        params.put(Constants.getParamType(), "movie");
        params.put(Constants.getParamPage(), String.valueOf(page));
        params.put(Constants.getParamApiKey(), Constants.getApiKey());
    }

    private void initPagination(String title) {
        page = 1;
        numPages = 2;
        query = title;
    }

    private void setPagination(MovieList movieList) {
        numPages = movieList.getTotalResults() / 10;
        if (movieList.getTotalResults() % 10 != 0) {
            numPages++;
        }
    }
}
