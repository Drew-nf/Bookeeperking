package com.example.bookkeepingking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

        view.findViewById(R.id.buttonHomeCalender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_calendarFragment);
            }
        });
        view.findViewById(R.id.buttonHomeEmployees).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_employeesFragment);
            }
        });
        view.findViewById(R.id.buttonHomeInvoices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_invoicesFragment);
            }
        });
        view.findViewById(R.id.buttonHomePayroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_payrollFragment);
            }
        });
        view.findViewById(R.id.buttonHomeSalestax).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_salesTaxFragment);
            }
        });
    }
}