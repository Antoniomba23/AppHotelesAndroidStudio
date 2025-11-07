package com.example.apphotel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private TextInputLayout loginTILUser, loginTILPassword;
    private Button loginButton;
    private TextView loginTVRegister;
    private FormUtils formUtils = new FormUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTILUser = findViewById(R.id.loginTILuserName);
        loginTILPassword = findViewById(R.id.loginTILpassword);
        loginButton = findViewById(R.id.loginButton);
        loginTVRegister = findViewById(R.id.loginTVRegister);

        loginButton.setOnClickListener(v -> attemptLogin());

        loginTVRegister.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
    }

    private void attemptLogin() {
        String usernameInput = formUtils.getTILText(loginTILUser);
        String passwordInput = formUtils.getTILText(loginTILPassword);

        SharedPreferences prefs = getSharedPreferences("AppHotelPrefs", MODE_PRIVATE);
        String savedUser = prefs.getString("userName", null);
        String savedHash = prefs.getString("password_hash", null);

        if (savedUser == null || savedHash == null) {
            Toast.makeText(this, "No hay usuario registrado. Regístrate primero.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!usernameInput.equals(savedUser)) {
            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
            return;
        }

        if (formUtils.checkPassword(passwordInput, savedHash)) {
            Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userName", savedUser);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }
}
