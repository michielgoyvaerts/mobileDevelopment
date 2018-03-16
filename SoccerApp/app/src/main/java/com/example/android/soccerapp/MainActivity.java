package com.example.android.soccerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.soccerapp.data.SoccerDbHelper;
import com.example.android.soccerapp.data.UserContract;
import com.example.android.soccerapp.data.WeekScheduleContract;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;
    private EditText mUsername, mPassword;
    private TextView mErrorLogin, mFriends;
    private Cursor mCursor;

    private CallbackManager callbackManager;
    private ProgressDialog mDialog;
    private ImageView mAvatar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoccerDbHelper dbHelper = new SoccerDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        mUsername = (EditText) findViewById(R.id.etUsername);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mErrorLogin = (TextView) findViewById(R.id.tvErrorLogin);

        callbackManager = CallbackManager.Factory.create();

        mFriends = (TextView) findViewById(R.id.tvFacebookFriends);
        mAvatar = (ImageView) findViewById(R.id.ivFootball);

        LoginButton loginButton = (LoginButton) findViewById(R.id.loginFacebook);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog=new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Retrieving data ...");
                mDialog.show();

                String accessToken = loginResult.getAccessToken().getToken();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("Response,",response.toString());
                        getData(object);
                        mFriends.setVisibility(View.VISIBLE);
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

//        printKeyHash();
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
                String firstname = mCursor.getString(1);
                String lastname = mCursor.getString(2);
                String name = firstname + " " + lastname;
                String email = mCursor.getString(3);
                username = mCursor.getString(4);
                password = mCursor.getString(5);
                String phone = mCursor.getString(6);
                String street = mCursor.getString(7);
                String postalcode = mCursor.getString(8);
                String city = mCursor.getString(9);
                String country = mCursor.getString(10);

                //String name = mCursor.getString(1) + " " + mCursor.getString(2);
                intent.putExtra(Intent.EXTRA_TEXT, name);
                SharedPreferences shared = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("firstname", firstname);
                editor.putString("lastname", lastname);
                editor.putString("name", name);
                editor.putString("email", email);
                editor.putString("username", username);
                editor.putString("password", password);
                editor.putString("phone", phone);
                editor.putString("street", street);
                editor.putString("postalcode", postalcode);
                editor.putString("city", city);
                editor.putString("country", country);


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

//    private void printKeyHash(){
//        try{
//            PackageInfo info = getPackageManager().getPackageInfo("com.example.android.soccerapp", getPackageManager().GET_SIGNATURES);
//
//            for(Signature signature:info.signatures){
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    private void getData(JSONObject object){
        try{
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");

            Picasso.with(this).load(profile_picture.toString()).into(mAvatar);

            mFriends.setText("Friends: " + object.getJSONObject("friends").getJSONObject("summary").getString("total_count"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}