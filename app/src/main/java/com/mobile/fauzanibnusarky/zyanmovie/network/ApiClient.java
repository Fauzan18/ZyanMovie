package com.mobile.fauzanibnusarky.zyanmovie.network;

import com.mobile.fauzanibnusarky.zyanmovie.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fauzanibnusarky on 11/7/18.
 */

public class ApiClient {

    public static Retrofit setInit(){
            return new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        public static ApiService getInstance(){
        return setInit().create(ApiService.class);

    }
}
