package com.example.android.soccerapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.android.soccerapp.data.SoccerDbHelper;
import com.example.android.soccerapp.data.UserContract;

/**
 * Created by michiel.goyvaerts on 3/02/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText firstname, lastname, email, username, password;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname = (EditText) findViewById(R.id.etFirstname);
        lastname = (EditText) findViewById(R.id.etLastname);
        email = (EditText) findViewById(R.id.etEmail);
        username = (EditText) findViewById(R.id.etRegisterUsername);
        password = (EditText) findViewById(R.id.etRegisterPassword);

        SoccerDbHelper dbHelper = new SoccerDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
    }

    public void register(View v){
        addUser(
                firstname.getText().toString(),
                lastname.getText().toString(),
                email.getText().toString(),
                username.getText().toString(),
                password.getText().toString()
        );

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public long addUser(String firstname, String lastname, String email, String username, String password){
        ContentValues cv = new ContentValues();
        cv.put(UserContract.UserEntry.COLUMN_FIRSTNAME,firstname);
        cv.put(UserContract.UserEntry.COLUMN_LASTNAME,lastname);
        cv.put(UserContract.UserEntry.COLUMN_EMAIL,email);
        cv.put(UserContract.UserEntry.COLUMN_USERNAME,username);
        cv.put(UserContract.UserEntry.COLUMN_PASSWORD,password);

        return mDb.insert(UserContract.UserEntry.TABLE_NAME,null,cv);
    }
}
