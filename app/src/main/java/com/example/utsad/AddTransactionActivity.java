package com.example.utsad;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddTransactionActivity extends AppCompatActivity {
    // Key untuk Intent extra yang dikirim dari MainActivity
    public static final String EXTRA_SOURCE = "EXTRA_SOURCE";

    private boolean isExpensesActive = true;

    private Button btnAddIncome;
    private Button btnAddExpenses;
    private TextView tvCategoryAdd;
    private TextView tvSourceAdd;
    private TextView tvDateAdd;

    // Data kategori expense dan income
    private final String[] expenseCategories = {
            "Food", "Transportation", "Shopping", "Education", "Telephone", "Health", "Social", "Bills"
    };
    private final String[] incomeCategories = {
            "Salary", "Refunds", "Awards"
    };
    private final String[] sourceOptions = {"Cash", "E-Wallet", "Bank Transfer", "Credit Card"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Ambil data dari Intent (dikirim oleh MainActivity)
        String source = getIntent().getStringExtra(EXTRA_SOURCE);
        initViews();
        setupBackButton();
        setupToggle();
        setupCategoryDropdown();
        setupSourceDropdown();
        setupDatePicker();
        setupSaveButton();
    }

    private void initViews() {
        btnAddIncome = findViewById(R.id.btn_add_income);
        btnAddExpenses = findViewById(R.id.btn_add_expenses);
        tvCategoryAdd = findViewById(R.id.tv_category_add);
        tvSourceAdd = findViewById(R.id.tv_source_add);
        tvDateAdd = findViewById(R.id.tv_date_add);
    }

    private void setupBackButton() {
        findViewById(R.id.iv_back).setOnClickListener(v -> finish());
    }

    private void setupToggle() {
        btnAddIncome.setOnClickListener(v -> {
            isExpensesActive = false;
            updateToggleUI();
            // Reset pilihan kategori saat toggle berganti
            tvCategoryAdd.setText("Category");
            tvCategoryAdd.setTextColor(getResources().getColor(R.color.text_hint, null));
        });

        btnAddExpenses.setOnClickListener(v -> {
            isExpensesActive = true;
            updateToggleUI();
            tvCategoryAdd.setText("Category");
            tvCategoryAdd.setTextColor(getResources().getColor(R.color.text_hint, null));
        });
    }

    private void updateToggleUI() {
        if (isExpensesActive) {
            btnAddExpenses.setBackgroundResource(R.drawable.bg_toggle_active);
            btnAddExpenses.setTextColor(getResources().getColor(R.color.text_white, null));
            btnAddIncome.setBackgroundResource(R.drawable.bg_toggle_inactive);
            btnAddIncome.setTextColor(getResources().getColor(R.color.text_secondary, null));
        } else {
            btnAddIncome.setBackgroundResource(R.drawable.bg_toggle_active);
            btnAddIncome.setTextColor(getResources().getColor(R.color.text_white, null));
            btnAddExpenses.setBackgroundResource(R.drawable.bg_toggle_inactive);
            btnAddExpenses.setTextColor(getResources().getColor(R.color.text_secondary, null));
        }
    }

    private void setupCategoryDropdown() {
        RelativeLayout dropdownCategory = findViewById(R.id.dropdown_category_add);
        dropdownCategory.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            // Tambahkan item sesuai state toggle
            String[] categories = isExpensesActive ? expenseCategories : incomeCategories;
            for (int i = 0; i < categories.length; i++) {
                popup.getMenu().add(0, i, i, categories[i]);
            }
            popup.setOnMenuItemClickListener(item -> {
                String selected = item.getTitle().toString();
                tvCategoryAdd.setText(selected);
                tvCategoryAdd.setTextColor(getResources().getColor(R.color.text_primary, null));
                return true;
            });
            popup.show();
        });
    }

    private void setupSourceDropdown() {
        RelativeLayout dropdownSource = findViewById(R.id.dropdown_source_add);
        dropdownSource.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            for (int i = 0; i < sourceOptions.length; i++) {
                popup.getMenu().add(0, i, i, sourceOptions[i]);
            }
            popup.setOnMenuItemClickListener(item -> {
                String selected = item.getTitle().toString();
                tvSourceAdd.setText(selected);
                tvSourceAdd.setTextColor(getResources().getColor(R.color.text_primary, null));
                return true;
            });
            popup.show();
        });
    }

    private void setupDatePicker() {
        RelativeLayout fieldDate = findViewById(R.id.field_date);
        fieldDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                        String dateText = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        tvDateAdd.setText(dateText);
                        tvDateAdd.setTextColor(getResources().getColor(R.color.text_primary, null));
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
    }

    private void setupSaveButton() {
        Button btnSave = findViewById(R.id.btn_save_transaction);
        btnSave.setOnClickListener(v -> {
            // Validasi: ambil nilai amount
            android.widget.EditText etAmount = findViewById(R.id.et_amount_add);
            String amountStr = etAmount.getText().toString().trim();

            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Masukkan jumlah transaksi", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tampilkan konfirmasi
            String type = isExpensesActive ? "Pengeluaran" : "Pemasukan";
            Toast.makeText(this,
                    type + " Rp" + amountStr + " berhasil disimpan!",
                    Toast.LENGTH_SHORT).show();

            // Kembali ke MainActivity
            finish();
        });
    }
}