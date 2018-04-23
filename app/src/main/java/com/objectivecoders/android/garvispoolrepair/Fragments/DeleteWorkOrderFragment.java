package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrder;
import com.objectivecoders.android.garvispoolrepair.HomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffr on 4/23/2018.
 */

public class DeleteWorkOrderFragment extends DialogFragment {
    DatabaseReference databaseClients;
    List<Client> clientList = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        loadClientData();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete Work Order?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO Firebase code
                        databaseClients = FirebaseDatabase.getInstance().getReference("clients");
                        DatabaseReference r1 = databaseClients.child(getClientID(getArguments().getString("OrderNumber"))) ;
                        DatabaseReference r2 = r1.child("workOrders");
                        DatabaseReference r3 = r2.child(getWorkOrderIndex(r2,getClientID(getArguments().getString("OrderNumber"))));
                        r3.removeValue();
                        getActivity().finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    private void loadClientData() {
        DatabaseReference  databaseClients = FirebaseDatabase.getInstance().getReference("clients");
        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                clientList.clear();

                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {
                    Client client = clientSnapshot.getValue(Client.class);

                    clientList.add(client);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String getClientID(String workOrderId){
        for(Client client : clientList){
            if(client.getWorkOrders() != null){
                for(WorkOrder workOrder : client.getWorkOrders()){
                    if (workOrder.getOrderId().equals(workOrderId)){
                        return client.getId();
                    }

                }
            }

        }
        return "";
    }

    private String getWorkOrderIndex(DatabaseReference databaseClients, String orderNumber){
        String index = "";
        final List<WorkOrder> workOrders = new ArrayList<>();
        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {
                    WorkOrder workOrder = clientSnapshot.getValue(WorkOrder.class);
                    workOrders.add(workOrder);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        int i= 0;
        for (WorkOrder workOrder : workOrders){
            if(workOrder.getOrderNumber().equals(orderNumber)){
                return ""+i;
            }
            i++;
        }
        return "";
    }
}
