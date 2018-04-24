package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.objectivecoders.android.garvispoolrepair.R;

/**
 * Created by jeffr on 3/9/2018.
 */

public class ClientCardViewFragment extends android.support.v4.app.Fragment {

    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView addressTextView;
    TextView emailTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_client_list_item, container, false);
        firstNameTextView = rootView.findViewById(R.id.clientFirstNameTextView);
        lastNameTextView = rootView.findViewById(R.id.clientLastNameTextView);
        addressTextView = rootView.findViewById(R.id.clientAddressTextView);
        emailTextView = rootView.findViewById(R.id.clientEmailTextView);




        firstNameTextView.setText("First Name: " + getArguments().getString("FirstName"));
        lastNameTextView.setText("Last Name: " + getArguments().getString("LastName"));
        addressTextView.setText("Address: " + getArguments().getString("Address"));
        emailTextView.setText("Email: " + getArguments().getString("Email"));

        return rootView;
    }

}
