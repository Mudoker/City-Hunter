package com.example.android_city_hunter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler; // Import the correct Handler
import android.os.Looper; // Add this import for the main Looper
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_view);

        Handler handler = new Handler(Looper.getMainLooper()); // Initialize Handler with the main Looper

        handler.postDelayed(() -> {
            Intent intent = new Intent(this, AuthenticationView.class);
            startActivity(intent);
            finish();
        }, 1000);
    }
}
