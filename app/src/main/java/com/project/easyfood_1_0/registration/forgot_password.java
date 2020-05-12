package com.project.easyfood_1_0.registration;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.felipecsl.gifimageview.library.GifImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.project.easyfood_1_0.R;
import com.project.easyfood_1_0.SplashScreenActivity;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class forgot_password extends AppCompatActivity {
    private static final String TAG = "Issue";
    private TextView send_email;
    private TextView email;
    private ImageView logo;
    private FirebaseAuth mAuth;
    private static int SPLASH_TIME_OUT = 2000;
    private GifImageView gifImageView;
    protected String emailValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        mAuth = FirebaseAuth.getInstance();
        send_email = findViewById(R.id.send_reset_request_view);
        email = findViewById(R.id.email_to_reset);
        gifImageView = findViewById(R.id.check);
        logo = findViewById(R.id.logo_reset_password);
        //Accessing the values



        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValue = email.getText().toString();
                if(emailValue!=null)
                    Authenticate(emailValue);
                else if((emailValue=="" && emailValue.length()==0) || !emailValue.contains("@") && (!emailValue.contains(".com") || !emailValue.contains(".ca")) || !emailValue.contains(".uk") )
                    Toast.makeText(forgot_password.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void Authenticate(String email){
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            logo.setVisibility(logo.GONE);
                            gifImageView.setVisibility(gifImageView.VISIBLE);
                            try{
                                InputStream inputStream = getAssets().open("checkmark.gif");
                                byte[] bytes = IOUtils.toByteArray(inputStream);
                                gifImageView.setBytes(bytes);
                                gifImageView.startAnimation();
                            }catch (IOException ex){

                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // This method will be executed once the timer is over
                                    Intent i = new Intent(forgot_password.this, authentication.class);
                                    startActivity(i);
                                    // close this activity
                                    finish();
                                }
                            }, SPLASH_TIME_OUT);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(forgot_password.this, "Could not find your email. Try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}