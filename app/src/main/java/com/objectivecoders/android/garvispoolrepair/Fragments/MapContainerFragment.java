package com.objectivecoders.android.garvispoolrepair.Fragments;


import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.objectivecoders.android.garvispoolrepair.MainActivity;
import com.objectivecoders.android.garvispoolrepair.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapContainerFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    LocationManager locationManager;
    LocationListener locationListener;

    public MapContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_container, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Zooms in fort myers area
        LatLng FortMyers = new LatLng(26.6406,-81.8723);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(FortMyers, 11));
        //TODO add markers to the addresses from the customers in the databse
    }
}
