package com.example.bookkeepingking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonCalender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_calendarFragment);
            }
        });
        view.findViewById(R.id.buttonEmployees).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_employeesFragment);
            }
        });
        view.findViewById(R.id.buttonInvoices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_invoicesFragment);
            }
        });
        view.findViewById(R.id.buttonPayroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_payrollFragment);
            }
        });
        view.findViewById(R.id.buttonSalesTax).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_salesTaxFragment);
            }
        });
    }
}