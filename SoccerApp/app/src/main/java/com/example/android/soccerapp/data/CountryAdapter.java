package com.example.android.soccerapp.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.soccerapp.R;

/**
 * Created by michiel.goyvaerts on 11/02/2018.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private CountryContract[] mCountries;
    private Context mContext;

    public CountryAdapter(Context context, CountryContract[] countries){
        this.mContext = context;
        this.mCountries = countries;
    }

    // Define listener member variable
    private static OnCountryItemClickListener mListener;

    // Define the listener interface
    public interface OnCountryItemClickListener {
        void onItemClicked(String text);
    }

    // Define the method that allows the parent activity or fragment to define the listener.
    public void setOnRecyclerViewItemClickListener(OnCountryItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.country_list_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder viewHolder, int position) {

        viewHolder.countryName.setText(mCountries[position].getTitle());
        //viewHolder.flag.setImageResource(mCountries[position].getImageUrl());
        switch(mCountries[position].getTitle()){
            case "Belgium":
                viewHolder.flag.setImageResource(R.drawable.belgium);
                break;
            case "England":
                viewHolder.flag.setImageResource(R.drawable.england);
                break;
            case "France":
                viewHolder.flag.setImageResource(R.drawable.france);
                break;
            case "Germany":
                viewHolder.flag.setImageResource(R.drawable.germany);
                break;
            case "Italy":
                viewHolder.flag.setImageResource(R.drawable.italy);
                break;
            case "Netherlands":
                viewHolder.flag.setImageResource(R.drawable.netherlands);
                break;
            case "Portugal":
                viewHolder.flag.setImageResource(R.drawable.portugal);
                break;
            case "Spain":
                viewHolder.flag.setImageResource(R.drawable.spain);
                break;
        }
    }

    @Override
    public int getItemCount(){
        if(mCountries==null)
            return 0;

        return mCountries.length;
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        public TextView countryName;
        public ImageView flag;

        public CountryViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            countryName = (TextView) itemLayoutView.findViewById(R.id.tvCountryName);
            flag = (ImageView) itemLayoutView.findViewById(R.id.ivFlag);

            countryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // send the text to the listener, i.e Activity.
                    mListener.onItemClicked((String) ((TextView) v).getText());
                }
            });
        }
    }
}
