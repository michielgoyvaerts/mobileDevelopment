package com.example.android.soccerapp.data;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.soccerapp.CalendarActivity;
import com.example.android.soccerapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by michiel.goyvaerts on 15/02/2018.
 */

public class WeatherFetchTask extends AsyncTask<Void,Void, JSONObject> {
    JSONObject mData;

    @Override
    protected JSONObject doInBackground(Void... params){
        try{
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Genk&APPID=2f737d7e258c19a0058afef0b9572d6e");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";

            while((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            mData = new JSONObject(json.toString());

        }catch (Exception e) {

            System.out.println("Exception "+ e.getMessage());
            Log.d("Exception", e.getMessage());
            return null;
        }
        return mData;
    }

    @Override
    protected void onPostExecute(JSONObject object) {
        super.onPostExecute(object);
        if(object!=null)
            Log.d("Fetch data", "Weather data is succesfully retrieved");
    }
}
