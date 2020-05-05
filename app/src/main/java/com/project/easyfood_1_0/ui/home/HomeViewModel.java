package com.project.easyfood_1_0.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.easyfood_1_0.api.RestaurantRetriever;
import com.project.easyfood_1_0.api.RestaurantRetrieverFactory;
import com.project.easyfood_1_0.entities.Restaurant;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private LiveData<List<Restaurant>> restaurants;
    private RestaurantRetriever retriever;
    public HomeViewModel(Context context) {
        restaurants = new MutableLiveData<>();
        retriever = RestaurantRetrieverFactory.getInstance(context);
        restaurants = retriever.getRestaurants();


    }


    public LiveData<List<Restaurant>> getRestaurants(){
        return restaurants;
    }
}