package com.example.android.soccerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.soccerapp.data.CountryAdapter;
import com.example.android.soccerapp.data.CountryContract;

/**
 * Created by michiel.goyvaerts on 9/02/2018.
 */

public class WelcomeActivity extends AppCompatActivity {
    private TextView mName;
    private RecyclerView mCountryList;
    private CountryAdapter mAdapter;
    private CountryContract[] countries = new CountryContract[]{
            new CountryContract("Belgium"),
            new CountryContract("England"),
            new CountryContract("France"),
            new CountryContract("Germany"),
            new CountryContract("Italy"),
            new CountryContract("Netherlands"),
            new CountryContract("Portugal"),
            new CountryContract("Spain")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intentStartedThisActivity = getIntent();

        mName = (TextView) findViewById(R.id.tvName);
        SharedPreferences shared = getSharedPreferences("user", MODE_PRIVATE);
        String name = (shared.getString("name", ""));
        mName.setText("Welcome " + name);
        //mName.setText("Welcome " + intentStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT));

        mCountryList = (RecyclerView) findViewById(R.id.rvCountries);

        mAdapter = new CountryAdapter(this, countries);

        mAdapter.setOnRecyclerViewItemClickListener(new CountryAdapter.OnCountryItemClickListener() {
            @Override
            public void onItemClicked(String text) {
                if(text.equals("Belgium")){
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    startActivity(intent);
                }
            }
        });

        mCountryList.setLayoutManager(new LinearLayoutManager(this));

        mCountryList.setAdapter(mAdapter);
    }

//    public void chooseCountry(View v){
//        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
//        startActivity(intent);
//    }
}
