package com.objectivecoders.android.garvispoolrepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateWorkOrderActivity extends AppCompatActivity {

    private Button dateButton;
    private Button existingClientButton;
    private Button newClientButton;
    private TextView orderNumber;
    private EditText addressEditText;
    private EditText descriptionEditText;
    private Spinner jobTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_work_order);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        dateButton = findViewById(R.id.edit_date_button);
        existingClientButton = findViewById(R.id.work_order_existing_client);
        newClientButton = findViewById(R.id.work_order_new_client);
        orderNumber = findViewById(R.id.work_order_number_textview);
        addressEditText = findViewById(R.id.work_order_address_edit_text);
        descriptionEditText = findViewById(R.id.work_order_description_eidt_text);
        jobTypeSpinner = findViewById(R.id.work_order_job_type_spinner);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_work_order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
