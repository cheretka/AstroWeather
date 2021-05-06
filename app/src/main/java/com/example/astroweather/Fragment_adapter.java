package com.example.astroweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Fragment_adapter  extends FragmentPagerAdapter {

    public Fragment_adapter (FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return Fragment_sun.newInstance();
        else
            return Fragment_moon.newInstance();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
