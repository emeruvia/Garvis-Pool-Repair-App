package com.objectivecoders.android.garvispoolrepair.Fragments;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
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

import java.util.List;


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

    public LatLng getLocationFromAddress(Context context, String address) {

        Geocoder coder = new Geocoder(getContext());
        List<Address> addresslist;
        LatLng latLng = null;
        try {
            addresslist = coder.getFromLocationName(address, 5);
            if (addresslist == null) {
                return null;
            }
            Address location = addresslist.get(0);
            location.getLatitude();
            location.getLongitude();
            latLng = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return latLng;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Zooms in fort myers area

        //TODO get the addresses from the database
        String address = "2366 Crystal Drive, FortMyers";
        LatLng latLngAddress = getLocationFromAddress(getContext(), address);
        map.addMarker(new MarkerOptions().position(latLngAddress).title(address));

        LatLng FortMyers = new LatLng(26.6406,-81.8723);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(FortMyers, 11));
        //TODO add markers to the addresses from the customers in the databse
    }
}
