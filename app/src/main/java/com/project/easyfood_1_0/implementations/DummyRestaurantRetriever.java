package com.project.easyfood_1_0.implementations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.easyfood_1_0.api.RestaurantRetriever;
import com.project.easyfood_1_0.entities.Food;
import com.project.easyfood_1_0.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DummyRestaurantRetriever implements RestaurantRetriever {
    @Override
    public LiveData<List<Restaurant>> getRestaurants() {
        MutableLiveData<List<Restaurant>> data = new MutableLiveData<>();
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("Hotel Restaurant Badalodge Bamako","logo.jpg","blank","blank","66-74-22-51","Kalabanbougou","01","blank",12.574931,-8.047777,"Description en francais","Description in English","1.0","restaurant");
        //restaurants.add(new Restaurant("Hotel Restaurant Badalodge Bamako","logo.jpg","blank","blank","66-74-22-51","Kalabanbougou","01",00034543700,43545335,12.574931,-8.047777,"Description en francais","Description in English"));
        restaurants.add(new Restaurant("Hotel Restaurant Badalodge Bamako","https://konofara.files.wordpress.com/2015/11/img_20151025_165741.jpg?w=656","blank","blank","66-74-22-51","Kalabanbougou","01","blank",12.574931,-8.047777,"Description en francais","Description in English","1.0","restaurant"));
        restaurants.add(new Restaurant("Soukhotai","https://media-cdn.tripadvisor.com/media/photo-s/15/68/73/ba/photo1jpg.jpg","blank","blank","66-74-22-51","Kalabanbougou","01","blank",12.635306,-7.998563,"Description en francais","Description in English","1.0","restaurant"));
        restaurants.add(new Restaurant("Jaopraya Restaurent Thai","https://media-cdn.tripadvisor.com/media/photo-s/0a/8a/9d/01/jaopraya-thai-restaurant.jpg","blank","blank","66-74-22-51","Kalabanbougou","01","blank",12.634175,-8.004442,"Description en francais","Description in English","1.0","restaurant"));
        restaurants.add(new Restaurant("Bamako Kitchen","blank","https://lh3.googleusercontent.com/proxy/qvMscI_g-n7xHDo3zKoBisDo2wkjgvH9A_hO5_7VMX47KaQYkXnoLSgeUAG0UWRRDCZ70TvH-yEgSb1u72NgCsEFKb7lfNQAtJRfzlodFNnlSHKm9ZVpPCYjGopYB-ZalvPx9BPrYksj064PwKrXRW44KQdjSBa77FzvHxSx9Q","blank","66-74-22-51","Kalabanbougou","01","blank",12.631549,-8.032436,"Description en francais","Description in English","1.0","restaurant"));
        restaurants.add(new Restaurant("Chez Thierry","https://media-cdn.tripadvisor.com/media/photo-s/12/32/6e/73/chez-thierry-un-tres.jpg","blank","blank","66-74-22-51","Kalabanbougou","01","blank",12.650805,-7.978212,"Description en francais","Description in English","1.0","restaurant"));
        data.setValue(restaurants);
        return data;
    }
    public LiveData<List<Food>> getFoods(){
        MutableLiveData<List<Food>> data = new MutableLiveData<>();
        List<Food> menu = new ArrayList<>();
        String image0 = "https://konofara.files.wordpress.com/2015/11/img_20151025_165741.jpg?w=656";
        String image1 = "https://tmbidigitalassetsazure.blob.core.windows.net/secure/RMS/attachments/37/1200x1200/exps28800_UG143377D12_18_1b_RMS.jpg";
        String image2 = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80";
        String image3 = "https://media.mnn.com/assets/images/2017/06/sonic_mushroom_beef_burger.jpg.653x0_q80_crop-smart.jpg";
                menu.add(new Food(1,"Burgers",1500,image0,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image1,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image2,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image3,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image0,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image1,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image2,"Burger"));
        menu.add(new Food(1,"Burgers",1500,image3,"Burger"));

        data.setValue(menu);
        return data;
    }
}
