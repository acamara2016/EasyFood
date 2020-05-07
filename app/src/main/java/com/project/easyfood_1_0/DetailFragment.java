package com.project.easyfood_1_0;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment implements OnMapReadyCallback {
    private ImageView eventImageView;
    private TextView eventTitleView;
    private TextView eventDateView;
    private TextView eventAddressView;
    //private GoogleMap mMap;
    private TextView eventDescriptionFr;
    private TextView eventDescriptionEn;
    private TextView frenchCostView;
    private TextView englishCostView;
    private TextView delivery_estimate;
    private TextView restaurant_type;
    private ListView listView;

    @SuppressLint("WrongViewCast")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_2, container, false);
        //SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        String[] foods={"Food1","Food2","Food3","Food4","Food5","Food6"};
        initViews(view);
        final Restaurant event = (Restaurant) getArguments().getSerializable("restaurant");
        Picasso.get().load(event.getImage()).fit().centerCrop().into(eventImageView);
        eventTitleView.setText(event.getName());
        delivery_estimate.setText("5-10 MIN");
        restaurant_type.setText("$. African. Fast Food. Pizza");

        ListView simpleList;
        String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
        int flags[] = {R.drawable.ic_star_black_24dp, R.drawable.ic_star_black_24dp, R.drawable.ic_star_black_24dp, R.drawable.ic_star_black_24dp, R.drawable.ic_star_black_24dp, R.drawable.ic_star_black_24dp};
        simpleList = (ListView) view.findViewById(R.id.simpleListView);
        CustomListAdapter customAdapter = new CustomListAdapter(getContext(), countryList, flags);
        simpleList.setAdapter(customAdapter);

        /*mapFragment.getMapAsync(new OnMapReadyCallback() {
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
        });*/
        return view;
    }

    private void initViews(View view) {
        eventImageView = view.findViewById(R.id.event_image_view);
        eventTitleView = view.findViewById(R.id.restaurant_title);
        delivery_estimate = view.findViewById(R.id.delivery_duration_vew);
        restaurant_type = view.findViewById(R.id.type_view);
        listView=view.findViewById(R.id.food_listing);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
