package com.project.easyfood_1_0.implementations;

import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.easyfood_1_0.entities.Food;
import com.project.easyfood_1_0.entities.Restaurant;
import com.project.easyfood_1_0.entities.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FirebaseHelper{
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //public String uid = user.getUid();

    public FirebaseHelper() {
    }



    public String giveDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(cal.getTime());
    }
    public String giveYear() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(cal.getTime());
    }
    public String giveMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(cal.getTime());
    }
    public String giveDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return sdf.format(cal.getTime());
    }

    public void storeNewUser(User u){
        ref.child("users").child(u.getUID()).setValue(u);
}



    public void setRestaurantBasics(DataSnapshot snapshot, Restaurant restaurant){
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


    public List<Restaurant> retrieveRestaurants() {
        ref = FirebaseDatabase.getInstance().getReference().child("Report");
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
                //System.out.println("---------------------------------"+list.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //System.out.println(list.size());
        return list;
    }


}
