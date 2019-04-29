package id.test.omdbcicil.presenters;

import android.support.annotation.NonNull;

import java.util.HashMap;

import id.test.omdbcicil.api.MovieApiService;
import id.test.omdbcicil.ext.Constants;
import id.test.omdbcicil.models.MovieDetails;
import id.test.omdbcicil.tasks.MovieDetailsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsPresenter implements MovieDetailsInterface.Presenter  {

    private MovieDetailsInterface.View view;
    private HashMap<String, String> params;
    private MovieApiService moviesApiService;

    public MovieDetailsPresenter(MovieDetailsInterface.View view) {
        this.view = view;
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moviesApiService = retrofit.create(MovieApiService.class);
    }

    @Override
    public void getMovie(String imdbID) {
        initParams(imdbID);
        Call<MovieDetails> call = moviesApiService.getMovie(params);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                final MovieDetails movie = response.body();
                if (response.isSuccessful() && movie != null) {
                    view.showMovie(movie);
                } else {
                    view.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {
                view.onFailed(t.getMessage());
            }
        });
    }

    private void initParams(String imdbID) {
        params = new HashMap<>();
        params.put(Constants.getParamId(), imdbID);
        params.put(Constants.getParamPlot(), "full");
        params.put(Constants.getParamApiKey(), Constants.getApiKey());
    }
}
