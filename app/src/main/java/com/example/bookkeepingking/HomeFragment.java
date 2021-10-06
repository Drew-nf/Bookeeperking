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
    TextView showCountTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_home,container,false);
        showCountTextView = fragmentFirstLayout.findViewById(R.id.textView_one);
        // Inflate the layout for this fragment
        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.random_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(showCountTextView.getText().toString());
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        view.findViewById(R.id.buttonToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "@string/chat", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.buttonCount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countMe(view);
            }
        });
        view.findViewById(R.id.buttonCalender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_FirstFragment_to_calendarFragment);
            }
        });
    }
    private void countMe(View view) {
        String countString = showCountTextView.getText().toString();
        Integer count = Integer.parseInt(countString);
        count++;
        showCountTextView.setText(count.toString());
    }
}