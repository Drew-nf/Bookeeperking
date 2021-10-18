package com.example.bookkeepingking;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

        Button add= (Button) view.findViewById(R.id.plus_invoice);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in= new Intent(getActivity(), com.example.bookkeepingking.addInvoice.class);
                startActivity(in);
            }
        });
        return view;
    }

}