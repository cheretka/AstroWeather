package com.example.astroweather;

import android.arch.lifecycle.ViewModel;
import com.astrocalculator.AstroCalculator;



public class DataViewModel extends ViewModel {

    private AstroCalculator astroCal;
    private AstroCalculator.SunInfo sunInfo;
    private AstroCalculator.MoonInfo moonInfo;


    public void setAstroCal(AstroCalculator astroCal) {
        this.astroCal = astroCal;
        sunInfo = this.astroCal.getSunInfo();
        moonInfo = this.astroCal.getMoonInfo();
    }


    public AstroCalculator.SunInfo getSunInfo() {
        return sunInfo;
    }

    public AstroCalculator.MoonInfo getMoonInfo() {
        return moonInfo;
    }
}
