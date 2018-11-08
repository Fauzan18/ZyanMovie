package com.mobile.fauzanibnusarky.zyanmovie.ui_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobile.fauzanibnusarky.zyanmovie.BuildConfig;
import com.mobile.fauzanibnusarky.zyanmovie.R;
import com.mobile.fauzanibnusarky.zyanmovie.adapter.AdapterMovie;
import com.mobile.fauzanibnusarky.zyanmovie.network.ApiClient;
import com.mobile.fauzanibnusarky.zyanmovie.network.ApiService;
import com.mobile.fauzanibnusarky.zyanmovie.pojo.ResponseMovie;
import com.mobile.fauzanibnusarky.zyanmovie.pojo.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class UpComingFragment extends Fragment {
    RecyclerView recyclerViewMovie;
    @BindView(R.id.pdBar)
    ProgressBar pdBar;
    @BindView(R.id.id)
    TextView id;
    Unbinder unbinder;



    public UpComingFragment(){
        //required empty costructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_now_playing,container,false);
        unbinder = ButterKnife.bind(this,view);

        recyclerViewMovie= (RecyclerView) view.findViewById(R.id.rv_recyclerview);
        recyclerViewMovie.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        pdBar.setVisibility(View.VISIBLE);
        id.setVisibility(View.VISIBLE);
        getMovie();

        return view;
    }

    private void getMovie() {
        ApiService service = ApiClient.getInstance();
        retrofit2.Call<ResponseMovie> call = service.getUpComingMoviee(BuildConfig.APIKEY, BuildConfig.LANGUAGE);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseMovie> call, Response<ResponseMovie> response) {

                List<ResultsItem> dataMovie = response.body().getResults();
                String data1 = response.body().getResults().toString();
                ResponseMovie responseMovie = response.body();
                Log.d("","onResponse data: "+dataMovie);
                AdapterMovie adapterMovie = new AdapterMovie(getActivity(), dataMovie);
                pdBar.setVisibility(View.GONE);
                id.setVisibility(View.GONE);
                recyclerViewMovie.setAdapter(adapterMovie);
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseMovie> call, Throwable t) {
                Log.d("","onFailure: "+t.toString());

            }
        });

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }
}

