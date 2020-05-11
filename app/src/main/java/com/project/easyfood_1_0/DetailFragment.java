package com.project.easyfood_1_0;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.easyfood_1_0.entities.Food;
import com.project.easyfood_1_0.entities.Restaurant;
import com.project.easyfood_1_0.implementations.DummyRestaurantRetriever;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


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

        initViews(view);
        final Restaurant event = (Restaurant) getArguments().getSerializable("restaurant");
        Picasso.get().load(event.getImage()).fit().centerCrop().into(eventImageView);
        eventTitleView.setText(event.getName());
        delivery_estimate.setText("5-10 MIN");
        restaurant_type.setText("$. African. Fast Food. Pizza");

        final ListView simpleList;
        simpleList = (ListView) view.findViewById(R.id.simpleListView);
        final CustomListAdapter customAdapter = new CustomListAdapter(getContext(), returnMenu());
        simpleList.setAdapter(customAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Bundle bundle = new Bundle();
                bundle.putSerializable("restaurant", returnMenu().get(0));

                Navigation.findNavController(simpleList)
                        .navigate(R.id.action_detailFragment_to_selected_food_details2, bundle);

            }
        });
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
    public List<Food> returnMenu(){
        List<Food> menu = new ArrayList<>();
        String image0 = "https://www.google.com/search?q=Image+of+burgers&rlz=1C1LOQA_enCA896CA896&sxsrf=ALeKk00WQI8ShD7G-6yy-lPAF76Ux6HT6g:1588854936478&tbm=isch&source=iu&ictx=1&fir=P5k652d4fzAcNM%253A%252CdkmShPVYjDCcpM%252C_&vet=1&usg=AI4_-kT1u532JXOU_iQfrHWV8pQp93aDjw&sa=X&ved=2ahUKEwiD_9nw4aHpAhVDheAKHR6VBNMQ9QEwBHoECAsQKQ#imgrc=P5k652d4fzAcNM";
        String image1 = "https://www.google.com/search?q=Image+of+burgers&rlz=1C1LOQA_enCA896CA896&sxsrf=ALeKk00WQI8ShD7G-6yy-lPAF76Ux6HT6g:1588854936478&tbm=isch&source=iu&ictx=1&fir=P5k652d4fzAcNM%253A%252CdkmShPVYjDCcpM%252C_&vet=1&usg=AI4_-kT1u532JXOU_iQfrHWV8pQp93aDjw&sa=X&ved=2ahUKEwiD_9nw4aHpAhVDheAKHR6VBNMQ9QEwBHoECAsQKQ#imgrc=TlmmHqkV_XO9IM";
        String image2 = "https://www.google.com/search?q=Image+of+burgers&rlz=1C1LOQA_enCA896CA896&sxsrf=ALeKk00WQI8ShD7G-6yy-lPAF76Ux6HT6g:1588854936478&tbm=isch&source=iu&ictx=1&fir=P5k652d4fzAcNM%253A%252CdkmShPVYjDCcpM%252C_&vet=1&usg=AI4_-kT1u532JXOU_iQfrHWV8pQp93aDjw&sa=X&ved=2ahUKEwiD_9nw4aHpAhVDheAKHR6VBNMQ9QEwBHoECAsQKQ#imgrc=513pfmSMYjYJmM";
        String image3 = "https://www.google.com/search?q=Image+of+burgers&rlz=1C1LOQA_enCA896CA896&sxsrf=ALeKk00WQI8ShD7G-6yy-lPAF76Ux6HT6g:1588854936478&tbm=isch&source=iu&ictx=1&fir=P5k652d4fzAcNM%253A%252CdkmShPVYjDCcpM%252C_&vet=1&usg=AI4_-kT1u532JXOU_iQfrHWV8pQp93aDjw&sa=X&ved=2ahUKEwiD_9nw4aHpAhVDheAKHR6VBNMQ9QEwBHoECAsQKQ#imgrc=-mb_6QxRRYBNXM";
        menu.add(new Food(1,"Burgers",1500,image0,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image1,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image2,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image3,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image0,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image1,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image2,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image3,"Burger"));

        return menu;
    }
}
