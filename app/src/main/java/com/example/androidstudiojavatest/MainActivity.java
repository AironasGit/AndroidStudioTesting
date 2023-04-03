package com.example.androidstudiojavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnWeather = findViewById(R.id.btnWeather);
        Button btnTBD1 = findViewById(R.id.btnTBD1);
        Button btnTBD2 = findViewById(R.id.btnTBD2);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherScreen.class);
                startActivity(intent);
            }
        });
        btnTBD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TBDScreen1.class);
                startActivity(intent);
            }
        });
        btnTBD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TBDScreen2.class);
                startActivity(intent);
            }
        });
    }
}