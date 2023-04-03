package com.example.androidstudiojavatest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherScreen extends AppCompatActivity {
    public interface WeatherCallback{
        void onSuccess(JSONObject response);
        void onError(String error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_screen_layout);

        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        Button btnGetWeather = findViewById(R.id.getWeather);
        TextView labelTemp = findViewById(R.id.displayTemp);
        TextView labelWindSpeed = findViewById(R.id.displayWindSpeed);
        TextView labelWindDirection = findViewById(R.id.displayWindDirection);
        TextView labelTime = findViewById(R.id.displayTime);

        double lonKaunas = 54.898521;
        double latKaunas = 23.903597;

        btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherByCoords(lonKaunas, latKaunas, new WeatherScreen.WeatherCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {

                        try {
                            labelTemp.setText("Temperature: " + response.getString("temperature"));
                            labelWindSpeed.setText("Wind Speed: " + response.getString("windspeed"));
                            labelWindDirection.setText("Wind Direction: " + response.getString("winddirection"));
                            labelTime.setText("Time: " + response.getString("time"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onError(String error) {
                        labelTemp.setText("Failed to get data");
                    }
                });
            }
        });

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeatherScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void getWeatherByCoords(double lon, double lat, WeatherScreen.WeatherCallback callback){
        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%,.6f&longitude=%,.6f&current_weather=true",lon ,lat);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject weather = response.getJSONObject("current_weather");
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
