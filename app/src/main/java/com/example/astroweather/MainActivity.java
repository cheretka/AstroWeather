package com.example.astroweather;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import java.util.Calendar;
import java.util.Objects;



public class MainActivity extends AppCompatActivity {


    private SharedPreferences fileWithDataInformation;
    private ViewPager viewPager;
    private boolean isTablet;
    private Handler handler;
    private Runnable refreshDateThread;
    private TextView szerokosc_text;
    private TextView dlugosc_text;
    private TextView czestotliwosc_text;
    private DataViewModel dataViewModel;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        szerokosc_text = findViewById(R.id.szerokosc_main);
        dlugosc_text = findViewById(R.id.dlugosc_main);
        czestotliwosc_text = findViewById(R.id.czestotliwosc_main);


        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);


        fileWithDataInformation = getSharedPreferences("saveddata6", Activity.MODE_PRIVATE);
        setDataOnScreen();




        isTablet = getResources().getBoolean(R.bool.isTablet);
        if(isTablet){
            FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_1, new Fragment_moon());
            transaction.replace(R.id.fragment_2, new Fragment_sun());
            transaction.commit();
        } else {
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        }




        handler = new Handler();



//        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
//        startActivityForResult(intent,1);

    }


    void setDataOnScreen(){


        szerokosc_text.setText(fileWithDataInformation.getString("szerokosc geograficzna", "---"));

        dlugosc_text.setText(fileWithDataInformation.getString("dlugosc geograficzna", "---"));

        czestotliwosc_text.setText(fileWithDataInformation.getString("czas odswierzania", "---"));


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            setDataOnScreen();

            refreshDateThread = new Runnable() {

                @Override
                public void run() {


                    Calendar cal = Calendar.getInstance();

                    AstroCalculator astroCalculator = new AstroCalculator(
                            new AstroDateTime(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND),cal.get(Calendar.ZONE_OFFSET)/3600_000,true),
                            new AstroCalculator.Location( Double.parseDouble(szerokosc_text.getText().toString()), Double.parseDouble(dlugosc_text.getText().toString())));

                    dataViewModel.setAstroCal(astroCalculator);




                    if(isTablet){
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_1, new Fragment_moon());
                        transaction.replace(R.id.fragment_2, new Fragment_sun());
                        transaction.commit();
                    } else {
                        Objects.requireNonNull(viewPager.getAdapter()).notifyDataSetChanged();
                    }


                    handler.postDelayed(this,Integer.parseInt(czestotliwosc_text.getText().toString())*1000 );

                }
            };


        }
    }


    @Override
    protected void onResume() {

        super.onResume();
        handler.post(refreshDateThread);

    }

    @Override
    protected void onPause() {

        super.onPause();
        handler.removeCallbacks(refreshDateThread);

    }


    public void funUstawienia(View view) {

        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(intent,1);

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        SharedPreferences.Editor preferencesEditor = fileWithDataInformation.edit();
//        preferencesEditor.clear();
//        preferencesEditor.commit();
//    }

}

