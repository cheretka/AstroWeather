package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

public class SunInfoFragment extends Fragment {

    public static SunInfoFragment newInstance() {
        SunInfoFragment fragment = new SunInfoFragment();
//        Bundle args = new Bundle();
//        args.putSerializable("SomeTask", task);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sun_info_fragment, container,
                false);

        return view;

    }
}
