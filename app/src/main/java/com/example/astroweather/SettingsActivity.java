package com.example.astroweather;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;



public class SettingsActivity extends AppCompatActivity {


    private SharedPreferences fileWithDataInformation;
    private EditText latitude_text, longitude_text, frequency_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        latitude_text = findViewById(R.id.szerokosc);
        longitude_text = findViewById(R.id.dlugosc);
        frequency_text = findViewById(R.id.czestotliwosc);

        fileWithDataInformation = getSharedPreferences("saveddata6", Activity.MODE_PRIVATE);
        latitude_text.setText(fileWithDataInformation.getString("szerokosc geograficzna", "0"));
        longitude_text.setText(fileWithDataInformation.getString("dlugosc geograficzna", "0"));
        frequency_text.setText(fileWithDataInformation.getString("czas odswierzania", "60"));
    }




    public void funSaveNewData(View view) {


        if(ifAllInfoGood()){

            SharedPreferences.Editor preferencesEditor = fileWithDataInformation.edit();
            preferencesEditor.putString("szerokosc geograficzna", latitude_text.getText().toString());
            preferencesEditor.putString("dlugosc geograficzna", longitude_text.getText().toString());
            preferencesEditor.putString("czas odswierzania", frequency_text.getText().toString());
            preferencesEditor.apply();

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }

    }



    boolean ifAllInfoGood(){


        String text_szerokosc = latitude_text.getText().toString();
        String text_dlugosc = longitude_text.getText().toString();
        String text_czestotliwosc = frequency_text.getText().toString();



        if (TextUtils.isEmpty(text_szerokosc) || TextUtils.isEmpty(text_dlugosc) || TextUtils.isEmpty(text_czestotliwosc) || Integer.parseInt(text_czestotliwosc)<1 || Integer.parseInt(text_czestotliwosc)>900){
            Toast.makeText(this, "prosze poprawić dane wejściowe", Toast.LENGTH_SHORT).show();
            return false;
        }


        BigDecimal szerokosc = new BigDecimal(text_szerokosc);
        BigDecimal dlugosc = new BigDecimal(text_dlugosc);
        if(szerokosc.compareTo(BigDecimal.valueOf(90)) > 0 || szerokosc.compareTo(BigDecimal.valueOf(-90)) < 0 || dlugosc.compareTo(BigDecimal.valueOf(180)) > 0 || dlugosc.compareTo(BigDecimal.valueOf(-180)) < 0){
            Toast.makeText(this, "prosze poprawić dane wejściowe", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
}