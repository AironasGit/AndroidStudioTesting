package com.example.androidstudiojavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    public interface WeatherCallback{
        void onSuccess(String response);
        void onError(String error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.getWeather);
        TextView label = findViewById(R.id.displayTemp);

        double lonKaunas = 54.898521;
        double latKaunas = 23.903597;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherByCoords(lonKaunas, latKaunas, new WeatherCallback() {
                    @Override
                    public void onSuccess(String response) {
                        label.setText(response);
                    }

                    @Override
                    public void onError(String error) {
                        label.setText("Failed to get data");
                    }
                });
            }
        });
    }
    public void getWeatherByCoords(double lon, double lat, WeatherCallback callback){
        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%,.6f&longitude=%,.6f&current_weather=true",lon ,lat);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            String weather = response.getString("current_weather");
                            callback.onSuccess(weather);
                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                callback.onError(error.toString());
            }
        });
        Volley.newRequestQueue(this).add(request);
    }

}
//https://api.open-meteo.com/v1/forecast?latitude=54.898521&longitude=23.903597&current_weather=true