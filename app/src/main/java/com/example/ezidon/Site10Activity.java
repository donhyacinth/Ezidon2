package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site10Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site10Activity", "Site10Activity onCreate started");
        setContentView(R.layout.activity_site10);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site10Activity", "Site10Activity onCreate completed");
    }
}