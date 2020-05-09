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
    private LiveData<List<Restaurant>> bamakoRestaurants;
    private RestaurantRetriever retriever;
    private RestaurantRetriever retriever1;
    public HomeViewModel(Context context) {
        restaurants = new MutableLiveData<>();
        bamakoRestaurants = new MutableLiveData<>();
        retriever = RestaurantRetrieverFactory.getInstance(context);
        retriever1 = RestaurantRetrieverFactory.getInstance(context);
        restaurants = retriever.getRestaurants();
        bamakoRestaurants = retriever1.getRestaurantsBamako();

    }


    public LiveData<List<Restaurant>> getRestaurants(){
        return restaurants;
    }
    public LiveData<List<Restaurant>> getBamakoRestaurants(){
        return bamakoRestaurants;
    }
}