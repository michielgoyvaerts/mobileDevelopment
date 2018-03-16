package com.example.android.soccerapp.data;

/**
 * Created by michiel.goyvaerts on 11/02/2018.
 */

public class CountryContract {
    private String title;
    private int imageUrl;

    public CountryContract(String title){

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }
}
