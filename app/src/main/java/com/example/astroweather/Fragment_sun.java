package com.example.astroweather;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;

public class Fragment_sun extends Fragment {

    private MyViewModel data;

    private TextView sunriseTextView;
    private TextView sunsetTextView;
    private TextView sunriseAzimuthTextView;
    private TextView sunsetAzimuthTextView;
    private TextView morningTwilightTextView;
    private TextView eveningTwilightTextView;


    public static Fragment_sun newInstance() {
        Fragment_sun fragment = new Fragment_sun();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        setRetainInstance(true);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sun_info_fragment, container, false);

        sunriseTextView = view.findViewById(R.id.sunriseTimeText);
        sunsetTextView = view.findViewById(R.id.sunsetTimeText);
        sunriseAzimuthTextView = view.findViewById(R.id.sunriseAzimuthText);
        sunsetAzimuthTextView = view.findViewById(R.id.sunsetAzimuthText);
        morningTwilightTextView = view.findViewById(R.id.morningTwilightText);
        eveningTwilightTextView = view.findViewById(R.id.eveningTwilightText);


        if(data.getSunInfo()!=null){
            sunriseTextView.setText( String.format("%d:%02d:%02d", data.getSunInfo().getSunrise().getHour(), data.getSunInfo().getSunrise().getMinute(), data.getSunInfo().getSunrise().getSecond()) );
            sunriseAzimuthTextView.setText(  String.format("%.04f", data.getSunInfo().getAzimuthRise() ));

            sunsetTextView.setText(  String.format("%d:%02d:%02d", data.getSunInfo().getSunset().getHour(), data.getSunInfo().getSunset().getMinute(), data.getSunInfo().getSunset().getSecond()));
            sunsetAzimuthTextView.setText(String.format("%.04f", data.getSunInfo().getAzimuthSet() ));

            morningTwilightTextView.setText( String.format("%d:%02d:%02d", data.getSunInfo().getTwilightMorning().getHour(), data.getSunInfo().getTwilightMorning().getMinute(), data.getSunInfo().getTwilightMorning().getSecond()));
            eveningTwilightTextView.setText( String.format("%d:%02d:%02d", data.getSunInfo().getTwilightEvening().getHour(), data.getSunInfo().getTwilightEvening().getMinute(), data.getSunInfo().getTwilightEvening().getSecond()));
        }

        return view;
    }
}