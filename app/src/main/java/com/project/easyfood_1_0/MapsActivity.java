package com.project.easyfood_1_0;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.easyfood_1_0.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double dst_latitude, dst_longitude, client_latitude, client_longitude;
    private String location_name;
    private String phone, image;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        location_name = intent.getStringExtra("restaurant_name");
        dst_latitude = intent.getDoubleExtra("latitude", 0);
        dst_longitude = intent.getDoubleExtra("longitude", 0);
        image = intent.getStringExtra("image");
        phone = intent.getStringExtra("phone");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ref = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                client_latitude = 0.0;
                client_longitude = 0.0;
                if(dataSnapshot.child("latitude").getValue().toString()!=null) {
                    client_latitude = Double.parseDouble(dataSnapshot.child("latitude").getValue().toString());
                    client_longitude = Double.parseDouble(dataSnapshot.child("longitude").getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Add a marker in Sydney and move the camera
        //TODO replace hardocded value into user'location
        LatLng userLocation = new LatLng(12.650501, -7.9782981);
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Me"));
        LatLng sydney = new LatLng(dst_latitude, dst_longitude);
        Polyline polyline = mMap.addPolyline(new PolylineOptions()
        .clickable(true)
        .add(sydney).add(userLocation));
        mMap.addMarker(new MarkerOptions().position(sydney).title(location_name+" "+phone));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMaxZoomPreference(20);
        mMap.setMinZoomPreference(15);

    }
}
