package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site3Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site3Activity", "Site3Activity onCreate started");
        setContentView(R.layout.activity_site3);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site3Activity", "Site3Activity onCreate completed");
    }
}