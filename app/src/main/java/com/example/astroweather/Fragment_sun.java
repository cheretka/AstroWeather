package com.example.astroweather;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import java.util.Calendar;
import java.util.Objects;
import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;

public class Fragment_sun extends Fragment {


    private DataViewModel data;

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

        TextView sunriseTimeText = view.findViewById(R.id.sunriseTimeText);
        TextView sunriseAzimuthText = view.findViewById(R.id.sunriseAzimuthText);

        TextView sunsetTimeText = view.findViewById(R.id.sunsetTimeText);
        TextView sunsetAzimuthText = view.findViewById(R.id.sunsetAzimuthText);

        TextView sunMorTwilightText = view.findViewById(R.id.morningTwilightText);
        TextView sunEvenTwilightText = view.findViewById(R.id.eveningTwilightText);



        if(data.getSunInfo()!=null){
            sunriseTimeText.setText( String.format("%d:%02d:%02d", data.getSunInfo().getSunrise().getHour(), data.getSunInfo().getSunrise().getMinute(), data.getSunInfo().getSunrise().getSecond()) );
            sunriseAzimuthText.setText(  String.format("%.09f", data.getSunInfo().getAzimuthRise() ));

            sunsetTimeText.setText(  String.format("%d:%02d:%02d", data.getSunInfo().getSunset().getHour(), data.getSunInfo().getSunset().getMinute(), data.getSunInfo().getSunset().getSecond()));
            sunsetAzimuthText.setText(String.format("%.09f", data.getSunInfo().getAzimuthSet() ));

            sunMorTwilightText.setText( String.format("%d:%02d:%02d", data.getSunInfo().getTwilightMorning().getHour(), data.getSunInfo().getTwilightMorning().getMinute(), data.getSunInfo().getTwilightMorning().getSecond()));
            sunEvenTwilightText.setText( String.format("%d:%02d:%02d", data.getSunInfo().getTwilightEvening().getHour(), data.getSunInfo().getTwilightEvening().getMinute(), data.getSunInfo().getTwilightEvening().getSecond()));
        }



        return view;
    }
}