package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.objectivecoders.android.garvispoolrepair.AuxillaryFragmentHolderActivity;
import com.objectivecoders.android.garvispoolrepair.CreateWorkOrderActivity;
import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
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

        //TODO Get rid of the dummy data once the database is impleted
        clientList.add(new Client("Juan", "Gomez", "123 FUCK JUAN",
                "jgomez@gmail.com"));
        clientList.add(new Client("Jeffey", "Fleurent", "123 IDK",
                "jfleurent@gmail.com"));
        clientList.add(new Client("Haley", "Ovenhouse", "123 IDK", "IDKEITHER@gmail.com"));
        clientList.add(new Client("David", "Murad", "1234 IDK", "IDKEITHER2@gmail.com"));
        clientList.add(new Client("Edgar", "Meruvia", "2366 Crystal Drive",
                "emeruvia@gmail.com"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(fragmentActivity);
        clientRecyclerView = new ClientRecyclerView(clientList,this);
        RecyclerView recyclerView = rootView.findViewById(R.id.client_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(clientRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }


    @Override
    public void rowSelected(int row) {
        //Used for CreateWorkOrderScreen
        if(getActivity() instanceof AuxillaryFragmentHolderActivity){
            CreateWorkOrderActivity.getBundle().putString("FirstName", clientList.get(row).getFirstName());
            CreateWorkOrderActivity.getBundle().putString("LastName", clientList.get(row).getLastName());
            CreateWorkOrderActivity.getBundle().putString("Email", clientList.get(row).getEmail());
            CreateWorkOrderActivity.getBundle().putString("Address", clientList.get(row).getAddress());
            getActivity().finish();
        }
        //TODO make an Intent with extras as Strings that comprise the Client object based on the index
        //TODO "row" of the clientList object
        else{

        }

    }


}
