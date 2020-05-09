package com.project.easyfood_1_0.ui.home;

import android.graphics.drawable.Drawable;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new HomeViewModel(getContext());
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final com.project.easyfood_1_0.ListAdapter listAdapter = new com.project.easyfood_1_0.ListAdapter();
        final com.project.easyfood_1_0.ListAdapter listAdapter2 = new com.project.easyfood_1_0.ListAdapter();


        //The following 5 lines are for the slideshow
        ViewFlipper simpleViewFlipper = (ViewFlipper) root.findViewById(R.id.newsViewFlipper); // get the reference of ViewFlipper
        ImageView imageView = new ImageView(this.getContext());
        Drawable dr = getResources().getDrawable(R.drawable.ic_launcher_foreground);
        imageView.setImageDrawable(dr);
        simpleViewFlipper.addView(imageView);
        initViews(root,listAdapter,listAdapter2);

        homeViewModel.getRestaurants().observe(getViewLifecycleOwner(), new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                List<Restaurant> bamakoList = new ArrayList<>();
                listAdapter.data = restaurants;
                listAdapter.notifyDataSetChanged();
                int x = 0;
                while(restaurants.get(x).getCity()!=null){
                    if(restaurants.get(x).getCity().equals("Bamako"))
                        System.out.println("YESSSSSSSSSSSSSSSS");
                        bamakoList.add(restaurants.get(x));
                    x++;
                }
                listAdapter2.data = bamakoList;
                listAdapter2.notifyDataSetChanged();
            }
        });

        return root;
    }

    private void initViews(View view, ListAdapter listAdapter, ListAdapter listAdapter2){
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView2 = view.findViewById(R.id.my_recycler_view2);
        recyclerView.setAdapter(listAdapter);
        //System.out.println(listAdapter.data.get(0).getAddress());
        /*for(int i=0; i<600; i++){
            if(listAdapter.data.get(i).getAddress()=="Bamako"){
                System.out.println("--------------------");
            }
        }*/
        recyclerView2.setAdapter(listAdapter2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
    }


}
