package com.objectivecoders.android.garvispoolrepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.objectivecoders.android.garvispoolrepair.DataObjects.Client;
import android.view.Menu;

public class CreateClientActivity extends AppCompatActivity {

    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextEmail;
    EditText editTextAddress;
    Button createClientButton;
    DatabaseReference databaseClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);

        editTextFirstName =  findViewById(R.id.editTextFirstName);
        editTextLastName =  findViewById(R.id.editTextLastName);
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextAddress =  findViewById(R.id.editTextAddress);
        createClientButton =  findViewById(R.id.createClientButton);

        databaseClients = FirebaseDatabase.getInstance().getReference("clients");

        createClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createClient();
            }
        });
    }

    private void createClient(){
        String clientFirstName = editTextFirstName.getText().toString().trim();
        String clientLastName = editTextLastName.getText().toString().trim();
        String clientEmail = editTextEmail.getText().toString().trim();
        String clientAddress = editTextAddress.getText().toString().trim();

        if (TextUtils.isEmpty(clientFirstName)) {
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(clientLastName)) {
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(clientEmail)) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(clientAddress)) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_LONG).show();
        } else {
            String id = databaseClients.push().getKey();
            Client client = new Client (id, clientFirstName, clientLastName, clientAddress, clientEmail);
            databaseClients.child(id).setValue(client);
            Toast.makeText(this, "Client added", Toast.LENGTH_LONG).show();
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_client_menu, menu);
        return true;
    }
}
