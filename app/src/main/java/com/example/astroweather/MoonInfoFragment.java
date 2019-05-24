package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MoonInfoFragment extends Fragment {

    public static MoonInfoFragment newInstance() {
        MoonInfoFragment fragment = new MoonInfoFragment();
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

        View view = inflater.inflate(R.layout.moon_info_fragment, container,
                false);

        return view;

    }
}
