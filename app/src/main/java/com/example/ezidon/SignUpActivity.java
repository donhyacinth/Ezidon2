package com.example.ezidon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private EditText etEmail, etPassword;
    private Button btnSignUp, btnBackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        etEmail = findViewById(R.id.etEmailSignUp);
        etPassword = findViewById(R.id.etPasswordSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnBackLogin = findViewById(R.id.btnBackLogin);

        btnSignUp.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            prefs.edit()
                    .putString("email", email)
                    .putString("password", password)
                    .putBoolean("loggedIn", true)
                    .apply();

            startActivity(new Intent(SignUpActivity.this, MenuActivity.class));
            finish();
        });

        btnBackLogin.setOnClickListener(v -> finish()); // go back to LoginActivity
    }
}
