package com.example.bookkeepingking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Employee;

public class EmployeesFragment extends Fragment {
    boolean empIsNew;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_employees, container, false);

        //List of Names
        List<Employee> list = new ArrayList<>();
        DataBaseHelper db = new DataBaseHelper(getContext());
        list = db.getAllEmployee();
        String [] names = {list.get(0).getF_name(), list.get(0).getL_name(), list.get(1).getF_name(), list.get(2).getF_name()};


        //Choice set
        //String [] names = {"Evan", "Fadi", "Drew", "Frank"};
        Spinner spinner = (Spinner) v.findViewById(R.id.employeeNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.buttonAddEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EmployeesFragment.this).navigate(R.id.action_employeesFragment_to_addEmployeeFragment);
            }
        });
        view.findViewById(R.id.buttonEditEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EmployeesFragment.this).navigate(R.id.action_employeesFragment_to_editEmployeeFragment);
            }
        });
    }

}