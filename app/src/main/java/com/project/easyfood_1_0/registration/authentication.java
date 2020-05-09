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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.easyfood_1_0.MainActivity;
import com.project.easyfood_1_0.R;

public class authentication extends AppCompatActivity {
    private static final String TAG = "Issue";
    private Button login;
    private Button signup;
    private TextView email, forgot_password, passowrd;
    private FirebaseAuth mAuth;
    protected String emailValue, passwordValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.signup_button_view);
        email = findViewById(R.id.authentication_email_view);
        passowrd = findViewById(R.id.authentication_password_view);
        forgot_password = findViewById(R.id.authentication_forget_password);
        //Accessing the values
        emailValue = email.toString();
        passwordValue = passowrd.toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValue = email.toString();
                passwordValue = passowrd.toString();
                Authenticate(emailValue,passwordValue);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser!=null){
            //
            startActivity(new Intent(authentication.this, MainActivity.class));
        }
    }

    private void Authenticate(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(authentication.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }
}
