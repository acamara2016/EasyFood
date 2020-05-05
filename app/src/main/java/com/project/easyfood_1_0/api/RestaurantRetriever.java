package com.project.easyfood_1_0.api;

import androidx.lifecycle.LiveData;

import com.project.easyfood_1_0.entities.Restaurant;

import java.util.List;

public interface RestaurantRetriever {
    public LiveData<List<Restaurant>> getRestaurants();
}
