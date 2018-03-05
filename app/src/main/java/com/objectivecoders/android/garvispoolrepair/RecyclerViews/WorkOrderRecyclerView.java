package com.objectivecoders.android.garvispoolrepair.RecyclerViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrder;
import com.objectivecoders.android.garvispoolrepair.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffr on 3/3/2018.
 */

public class WorkOrderRecyclerView extends RecyclerView.Adapter<WorkOrderRecyclerView.WorkOrderRecyclerViewHolder> {

    List<WorkOrder> workOrderList = new ArrayList<>();

    public  WorkOrderRecyclerView(List<WorkOrder> workOrderList){
        this.workOrderList = workOrderList;
    }

    @Override
    public WorkOrderRecyclerView.WorkOrderRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.fragment_work_order_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new WorkOrderRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkOrderRecyclerViewHolder holder, int position) {
        holder.workOrderNumber.setText(String.valueOf(workOrderList.get(position).getOrderNumber()));
        holder.workOrderJob.setText(workOrderList.get(position).getJobType());
        holder.workOrderDate.setText(workOrderList.get(position).getDate().toString());
        holder.completionIcon.setImageResource(R.drawable.ic_action_name);
        holder.workOrderIcon.setImageResource(R.drawable.ic_work_order);
    }

    @Override
    public int getItemCount() {
        return workOrderList.size();
    }

    public class WorkOrderRecyclerViewHolder extends RecyclerView.ViewHolder {

            ImageView workOrderIcon;
            ImageView completionIcon;
            TextView workOrderNumber;
            TextView workOrderDate;
            TextView workOrderJob;

        public WorkOrderRecyclerViewHolder(View view) {
            super(view);
            workOrderDate = view.findViewById(R.id.work_order_date);
            workOrderIcon = view.findViewById(R.id.work_order_icon);
            workOrderJob = view.findViewById(R.id.work_order_job_textview);
            completionIcon = view.findViewById(R.id.completion_icon);
            workOrderNumber = view.findViewById(R.id.work_order_number_textview);
        }
    }
}
