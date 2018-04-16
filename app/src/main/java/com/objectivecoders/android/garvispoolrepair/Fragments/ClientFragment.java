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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.objectivecoders.android.garvispoolrepair.AuxiliaryFragmentHolderActivity;
import com.objectivecoders.android.garvispoolrepair.ClientActivity;
import com.objectivecoders.android.garvispoolrepair.CreateWorkOrderActivity;
import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import com.objectivecoders.android.garvispoolrepair.HomePage;
import com.objectivecoders.android.garvispoolrepair.R;
import com.objectivecoders.android.garvispoolrepair.RecyclerViews.ClientRecyclerView;
import com.objectivecoders.android.garvispoolrepair.RecyclerViews.RecyclerViewOnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emeruvia on 3/4/2018.
 */

public class ClientFragment extends Fragment implements RecyclerViewOnClick{


    private List<Client> clientList = new ArrayList<>();
    private Client client;
    private ClientRecyclerView clientRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_client_list, container, false);
        final FragmentActivity fragmentActivity = getActivity();

        if(HomePage.fragment instanceof ClientFragment){
           rootView.setPadding(0,100,0,0);
        }

        loadClientData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentActivity);
        clientRecyclerView = new ClientRecyclerView(clientList,this);
        RecyclerView recyclerView = rootView.findViewById(R.id.client_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(clientRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        return rootView;
    }

    private void loadClientData() {
        DatabaseReference  databaseClients = FirebaseDatabase.getInstance().getReference("clients");
        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                clientList.clear();

                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {
                    Client client = clientSnapshot.getValue(Client.class);

                  //  System.out.println(client);
                    clientList.add(client);

                }
                clientRecyclerView.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void rowSelected(int row) {
        //Used for CreateWorkOrderScreen
        if(getActivity() instanceof AuxiliaryFragmentHolderActivity){
            CreateWorkOrderActivity.getBundle().putString("FirstName", clientList.get(row).getFirstName());
            CreateWorkOrderActivity.getBundle().putString("LastName", clientList.get(row).getLastName());
            CreateWorkOrderActivity.getBundle().putString("Email", clientList.get(row).getEmail());
            CreateWorkOrderActivity.getBundle().putString("Address", clientList.get(row).getAddress());
            CreateWorkOrderActivity.getBundle().putString("ClientID", clientList.get(row).getId());



            getActivity().finish();
        }
        //TODO make an Intent with extras as Strings that comprise the Client object based on the index
        //TODO "row" of the clientList object
        else{
            Intent clientIntent = new Intent(getActivity(), ClientActivity.class);
            clientIntent.putExtra("FirstName", clientList.get(row).getFirstName());
            clientIntent.putExtra("LastName", clientList.get(row).getLastName());
            clientIntent.putExtra("Email", clientList.get(row).getEmail());
            clientIntent.putExtra("Address", clientList.get(row).getAddress());
            startActivity(clientIntent);
        }
    }

    //Accounts for bundle in CreateWorkOrderActivity having data in it
    @Override
    public void onResume() {
        CreateWorkOrderActivity.getBundle().clear();
        super.onResume();
    }
}
