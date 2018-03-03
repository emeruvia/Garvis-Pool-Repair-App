package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.objectivecoders.android.garvispoolrepair.R;

/**
 * Created by jeffr on 3/3/2018.
 */

public class HomePageFragment extends android.support.v4.app.Fragment {

    CalendarView calendarView;

    public HomePageFragment() {
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);

        calendarView = rootView.findViewById(R.id.calendarView);

        calendarView.setDate(System.currentTimeMillis());

        return rootView;
    }
}
