package com.example.astroweather;


import android.arch.lifecycle.ViewModel;

import com.astrocalculator.AstroCalculator;



public class DataViewModel extends ViewModel {

    private AstroCalculator data;
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

    public AstroCalculator getData() {
        return data;
    }

    public void setData(AstroCalculator data) {
        this.data = data;
    }
}
