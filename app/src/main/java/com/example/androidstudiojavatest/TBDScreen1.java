package com.example.androidstudiojavatest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TBDScreen1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tbd_screen_layout1);

        Button btnBackToMain = findViewById(R.id.btnBackToMain);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TBDScreen1.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
