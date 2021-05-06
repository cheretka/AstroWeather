package com.example.astroweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Adapter extends FragmentPagerAdapter {

    public Adapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new Fragment_sun();
        else
            return new Fragment_moon();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
