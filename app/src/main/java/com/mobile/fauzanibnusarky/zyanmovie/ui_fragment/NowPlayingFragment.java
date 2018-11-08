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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class NowPlayingFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.pdBar)
    ProgressBar pdBar;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.rv_recyclerview)
    RecyclerView rvRecyclerviewMovie;
    Unbinder unbinder;

    public NowPlayingFragment() {
        // Require Empty public Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        unbinder = ButterKnife.bind(this, view);

        rvRecyclerviewMovie = (RecyclerView) view.findViewById(R.id.rv_recyclerview);
        rvRecyclerviewMovie.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        pdBar.setVisibility(View.GONE);
        id.setVisibility(View.GONE);
        getMovie();

        return view;
}

    private void getMovie() {
        ApiService service = ApiClient.getInstance();
        Call<ResponseMovie> call = service.getNowPlaying(BuildConfig.APIKEY, BuildConfig.LANGUAGE);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                List<ResultsItem> dataMovie = response.body().getResults();
                Log.d(" ", "onResponse data: " + dataMovie);
                AdapterMovie adapterMovie = new AdapterMovie(getActivity(), dataMovie);
                pdBar.setVisibility(View.GONE);
                id.setVisibility(View.GONE);
                rvRecyclerviewMovie.setAdapter(adapterMovie);

            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Log.d(" ","onFailure: " + t.toString());

            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
