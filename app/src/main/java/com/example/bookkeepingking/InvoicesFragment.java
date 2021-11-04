package com.example.bookkeepingking;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InvoicesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_invoices, container, false);

        Button add = (Button) view.findViewById(R.id.buttonAddInvoicePhoto);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in= new Intent(getActivity(), AddInvoice.class);
                startActivity(in);
            }
        });
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonEditInvoice).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                NavHostFragment.findNavController(InvoicesFragment.this).
                        navigate(R.id.action_invoicesFragment_to_fragmentInvoiceEditor);
            }
        });
        view.findViewById(R.id.buttonAddInvoice).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                NavHostFragment.findNavController(InvoicesFragment.this).
                        navigate(R.id.action_invoicesFragment_to_addInvoiceFragment);
            }
        });
    }

}