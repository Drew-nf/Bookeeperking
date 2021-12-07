package com.example.bookkeepingking;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Invoice;

public class EditInvoiceFragment extends Fragment {
    boolean invoiceIsNew;
    public int id = 1;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_invoice, container, false);

        getParentFragmentManager().setFragmentResultListener("dataFromInvoice", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                id = result.getInt("key");
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                Invoice invoice = dataBaseHelper.getInvoice(id);
                EditText textBox = (EditText) getView().findViewById(R.id.editTextInvoiceDate);
                textBox.setText(invoice.getI_date());
                textBox = (EditText) getView().findViewById(R.id.editTextInvoiceNum);
                textBox.setText(invoice.getInvoice_num());
                textBox = (EditText) getView().findViewById(R.id.editTextInvoiceTotal);
                textBox.setText(invoice.getAmount());
            }

        });

        String[] vendorId = { "1", "2", "3" };
        String[] item = {"Chips","Beer","Milk"};
        String[] gl = {"1","2","3"};
        String[] paymentMethod = {"Cash","Check","Credit"};
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerVendorIdEdit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vendorId);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinnerItemEdit);
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinnerGlEdit);
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, gl);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinnerPaymentMethodEdit);
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, paymentMethod);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //populate the edit text boxes
        //requires employee id number
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        Invoice invoice = dataBaseHelper.getInvoice(1);
        EditText textBox = (EditText) getView().findViewById(R.id.editTextInvoiceDate);
        textBox.setText(invoice.getI_date());
        textBox = (EditText) getView().findViewById(R.id.editTextInvoiceNum);
        textBox.setText(invoice.getInvoice_num());
        textBox = (EditText) getView().findViewById(R.id.editTextInvoiceTotal);
        textBox.setText(invoice.getAmount());
        //create database object
        //DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        //create list of all employees in database
        //List<Invoice> list = dataBaseHelper.getAllInvoice();
        //create edit text object to set new value to
        //EditText editTextFirstName = (EditText) getView().findViewById(R.id.editTextInvoiceDate);
        //set edit text box to first employee first name
        //editTextFirstName.setText(list.get(0).getI_date());

        view.findViewById(R.id.buttonCancelInvoiceEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditInvoiceFragment.this).
                        navigate(R.id.action_editInvoiceFragment_to_HomeFragment);
            }
        });
        view.findViewById(R.id.buttonSaveInvoiceEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditInvoiceFragment.this).
                        navigate(R.id.action_editInvoiceFragment_to_HomeFragment);
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
}