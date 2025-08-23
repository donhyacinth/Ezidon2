package com.example.ezidon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvSignUpPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // If already logged in, skip to Home
        if (prefs.getBoolean("loggedIn", false)) {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUpPrompt = findViewById(R.id.tvSignUpPrompt);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            String savedEmail = prefs.getString("email", "");
            String savedPassword = prefs.getString("password", "");

            if (email.equals(savedEmail) && password.equals(savedPassword)) {
                prefs.edit().putBoolean("loggedIn", true).apply();
                startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials or account doesn't exist.", Toast.LENGTH_SHORT).show();
            }
        });

        // Make only "Sign Up" clickable dynamically in red
        String text = "Don't have an account? Sign Up";
        String clickableText = "Sign Up";

        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull android.view.View widget) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);       // clickable text color is now red
                ds.setUnderlineText(false);   // remove underline
                ds.setFakeBoldText(true);     // optional: make it bold
            }
        };

        int startIndex = text.indexOf(clickableText);
        if (startIndex != -1) {
            int endIndex = startIndex + clickableText.length();
            spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tvSignUpPrompt.setText(spannableString);
        tvSignUpPrompt.setMovementMethod(LinkMovementMethod.getInstance());
        tvSignUpPrompt.setHighlightColor(Color.TRANSPARENT); // optional
    }
}
