package com.example.astroweather;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




public class Fragment_moon extends Fragment {


    private DataViewModel data;


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


        TextView moonrise_text = view.findViewById(R.id.moon_rise);
        TextView moonset_text = view.findViewById(R.id.moon_set);

        TextView newMoon_text = view.findViewById(R.id.moon_new);
        TextView fullMoon_text = view.findViewById(R.id.moon_full);

        TextView moonPhase_text = view.findViewById(R.id.moon_phase);


        if(data.getMoonInfo()!=null) {

            moonrise_text.setText(String.format("%d:%02d:%02d", data.getMoonInfo().getMoonrise().getHour(), data.getMoonInfo().getMoonrise().getMinute(), data.getMoonInfo().getMoonrise().getSecond()) );
            moonset_text.setText(String.format("%d:%02d:%02d", data.getMoonInfo().getMoonset().getHour(), data.getMoonInfo().getMoonset().getMinute(), data.getMoonInfo().getMoonset().getSecond()) );

            newMoon_text.setText(String.format("%02d/%02d/%04d", data.getMoonInfo().getNextNewMoon().getDay(),  data.getMoonInfo().getNextNewMoon().getMonth(), data.getMoonInfo().getNextNewMoon().getYear()));
            fullMoon_text.setText(String.format("%02d/%02d/%04d", data.getMoonInfo().getNextFullMoon().getDay(),  data.getMoonInfo().getNextFullMoon().getMonth(), data.getMoonInfo().getNextFullMoon().getYear()));

            moonPhase_text.setText(String.format("%f%%", data.getMoonInfo().getIllumination()));
        }


        return view;
    }
}