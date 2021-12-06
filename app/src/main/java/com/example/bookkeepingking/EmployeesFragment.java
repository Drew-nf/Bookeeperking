package com.example.bookkeepingking;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
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
        return v;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Employee> list = new ArrayList<>();
        DataBaseHelper db = new DataBaseHelper(getContext());
        list = db.getAllEmployee();
        List<String> names = new ArrayList<>();
        for(int i = 0; i < list.size() ; i++){
            names.add(list.get(i).getF_name() + " " + list.get(i).getL_name());
        }
        Spinner spinner = (Spinner) view.findViewById(R.id.employeeNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        view.findViewById(R.id.buttonAddEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EmployeesFragment.this).navigate(R.id.action_employeesFragment_to_addEmployeeFragment);
            }
        });
        view.findViewById(R.id.buttonEditEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                int index = names.indexOf(spinner.getSelectedItem());
                result.putInt("key", (index +1));
                getParentFragmentManager().setFragmentResult("dataFromEmployee",result);
                NavHostFragment.findNavController(EmployeesFragment.this).navigate(R.id.action_employeesFragment_to_editEmployeeFragment);
            }
        });
    }

}