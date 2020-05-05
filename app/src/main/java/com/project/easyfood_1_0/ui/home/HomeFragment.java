package com.project.easyfood_1_0.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.easyfood_1_0.ListAdapter;
import com.project.easyfood_1_0.R;
import com.project.easyfood_1_0.entities.Restaurant;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new HomeViewModel(getContext());
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final com.project.easyfood_1_0.ListAdapter listAdapter = new com.project.easyfood_1_0.ListAdapter();
        //The following 5 lines are for the slideshow
        ViewFlipper simpleViewFlipper = (ViewFlipper) root.findViewById(R.id.newsViewFlipper); // get the reference of ViewFlipper
        ImageView imageView = new ImageView(this.getContext());
        Drawable dr = getResources().getDrawable(R.drawable.ic_launcher_foreground);
        imageView.setImageDrawable(dr);
        simpleViewFlipper.addView(imageView);
        initViews(root,listAdapter);
        homeViewModel.getRestaurants().observe(getViewLifecycleOwner(), new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                listAdapter.data = restaurants;
                listAdapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    private void initViews(View view, ListAdapter listAdapter){
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView2 = view.findViewById(R.id.my_recycler_view2);
        recyclerView2.setAdapter(listAdapter);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
    }
}
