package com.example.utsad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Referensi tombol Next dari layout
        Button btnNext = findViewById(R.id.btn_next);

        // Event listener: saat tombol Next ditekan
        btnNext.setOnClickListener(v -> navigateToMain());
    }

    private void navigateToMain() {
        Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
        // Bersihkan back stack: user tidak bisa back ke splash/onboarding
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}