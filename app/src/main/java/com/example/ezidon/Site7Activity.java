package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site7Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site7Activity", "Site7Activity onCreate started");
        setContentView(R.layout.activity_site7);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site7Activity", "Site7Activity onCreate completed");
    }
}