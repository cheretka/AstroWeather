package com.example.astroweather;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FragmentPagerAdapter fragmentPagerAdapter;
    private final FragmentManager fm = getSupportFragmentManager();
    private Fragment moonInfoFragment;
    private Fragment sunInfoFragment;
    private boolean isTablet;
    Button optionsBtn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isTablet = getResources().getBoolean(R.bool.isTablet);

        if(isTablet){
            FragmentTransaction ft = this.fm.beginTransaction();

            moonInfoFragment = MoonInfoFragment.newInstance();
            ft.replace(R.id.fragment_container, moonInfoFragment);

            sunInfoFragment = SunInfoFragment.newInstance();
            ft.replace(R.id.fragment_container2, sunInfoFragment);
            ft.commit();
        } else {
            ViewPager vPager = findViewById(R.id.vp);
            fragmentPagerAdapter = new InfoViewPager(getSupportFragmentManager());
            vPager.setAdapter(fragmentPagerAdapter);
        }

        optionsBtn = findViewById(R.id.optionsButton);
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(getApplicationContext(), "resumed", Toast.LENGTH_SHORT).show();
    }
}
