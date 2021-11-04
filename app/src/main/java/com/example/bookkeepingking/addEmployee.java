package com.example.bookkeepingking;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Employee;

public class addEmployee extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);
        Button button = (Button) view.findViewById(R.id.buttonSave);
        Context thisContext = container.getContext();
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Employee employee;
                try{
                    employee = new Employee(-1,1, getString(getView().findViewById(R.id.editTextFirstName)),
                            getString(getView().findViewById(R.id.editTextLastName)),
                            getString(getView().findViewById(R.id.editTextAddress)),
                            getString(getView().findViewById(R.id.editTextState)),
                            getString(getView().findViewById(R.id.editTextCity)),
                            getString(getView().findViewById(R.id.editTextZip)),
                            getString(getView().findViewById(R.id.editTextPhnnumber)),
                            getString(getView().findViewById(R.id.editTextSsn)),
                            0,
                            "weekly",
                            true,
                            true);
                }catch(Exception e){
                employee = new Employee(-1,1,"error", "error",
                        "error","error","error","error", "error","error",
                        0,"weekly", true, true);
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(thisContext);
                boolean success = dataBaseHelper.addOne(employee);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public String getString() {
        return getString();
    }

    public String getString(EditText e){
        String k = e.getText().toString();
        return k;
    }

}
