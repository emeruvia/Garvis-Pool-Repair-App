package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.objectivecoders.android.garvispoolrepair.HomePage;

/**
 * Created by jeffr on 4/23/2018.
 */

public class DeleteClientFragment extends DialogFragment {
    DatabaseReference databaseClients;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        databaseClients = FirebaseDatabase.getInstance().getReference("clients");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete Client?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO Firebase code
                        databaseClients.child(getArguments().getString("ClientID")).removeValue();
                        getActivity().finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}