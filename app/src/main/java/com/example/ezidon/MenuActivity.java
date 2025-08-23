package com.example.ezidon;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuActivity extends BaseActivity {

    private GridLayout gridLayout;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MenuActivity", "MenuActivity onCreate started");
        setContentView(R.layout.activity_menu); // XML with spinner & search bar

        // Setup spinner/navigation using BaseActivity
        setupSpinner(R.id.spinnerNav);

        gridLayout = findViewById(R.id.gridLayout);
        searchBar = findViewById(R.id.searchBar);

        Log.d("MenuActivity", "Setting up site buttons");

        // Tourist site buttons
        findViewById(R.id.btnViewSite1).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site1 button clicked");
            startActivity(new Intent(this, Site1Activity.class));
        });
        findViewById(R.id.btnViewSite2).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site2 button clicked");
            startActivity(new Intent(this, Site2Activity.class));
        });
        findViewById(R.id.btnViewSite3).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site3 button clicked");
            startActivity(new Intent(this, Site3Activity.class));
        });
        findViewById(R.id.btnViewSite4).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site4 button clicked");
            startActivity(new Intent(this, Site4Activity.class));
        });
        findViewById(R.id.btnViewSite5).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site5 button clicked");
            startActivity(new Intent(this, Site5Activity.class));
        });
        findViewById(R.id.btnViewSite6).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site6 button clicked");
            startActivity(new Intent(this, Site6Activity.class));
        });
        findViewById(R.id.btnViewSite7).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site7 button clicked");
            startActivity(new Intent(this, Site7Activity.class));
        });
        findViewById(R.id.btnViewSite8).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site8 button clicked");
            startActivity(new Intent(this, Site8Activity.class));
        });
        findViewById(R.id.btnViewSite9).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site9 button clicked");
            startActivity(new Intent(this, Site9Activity.class));
        });
        findViewById(R.id.btnViewSite10).setOnClickListener(v -> {
            Log.d("MenuActivity", "Site10 button clicked");
            startActivity(new Intent(this, Site10Activity.class));
        });

        // Search bar functionality
        if (searchBar != null) {
            searchBar.addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterSites(s.toString());
                }
                @Override public void afterTextChanged(Editable s) {}
            });
        }

        Log.d("MenuActivity", "MenuActivity onCreate completed");
    }

    // Filter GridLayout cards based on search query
    private void filterSites(String query) {
        if (gridLayout == null) return;

        query = query.toLowerCase();
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            try {
                LinearLayout card = (LinearLayout) gridLayout.getChildAt(i);
                TextView title = (TextView) card.getChildAt(1); // Title is second child
                String siteName = title.getText().toString().toLowerCase();
                card.setVisibility(siteName.contains(query) ? android.view.View.VISIBLE : android.view.View.GONE);
            } catch (Exception e) {
                Log.e("MenuActivity", "Error filtering sites: " + e.getMessage());
            }
        }
    }
}