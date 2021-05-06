package com.example.astroweather;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;

public class Fragment_moon extends Fragment {

    private MyViewModel moonViewModel;

    private TextView moonriseText;
    private TextView moonsetText;
    private TextView newMoonText;
    private TextView fullMoonText;
    private TextView moonPhaseText;
    private TextView synodicDayText;

    public static Fragment_moon newInstance() {
        Fragment_moon fragment = new Fragment_moon();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moonViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.moon_info_fragment, container, false);

        moonriseText = view.findViewById(R.id.moon_rise);
        moonsetText = view.findViewById(R.id.moon_set);
        newMoonText = view.findViewById(R.id.moon_new);
        fullMoonText = view.findViewById(R.id.moon_full);
        moonPhaseText = view.findViewById(R.id.moon_phase);
        synodicDayText = view.findViewById(R.id.moon_synodic);

        AstroCalculator.MoonInfo moonInfo = moonViewModel.getMoonInfo();
        if(moonInfo!=null){

            moonriseText.setText(moonInfo.getMoonrise().toString());
            moonsetText.setText(moonInfo.getMoonset().toString());

            newMoonText.setText(moonInfo.getNextNewMoon().toString());
            fullMoonText.setText(moonInfo.getNextFullMoon().toString());

            moonPhaseText.setText(String.valueOf(Math.round((moonInfo.getIllumination()*100))));

            Calendar today = Calendar.getInstance();
            AstroDateTime nextNewMoon = moonInfo.getNextNewMoon();
            Calendar newMoonCalendar = Calendar.getInstance();
            newMoonCalendar.set(nextNewMoon.getYear(), nextNewMoon.getMonth()-1, nextNewMoon.getDay(),today.get(Calendar.HOUR),today.get(Calendar.MINUTE), today.get(Calendar.SECOND));
            int synodicDay = (int)((newMoonCalendar.getTimeInMillis()-today.getTimeInMillis())/ (24 * 60 * 60 * 1000));
            if(synodicDay>0) synodicDay = 29-synodicDay;
            synodicDayText.setText(String.valueOf(synodicDay));
        }

        return view;

    }
}