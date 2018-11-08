package com.mobile.fauzanibnusarky.zyanmovie.network;

import com.mobile.fauzanibnusarky.zyanmovie.pojo.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fauzanibnusarky on 11/7/18.
 */

//https://api.themoviedb.org/3/movie/upcoming?api_key=2bea38317c7da072ccff5b9ad2bcc5a2&language=en-US

public interface ApiService {
    @GET("movie/upcoming")
    Call<ResponseMovie> getUpComingMoviee(@Query("api_key") String apikey,
                                          @Query("language") String language);
    @GET("movie/now_playing")
    Call<ResponseMovie> getNowPlaying(@Query("api_key") String apikey,
                                      @Query("language") String language);
}