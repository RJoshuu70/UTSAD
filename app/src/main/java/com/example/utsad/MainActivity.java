package com.example.utsad;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;

    private HomeFragment homeFragment;
    private TransactionFragment transactionFragment;
    private OverviewFragment overviewFragment;
    private AboutFragment aboutFragment;

    // Referensi fragment yang sedang aktif
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initFragments();
        setupBottomNavigation();
    }

    private void initViews() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        transactionFragment = new TransactionFragment();
        overviewFragment = new OverviewFragment();
        aboutFragment = new AboutFragment();

        // Set HomeFragment sebagai tampilan awal
        activeFragment = homeFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, aboutFragment, "about").hide(aboutFragment)
                .add(R.id.fragment_container, overviewFragment, "overview").hide(overviewFragment)
                .add(R.id.fragment_container, transactionFragment, "transaction").hide(transactionFragment)
                .add(R.id.fragment_container, homeFragment, "home")
                .commit();
    }

    private void setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                loadFragment(homeFragment);
                return true;
            } else if (itemId == R.id.nav_transaction) {
                loadFragment(transactionFragment);
                return true;
            } else if (itemId == R.id.nav_overview) {
                loadFragment(overviewFragment);
                return true;
            } else if (itemId == R.id.nav_about) {
                loadFragment(aboutFragment);
                return true;
            }
            return false;
        });

        // Pastikan item Home terseleksi saat pertama kali
        bottomNavigation.setSelectedItemId(R.id.nav_home);
    }

    private void loadFragment(Fragment targetFragment) {
        if (targetFragment != activeFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(activeFragment)
                    .show(targetFragment)
                    .commit();
            activeFragment = targetFragment;
        }
    }
}