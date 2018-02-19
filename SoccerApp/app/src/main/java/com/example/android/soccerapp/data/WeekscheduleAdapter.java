package com.example.android.soccerapp.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.soccerapp.R;

import java.util.List;

/**
 * Created by michiel.goyvaerts on 7/02/2018.
 */

public class WeekscheduleAdapter extends RecyclerView.Adapter<WeekscheduleAdapter.WeekscheduleViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public WeekscheduleAdapter(Context context, Cursor cursor){
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public WeekscheduleViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.weekschedule_list_item,parent,false);
        return new WeekscheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeekscheduleViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;
        String date = mCursor.getString(mCursor.getColumnIndex(WeekScheduleContract.WeekScheduleEntry.COLUMN_DATE));
        String clubHome = mCursor.getString(mCursor.getColumnIndex(WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBHOME));
        String scoreHome = mCursor.getString(mCursor.getColumnIndex(WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREHOME));
        String clubAway = mCursor.getString(mCursor.getColumnIndex(WeekScheduleContract.WeekScheduleEntry.COLUMN_CLUBAWAY));
        String scoreAway = mCursor.getString(mCursor.getColumnIndex(WeekScheduleContract.WeekScheduleEntry.COLUMN_SCOREAWAY));

        holder.dateView.setText(date);
        holder.clubHomeView.setText(clubHome);
        holder.scoreHomeView.setText(scoreHome);
        holder.scoreAwayView.setText(scoreAway);
        holder.clubAwayView.setText(clubAway);
    }


    @Override
    public int getItemCount(){
        if(mCursor==null)
            return 0;

        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor!=null)
            mCursor.close();

        mCursor = newCursor;
        if(mCursor!=null){
            this.notifyDataSetChanged();
        }
    }


    class WeekscheduleViewHolder extends RecyclerView.ViewHolder{
        TextView gameweekView;
        TextView dateView;
        TextView clubHomeView;
        TextView scoreHomeView;
        TextView scoreAwayView;
        TextView clubAwayView;


        public WeekscheduleViewHolder(View itemView) {
            super(itemView);
            gameweekView = (TextView) itemView.findViewById(R.id.tvGameweek);
            dateView = (TextView) itemView.findViewById(R.id.tvDate);
            clubHomeView = (TextView) itemView.findViewById(R.id.tvClubHome);
            scoreHomeView = (TextView) itemView.findViewById(R.id.tvScoreHome);
            scoreAwayView = (TextView) itemView.findViewById(R.id.tvScoreAway);
            clubAwayView = (TextView) itemView.findViewById(R.id.tvClubAway);
        }
    }
}
