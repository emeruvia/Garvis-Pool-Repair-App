package com.objectivecoders.android.garvispoolrepair;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrder;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrderDate;
import com.objectivecoders.android.garvispoolrepair.Fragments.ClientCardViewFragment;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateWorkOrderActivity extends AppCompatActivity {

    DatabaseReference databaseClients;

    Client client;

    int day;
    int month;
    int year;

    int lastId;

    private Button dateButton;
    private Button existingClientButton;
    private Button newClientButton;
    private TextView orderId;
    private TextView workOrderDateTextView;
    private EditText addressEditText;
    private EditText descriptionEditText;
    private Spinner jobTypeSpinner;
    private LinearLayout clientLayout;

    Calendar calendar = Calendar.getInstance();



    private static Bundle bundle = new Bundle();
    public static String date = "Select Date...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_work_order);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        databaseClients = FirebaseDatabase.getInstance().getReference("clients");

        dateButton = findViewById(R.id.edit_date_button);
        existingClientButton = findViewById(R.id.work_order_existing_client);
        newClientButton = findViewById(R.id.work_order_new_client);
        orderId = findViewById(R.id.work_order_number_textview);
        descriptionEditText = findViewById(R.id.work_order_description_eidt_text);
        jobTypeSpinner = findViewById(R.id.work_order_job_type_spinner);
        workOrderDateTextView = findViewById(R.id.textViewWorkOrderDate);
        clientLayout = findViewById(R.id.client_linear_layout);
        workOrderDateTextView.setText(new WorkOrderDate(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR)).toString());

        dateButton.setText(date);

        dateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                DatePickerDialog workOrderDatePicker = new DatePickerDialog(CreateWorkOrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datepicker, int y, int m, int d) {
                    workOrderDateTextView.setText(new WorkOrderDate(d,m,y).toString());
                }
        }, year, month, day);
                workOrderDatePicker.show();
        }
    });

        ;

        if(!bundle.isEmpty()){
            android.support.v4.app.Fragment fragment = new ClientCardViewFragment();
            clientLayout.removeAllViews();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.client_linear_layout,fragment,fragment.getTag()).commit();
            String firstName = fragment.getArguments().getString("FirstName");
            String lastName = fragment.getArguments().getString("LastName");
            String address = fragment.getArguments().getString("Address");
            String email = fragment.getArguments().getString("Email");
            String clientId = fragment.getArguments().getString("ClientID");

            this.client = new Client(clientId, firstName, lastName, address, email);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_work_order_menu, menu);
        return true;
    }

    public void createWorkOrder() {

        final List<WorkOrder> workOrderList = new ArrayList<WorkOrder>();
        final List<WorkOrder> workOrderIDList = new ArrayList<WorkOrder>();


       Query databaseClients = FirebaseDatabase.getInstance().getReference("clients/" + client.getId() + "/workOrders");
       databaseClients.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               // System.out.println("test1");

                for (DataSnapshot clientSnapshot : dataSnapshot.getChildren()) {

                    //   GenericTypeIndicator<ArrayList<WorkOrder>> typeIndicator = new GenericTypeIndicator<ArrayList<WorkOrder>>();

                 //   System.out.println(clientSnapshot.getKey());

                    WorkOrder workOrderQuery = clientSnapshot.getValue(WorkOrder.class);

                    workOrderList.add(workOrderQuery);

                 //   System.out.println(workOrderQuery.getJobType());

                //    workOrders.add(workOrder);

                }

               // System.out.println(Arrays.asList(workOrders));

            //    clientRecyclerView.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        int listId = 0;
        int orderIdInt = 4;
        String orderId = Integer.toString(orderIdInt);
        String date = workOrderDateTextView.getText().toString().trim();
        String jobNotes = descriptionEditText.getText().toString().trim();
        String jobType = jobTypeSpinner.getSelectedItem().toString().trim();
        boolean completed = false;

        WorkOrder workOrder = new WorkOrder(orderId,date,jobNotes,jobType,completed);

        workOrderList.add(workOrder);

        client.setWorkOrders(workOrderList);

        DatabaseReference  databaseClientsRef = FirebaseDatabase.getInstance().getReference("clients");
        String id = client.getId();
        for(WorkOrder w : workOrderList) {
            databaseClientsRef.child(id).child("workOrders").child(Integer.toString(listId)).setValue(w);
        listId++;
        }

        Toast.makeText(this, "Client added", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.create_work_order){

        createWorkOrder();


            Toast.makeText(this,"Work Order Created",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }




    //TODO place code that would open the create new client page
    public void onClickNewClient(View view){
        Intent clientIntent = new Intent(this,CreateClientActivity.class);
        startActivity(clientIntent);
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
