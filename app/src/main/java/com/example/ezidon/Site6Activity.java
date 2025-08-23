package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site6Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site6Activity", "Site6Activity onCreate started");
        setContentView(R.layout.activity_site6);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site6Activity", "Site6Activity onCreate completed");
    }
}