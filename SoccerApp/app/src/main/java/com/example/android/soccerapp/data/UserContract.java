package com.example.android.soccerapp.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by michiel.goyvaerts on 3/02/2018.
 */

public class UserContract {
    public static final class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_FIRSTNAME = "firstname";
        public static final String COLUMN_LASTNAME = "lastname";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_STREET = "street";
        public static final String COLUMN_POSTALCODE = "postalcode";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_COUNTRY = "country";
    }
}
