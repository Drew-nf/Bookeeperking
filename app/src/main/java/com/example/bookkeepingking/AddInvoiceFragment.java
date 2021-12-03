package com.example.bookkeepingking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Employee;
import LocalDatabase.Invoice;

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


        view.findViewById(R.id.buttonSaveInvoiceAdd).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Invoice invoice;
                try{
                    invoice = new Invoice(-1,1,1,1,
                            getString(getView().findViewById(R.id.editTextInvoiceNum)),
                            " ",
                            getString(getView().findViewById(R.id.editTextInvoiceTotal)),
                            getString(getView().findViewById(R.id.editTextInvoiceDate))
                            );
                }catch(Exception e){
                    invoice = new Invoice(-1,1,1, 1,
                            "error","error","error","error");
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                //This will send it back to the main menu and add to database
                boolean success = dataBaseHelper.addInvoice(invoice);
                Toast toast;
                if(success){
                    toast = Toast.makeText(getContext(), "Invoice Added", Toast.LENGTH_LONG);
                }else{
                    toast = Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG);
                }
                toast.show();
                NavHostFragment.findNavController(AddInvoiceFragment.this).
                        navigate(R.id.action_addInvoiceFragment_to_HomeFragment);
            }
        });



        view.findViewById(R.id.buttonCancelInvoiceAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddInvoiceFragment.this).
                        navigate(R.id.action_addInvoiceFragment_to_HomeFragment);
            }
        });
        view.findViewById(R.id.buttonUploadInvoice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent in= new Intent(getActivity(), AddInvoice.class);
                startActivity(in);
            }
        });

    }

    public String getString() {
        return getString();
    }

    public String getString(EditText e){
        String k = e.getText().toString();
        return k;
    }

}