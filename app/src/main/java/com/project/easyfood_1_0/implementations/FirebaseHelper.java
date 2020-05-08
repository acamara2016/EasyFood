package com.project.easyfood_1_0.implementations;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.easyfood_1_0.entities.User;

public class FirebaseHelper {
    private FirebaseDatabase db;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;

    FirebaseHelper(){
        db = FirebaseDatabase.getInstance();
        reference = db.getReference();
        mAuth = FirebaseAuth.getInstance();
    }
    FirebaseHelper(FirebaseDatabase db, DatabaseReference ref, FirebaseAuth mAuth){
        this.db = db;
        this.reference = ref;
        this.mAuth = mAuth;
    }

    public void addUserInfo(User u){
        reference.child("user").child(u.getUID()).setValue(u);
    }



}
