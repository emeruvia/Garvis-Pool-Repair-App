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

import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import com.objectivecoders.android.garvispoolrepair.R;
import com.objectivecoders.android.garvispoolrepair.RecyclerViews.ClientRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emeruvia on 3/4/2018.
 */

public class ClientFragment extends Fragment {

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
        clientRecyclerView = new ClientRecyclerView(clientList);
        RecyclerView recyclerView = rootView.findViewById(R.id.client_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(clientRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
}
