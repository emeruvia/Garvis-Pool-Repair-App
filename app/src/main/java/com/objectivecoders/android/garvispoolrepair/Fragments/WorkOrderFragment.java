package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.objectivecoders.android.garvispoolrepair.R;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrderFragment extends Fragment {

    private RecyclerView workOrderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work_orders_list, container, false);

        return rootView;
    }
}
