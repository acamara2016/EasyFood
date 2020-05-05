package com.project.easyfood_1_0;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.easyfood_1_0.entities.Restaurant;


public class DetailFragment extends Fragment implements OnMapReadyCallback {
    private ImageView eventImageView;
    private TextView eventTitleView;
    private TextView eventDateView;
    private TextView eventAddressView;
    private GoogleMap mMap;
    private TextView eventDescriptionFr;
    private TextView eventDescriptionEn;
    private TextView frenchCostView;
    private TextView englishCostView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        initViews(view);
        final Restaurant event = (Restaurant) getArguments().getSerializable("restaurant");
        //Picasso.get().load(event.getImageUrl()).fit().centerCrop().into(eventImageView);
        eventTitleView.setText(event.getName());
        eventAddressView.setText(event.getAddress());
        eventDescriptionFr.setText(event.getFr_description());
        eventDescriptionEn.setText(event.getFr_description());
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(event.getLongitude(),event.getLatitude()))
                        .zoom(17)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);

                mMap.addMarker(new MarkerOptions()
                        .title(event.getName())
                        .position(new LatLng(event.getLongitude(), event.getLatitude())));

            }
        });
        return view;
    }

    private void initViews(View view) {
        eventTitleView = view.findViewById(R.id.event_title);
        eventAddressView = view.findViewById(R.id.event_address_view);
        eventDescriptionFr = view.findViewById(R.id.event_description_fr);
        eventDescriptionEn = view.findViewById(R.id.event_description_en);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
