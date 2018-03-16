package com.example.android.soccerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by michiel.goyvaerts on 14/02/2018.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onNavigationItemSelected(item);

        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id==R.id.action_calendar){
            Intent calendarIntent = new Intent(getApplicationContext(), CalendarActivity.class);
            startActivity(calendarIntent);
        }
//        if(id==R.id.action_standings){
//            Intent standingsIntent = new Intent(getApplicationContext(), StandingsActivity.class);
//            startActivity(standingsIntent);
//        }
        if(id==R.id.action_logout){
            SharedPreferences shared = getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.remove("username");
            Intent standingsIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(standingsIntent);
        }
        return true;
    }
}
