package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrder;
import com.objectivecoders.android.garvispoolrepair.R;
import com.objectivecoders.android.garvispoolrepair.RecyclerViews.WorkOrderRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrderFragment extends Fragment {

    private List<WorkOrder> workOrderList = new ArrayList<>();
    private WorkOrder workOrder;
    private WorkOrderRecyclerView workOrderRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work_orders_list, container, false);
        final FragmentActivity fragmentActivity = getActivity();


                    ///Dummy Data///
        /////////////////////////////////////
        workOrderList.add(new WorkOrder(12312,new Date(System.currentTimeMillis()),
                "3412 20th st w Fort Myers, Fl","Just Do It!","Make You're Dreams Come True!"));
        workOrderList.add(new WorkOrder(12312,new Date(System.currentTimeMillis()),
                "3412 20th st w Fort Myers, Fl","Just Do It!","Make You're Dreams Come True!"));
        workOrderList.add(new WorkOrder(12312,new Date(System.currentTimeMillis()),
                "3412 20th st w Fort Myers, Fl","Just Do It!","Make You're Dreams Come True!"));
        /////////////////////////////////////

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);
        workOrderRecyclerView = new WorkOrderRecyclerView(workOrderList);
        RecyclerView r = rootView.findViewById(R.id.work_order_list);
        r.setLayoutManager(linearLayoutManager);
        r.setAdapter(workOrderRecyclerView);
        r.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
}
