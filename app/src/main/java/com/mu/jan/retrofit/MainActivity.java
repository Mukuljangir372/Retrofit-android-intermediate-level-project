package com.mu.jan.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;

import com.mu.jan.retrofit.Model.Movie;
import com.mu.jan.retrofit.Model.MovieResponse;
import com.mu.jan.retrofit.adapter.MyRecyclerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private Retrofit retrofit;

    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    private MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.myRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        connectAndgetApiData();

    }

    private void connectAndgetApiData(){

        //retrofit config
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //implementation of interface MovieApiService using retrofit
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        //calling api
        //using interface method
        Call<MovieResponse> movieList = movieApiService.getTopRatedMovies(getString(R.string.api_key));

        movieList.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                List<Movie> movies = response.body().getResults();

                adapter = new MyRecyclerAdapter(MainActivity.this,movies);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });



    }
}
