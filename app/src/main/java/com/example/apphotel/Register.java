package com.example.apphotel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    private TextInputLayout tilUser, tilEmail, tilPass, tilPassConfirm;
    private Button btnRegister;
    private FormUtils formUtils = new FormUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tilUser = findViewById(R.id.registerTILuserName);
        tilEmail = findViewById(R.id.registerTILemail);
        tilPass = findViewById(R.id.registerTILpassword);
        tilPassConfirm = findViewById(R.id.registerTILpasswordDoubleCheck);

        btnRegister = findViewById(R.id.registerButton);

        btnRegister.setOnClickListener(v -> validateAndRegister());
    }

    private void validateAndRegister() {
        String user = formUtils.getTILText(tilUser);
        String email = formUtils.getTILText(tilEmail);
        String pass = formUtils.getTILText(tilPass);
        String passConfirm = formUtils.getTILText(tilPassConfirm);

        boolean valid = true;

        if (user.isEmpty()) {
            tilUser.setError("Ingrese un nombre de usuario");
            valid = false;
        } else tilUser.setError(null);

        if (!email.contains("@") || !email.contains(".")) {
            tilEmail.setError("Email no válido");
            valid = false;
        } else tilEmail.setError(null);

        if (pass.length() < 4) {
            tilPass.setError("La contraseña es demasiado corta");
            valid = false;
        } else tilPass.setError(null);

        if (!pass.equals(passConfirm)) {
            tilPassConfirm.setError("Las contraseñas no coinciden");
            valid = false;
        } else tilPassConfirm.setError(null);

        if (!valid) return;

        // Hash de la contraseña antes de guardar
        String hashed = formUtils.generateHashedPassword(pass);

        SharedPreferences prefs = getSharedPreferences("AppHotelPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userName", user);
        editor.putString("email", email);
        editor.putString("password_hash", hashed);
        editor.apply();

        Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
