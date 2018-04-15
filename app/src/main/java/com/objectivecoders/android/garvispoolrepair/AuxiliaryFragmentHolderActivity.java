package com.objectivecoders.android.garvispoolrepair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.objectivecoders.android.garvispoolrepair.Fragments.ClientFragment;
import com.objectivecoders.android.garvispoolrepair.Fragments.HomePageFragment;
import com.objectivecoders.android.garvispoolrepair.Fragments.WorkOrderFragment;

//This class is used for holding various fragments when an activity is needed for functionality
public class AuxiliaryFragmentHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auxillary_fragment_holder);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("ToShow").equals("Date")) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowTitleEnabled(false);

                android.support.v4.app.Fragment fragment = new HomePageFragment();
                fragment.setArguments(getIntent().getExtras());
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mini_layout, fragment, fragment.getTag()).commit();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                int height = displayMetrics.heightPixels;
                getWindow().setLayout((int) (width * 0.7), (int) (height * 0.48));

            } else if (getIntent().getExtras().getString("ToShow").equals("ExistingClient")) {
                android.support.v4.app.Fragment fragment = new ClientFragment();
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mini_layout, fragment, fragment.getTag()).commit();
            }

            //TODO Change the list of items based on the day that was clicked
            else if (getIntent().getExtras().getString("ToShow").equals("WorkOrderOfTheDay")) {
                android.support.v4.app.Fragment fragment = new WorkOrderFragment();
                fragment.setArguments(getIntent().getExtras());
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mini_layout, fragment, fragment.getTag()).commit();
            }
        }
        else{
            android.support.v4.app.Fragment fragment = new WorkOrderFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mini_layout, fragment, fragment.getTag()).commit();
        }
    }
}
