package com.example.android.soccerapp.data;

/**
 * Created by michiel.goyvaerts on 11/02/2018.
 */

public class CountryContract {
    private String title;
    private int imageUrl;

    public CountryContract(String title,int imageUrl){

        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }
}
