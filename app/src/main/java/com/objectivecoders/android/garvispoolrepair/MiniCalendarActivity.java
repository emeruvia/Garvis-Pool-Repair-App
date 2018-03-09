package com.objectivecoders.android.garvispoolrepair;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.objectivecoders.android.garvispoolrepair.Fragments.HomePageFragment;

public class MiniCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_calendar);
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
}
