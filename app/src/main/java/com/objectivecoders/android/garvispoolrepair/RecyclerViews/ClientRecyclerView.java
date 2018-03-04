package com.objectivecoders.android.garvispoolrepair.RecyclerViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by emeruvia on 3/3/2018.
 */

public class ClientRecyclerView extends RecyclerView.Adapter<ClientRecyclerView.ClientRecyclerViewHolder> {


    @Override
    public ClientRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ClientRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ClientRecyclerViewHolder extends RecyclerView.ViewHolder {

        public ClientRecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }

}
