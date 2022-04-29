package com.example.bookkeepingking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.events.calendar.views.EventsCalendar;

import java.util.ArrayList;
import java.util.Calendar;

import LocalDatabase.Calender;
import LocalDatabase.DataBaseHelper;


public class CalendarFragment extends Fragment {
    private Button button3;
    private PopupWindow popupWindow;
    private CalendarView eventsCalendar;
    // an array to save all events (including dates)
    private ArrayList<Calender> events;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_calendar, container, false);
        eventsCalendar = fragmentFirstLayout.findViewById(R.id.calendarView);
        eventsCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // traversing/iterating all events
                // goes through the array and if the dates matches the click, it gives a toaster
                // WHAT WE NEED TO DO:
                // date should be a date object
                // add all the events into an array
                // we want to parse the date into year, month, and day from the database
                /*
                for(Calender calender : events){
                    if(calender.getC_date()){
                        Toast.makeText(requireContext(), calender.getC_name(), Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                 */
                // Months are from 0-11
                if(year == 2022 && month == 4 && dayOfMonth == 24){
                    Toast.makeText(requireContext(), "SALES TAX DUE", Toast.LENGTH_LONG).show();
                }
                if(year == 2022 && month == 4 && dayOfMonth == 15){
                    Toast.makeText(requireContext(), "RENEWAL OF CIG LICENSE", Toast.LENGTH_LONG).show();
                }
            }
        });

        // events = new ArrayList<>();//fetch the list from the db

        // Inflate the layout for this fragment
        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.button3).setOnClickListener(v -> {
            Calender calender;


            try {
                calender = new Calender(

                        getString(getView().findViewById(R.id.editTextTextPersonName3)),
                        getString(getView().findViewById(R.id.editTextTextPersonName4))
                );


            } catch (Exception e) {
                calender = new Calender("error", "error");
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
            boolean success = dataBaseHelper.addCalender(calender);
            Toast toast;
            if (success) {
                toast = Toast.makeText(getContext(), "Event Added", Toast.LENGTH_LONG);
            } else {
                toast = Toast.makeText(getContext(), "Your Event has been saved! Thank you :)", Toast.LENGTH_LONG);
            }

            toast.show();
            NavHostFragment.findNavController(CalendarFragment.this).
                    navigate(R.id.action_calendarFragment_to_FirstFragment);


        });

    }

    public String getString() {
        return getString();
    }

    public String getString(EditText e) {
        String k = e.getText().toString();
        return k;
    }

}




