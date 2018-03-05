package com.objectivecoders.android.garvispoolrepair.RecyclerViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import com.objectivecoders.android.garvispoolrepair.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emeruvia on 3/3/2018.
 */

public class ClientRecyclerView extends RecyclerView.Adapter<ClientRecyclerView.ClientRecyclerViewHolder> {

    List<Client> clientList = new ArrayList<>();

    public ClientRecyclerView(List<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public ClientRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.fragment_client_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new ClientRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClientRecyclerViewHolder holder, int position) {
        holder.firstNameTextView.setText("First Name: " + clientList.get(position).getFirstName());
        holder.lastNameTextView.setText("Last Name: " + clientList.get(position).getLastName());
        holder.addressTextView.setText("Address: " + clientList.get(position).getAddress());
        holder.emailTextView.setText("Email: " + clientList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class ClientRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView addressTextView;
        TextView emailTextView;

        public ClientRecyclerViewHolder(View view) {
            super(view);
            firstNameTextView = view.findViewById(R.id.clientFirstNameTextView);
            lastNameTextView = view.findViewById(R.id.clientLastNameTextView);
            addressTextView = view.findViewById(R.id.clientAddressTextView);
            emailTextView = view.findViewById(R.id.clientEmailTextView);
        }
    }
}
