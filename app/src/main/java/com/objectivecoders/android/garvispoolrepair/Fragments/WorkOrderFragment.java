package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.objectivecoders.android.garvispoolrepair.CreateWorkOrderActivity;
import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrder;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrderDate;
import com.objectivecoders.android.garvispoolrepair.HomePage;
import com.objectivecoders.android.garvispoolrepair.R;
import com.objectivecoders.android.garvispoolrepair.RecyclerViews.RecyclerViewOnClick;
import com.objectivecoders.android.garvispoolrepair.RecyclerViews.WorkOrderRecyclerView;
import com.objectivecoders.android.garvispoolrepair.WorkOrderActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrderFragment extends Fragment implements RecyclerViewOnClick {

    private List<WorkOrder> workOrderList = new ArrayList<>();
    private WorkOrder workOrder;
    private WorkOrderRecyclerView workOrderRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work_order_list, container, false);
        final FragmentActivity fragmentActivity = getActivity();
        if(HomePage.fragment instanceof WorkOrderFragment){
            rootView.setPadding(0,100,0,0);
        }

        //TODO Get rid of the dummy data once the database is impleted
                    ///Dummy Data///
        /////////////////////////////////////
        workOrderList.add(new WorkOrder(12312,new WorkOrderDate(System.currentTimeMillis()),
                "3412 20th st w Fort Myers, Fl","Do the job","Replace Filter"
        ,new Client("1","Juan", "Gomez", "123 address",
                "jgomez@gmail.com")));
        workOrderList.add(new WorkOrder(12313,new WorkOrderDate(System.currentTimeMillis()),
                "3412 20th st w Fort Myers, Fl","Do the job","Fix Pump"
                ,new Client("2","Jeffey", "Fleurent", "123 IDK",
                "jfleurent@gmail.com")));
        workOrderList.add(new WorkOrder(12314,new WorkOrderDate(System.currentTimeMillis()),
                "3412 20th st w Fort Myers, Fl","Do the job","Clean Pool",
        new Client("3","Haley", "Ovenhouse", "123 IDK", "IDKEITHER@gmail.com")));
        /////////////////////////////////////

        WorkOrderDate date = new WorkOrderDate(System.currentTimeMillis());


        TextView textView = rootView.findViewById(R.id.work_order_date_textview);
        textView.setText(date.toString());
        if(getArguments() != null){
            textView.setText(getArguments().getString("Date"));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);
        workOrderRecyclerView = new WorkOrderRecyclerView(workOrderList,this);
        RecyclerView r = rootView.findViewById(R.id.work_order_list);
        r.setLayoutManager(linearLayoutManager);
        r.setAdapter(workOrderRecyclerView);
        r.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    @Override
    public void rowSelected(int row) {
        Intent workOrderIntent = new Intent(getActivity(), WorkOrderActivity.class);
        workOrderIntent.putExtra("Date", workOrderList.get(row).getDate().toString());
        workOrderIntent.putExtra("OrderNumber", String.valueOf(workOrderList.get(row).getOrderNumber()));
        workOrderIntent.putExtra("Description", workOrderList.get(row).getDescription());
        workOrderIntent.putExtra("Address", workOrderList.get(row).getAddress());
        workOrderIntent.putExtra("JobType", workOrderList.get(row).getJobType());
        workOrderIntent.putExtra("FirstName",workOrderList.get(row).getClient().getFirstName());
        workOrderIntent.putExtra("LastName",workOrderList.get(row).getClient().getLastName());
        workOrderIntent.putExtra("Email",workOrderList.get(row).getClient().getEmail());
        workOrderIntent.putExtra("ClientAddress",workOrderList.get(row).getClient().getAddress());

        startActivity(workOrderIntent);
    }

    //Accounts for bundle in CreateWorkOrderActivity having data in it
    @Override
    public void onResume() {
        CreateWorkOrderActivity.getBundle().clear();
        super.onResume();
    }


}
