package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.objectivecoders.android.garvispoolrepair.AuxiliaryFragmentHolderActivity;
import com.objectivecoders.android.garvispoolrepair.CreateWorkOrderActivity;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrderDate;
import com.objectivecoders.android.garvispoolrepair.HomePage;
import com.objectivecoders.android.garvispoolrepair.R;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by jeffr on 3/3/2018.
 */

public class HomePageFragment extends android.support.v4.app.Fragment {

    CalendarView calendarView;
    GraphView graph;
    public HomePageFragment() {
        //Needed for HomePage Activity, don't delete
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

        graph = (GraphView) rootView.findViewById(R.id.graph);
        createGraph();


        if(getArguments() != null && getArguments().getString("ToShow").equals("Date")){
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                    String month = i1+1 >= 10 ? String.valueOf(i1+1) : ""+ String.valueOf(i1+1);
                    String day = i2 > 10 ? String.valueOf(i2) : ""+ String.valueOf(i2);
                        CreateWorkOrderActivity.date  = month+"-"+day+"-"+String.valueOf(i);
                }

            });
        }
        else{
            calendarView.setPadding(0,90,0,0);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                    Intent workOrdersIntent = new Intent(getActivity(), AuxiliaryFragmentHolderActivity.class);
                    String month = i1+1 >= 10 ? String.valueOf(i1+1) : ""+ String.valueOf(i1+1);
                    String day = i2 > 10 ? String.valueOf(i2) : ""+ String.valueOf(i2);
                    workOrdersIntent.putExtra("ToShow","WorkOrderOfTheDay");
                    workOrdersIntent.putExtra("Date", month+"-"+day+"-"+String.valueOf(i));
                    startActivity(workOrdersIntent);
                }
            });
        }
        return rootView;
    }

    //Accounts for bundle in CreateWorkOrderActivity having data in it
    @Override
    public void onResume() {
        CreateWorkOrderActivity.getBundle().clear();
        super.onResume();
    }

    //Creates the graph
    public void createGraph(){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 10),
                new DataPoint(1, 12),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 9),
                new DataPoint(6, 12),
                new DataPoint(7, 20),
                new DataPoint(8, 35),
                new DataPoint(9, 12),
                new DataPoint(10, 7),
                new DataPoint(11, 8)
        });
        graph.addSeries(series);
    }

    public ArrayList fetchWorkOrderCount(){
        ArrayList<Double> woCount = new ArrayList<>();
        double adding = 0;
        for(int i=0; i<12;i++){
            adding = i;
            woCount.add(adding);
        }
        return woCount;
    }
}
