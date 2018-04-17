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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrderFragment extends Fragment implements RecyclerViewOnClick {

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

        //TODO Get rid of the dummy data once the database is impleted
                    ///Dummy Data///
        /////////////////////////////////////
//      workOrderList.add(new WorkOrder("12312","1",
//              "Do the job","Replace Filter"
//     , false));
//      workOrderList.add(new WorkOrder("12313","1", "Do the job","Fix Pump",false));
////        workOrderList.add(new WorkOrder("12314",new WorkOrderDate(System.currentTimeMillis()),
////                "3412 20th st w Fort Myers, Fl","Do the job","Clean Pool",
////        new Client("3","Haley", "Ovenhouse", "123 IDK", "IDKEITHER@gmail.com")));
        /////////////////////////////////////


        for (WorkOrder w : workOrderList) {

            System.out.println(w.getJobType());

        }
      //  WorkOrderDate date = new WorkOrderDate(System.currentTimeMillis());


        TextView textView = rootView.findViewById(R.id.work_order_date_textview);
        textView.setText("1");
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
                // System.out.println("test1");

                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {

                    //   GenericTypeIndicator<ArrayList<WorkOrder>> typeIndicator = new GenericTypeIndicator<ArrayList<WorkOrder>>();

                    //   System.out.println(clientSnapshot.getKey());

                    String clientKey = clientSnapshot.getKey();

                    Query databaseWorkOrder = FirebaseDatabase.getInstance().getReference("clients/" + clientKey + "/workOrders");
                    databaseWorkOrder.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            // System.out.println("test1");


                            for (DataSnapshot workOrderSnapshot : dataSnapshot.getChildren()) {

                                //   GenericTypeIndicator<ArrayList<WorkOrder>> typeIndicator = new GenericTypeIndicator<ArrayList<WorkOrder>>();

                                //   System.out.println(clientSnapshot.getKey());

                                WorkOrder workOrderQuery = workOrderSnapshot.getValue(WorkOrder.class);

                                System.out.println(workOrderQuery.isCompleted());
                                System.out.println(workOrderQuery.getJobType());

                                if (workOrderQuery.isCompleted()==false) {
                                    System.out.println("test");
                                    workOrderList.add(workOrderQuery);
                                }

                                workOrderRecyclerView.notifyDataSetChanged();

                                //   System.out.println(workOrderQuery.getJobType());

                                //    workOrders.add(workOrder);

                            }

                            // System.out.println(Arrays.asList(workOrders));

                            //    clientRecyclerView.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }

                // System.out.println(Arrays.asList(workOrders));

                //    clientRecyclerView.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void rowSelected(int row) {
        Intent workOrderIntent = new Intent(getActivity(), WorkOrderActivity.class);
        workOrderIntent.putExtra("Date", workOrderList.get(row).getDate().toString());
        workOrderIntent.putExtra("OrderNumber", String.valueOf(workOrderList.get(row).getOrderNumber()));
        workOrderIntent.putExtra("Description", workOrderList.get(row).getDescription());
        workOrderIntent.putExtra("JobType", workOrderList.get(row).getJobType());
//        workOrderIntent.putExtra("FirstName",workOrderList.get(row).getClient().getFirstName());
//        workOrderIntent.putExtra("LastName",workOrderList.get(row).getClient().getLastName());
//        workOrderIntent.putExtra("Email",workOrderList.get(row).getClient().getEmail());
//        workOrderIntent.putExtra("Address",workOrderList.get(row).getClient().getAddress());

        startActivity(workOrderIntent);
    }

    //Accounts for bundle in CreateWorkOrderActivity having data in it
    @Override
    public void onResume() {
        CreateWorkOrderActivity.getBundle().clear();
        super.onResume();
    }


}
