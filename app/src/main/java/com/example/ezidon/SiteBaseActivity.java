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

public class SiteBaseActivity extends AppCompatActivity {

    protected Spinner spinnerNav;
    protected SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
    }

    protected void setupSiteSpinner(int spinnerId) {
        spinnerNav = findViewById(spinnerId);

        if (spinnerNav == null) {
            Log.e("SiteBaseActivity", "Spinner not found with ID: " + spinnerId);
            return;
        }

        // Create a simple array for site navigation
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.site_navigation_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNav.setAdapter(adapter);

        // Set "Navigate..." as default selection
        spinnerNav.setSelection(0);

        spinnerNav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SiteNavigation", "Item selected: " + position + " in " + SiteBaseActivity.this.getClass().getSimpleName());

                // Skip the default "Navigate..." option
                if (position == 0) {
                    return;
                }

                switch (position) {
                    case 1: // Back to Menu
                        Log.d("SiteNavigation", "Navigating back to Menu");
                        Intent menuIntent = new Intent(SiteBaseActivity.this, MenuActivity.class);
                        menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(menuIntent);
                        break;
                    case 2: // Contact
                        Log.d("SiteNavigation", "Navigating to Contact");
                        Intent contactIntent = new Intent(SiteBaseActivity.this, ContactActivity.class);
                        startActivity(contactIntent);
                        break;
                    case 3: // About Us
                        Log.d("SiteNavigation", "Navigating to About Us");
                        Intent aboutIntent = new Intent(SiteBaseActivity.this, AboutUsActivity.class);
                        startActivity(aboutIntent);
                        break;
                    case 4: // Logout
                        Log.d("SiteNavigation", "Logging out");
                        sharedPreferences.edit().putBoolean("loggedIn", false).apply();
                        Intent loginIntent = new Intent(SiteBaseActivity.this, LoginActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(loginIntent);
                        finish();
                        break;
                }

                // Reset spinner to default position after navigation
                spinnerNav.post(() -> spinnerNav.setSelection(0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}