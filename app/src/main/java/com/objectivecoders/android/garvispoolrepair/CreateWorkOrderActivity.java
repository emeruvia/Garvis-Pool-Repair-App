package com.objectivecoders.android.garvispoolrepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private Button dateButton;
    private Button existingClientButton;
    private Button newClientButton;
    private TextView orderNumber;
    private EditText addressEditText;
    private EditText descriptionEditText;
    private Spinner jobTypeSpinner;
    private LinearLayout clientLayout;

    private static Bundle bundle = new Bundle();
    public static String date = "01-01-2001";

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
        dateButton.setText(date);

        if(!bundle.isEmpty()){
            android.support.v4.app.Fragment fragment = new ClientCardViewFragment();
            clientLayout.removeAllViews();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.client_linear_layout,fragment,fragment.getTag()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_work_order_menu, menu);
        return true;
    }

    //TODO Create code to create new WorkOrder object
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.create_work_order){
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickDate(View view){
        Intent miniCalendarIntent = new Intent(this, AuxiliaryFragmentHolderActivity.class);
        miniCalendarIntent.putExtra("ToShow", "Date");
        startActivity(miniCalendarIntent);
    }


    //TODO place code that would open the create new client page
    public void onClickNewClient(View view){

    }

    public void onClickExistingClient(View view){
        Intent clientListIntent = new Intent(this,AuxiliaryFragmentHolderActivity.class);
        clientListIntent.putExtra("ToShow","ExistingClient");
        startActivity(clientListIntent);
    }

    static public Bundle getBundle(){
        return bundle;
    }

    //TODO Fix this to account for additional work order adds after the initial one
    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }
}
