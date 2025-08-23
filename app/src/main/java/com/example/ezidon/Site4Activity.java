package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site4Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site4Activity", "Site4Activity onCreate started");
        setContentView(R.layout.activity_site4);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site4Activity", "Site4Activity onCreate completed");
    }
}