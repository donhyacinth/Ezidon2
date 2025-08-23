package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site9Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site9Activity", "Site9Activity onCreate started");
        setContentView(R.layout.activity_site9);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site9Activity", "Site9Activity onCreate completed");
    }
}