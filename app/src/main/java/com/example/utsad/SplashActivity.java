package com.example.utsad;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY_MS = 2000L;

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Install SplashScreen API sebelum super.onCreate
        SplashScreen.installSplashScreen(this);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Runnable: navigasi ke OnboardingActivity setelah SPLASH_DELAY_MS
        Runnable navigateToOnboarding = () -> {
            // Intent eksplisit: langsung menyebutkan kelas tujuan
            Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
            startActivity(intent);
            // Hapus SplashActivity dari back stack agar tidak bisa di-back ke sini
            finish();
        };

        // Post delay: eksekusi Runnable setelah 2 detik
        handler.postDelayed(navigateToOnboarding, SPLASH_DELAY_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Penting: hapus semua callback agar tidak memory leak jika Activity di-destroy sebelum delay selesai
        handler.removeCallbacksAndMessages(null);
    }
}