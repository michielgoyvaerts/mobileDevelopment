package com.example.android.soccerapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.soccerapp.data.SoccerDbHelper;
import com.example.android.soccerapp.data.UserContract;

/**
 * Created by AllPhi on 16/03/2018.
 */

public class SettingsActivity extends BaseActivity {
    private EditText mFirstname, mLastname, mEmail, mPhone, mStreet, mPostalcode, mCity, mCountry, mOldPassword, mNewPassword;
    private TextView mErrorPassword;

    private String username;
    private String password;

    private SharedPreferences shared;

    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mFirstname = (EditText) findViewById(R.id.etFirstname);
        mLastname = (EditText) findViewById(R.id.etLastname);
        mEmail = (EditText) findViewById(R.id.etEmail);
        mPhone = (EditText) findViewById(R.id.etPhone);
        mStreet = (EditText) findViewById(R.id.etStreet);
        mPostalcode = (EditText) findViewById(R.id.etPostalCode);
        mCity = (EditText) findViewById(R.id.etCity);
        mCountry = (EditText) findViewById(R.id.etCountry);
        mOldPassword = (EditText) findViewById(R.id.etCurrentPassword);
        mErrorPassword = (TextView) findViewById(R.id.tvErrorPassword);
        mNewPassword = (EditText) findViewById(R.id.etNewPassword);

        shared = getSharedPreferences("user", MODE_PRIVATE);

        String firstname = shared.getString("firstname","");
        String lastname = shared.getString("lastname","");
        String email = shared.getString("email","");
        username = shared.getString("username","");
        password = shared.getString("password","");
        String phone = shared.getString("phone","");
        String street = shared.getString("street","");
        String postalcode = shared.getString("postalcode","");
        String city = shared.getString("city","");
        String country = shared.getString("country","");


        mFirstname.setText(firstname);
        mLastname.setText(lastname);
        mEmail.setText(email);
        mPhone.setText(phone);
        mStreet.setText(street);
        mPostalcode.setText(postalcode);
        mCity.setText(city);
        mCountry.setText(country);

        SoccerDbHelper dbHelper = new SoccerDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
    }

    public void saveUser(View v){
        updateUser(
                mFirstname.getText().toString(),
                mLastname.getText().toString(),
                mEmail.getText().toString(),
                mPhone.getText().toString(),
                mStreet.getText().toString(),
                mPostalcode.getText().toString(),
                mCity.getText().toString(),
                mCountry.getText().toString());

        SharedPreferences.Editor editor = shared.edit();
        editor.putString("firstname", mFirstname.getText().toString());
        editor.putString("lastname", mLastname.getText().toString());
        editor.putString("name", mFirstname.getText().toString() + " " + mLastname.getText().toString());
        editor.putString("email", mEmail.getText().toString());
        editor.putString("phone", mPhone.getText().toString());
        editor.putString("street", mStreet.getText().toString());
        editor.putString("postalcode", mPostalcode.getText().toString());
        editor.putString("city", mCity.getText().toString());
        editor.putString("country", mCountry.getText().toString());
        editor.commit();

        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
    }

    public long updateUser(String firstname, String lastname, String email, String phone, String street, String postalcode, String city, String country){
        ContentValues cv = new ContentValues();
        cv.put(UserContract.UserEntry.COLUMN_FIRSTNAME,firstname);
        cv.put(UserContract.UserEntry.COLUMN_LASTNAME,lastname);
        cv.put(UserContract.UserEntry.COLUMN_EMAIL,email);
        cv.put(UserContract.UserEntry.COLUMN_PHONE,phone);
        cv.put(UserContract.UserEntry.COLUMN_STREET,street);
        cv.put(UserContract.UserEntry.COLUMN_POSTALCODE,postalcode);
        cv.put(UserContract.UserEntry.COLUMN_CITY,city);
        cv.put(UserContract.UserEntry.COLUMN_COUNTRY,country);

        return mDb.update(UserContract.UserEntry.TABLE_NAME,cv,UserContract.UserEntry.COLUMN_USERNAME + "=?",new String[]{username});
    }

    public void editPassword(View v){
        updatePassword(
                mOldPassword.getText().toString(),
                mNewPassword.getText().toString()
        );
    }

    public long updatePassword(String oldPassword, String newPassword){
        ContentValues cv = new ContentValues();
        if(password.equals(oldPassword)){
            cv.put(UserContract.UserEntry.COLUMN_PASSWORD,newPassword);

            SharedPreferences.Editor editor = shared.edit();
            editor.putString("password", mNewPassword.getText().toString());
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivity(intent);

            return mDb.update(UserContract.UserEntry.TABLE_NAME,cv,UserContract.UserEntry.COLUMN_USERNAME + "=?",new String[]{username});
        }
        else{
            mOldPassword.setText("");
            mNewPassword.setText("");
            mErrorPassword.setVisibility(View.VISIBLE);
            return 0;
        }
    }
}
