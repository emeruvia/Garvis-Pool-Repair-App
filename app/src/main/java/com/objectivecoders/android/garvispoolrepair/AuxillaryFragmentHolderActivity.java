package com.objectivecoders.android.garvispoolrepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.objectivecoders.android.garvispoolrepair.Fragments.ClientFragment;
import com.objectivecoders.android.garvispoolrepair.Fragments.HomePageFragment;

public class AuxillaryFragmentHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auxillary_fragment_holder);

        if(getIntent().getExtras().getString("CreateWorkOrderActivity").equals("Date")){
            android.support.v4.app.Fragment fragment = new HomePageFragment();

            fragment.setArguments(getIntent().getExtras());

            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.mini_layout,fragment,fragment.getTag()).commit();

            DisplayMetrics displayMetrics = new DisplayMetrics();

            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            int width = displayMetrics.widthPixels;

            int height = displayMetrics.heightPixels;

            getWindow().setLayout((int) (width * 0.5), (int) (height * 0.6));
        }
        else if(getIntent().getExtras().getString("CreateWorkOrderActivity").equals("ExistingClient")){
            android.support.v4.app.Fragment fragment = new ClientFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mini_layout,fragment,fragment.getTag()).commit();
        }

    }
}
