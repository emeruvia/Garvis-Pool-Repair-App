package com.objectivecoders.android.garvispoolrepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrder;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrderDate;
import com.objectivecoders.android.garvispoolrepair.Fragments.ClientCardViewFragment;

public class WorkOrderActivity extends AppCompatActivity {
    Intent workOrderIntent;
    TextView address;
    TextView jobType;
    TextView date;
    TextView orderNumber;
    TextView description;
    CardView clientCardView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workOrderIntent = getIntent();
        setContentView(R.layout.activity_work_order);
        address = findViewById(R.id.work_order_address_textview);
        jobType = findViewById(R.id.work_order_job_type_textview);
        date = findViewById(R.id.work_order_date_textview);
        orderNumber = findViewById(R.id.work_order_number_textview);
        description = findViewById(R.id.work_order_description_textview);
        clientCardView = findViewById(R.id.work_order_client_cardview);

        //TODO Possibly make code to display a client profile with onClick
        android.support.v4.app.Fragment fragment = new ClientCardViewFragment();
        fragment.setArguments(getIntent().getExtras());
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.work_order_client_cardview,fragment,fragment.getTag()).commit();

        address.setText(workOrderIntent.getStringExtra("Address"));
        jobType.setText(workOrderIntent.getStringExtra("JobType"));
        date.setText(workOrderIntent.getStringExtra("Date"));
        orderNumber.setText(workOrderIntent.getStringExtra("OrderNumber"));
        description.setText(workOrderIntent.getStringExtra("Description"));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.edit_entity){
            Intent editWorkOrderIntent = new Intent(this,CreateWorkOrderActivity.class);
            startActivity(editWorkOrderIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
