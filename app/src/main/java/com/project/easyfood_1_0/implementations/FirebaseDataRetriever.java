package com.project.easyfood_1_0.implementations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.easyfood_1_0.api.RestaurantRetriever;
import com.project.easyfood_1_0.entities.Restaurant;

import java.util.List;

public class FirebaseDataRetriever implements RestaurantRetriever {
    private FirebaseHelper db = new FirebaseHelper();
    @Override
    public LiveData<List<Restaurant>> getRestaurants() {
        MutableLiveData<List<Restaurant>> listMutableLiveData = new MutableLiveData<>();
        System.out.println(db.retrieveRestaurants().size());
        listMutableLiveData.setValue(db.retrieveRestaurants());
        return listMutableLiveData;
    }
}
