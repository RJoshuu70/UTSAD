package com.example.utsad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TransactionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Make activity draw under system bars
        androidx.core.view.WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        // Make status bar transparent so root background shows through
        getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
        
        setContentView(R.layout.activity_transaction_detail);

        // Apply status bar padding to the root view
        MainActivity.applyStatusBarInset(findViewById(R.id.root_detail));

        ImageView ivBack = findViewById(R.id.iv_back);
        TextView tvCategory = findViewById(R.id.tv_detail_category);
        TextView tvSource = findViewById(R.id.tv_detail_source);
        TextView tvAmount = findViewById(R.id.tv_detail_amount);

        // Handle Back Navigation
        ivBack.setOnClickListener(v -> finish());

        // Get Intent Data
        Intent intent = getIntent();
        if (intent != null) {
            String category = intent.getStringExtra("CATEGORY");
            String source = intent.getStringExtra("SOURCE");
            String amount = intent.getStringExtra("AMOUNT");

            if (category != null) tvCategory.setText(category);
            if (source != null) tvSource.setText(source);
            if (amount != null) {
                tvAmount.setText(amount);
                // Adjust color based on sign
                if (amount.startsWith("-")) {
                    tvAmount.setTextColor(getResources().getColor(R.color.expense_red, null));
                } else {
                    tvAmount.setTextColor(getResources().getColor(R.color.income_green, null));
                }
            }
        }
    }
}
