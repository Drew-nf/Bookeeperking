package com.example.bookkeepingking;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SignUpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle  savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SignUpFragment.this).
                        navigate(R.id.action_signUpFragment_to_HomeFragment);
            }
        });

        view.findViewById(R.id.cancelSUButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SignUpFragment.this).
                        navigate(R.id.action_signUpFragment_to_startUpFragment);
            }
        });
    }
}