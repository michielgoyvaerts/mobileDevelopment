package com.example.android.soccerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michiel.goyvaerts on 3/02/2018.
 */

public class SoccerDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "soccer.db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
            UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UserContract.UserEntry.COLUMN_FIRSTNAME + " TEXT NOT NULL, " +
            UserContract.UserEntry.COLUMN_LASTNAME + " TEXT NOT NULL, " +
            UserContract.UserEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
            UserContract.UserEntry.COLUMN_USERNAME + " TEXT NOT NULL, " +
            UserContract.UserEntry.COLUMN_PASSWORD + " TEXT NOT NULL," +
            UserContract.UserEntry.COLUMN_PHONE + " TEXT NULL," +
            UserContract.UserEntry.COLUMN_STREET + " TEXT NULL," +
            UserContract.UserEntry.COLUMN_POSTALCODE + " TEXT NULL," +
            UserContract.UserEntry.COLUMN_CITY + " TEXT NULL," +
            UserContract.UserEntry.COLUMN_COUNTRY + " TEXT NULL" +
            ");";

    private static final String SQL_CREATE_WEEKSCHEDULE_TABLE = "CREATE TABLE " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME + " (" +
            WeekScheduleContract.WeekScheduleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME + " TEXT NOT NULL, " +
            WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + " TEXT NOT NULL, " +
            WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY + " TEXT NOT NULL, " +
            WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + " TEXT NOT NULL, " +
            WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE + " TEXT NOT NULL" +
            ");";

    public SoccerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_INSERT_WEEKSCHEDULE_DATA1 = "insert into " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME +
                " (" + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE +
                ") values ('Royal Antwerp FC','0','RSC Anderlecht','0','28/07/2017');";
        final String SQL_INSERT_WEEKSCHEDULE_DATA2 = "insert into " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME +
                " (" + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE +
                ") values ('Lokeren','0','Club Brugge','4','29/07/2017');";
        final String SQL_INSERT_WEEKSCHEDULE_DATA3 = "insert into " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME +
                " (" + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE +
                ") values ('KAS Eupen','0','Zulte Waregem','5','29/07/2017');";
        final String SQL_INSERT_WEEKSCHEDULE_DATA4 = "insert into " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME +
                " (" + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE +
                ") values ('KRC Genk','3','Waasland-Beveren','3','29/07/2017');";
        final String SQL_INSERT_WEEKSCHEDULE_DATA5 = "insert into " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME +
                " (" + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE +
                ") values ('Sporting Charmeroi','1','Kortrijk','0','29/07/2017');";
        final String SQL_INSERT_WEEKSCHEDULE_DATA6 = "insert into " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME +
                " (" + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE +
                ") values ('KV Mechelen','1','Standard','1','30/07/2017');";
        final String SQL_INSERT_WEEKSCHEDULE_DATA7 = "insert into " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME +
                " (" + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE +
                ") values ('STVV','3','Gent','2','30/07/2017');";
        final String SQL_INSERT_WEEKSCHEDULE_DATA8 = "insert into " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME +
                " (" + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY +
                "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY + "," + WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE +
                ") values ('Oostende','0','Moeskroen','1','30/07/2017');";


        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_WEEKSCHEDULE_TABLE);
        sqLiteDatabase.execSQL(SQL_INSERT_WEEKSCHEDULE_DATA1);
        sqLiteDatabase.execSQL(SQL_INSERT_WEEKSCHEDULE_DATA2);
        sqLiteDatabase.execSQL(SQL_INSERT_WEEKSCHEDULE_DATA3);
        sqLiteDatabase.execSQL(SQL_INSERT_WEEKSCHEDULE_DATA4);
        sqLiteDatabase.execSQL(SQL_INSERT_WEEKSCHEDULE_DATA5);
        sqLiteDatabase.execSQL(SQL_INSERT_WEEKSCHEDULE_DATA6);
        sqLiteDatabase.execSQL(SQL_INSERT_WEEKSCHEDULE_DATA7);
        sqLiteDatabase.execSQL(SQL_INSERT_WEEKSCHEDULE_DATA8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeekScheduleContract.WeekScheduleEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
