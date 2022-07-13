package com.example.findlocalholidays;

import android.content.Context;
import android.view.View;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Holidays>> {
    static final String BASE_URL="https://date.nager.at/";
    Context context;
    HolidaysAdapter holidaysAdapter;

    //constructor to access holidaysAdapter
    public Controller(Context context, HolidaysAdapter holidaysAdapter) {
        this.context = context;
        this.holidaysAdapter = holidaysAdapter;
    }

    //make the call
    public void start(String year, String selectedCountry){
        MainActivity.cardView.setVisibility(View.VISIBLE);
        //Retrofit for JSON,GSON handling
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi= retrofit.create(JsonPlaceHolderApi.class);
        //get list of objects (as JSON format is [{},{},...])
        Call<List<Holidays>> call = jsonPlaceHolderApi.getHolidays(year,selectedCountry);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Holidays>> call, Response<List<Holidays>> response) {
        if(response.isSuccessful()){
            //change adapter list
            MainActivity.holidaysList=response.body();
            holidaysAdapter.setHolidaysList(MainActivity.holidaysList);
            holidaysAdapter.notifyItemRangeChanged(0,MainActivity.holidaysList.size());
            MainActivity.cardView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(Call<List<Holidays>> call, Throwable t) {
        t.printStackTrace();
    }
}
