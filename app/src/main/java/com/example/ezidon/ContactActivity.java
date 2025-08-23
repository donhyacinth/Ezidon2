package com.example.ezidon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactActivity extends BaseActivity {

    private EditText etName, etEmail, etMessage;
    private Button btnSubmit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        sharedPreferences = getSharedPreferences("ContactPrefs", MODE_PRIVATE);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMessage = findViewById(R.id.etMessage);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Setup spinner/navigation using BaseActivity
        setupSpinner(R.id.spinnerNav);

        // Submit button logic
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String message = etMessage.getText().toString().trim();

            if(name.isEmpty() || email.isEmpty() || message.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("message", message);
            editor.apply();

            Toast.makeText(this, "Message submitted!", Toast.LENGTH_SHORT).show();

            etName.setText("");
            etEmail.setText("");
            etMessage.setText("");
        });
    }
}
