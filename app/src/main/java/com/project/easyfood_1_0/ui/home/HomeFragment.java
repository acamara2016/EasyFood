package com.project.easyfood_1_0.ui.home;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.easyfood_1_0.BamakoListAdapter;
import com.project.easyfood_1_0.ListAdapter;
import com.project.easyfood_1_0.R;
import com.project.easyfood_1_0.entities.Restaurant;
import com.project.easyfood_1_0.implementations.FirebaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static java.lang.StrictMath.acos;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6, recyclerView7, recyclerView8, recyclerView9;
    DatabaseReference ref;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new HomeViewModel(getContext());
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final com.project.easyfood_1_0.ListAdapter african = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter fast_food = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter cafe = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter restaurant = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter ice_cream = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter grill = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter chinese = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter bakery = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter thai = new com.project.easyfood_1_0.ListAdapter();
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();



        //The following 5 lines are for the slideshow
        ViewFlipper simpleViewFlipper = (ViewFlipper) root.findViewById(R.id.newsViewFlipper); // get the reference of ViewFlipper
        ImageView imageView = new ImageView(this.getContext());
        Drawable dr = getResources().getDrawable(R.drawable.ic_launcher_foreground);
        imageView.setImageDrawable(dr);
        simpleViewFlipper.addView(imageView);
        initViews(root,african,fast_food,cafe,restaurant,ice_cream,grill,chinese,bakery,thai);

        homeViewModel.getRestaurants().observe(getViewLifecycleOwner(), new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(final List<Restaurant> restaurants) {
                final List<Restaurant> less_than_1k = new ArrayList<>();
                final List<Restaurant> hotelList = new ArrayList<>();
                final List<Restaurant> bakeryList = new ArrayList<>();
                final List<Restaurant> cafeList = new ArrayList<>();
                final List<Restaurant> fast_foodList = new ArrayList<>();
                final List<Restaurant> chineseList = new ArrayList<>();
                final List<Restaurant> africanList = new ArrayList<>();
                final List<Restaurant> lebaneseList = new ArrayList<>();
                final List<Restaurant> americanList = new ArrayList<>();
                final List<Restaurant> barList = new ArrayList<>();
                final List<Restaurant> thaiList = new ArrayList<>();
                final List<Restaurant> casinoList = new ArrayList<>();
                final List<Restaurant> grillList = new ArrayList<>();
                final List<Restaurant> ice_creamList = new ArrayList<>();
                final List<Restaurant> breakfastList = new ArrayList<>();


                ref = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
                ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String latitude = dataSnapshot.child("latitude").getValue().toString();
                        String longitude = dataSnapshot.child("longitude").getValue().toString();

                        int x = 0;
                        while(restaurants.get(x).getCity()!=null){
                            System.out.println("Types here "+restaurants.get(x).getType());
                            //TODO replace the hardcoded value into latitude and longitude
                            double distance = getKilometers(12.6340419,-8.0277175,restaurants.get(x).getLatitude(),restaurants.get(x).getLongitude());

                            if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("Bakery") || restaurants.get(x).getType().equals("Cafe"))) {
                                bakeryList.add(restaurants.get(x));
                            }else if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("Cafe") || restaurants.get(x).getType().equals("Cafe"))) {
                                cafeList.add(restaurants.get(x));
                            }else if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("African") || restaurants.get(x).getType().equals("Cafe"))) {
                                africanList.add(restaurants.get(x));
                            }else if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("Fast") || restaurants.get(x).getType().equals("Cafe"))) {
                                fast_foodList.add(restaurants.get(x));
                            }else if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("Restaurant") || restaurants.get(x).getType().equals("Cafe"))) {
                                restaurants.add(restaurants.get(x));
                            }else if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("Ice") || restaurants.get(x).getType().equals("Cafe"))) {
                                ice_creamList.add(restaurants.get(x));
                            }else if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("grill") || restaurants.get(x).getType().equals("Cafe"))) {
                                grillList.add(restaurants.get(x));
                            }else if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("chinese") || restaurants.get(x).getType().equals("Cafe"))) {
                                chineseList.add(restaurants.get(x));
                            }else if(restaurants.get(x).getType()!=null && (restaurants.get(x).getType().contains("thai") || restaurants.get(x).getType().equals("Cafe"))) {
                                thaiList.add(restaurants.get(x));
                            }
                            x++;
                        }
                        african.data=africanList;
                        african.notifyDataSetChanged();

                        fast_food.data=fast_foodList;
                        fast_food.notifyDataSetChanged();

                        cafe.data=cafeList;
                        cafe.notifyDataSetChanged();

                        restaurant.data=restaurants;
                        restaurant.notifyDataSetChanged();

                        ice_cream.data=ice_creamList;
                        ice_cream.notifyDataSetChanged();

                        grill.data=grillList;
                        grill.notifyDataSetChanged();

                        chinese.data=chineseList;
                        chinese.notifyDataSetChanged();

                        bakery.data=bakeryList;
                        bakery.notifyDataSetChanged();

                        thai.data=thaiList;
                        thai.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        return root;
    }

    public double getKilometers(double lat1, double long1, double lat2, double long2) {
        double PI_RAD = Math.PI / 180.0;
        double phi1 = lat1 * PI_RAD;
        double phi2 = lat2 * PI_RAD;
        double lam1 = long1 * PI_RAD;
        double lam2 = long2 * PI_RAD;

        return 6371.01 * acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(lam2 - lam1));
    }

    private void initViews(View view, ListAdapter african, ListAdapter fast_food, ListAdapter cafe, ListAdapter restaurant, ListAdapter ice_cream, ListAdapter grill, ListAdapter chinese, ListAdapter bakery, ListAdapter thai){
        recyclerView1 = view.findViewById(R.id.african_view);
        recyclerView2 = view.findViewById(R.id.fast_food_view);
        recyclerView3 = view.findViewById(R.id.cafe_view);
        recyclerView4 = view.findViewById(R.id.restaurant_view);
        recyclerView5 = view.findViewById(R.id.Ice_cream_view);
        recyclerView6 = view.findViewById(R.id.grill_view);
        recyclerView7 = view.findViewById(R.id.chinese_view);
        recyclerView8 = view.findViewById(R.id.bakery_view);
        recyclerView9 = view.findViewById(R.id.thai_view);






        recyclerView1.setAdapter(african);
        recyclerView2.setAdapter(fast_food);
        recyclerView3.setAdapter(cafe);
        recyclerView4.setAdapter(restaurant);
        recyclerView5.setAdapter(ice_cream);
        recyclerView6.setAdapter(grill);
        recyclerView7.setAdapter(chinese);
        recyclerView8.setAdapter(bakery);
        recyclerView9.setAdapter(thai);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
    }


}
