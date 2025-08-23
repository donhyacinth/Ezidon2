package com.example.ezidon;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class SplashActivity extends AppCompatActivity {

    private ImageView appLogo;
    private TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide status/navigation bar using modern API
        hideSystemUI();

        appLogo = findViewById(R.id.appLogo);
        appName = findViewById(R.id.appName);

        // Load animations
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        Animation nameAnim = AnimationUtils.loadAnimation(this, R.anim.name_slide_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        // 1️⃣ Start logo animation
        appLogo.startAnimation(logoAnim);

        // 2️⃣ After logo → start text
        logoAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                appName.setVisibility(View.VISIBLE);
                appName.startAnimation(nameAnim);
            }
            @Override public void onAnimationRepeat(Animation animation) {}
        });

        // 3️⃣ After text → fade out whole splash
        nameAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(android.R.id.content).startAnimation(fadeOut);
            }
            @Override public void onAnimationRepeat(Animation animation) {}
        });

        // 4️⃣ After fade out → move to Login
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
            @Override public void onAnimationRepeat(Animation animation) {}
        });
    }

    private void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android 11 (API 30) and above
            WindowInsetsController controller = getWindow().getInsetsController();
            if (controller != null) {
                controller.hide(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.navigationBars());
                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        } else {
            // For older Android versions - using AndroidX compat library
            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
            WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
            if (controller != null) {
                controller.hide(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.navigationBars());
                controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        }
    }
}