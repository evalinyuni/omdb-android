package id.test.omdbcicil.api;

import java.util.HashMap;

import id.test.omdbcicil.ext.Constants;
import id.test.omdbcicil.models.MovieDetails;
import id.test.omdbcicil.models.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface MovieApiService {
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Accept: application/json"
    })
    @GET(Constants.BASE_URL)
    Call<MovieList> getMovieList(@QueryMap HashMap<String, String> params);

    @GET(Constants.BASE_URL)
    Call<MovieDetails> getMovie(@QueryMap HashMap<String, String> params);
}
