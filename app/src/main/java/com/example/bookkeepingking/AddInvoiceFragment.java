package com.example.bookkeepingking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class AddInvoiceFragment extends Fragment {
    boolean invoiceIsNew;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_add_invoice, container, false);
        // Inflate the layout for this fragment
        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonSaveInvoiceEditor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddInvoiceFragment.this).
                        navigate(R.id.action_addInvoiceFragment_to_HomeFragment);
            }
        });
    }
}