package com.example.findlocalholidays;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class HolidaysAdapter extends RecyclerView.Adapter<HolidaysAdapter.ViewHolder>{
    List<Holidays> holidaysList;
    Context context;

    public HolidaysAdapter(List<Holidays> holidaysList, Context context) {
        this.holidaysList = holidaysList;
        this.context = context;
    }

    public void setHolidaysList(List<Holidays> holidaysList) {
        this.holidaysList = holidaysList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.holiday_card, parent, false);
        return new HolidaysAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //change text to explain result
        String fixed= holidaysList.get(position).getFixed();
        if(fixed.equals("true"))
            fixed="Fixed Holiday";
        else fixed="Moveable Holiday";

        //format the date
        String[] tmp =holidaysList.get(position).getDate().split("-");
        String date= tmp[2]+"-"+tmp[1]+"-"+tmp[0];

        //set texts with holiday data
        holder.textViewName.setText(holidaysList.get(position).getName());
        holder.textViewLocalName.setText(holidaysList.get(position).getLocalName());
        holder.textViewFixed.setText(fixed);
        holder.textViewDate.setText(date);

        //change list to string
        StringBuilder stringBuilder=new StringBuilder();
        for(String s: holidaysList.get(position).getTypes())
            stringBuilder.append(s+" ");
        holder.textViewType.setText(stringBuilder.toString());

    }

    @Override
    public int getItemCount() {
        return holidaysList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName,textViewLocalName,textViewDate,textViewFixed,textViewType;

        public ViewHolder(View itemView){
            super(itemView);
            textViewName= (TextView) itemView.findViewById(R.id.nameTextView);
            textViewLocalName= (TextView) itemView.findViewById(R.id.localNameTextView);
            textViewDate= (TextView) itemView.findViewById(R.id.dateTextView);
            textViewFixed= (TextView) itemView.findViewById(R.id.fixedTextView);
            textViewType= (TextView) itemView.findViewById(R.id.typeTextView);
        }

    }

}

