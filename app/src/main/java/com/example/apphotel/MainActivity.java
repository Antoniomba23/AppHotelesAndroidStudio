package com.example.apphotel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvGreeting = findViewById(R.id.tvGreeting);

        String userName = getIntent().getStringExtra("userName");
        if (userName == null) {
            SharedPreferences prefs = getSharedPreferences("AppHotelPrefs", MODE_PRIVATE);
            userName = prefs.getString("userName", "Invitado");
        }

        tvGreeting.setText("Hola, " + userName + " 👋 Bienvenido a AppHotel");
    }
}
