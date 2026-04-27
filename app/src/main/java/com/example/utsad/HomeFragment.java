package com.example.utsad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    // True = expenses aktif, false = income aktif
    private boolean isExpensesActive = true;

    private Button btnIncome;
    private Button btnExpenses;
    private RelativeLayout dropdownCategory;
    private TextView tvCategoryHint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout fragment_home.xml ke dalam View
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi referensi view
        btnIncome = view.findViewById(R.id.btn_income);
        btnExpenses = view.findViewById(R.id.btn_expenses);
        dropdownCategory = view.findViewById(R.id.dropdown_category);
        tvCategoryHint = view.findViewById(R.id.tv_category_hint);

        // Data dummy: tampilkan transaksi pertama (Food, Expense)
        TextView tvCategory1 = view.findViewById(R.id.item_transaction_1)
                .findViewById(R.id.tv_category_name);
        TextView tvAmount1 = view.findViewById(R.id.item_transaction_1)
                .findViewById(R.id.tv_amount);
        TextView tvSource1 = view.findViewById(R.id.item_transaction_1)
                .findViewById(R.id.tv_source);

        if (tvCategory1 != null) tvCategory1.setText("Food");
        if (tvAmount1 != null) tvAmount1.setText("-Rp20.000,00");
        if (tvSource1 != null) tvSource1.setText("Cash");

        // Data dummy: transaksi kedua (Transportation, Expense)
        TextView tvCategory2 = view.findViewById(R.id.item_transaction_2)
                .findViewById(R.id.tv_category_name);
        TextView tvAmount2 = view.findViewById(R.id.item_transaction_2)
                .findViewById(R.id.tv_amount);
        TextView tvSource2 = view.findViewById(R.id.item_transaction_2)
                .findViewById(R.id.tv_source);

        if (tvCategory2 != null) tvCategory2.setText("Transportation");
        if (tvAmount2 != null) tvAmount2.setText("-Rp10.000,00");
        if (tvSource2 != null) tvSource2.setText("E-Wallet");

        // Popup menu for 3-dot buttons on transaction items
        View item1 = view.findViewById(R.id.item_transaction_1);
        View item2 = view.findViewById(R.id.item_transaction_2);
        if (item1 != null) {
            View moreBtn1 = item1.findViewById(R.id.iv_more_home);
            if (moreBtn1 != null) moreBtn1.setOnClickListener(v -> showTransactionMenu(v));
        }
        if (item2 != null) {
            View moreBtn2 = item2.findViewById(R.id.iv_more_home);
            if (moreBtn2 != null) moreBtn2.setOnClickListener(v -> showTransactionMenu(v));
        }

        setupToggle();
        setupDropdown();
    }

    private void showTransactionMenu(View anchor) {
        PopupMenu popup = new PopupMenu(requireContext(), anchor);
        popup.getMenu().add(0, 1, 0, "Edit");
        popup.getMenu().add(0, 2, 1, "Delete");
        popup.setOnMenuItemClickListener(item -> {
            // TODO: wire up real edit/delete logic
            return true;
        });
        popup.show();
    }

    private void setupToggle() {
        btnIncome.setOnClickListener(v -> {
            if (isExpensesActive) {
                isExpensesActive = false;
                updateToggleUI();
            }
        });

        btnExpenses.setOnClickListener(v -> {
            if (!isExpensesActive) {
                isExpensesActive = true;
                updateToggleUI();
            }
        });
    }

    private void updateToggleUI() {
        // Reset category hint when toggling
        if (tvCategoryHint != null) {
            tvCategoryHint.setText("Category");
            tvCategoryHint.setTextColor(getResources().getColor(R.color.text_hint, null));
        }

        if (isExpensesActive) {
            btnExpenses.setBackgroundResource(R.drawable.bg_toggle_active);
            btnExpenses.setTextColor(getResources().getColor(R.color.text_white, null));
            btnIncome.setBackgroundResource(R.drawable.bg_toggle_inactive);
            btnIncome.setTextColor(getResources().getColor(R.color.text_secondary, null));
        } else {
            btnIncome.setBackgroundResource(R.drawable.bg_toggle_active);
            btnIncome.setTextColor(getResources().getColor(R.color.text_white, null));
            btnExpenses.setBackgroundResource(R.drawable.bg_toggle_inactive);
            btnExpenses.setTextColor(getResources().getColor(R.color.text_secondary, null));
        }
    }

    private void setupDropdown() {
        if (dropdownCategory != null) {
            dropdownCategory.setOnClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(requireContext(), dropdownCategory);
                if (isExpensesActive) {
                    popupMenu.getMenu().add("Food");
                    popupMenu.getMenu().add("Transportation");
                    popupMenu.getMenu().add("Entertainment");
                    popupMenu.getMenu().add("Bills");
                    popupMenu.getMenu().add("Others");
                } else {
                    popupMenu.getMenu().add("Salary");
                    popupMenu.getMenu().add("Business");
                    popupMenu.getMenu().add("Investment");
                    popupMenu.getMenu().add("Others");
                }

                popupMenu.setOnMenuItemClickListener(item -> {
                    if (tvCategoryHint != null) {
                        tvCategoryHint.setText(item.getTitle());
                        tvCategoryHint.setTextColor(getResources().getColor(R.color.text_primary, null));
                    }
                    return true;
                });

                popupMenu.show();
            });
        }
    }
}