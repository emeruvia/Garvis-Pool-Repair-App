package com.objectivecoders.android.garvispoolrepair.RecyclerViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrderRecyclerView extends RecyclerView.Adapter<WorkOrderRecyclerView.WorkOrderRecyclerViewHolder> {



    public  WorkOrderRecyclerView(){

    }

    @Override
    public WorkOrderRecyclerView.WorkOrderRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        return null;
    }

    @Override
    public void onBindViewHolder(WorkOrderRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class WorkOrderRecyclerViewHolder extends RecyclerView.ViewHolder {




        public WorkOrderRecyclerViewHolder(View view) {

            super(view);


        }

    }
}
