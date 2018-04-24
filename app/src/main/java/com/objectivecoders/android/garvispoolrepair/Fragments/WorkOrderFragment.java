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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.objectivecoders.android.garvispoolrepair.CreateWorkOrderActivity;
import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrder;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrderDate;
import com.objectivecoders.android.garvispoolrepair.HomePage;
import com.objectivecoders.android.garvispoolrepair.R;
import com.objectivecoders.android.garvispoolrepair.RecyclerViews.RecyclerViewOnClick;
import com.objectivecoders.android.garvispoolrepair.RecyclerViews.WorkOrderRecyclerView;
import com.objectivecoders.android.garvispoolrepair.WorkOrderActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrderFragment extends Fragment implements RecyclerViewOnClick {
    private List<Client> clientList = new ArrayList<>();
    private List<WorkOrder> workOrderList = new ArrayList<>();
    private WorkOrderRecyclerView workOrderRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work_order_list, container, false);
        final FragmentActivity fragmentActivity = getActivity();
        if(HomePage.fragment instanceof WorkOrderFragment){
            rootView.setPadding(0,100,0,0);
        }

        loadWorkOrderData();
        loadClientData();

        for (WorkOrder w : workOrderList) {

            System.out.println(w.getJobType());

        }

        TextView textView = rootView.findViewById(R.id.work_order_date_textview);
        textView.setText(new WorkOrderDate(System.currentTimeMillis()).getString());
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

    public void loadWorkOrderData() {

        Query databaseClients = FirebaseDatabase.getInstance().getReference("clients");
        databaseClients.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                workOrderList.clear();


                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {
                    String clientKey = clientSnapshot.getKey();

                    Query databaseWorkOrder = FirebaseDatabase.getInstance().getReference("clients/" + clientKey + "/workOrders");
                    databaseWorkOrder.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String date = "";
                            if(getActivity().getIntent().getExtras() != null){
                                date = getActivity().getIntent().getExtras().getString("Date");
                            }
                            else{
                                date = new WorkOrderDate(System.currentTimeMillis()).getString();
                            }
                            for (DataSnapshot workOrderSnapshot : dataSnapshot.getChildren()) {
                                WorkOrder workOrderQuery = workOrderSnapshot.getValue(WorkOrder.class);

                                if (workOrderQuery.isCompleted()==false) {
                                    if(workOrderQuery.getDate().equals(date)){
                                        workOrderList.add(workOrderQuery);
                                    }

                                }

                                workOrderRecyclerView.notifyDataSetChanged();

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void loadClientData() {
        DatabaseReference databaseClients = FirebaseDatabase.getInstance().getReference("clients");
        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                clientList.clear();

                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {
                    Client client = clientSnapshot.getValue(Client.class);

                    //  System.out.println(client);
                    clientList.add(client);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void rowSelected(int row) {
        Client rowClient = null;
        Intent workOrderIntent = new Intent(getActivity(), WorkOrderActivity.class);
        for(Client client : clientList){
            if(client.getWorkOrders() != null){
                for (WorkOrder workOrder : client.getWorkOrders()){
                    if(workOrder.equals(workOrderList.get(row))){
                        rowClient=client;
                        break;
                    }
                }
            }

        }

        workOrderIntent.putExtra("Date", workOrderList.get(row).getDate().toString());
        workOrderIntent.putExtra("OrderNumber", String.valueOf(workOrderList.get(row).getOrderNumber()));
        workOrderIntent.putExtra("Description", workOrderList.get(row).getDescription());
        workOrderIntent.putExtra("JobType", workOrderList.get(row).getJobType());
        workOrderIntent.putExtra("OrderID",workOrderList.get(row).getOrderId());
        workOrderIntent.putExtra("FirstName",rowClient.getFirstName());
        workOrderIntent.putExtra("LasttName",rowClient.getLastName());
        workOrderIntent.putExtra("Address",rowClient.getAddress());
        workOrderIntent.putExtra("Email",rowClient.getEmail());
        startActivity(workOrderIntent);
    }

    //Accounts for bundle in CreateWorkOrderActivity having data in it
    @Override
    public void onResume() {
        CreateWorkOrderActivity.getBundle().clear();
        super.onResume();
    }



}

