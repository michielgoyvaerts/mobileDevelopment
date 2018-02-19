package com.example.android.soccerapp.data;

import android.provider.BaseColumns;

/**
 * Created by michiel.goyvaerts on 5/02/2018.
 */

public class WeekScheduleContract {

    public static final class WeekScheduleEntry implements BaseColumns {
        public static final String TABLE_NAME = "weekschedule";
        public static final String COLUMN_CLUBHOME = "clubHome";
        public static final String COLUMN_SCOREHOME = "scoreHome";
        public static final String COLUMN_CLUBAWAY = "awayClub";
        public static final String COLUMN_SCOREAWAY = "scoreAway";
        public static final String COLUMN_DATE = "date";
    }
}
