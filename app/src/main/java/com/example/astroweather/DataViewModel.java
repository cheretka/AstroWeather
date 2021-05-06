package com.example.astroweather;


import android.arch.lifecycle.ViewModel;

import com.astrocalculator.AstroCalculator;



public class DataViewModel extends ViewModel {

    private AstroCalculator.SunInfo sunInfo;
    private AstroCalculator.MoonInfo moonInfo;

    public AstroCalculator.SunInfo getSunInfo() {
        return sunInfo;
    }
    public void setSunInfo(AstroCalculator.SunInfo sunInfo) {
        this.sunInfo = sunInfo;
    }

    public AstroCalculator.MoonInfo getMoonInfo() {
        return moonInfo;
    }
    public void setMoonInfo(AstroCalculator.MoonInfo moonInfo) {
        this.moonInfo = moonInfo;
    }

}
