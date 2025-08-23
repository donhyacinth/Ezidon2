package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site5Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site5Activity", "Site5Activity onCreate started");
        setContentView(R.layout.activity_site5);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site5Activity", "Site5Activity onCreate completed");
    }
}