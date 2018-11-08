package com.mobile.fauzanibnusarky.zyanmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobile.fauzanibnusarky.zyanmovie.adapter.AdapterMovie;
import com.mobile.fauzanibnusarky.zyanmovie.network.ApiClient;
import com.mobile.fauzanibnusarky.zyanmovie.network.ApiService;
import com.mobile.fauzanibnusarky.zyanmovie.pojo.ResponseMovie;
import com.mobile.fauzanibnusarky.zyanmovie.pojo.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContohMovieActivity extends AppCompatActivity {

    @BindView(R.id.rv_recyclerview)
    RecyclerView rvRcyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contoh_movie);
        ButterKnife.bind(this);
        rvRcyclerview.setLayoutManager(new GridLayoutManager(ContohMovieActivity.this,2));
        getMovie();
    }

    private void getMovie() {
        ApiService service = ApiClient.getInstance();
        Call<ResponseMovie> call = service.getUpComingMoviee(BuildConfig.APIKEY, BuildConfig.LANGUAGE);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                List<ResultsItem> dataMovie = response.body().getResults();
                String data1 = response.body().getResults().toString();
                ResponseMovie responseMovie = response.body();
                Log.d(" ", "onResponse data: "+dataMovie);


                AdapterMovie adapterMovie = new AdapterMovie(ContohMovieActivity.this,dataMovie);
                rvRcyclerview.setAdapter(adapterMovie);

            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Log.d("", "onFailure: "+t.toString());


            }
        });

    }}
