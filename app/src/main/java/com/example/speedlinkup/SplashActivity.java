package com.example.speedlinkup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        ImageView splash = findViewById(R.id.splash);
        ViewCompat.animate(splash).scaleX(1.0f).scaleY(1.0f).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {

            }

            @Override
            public void onAnimationEnd(View view) {
                Intent intent = new Intent(SplashActivity.this,
                        MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(View view) {

            }
        }).setDuration(SPLASH_DISPLAY_LENGTH);
    }
}
