package com.example.android.soccerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.soccerapp.data.SoccerDbHelper;
import com.example.android.soccerapp.data.UserContract;
import com.example.android.soccerapp.data.WeekScheduleContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;
    private EditText mUsername;
    private EditText mPassword;
    private TextView mErrorLogin;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoccerDbHelper dbHelper = new SoccerDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        mUsername = (EditText) findViewById(R.id.etUsername);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mErrorLogin = (TextView) findViewById(R.id.tvErrorLogin);
    }

    public void clickRegister(View v){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View v){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        mCursor = mDb.query(UserContract.UserEntry.TABLE_NAME,
                null,
                UserContract.UserEntry.COLUMN_USERNAME + "=?" + " and " + UserContract.UserEntry.COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null,
                null,
                null,
                null);
        if(mCursor.getCount()>0){
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            while (mCursor.moveToNext()){
                String name = mCursor.getString(1) + " " + mCursor.getString(2);
                intent.putExtra(Intent.EXTRA_TEXT, name);
                SharedPreferences shared = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("username", name);
                editor.commit();
                startActivity(intent);
                finish();
            }
        }
        else{
            mUsername.setText("");
            mPassword.setText("");
            mErrorLogin.setVisibility(View.VISIBLE);
        }
    }
}