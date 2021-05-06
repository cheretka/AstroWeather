package com.example.astroweather;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences preferences;
    private FragmentPagerAdapter fragmentPagerAdapter;


    private ViewPager viewPager;
//    private boolean isTablet;

    private Handler handler;
    private Runnable sunAndMoonRunnable;

    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private TextView frequencyTextView;


    private DataViewModel moonViewModel;
    private DataViewModel sunViewModel;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moonViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        sunViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

//        isTablet = getResources().getBoolean(R.bool.isTablet);
//        if(isTablet){
//            FragmentTransaction ft = this.fm.beginTransaction();
//
//            moonInfoFragment = MoonInfoFragment.newInstance();
//            ft.replace(R.id.fragment_container, moonInfoFragment);
//
//            sunInfoFragment = SunInfoFragment.newInstance();
//            ft.replace(R.id.fragment_container2, sunInfoFragment);
//            ft.commit();
//        } else {
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new Fragment_adapter(getSupportFragmentManager()));
//        }


        latitudeTextView = findViewById(R.id.latitudeText);
        longitudeTextView = findViewById(R.id.longitudeText);
        frequencyTextView = findViewById(R.id.frequencyText);

        preferences = getSharedPreferences("saved data", Activity.MODE_PRIVATE);
        latitudeTextView.setText(preferences.getString("szerokosc geograficzna", "51"));
        longitudeTextView.setText(preferences.getString("dlugosc geograficzna", "19"));
        frequencyTextView.setText(preferences.getString("czas odswierzania", "15"));


        handler = new Handler();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            latitudeTextView.setText(preferences.getString("szerokosc geograficzna", "51"));
            longitudeTextView.setText(preferences.getString("dlugosc geograficzna", "19"));
            frequencyTextView.setText(preferences.getString("czas odswierzania", "15"));

            sunAndMoonRunnable = new Runnable() {
                @Override
                public void run() {
                    String latitude = latitudeTextView.getText().toString();
                    String longitude = longitudeTextView.getText().toString();
                    String freq = frequencyTextView.getText().toString();

                    if(!(TextUtils.isEmpty(latitude) || TextUtils.isEmpty(longitude) || TextUtils.isEmpty(freq))){
                        Toast.makeText(getApplicationContext(), "sunAndMoonRunnable", Toast.LENGTH_SHORT).show();

                        double lati = Double.valueOf(latitude);
                        double longi = Double.valueOf(longitude);
                        Calendar c = Calendar.getInstance();

                        AstroDateTime astroDateTime = new AstroDateTime(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,
                                c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                                c.get(Calendar.SECOND),c.get(Calendar.ZONE_OFFSET)/3600_000,true);
                        AstroCalculator astroCalculator = new AstroCalculator(astroDateTime, new AstroCalculator.Location(lati, longi));

                        AstroCalculator.MoonInfo moonInfo = astroCalculator.getMoonInfo();
                        AstroCalculator.SunInfo sunInfo = astroCalculator.getSunInfo();
                        moonViewModel.setMoonInfo(moonInfo);
                        sunViewModel.setSunInfo(sunInfo);


//                        if(isTablet){
//                            FragmentTransaction ft = fm.beginTransaction();
//
//                            moonInfoFragment = MoonInfoFragment.newInstance();
//                            ft.replace(R.id.fragment_container, moonInfoFragment);
//
//                            sunInfoFragment = SunInfoFragment.newInstance();
//                            ft.replace(R.id.fragment_container2, sunInfoFragment);
//                            ft.commit();
//                        } else {
                        viewPager.getAdapter().notifyDataSetChanged();
//                        }
                        handler.postDelayed(this,Integer.valueOf(freq)*1000);
                    }
                }
            };
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(sunAndMoonRunnable);
        Toast.makeText(getApplicationContext(), "resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(sunAndMoonRunnable);
        Toast.makeText(getApplicationContext(), "paused", Toast.LENGTH_SHORT).show();
    }

    public void funUstawienia(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(intent,1);
    }
}

