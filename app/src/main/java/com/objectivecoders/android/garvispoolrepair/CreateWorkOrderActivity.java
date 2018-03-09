package com.objectivecoders.android.garvispoolrepair;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.objectivecoders.android.garvispoolrepair.Fragments.ClientCardViewFragment;

public class CreateWorkOrderActivity extends AppCompatActivity {

    private static Button dateButton;
    private Button existingClientButton;
    private Button newClientButton;
    private TextView orderNumber;
    private EditText addressEditText;
    private EditText descriptionEditText;
    private Spinner jobTypeSpinner;
    private LinearLayout clientLayout;



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
        clientLayout = findViewById(R.id.client_linear_layout);

        //TODO Used this code to replace a layout in the page once a user chooses a client
        android.support.v4.app.Fragment fragment = new ClientCardViewFragment();
        clientLayout.removeAllViews();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.client_linear_layout,fragment,fragment.getTag()).commit();



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

    public void onClickDate(View view){
        Intent miniCalendarIntent = new Intent(this, MiniCalendarActivity.class);
        miniCalendarIntent.putExtra("CreateWorkOrderActivity", true);
        startActivity(miniCalendarIntent);
    }

    static public Button getDateButton(){
        return dateButton;
    }
}
