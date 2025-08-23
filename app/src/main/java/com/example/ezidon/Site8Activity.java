package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site8Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site8Activity", "Site8Activity onCreate started");
        setContentView(R.layout.activity_site8);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site8Activity", "Site8Activity onCreate completed");
    }
}