package com.objectivecoders.android.garvispoolrepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WorkOrderActivity extends AppCompatActivity {
    Intent workOrderIntent;
    TextView address;
    TextView jobType;
    TextView date;
    TextView orderNumber;
    TextView description;


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

        address.setText(workOrderIntent.getStringExtra("Address"));
        jobType.setText(workOrderIntent.getStringExtra("JobType"));
        date.setText(workOrderIntent.getStringExtra("Date"));
        orderNumber.setText(workOrderIntent.getStringExtra("OrderNumber"));
        description.setText(workOrderIntent.getStringExtra("Description"));

    }


}
