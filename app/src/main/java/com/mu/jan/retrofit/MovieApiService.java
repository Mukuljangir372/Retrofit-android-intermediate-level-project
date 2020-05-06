package com.mu.jan.retrofit;

import com.mu.jan.retrofit.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("discover/movie")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_key);

}
