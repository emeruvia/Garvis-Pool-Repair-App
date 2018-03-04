package com.objectivecoders.android.garvispoolrepair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method implements onClick on the login button, it creates a new intent which
     * redirects the user to a new activity
     * @param view
     */
    public void loginButton(View view) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

}
