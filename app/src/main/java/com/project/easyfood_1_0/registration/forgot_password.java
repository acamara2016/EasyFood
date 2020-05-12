package com.project.easyfood_1_0.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.project.easyfood_1_0.R;

public class forgot_password extends AppCompatActivity {
    private static final String TAG = "Issue";
    private Button send_email;
    private TextView email;
    private FirebaseAuth mAuth;
    protected String emailValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        send_email = findViewById(R.id.forgot_password_button);
        email = findViewById(R.id.resest_email_view);
        //Accessing the values



        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValue = email.getText().toString();
                Authenticate(emailValue);
            }
        });

    }

    private void Authenticate(String email){
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(forgot_password.this,email_sucess.class));
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(forgot_password.this, "Could not find your email. Try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}