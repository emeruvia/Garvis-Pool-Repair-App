package com.objectivecoders.android.garvispoolrepair;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import com.objectivecoders.android.garvispoolrepair.Fragments.ClientFragment;
import com.objectivecoders.android.garvispoolrepair.Fragments.HomePageFragment;
import com.objectivecoders.android.garvispoolrepair.Fragments.WorkOrderFragment;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //Connect a fragment to the layout in activity_home_page.xml
        if(fragment == null){
            fragment = new HomePageFragment();
        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit();


        //For the navigation drawer implementation
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


    }

    public void floatingButtonAction(View view){

        //TODO Add code for intent that opens the create new client window
        if(fragment instanceof ClientFragment){

        }

        else if (fragment instanceof HomePageFragment) {
            Intent workOrderIntent = new Intent(this, CreateWorkOrderActivity.class);
            startActivity(workOrderIntent);
        }

        else if(fragment instanceof WorkOrderFragment){
            Intent workOrderIntent = new Intent(this, CreateWorkOrderActivity.class);
            startActivity(workOrderIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
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
            manager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit();
        }
        else if (id == R.id.nav_client) {
            fragment = new ClientFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment, fragment, fragment.getTag()).commit();
        }
        else if (id == R.id.nav_send) {

        }
        else if(id == R.id.nav_calendar) {
            fragment = new HomePageFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment,fragment,fragment.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
