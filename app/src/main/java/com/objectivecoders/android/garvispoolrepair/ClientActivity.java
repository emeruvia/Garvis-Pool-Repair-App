package com.objectivecoders.android.garvispoolrepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ClientActivity extends AppCompatActivity {
 private TextView firstName;
 private TextView lastName;
 private TextView address;
 private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        firstName = findViewById(R.id.client_first_name_textview);
        lastName = findViewById(R.id.client_last_name_textview);
        address = findViewById(R.id.client_address_textview);
        email = findViewById(R.id.client_email_textview);

        firstName.setText(getIntent().getExtras().getString("FirstName"));
        lastName.setText(getIntent().getExtras().getString("LastName"));
        address.setText(getIntent().getExtras().getString("Address"));
        email.setText(getIntent().getExtras().getString("Email"));

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
            Intent editClientIntent = new Intent(this,CreateClientActivity.class);
            editClientIntent.putExtra("Editing",true);
            startActivity(editClientIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
