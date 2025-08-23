package com.example.ezidon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected Spinner spinnerNav;
    protected SharedPreferences sharedPreferences;
    private boolean skipNextSelection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
    }

    // Existing single-argument method
    protected void setupSpinner(int spinnerId) {
        // Call the new two-argument version with default 'false'
        setupSpinner(spinnerId, false);
    }

    // New overloaded method with boolean
    protected void setupSpinner(int spinnerId, boolean someFlag) {
        spinnerNav = findViewById(spinnerId);

        if (spinnerNav == null) {
            Log.e("BaseActivity", "Spinner not found with ID: " + spinnerId);
            return;
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.navigation_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNav.setAdapter(adapter);

        // Set initial selection based on current activity
        setInitialSpinnerSelection();

        spinnerNav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Navigation", "Item selected: " + position + " in " + BaseActivity.this.getClass().getSimpleName());

                // Skip the initial automatic selection
                if (skipNextSelection) {
                    skipNextSelection = false;
                    Log.d("Navigation", "Skipping initial selection");
                    return;
                }

                // Example of using the boolean flag (optional behavior)
                if (someFlag) {
                    // You can customize behavior here if the flag is true
                }

                Log.d("Navigation", "Processing navigation to position: " + position);

                switch (position) {
                    case 0: // Menu
                        Log.d("Navigation", "Navigating to Menu");
                        if (!(BaseActivity.this instanceof MenuActivity)) {
                            Intent menuIntent = new Intent(BaseActivity.this, MenuActivity.class);
                            menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(menuIntent);
                        } else {
                            Log.d("Navigation", "Already in MenuActivity");
                        }
                        break;
                    case 1: // Contact
                        Log.d("Navigation", "Navigating to Contact");
                        if (!(BaseActivity.this instanceof ContactActivity)) {
                            Intent contactIntent = new Intent(BaseActivity.this, ContactActivity.class);
                            startActivity(contactIntent);
                        }
                        break;
                    case 2: // About Us
                        Log.d("Navigation", "Navigating to About Us");
                        if (!(BaseActivity.this instanceof AboutUsActivity)) {
                            Intent aboutIntent = new Intent(BaseActivity.this, AboutUsActivity.class);
                            startActivity(aboutIntent);
                        }
                        break;
                    case 3: // Logout
                        Log.d("Navigation", "Logging out");
                        sharedPreferences.edit().putBoolean("loggedIn", false).apply();
                        Intent loginIntent = new Intent(BaseActivity.this, LoginActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(loginIntent);
                        finish();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    // Set the spinner selection to match the current activity
    private void setInitialSpinnerSelection() {
        skipNextSelection = true; // Skip the automatic selection that happens when we set selection

        if (this instanceof MenuActivity) {
            Log.d("Navigation", "Setting spinner to Menu (0) for MenuActivity");
            spinnerNav.setSelection(0);
        } else if (this instanceof ContactActivity) {
            Log.d("Navigation", "Setting spinner to Contact (1) for ContactActivity");
            spinnerNav.setSelection(1);
        } else if (this instanceof AboutUsActivity) {
            Log.d("Navigation", "Setting spinner to About Us (2) for AboutUsActivity");
            spinnerNav.setSelection(2);
        } else {
            Log.d("Navigation", "Setting spinner to Menu (0) for Site activity: " + this.getClass().getSimpleName());
            spinnerNav.setSelection(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update spinner selection when returning to activity
        if (spinnerNav != null) {
            setInitialSpinnerSelection();
        }
    }
}