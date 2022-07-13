package com.example.findlocalholidays;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class YearsAdapter extends RecyclerView.Adapter<YearsAdapter.ViewHolder>{

    List<String> years;
    Context context;
    HolidaysAdapter holidaysAdapter;

    //constructor to access the holidaysAdapter
    public YearsAdapter(List<String> years, Context context,HolidaysAdapter holidaysAdapter) {
        this.years = years;
        this.context = context;
        this.holidaysAdapter=holidaysAdapter;
    }

    @NonNull
    @Override
    public YearsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.yearcard, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YearsAdapter.ViewHolder holder, int position) {
        //fill recycler view with year cards and on click call the controller.start()
        holder.textView.setText(years.get(position));
        holder.cardView.setOnClickListener(v->{
            //make the Api call through the controller
            Controller controller=new Controller(context,holidaysAdapter);
            controller.start(years.get(position),MainActivity.selectedCountry);
        });

    }

    @Override
    public int getItemCount() {
        return years.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        RelativeLayout cardView;

        public ViewHolder(View itemView){
            super(itemView);
            textView= itemView.findViewById(R.id.yearTextView);
            cardView= itemView.findViewById(R.id.yearCardView);
        }

    }


}
