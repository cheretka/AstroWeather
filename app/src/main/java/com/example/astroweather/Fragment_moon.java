package com.example.astroweather;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroDateTime;

import java.util.Calendar;

public class Fragment_moon extends Fragment {

    private DataViewModel data;

    private TextView moonrise_text, moonset_text;
    private TextView newMoon_text, fullMoon_text;
    private TextView moonPhase_text, synodicDay_text;

    public Fragment_moon() {
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

        View view = inflater.inflate(R.layout.moon_info_fragment, container, false);


        moonrise_text = view.findViewById(R.id.moon_rise);
        moonset_text = view.findViewById(R.id.moon_set);

        newMoon_text = view.findViewById(R.id.moon_new);
        fullMoon_text = view.findViewById(R.id.moon_full);

        moonPhase_text = view.findViewById(R.id.moon_phase);
        synodicDay_text = view.findViewById(R.id.moon_synodic);


        if(data.getMoonInfo()!=null) {
            moonrise_text.setText(String.format("%d:%02d:%02d", data.getMoonInfo().getMoonrise().getHour(), data.getMoonInfo().getMoonrise().getMinute(), data.getMoonInfo().getMoonrise().getSecond()) );
            moonset_text.setText(String.format("%d:%02d:%02d", data.getMoonInfo().getMoonset().getHour(), data.getMoonInfo().getMoonset().getMinute(), data.getMoonInfo().getMoonset().getSecond()) );

            newMoon_text.setText(String.format("%02d/%02d/%04d", data.getMoonInfo().getNextNewMoon().getDay(),  data.getMoonInfo().getNextNewMoon().getMonth(), data.getMoonInfo().getNextNewMoon().getYear()));
            fullMoon_text.setText(String.format("%02d/%02d/%04d", data.getMoonInfo().getNextFullMoon().getDay(),  data.getMoonInfo().getNextFullMoon().getMonth(), data.getMoonInfo().getNextFullMoon().getYear()));

            moonPhase_text.setText(String.format("%.02f%%", data.getMoonInfo().getIllumination()));

            Calendar today = Calendar.getInstance();
            AstroDateTime nextNewMoon = data.getMoonInfo().getNextNewMoon();
            Calendar newMoonCalendar = Calendar.getInstance();
            newMoonCalendar.set(nextNewMoon.getYear(), nextNewMoon.getMonth() - 1, nextNewMoon.getDay(), today.get(Calendar.HOUR), today.get(Calendar.MINUTE), today.get(Calendar.SECOND));
            int synodicDay = (int) ((newMoonCalendar.getTimeInMillis() - today.getTimeInMillis()) / (24 * 60 * 60 * 1000));
            if (synodicDay > 0) synodicDay = 29 - synodicDay;
            synodicDay_text.setText(String.valueOf(synodicDay));
        }

        return view;
    }
}