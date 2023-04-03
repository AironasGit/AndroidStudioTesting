package com.example.androidstudiojavatest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TBDScreen2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tbd_screen_layout2);

        Button btnBackToMain = findViewById(R.id.btnBackToMain);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TBDScreen2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
