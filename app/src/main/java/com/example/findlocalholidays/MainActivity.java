package com.example.findlocalholidays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<String> years;
    static List<Holidays> holidaysList;
    RecyclerView recyclerViewYears;
    RecyclerView recyclerViewHolidays;
    HolidaysAdapter holidaysAdapter;
    YearsAdapter yearsAdapter;
    LinearLayoutManager linearLayoutManager1,linearLayoutManager2;
    Spinner countriesSpinner;
    static String selectedCountry="GR";  //default country
    static CardView cardView; //progressBarCardView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewHolidays=findViewById(R.id.recyclerViewHolidays);
        recyclerViewYears=findViewById(R.id.recyclerViewYears);
        countriesSpinner=findViewById(R.id.coutrySpinner);
        cardView=findViewById(R.id.progressBarCardView);

        years= new ArrayList<>();
        holidaysList= new ArrayList<>();

        //populate list with years
        for(int i=1970;i<=2050;i++){
            years.add(String.valueOf(i));
        }

        //holidays recycler view
        linearLayoutManager2 =new LinearLayoutManager(this);
        recyclerViewHolidays.setLayoutManager(linearLayoutManager2);
        holidaysAdapter = new HolidaysAdapter(holidaysList,this);
        recyclerViewHolidays.setAdapter(holidaysAdapter);

        //populate years recycler view
        linearLayoutManager1 =new LinearLayoutManager(this);
        recyclerViewYears.setLayoutManager(linearLayoutManager1);
        yearsAdapter = new YearsAdapter(years,this,holidaysAdapter); //pass as param to access it later
        recyclerViewYears.setAdapter(yearsAdapter);

        //to focus recycler view on current year
        int year= Calendar.getInstance().get(Calendar.YEAR);
        int yearPosition=0;
        for(int i=0;i<=years.size()-1;i++){
            if(years.get(i).equals(String.valueOf(year))){
                yearPosition=i;
                break;
            }
        }
        recyclerViewYears.smoothScrollToPosition(yearPosition+5);


        //get Selected country
        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //clear recycler view when country is changed
                int size= holidaysList.size();
                if (size > 0) {
                    holidaysList.clear();
                    holidaysAdapter.notifyItemRangeRemoved(0,size);//update ui
                }

                TextView textView= (TextView) view;
                String tmp=textView.getText().toString();
                selectedCountry=tmp.substring(tmp.length()-3,tmp.length()-1); //get country code

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}