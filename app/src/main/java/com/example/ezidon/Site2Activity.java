package com.example.ezidon;

import android.os.Bundle;
import android.util.Log;

public class Site2Activity extends SiteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Site2Activity", "Site2Activity onCreate started");
        setContentView(R.layout.activity_site2);

        // Setup the site-specific spinner navigation
        setupSiteSpinner(R.id.spinnerNav);

        Log.d("Site2Activity", "Site2Activity onCreate completed");
    }
}