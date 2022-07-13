package com.example.findlocalholidays;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("/api/v3/PublicHolidays/{Year}/{CountryCode}")
    Call<List<Holidays>> getHolidays(@Path("Year") String year, @Path("CountryCode") String countryCode);
}
