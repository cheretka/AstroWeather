package com.example.astroweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class InfoViewPager extends FragmentPagerAdapter {

    public InfoViewPager (FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return SunInfoFragment.newInstance();
        } else {
            return MoonInfoFragment.newInstance();
        }
    }
}
