package com.objectivecoders.android.garvispoolrepair;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.firebase.auth.FirebaseAuth;
import com.objectivecoders.android.garvispoolrepair.DataObjects.GarvisSearchSuggestions;
import com.objectivecoders.android.garvispoolrepair.Fragments.ClientFragment;
import com.objectivecoders.android.garvispoolrepair.Fragments.HomePageFragment;
import com.objectivecoders.android.garvispoolrepair.Fragments.MapContainerFragment;
import com.objectivecoders.android.garvispoolrepair.Fragments.WorkOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static Fragment fragment;
    FloatingSearchView mSearchView;

    FloatingActionButton fab;
    final List list = new ArrayList<SearchSuggestion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //the starting layout will be activity_home_page
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);

        //Connect a fragment to the layout in activity_home_page.xml
        if (fragment == null) {
            fragment = new HomePageFragment();
        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment, fragment, fragment.getTag()).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list.add(new GarvisSearchSuggestions());
        list.add(new GarvisSearchSuggestions());
        list.add(new GarvisSearchSuggestions());
        list.add(new GarvisSearchSuggestions());

        mSearchView = findViewById(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new MySearchListener());
        mSearchView.setOnLeftMenuClickListener(new MySearchMenuListener());
        mSearchView.attachNavigationDrawerToMenuButton(drawer);

    }

    public void floatingButtonAction(View view) {
        if (fragment instanceof ClientFragment) {
            Intent clientIntent = new Intent(this, CreateClientActivity.class);
            startActivity(clientIntent);
        } else if (fragment instanceof HomePageFragment) {
            Intent workOrderIntent = new Intent(this, CreateWorkOrderActivity.class);
            startActivity(workOrderIntent);
        } else if (fragment instanceof WorkOrderFragment) {
            Intent workOrderIntent = new Intent(this, CreateWorkOrderActivity.class);
            startActivity(workOrderIntent);
        } else {
            Intent workOrderIntent = new Intent(this, CreateWorkOrderActivity.class);
            startActivity(workOrderIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        //getMenuInflater().inflate(R.menu.menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            Toast toast = Toast.makeText(getApplicationContext(),"test1", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }

        if (id == R.id.menuMainLogout) {
            Toast toast = Toast.makeText(getApplicationContext(),"test2", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_work_order) {
            fragment = new WorkOrderFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment, fragment, fragment.getTag()).commit();
        } else if (id == R.id.nav_client) {
            fragment = new ClientFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment, fragment, fragment.getTag()).commit();
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {
            fragment = new HomePageFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment, fragment, fragment.getTag()).commit();
        } else if (id == R.id.nav_map) {
            fragment = new MapContainerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment, fragment.getTag()).commit();
        }
        else if(id == R.id.nav_logout){

            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class MySearchListener implements FloatingSearchView.OnQueryChangeListener {

        @Override
        public void onSearchTextChanged(String oldQuery, String newQuery) {
            //get suggestions based on newQuery
            //pass them on to the search view
            mSearchView.swapSuggestions(list);
        }
    }

    public class MySearchMenuListener implements FloatingSearchView.OnLeftMenuClickListener {

        @Override
        public void onMenuOpened() {

        }

        @Override
        public void onMenuClosed() {

        }
    }
}