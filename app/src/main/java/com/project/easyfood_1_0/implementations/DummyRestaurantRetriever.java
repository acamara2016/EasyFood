package com.project.easyfood_1_0.implementations;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.easyfood_1_0.api.RestaurantRetriever;
import com.project.easyfood_1_0.entities.Food;
import com.project.easyfood_1_0.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DummyRestaurantRetriever implements RestaurantRetriever {

    @Override
    public LiveData<List<Restaurant>> getRestaurants() {
        final MutableLiveData<List<Restaurant>> data = new MutableLiveData<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Report");
        final ArrayList<Restaurant> list = new ArrayList<Restaurant>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    Restaurant s = new Restaurant();
                    setRestaurantBasics(userSnapshot,s);
                    //System.out.println(s.toString());
                    list.add(s);
                }
                data.setValue(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return data;
    }

    @Override
    public LiveData<List<Restaurant>> getRestaurantsBamako() {
        final MutableLiveData<List<Restaurant>> data = new MutableLiveData<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Report");
        final ArrayList<Restaurant> list = new ArrayList<Restaurant>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    Restaurant s = new Restaurant();
                    setRestaurantBasics(userSnapshot,s);
                    //System.out.println(s.toString());
                    list.add(s);
                }
                data.setValue(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return data;
    }
    public void setRestaurantBasics(DataSnapshot snapshot, Restaurant restaurant){
        restaurant.setCity((String) snapshot.child("address_city").getValue());
        restaurant.setName((String) snapshot.child("name").getValue());
        restaurant.setAddress((String) snapshot.child("address").getValue());
        String tmp = (String) snapshot.child("latitude").getValue();
        restaurant.setLatitude(Double.parseDouble(tmp));
        restaurant.setLongitude(Double.parseDouble((String) snapshot.child("longitude").getValue()));
        if(snapshot.child("email").getValue()!=null)
            restaurant.setEmail((String) snapshot.child("email").getValue());
        else
            restaurant.setEmail(null);
        if(snapshot.child("phone").getValue()!=null)
            restaurant.setPhone_numb((String) snapshot.child("phone").getValue());
        else
            restaurant.setPhone_numb(null);
        restaurant.setImage((String) snapshot.child("photo").getValue());
        if((String) snapshot.child("rating").getValue()!=null)
            restaurant.setRating((String) snapshot.child("rating").getValue());
        else
            restaurant.setRating(null);
        restaurant.setType((String) snapshot.child("types").getValue());
        restaurant.setFr_description((String)snapshot.child("site_description").getValue()+" "+(String)snapshot.child("site_title").getValue());
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
