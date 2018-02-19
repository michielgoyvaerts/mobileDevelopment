package com.example.android.soccerapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.android.soccerapp.data.SoccerDbHelper;
import com.example.android.soccerapp.data.WeatherFetchTask;
import com.example.android.soccerapp.data.WeekScheduleContract;
import com.example.android.soccerapp.data.WeekscheduleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by michiel.goyvaerts on 14/01/2018.
 */

public class CalendarActivity extends BaseActivity {
    private SQLiteDatabase mDb;
    private WeekscheduleAdapter mAdapter;
    private RecyclerView mWeekscheduleList;
    private Cursor mCursor;
    private JSONObject mData;
    private JSONArray weather;
    private TextView mDescription;
    private TextView mTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mDescription = (TextView) findViewById(R.id.tvDescription);
        mTemp = (TextView) findViewById(R.id.tvTemp);

        mWeekscheduleList = (RecyclerView) this.findViewById(R.id.rvWeekschedules);

        mCursor = getAllWeekschedules();
        mAdapter = new WeekscheduleAdapter(this,mCursor);

        mWeekscheduleList.setLayoutManager(new LinearLayoutManager(this));

        mWeekscheduleList.setAdapter(mAdapter);

        try {
            mData = new WeatherFetchTask().execute().get();
            setWeatherData(mData);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("Exception", e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.d("Exception", e.getMessage());
        }
    }

    private Cursor getAllWeekschedules(){
        SoccerDbHelper dbHelper = new SoccerDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Cursor cursor = mDb.query(
                WeekScheduleContract.WeekScheduleEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public void setWeatherData(JSONObject result) {
        try {
            weather = result.getJSONArray("weather");
            JSONObject jsonWeather = weather.getJSONObject(0);
            String description = jsonWeather.getString("description");
            mDescription.setText(description);
            JSONObject main = result.getJSONObject("main");
            String temp = main.getString("temp");
            temp = String.format("%.1f", Double.parseDouble(temp) - 272.15);
            mTemp.setText(temp + " °C");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Exception", e.getMessage());
        }
    }
}
