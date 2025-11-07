package com.example.apphotel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;


public class SplashScreen extends AppCompatActivity {
    ImageView fondo;
    ImageView splashLogo;
    TextView nombreApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        @SuppressLint("ResourceType") Animation aparicion = AnimationUtils.loadAnimation(this, R.anim.aparecer);

        nombreApp = findViewById(R.id.splashAppName);
        nombreApp.startAnimation(aparicion);
        splashLogo = findViewById(R.id.splashLogo);
        splashLogo.startAnimation(aparicion);

        fondo = findViewById(R.id.splashImagenFondo);
        Glide.with(this)
                .load(R.drawable.fondo)
                .centerCrop()
                .into(fondo);

        launchNextActivity();
    }
    public void launchNextActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Register.class);
                startActivity(intent);
            }
        }, 4000);

    }
}
