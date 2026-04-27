package com.example.utsad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.PopupMenu;
import android.widget.Toast;

public class TransactionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup more buttons for all hardcoded items
        int[] moreButtonIds = {
            R.id.btn_more_1, R.id.btn_more_2, R.id.btn_more_3, R.id.btn_more_4, R.id.btn_more_5
        };

        for (int id : moreButtonIds) {
            View btnMore = view.findViewById(id);
            if (btnMore != null) {
                btnMore.setOnClickListener(v -> showTransactionMenu(v));
            }
        }
    }

    private void showTransactionMenu(View v) {
        PopupMenu popup = new PopupMenu(requireContext(), v);
        popup.getMenuInflater().inflate(R.menu.menu_transaction_item, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_edit) {
                Toast.makeText(requireContext(), "Edit Clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.menu_delete) {
                Toast.makeText(requireContext(), "Delete Clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        popup.show();
    }
}