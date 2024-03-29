package com.project.easyfood_1_0.implementations;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.easyfood_1_0.api.RestaurantRetriever;
import com.project.easyfood_1_0.database.LocalDatabaseHandler;
import com.project.easyfood_1_0.entities.Food;
import com.project.easyfood_1_0.entities.Restaurant;

import java.util.List;

public class DatabaseRestaurantRetriever implements RestaurantRetriever {
    private LocalDatabaseHandler db;
    public DatabaseRestaurantRetriever(Context context){
        db = new LocalDatabaseHandler(context);
    }
    @Override
    public LiveData<List<Restaurant>> getRestaurants() {
        MutableLiveData<List<Restaurant>> data = new MutableLiveData<>();
        data.setValue(db.getAllRestaurants());
        ///TODO Code this part when API finally set
        return null;
    }

    @Override
    public LiveData<List<Restaurant>> getRestaurantsBamako() {
        return null;
    }

    public LiveData<List<Food>> getFoods(){
        MutableLiveData<List<Food>> food = new MutableLiveData<>();
        food.setValue(db.getFoods());
        ///TODO Code this part when API finally set
        return null;
    }
}
