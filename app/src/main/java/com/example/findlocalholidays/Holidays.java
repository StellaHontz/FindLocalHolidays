package com.example.findlocalholidays;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Holidays {
    //get data from API and save it as Holiday Obj
    @SerializedName("name")
    String name;
    @SerializedName("localName")
    String localName;
    @SerializedName("date")
    String date;
    @SerializedName("fixed")
    String fixed;
    @SerializedName("types")
    List<String> types;

    public Holidays(String name, String localName, String date, String fixed, List<String> types) {
        this.name = name;
        this.localName = localName;
        this.date = date;
        this.fixed = fixed;
        this.types = types;
    }

    public String getName() {
        return name;
    }


    public String getLocalName() {
        return localName;
    }

    public String getDate() {
        return date;
    }

    public String getFixed() {
        return fixed;
    }

    public List<String> getTypes() {
        return types;
    }
}
