package com.example.astroweather;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_sun extends Fragment {

    private DataViewModel data;

    private TextView sunriseTimeText, sunriseAzimuthText;
    private TextView sunsetTimeText, sunsetAzimuthText;
    private TextView sunMorTwilightText, sunEvenTwilightText;

    public Fragment_sun() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = ViewModelProviders.of(getActivity()).get(DataViewModel.class);
        setRetainInstance(true);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sun_info_fragment, container, false);
        sunriseTimeText = view.findViewById(R.id.sunriseTimeText);
        sunriseAzimuthText = view.findViewById(R.id.sunriseAzimuthText);
        sunsetTimeText = view.findViewById(R.id.sunsetTimeText);
        sunsetAzimuthText = view.findViewById(R.id.sunsetAzimuthText);
        sunMorTwilightText = view.findViewById(R.id.morningTwilightText);
        sunEvenTwilightText = view.findViewById(R.id.eveningTwilightText);


        if(data.getSunInfo()!=null){
            sunriseTimeText.setText( String.format("%d:%02d:%02d", data.getSunInfo().getSunrise().getHour(), data.getSunInfo().getSunrise().getMinute(), data.getSunInfo().getSunrise().getSecond()) );
            sunriseAzimuthText.setText(  String.format("%.06f", data.getSunInfo().getAzimuthRise() ));

            sunsetTimeText.setText(  String.format("%d:%02d:%02d", data.getSunInfo().getSunset().getHour(), data.getSunInfo().getSunset().getMinute(), data.getSunInfo().getSunset().getSecond()));
            sunsetAzimuthText.setText(String.format("%.06f", data.getSunInfo().getAzimuthSet() ));

            sunMorTwilightText.setText( String.format("%d:%02d:%02d", data.getSunInfo().getTwilightMorning().getHour(), data.getSunInfo().getTwilightMorning().getMinute(), data.getSunInfo().getTwilightMorning().getSecond()));
            sunEvenTwilightText.setText( String.format("%d:%02d:%02d", data.getSunInfo().getTwilightEvening().getHour(), data.getSunInfo().getTwilightEvening().getMinute(), data.getSunInfo().getTwilightEvening().getSecond()));
        }

        return view;
    }
}