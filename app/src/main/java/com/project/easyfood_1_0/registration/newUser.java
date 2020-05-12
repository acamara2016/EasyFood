package com.project.easyfood_1_0.registration;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.easyfood_1_0.MainActivity;
import com.project.easyfood_1_0.R;
import com.project.easyfood_1_0.entities.User;
import com.project.easyfood_1_0.implementations.FirebaseHelper;

public class newUser extends AppCompatActivity {

    private static final String TAG = "Issue";
    private static final int PERMISSION_ID = 44;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private EditText email, username, passowrd, phone;
    private FirebaseAuth mAuth;
    private TextView register, gotoLogin;
    protected String emailValue, passwordValue, usernameValue, phoneNumberValue;
    private double latitude, longitude;
    private FirebaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        mAuth = FirebaseAuth.getInstance();
        db = new FirebaseHelper();

        register = findViewById(R.id.new_user_signup_btn_view);
        email = findViewById(R.id.new_user_signup_email_view);
        username = findViewById(R.id.new_user_username);
        passowrd = findViewById(R.id.new_user_password);
        phone = findViewById(R.id.mobphone);
        gotoLogin = findViewById(R.id.goto_login);

        email.getText().toString();
        emailValue = email.getText().toString();
        passwordValue = passowrd.getText().toString();
        usernameValue = username.getText().toString();
        phoneNumberValue = phone.getText().toString();


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Accessing the values
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValue = email.getText().toString();
                passwordValue = passowrd.getText().toString();
                usernameValue = username.getText().toString();
                phoneNumberValue = phone.getText().toString();
                configureButton();
                Authenticate(emailValue, passwordValue,phoneNumberValue);
            }
        });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(newUser.this,authentication.class));
            }
        });
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                System.out.println(latitude + " " + longitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        configureButton();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
        } else {
            configureButton();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET
                    }, 10);
                    return;
                }
            }
            return;
        }
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
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
            startActivity(new Intent(newUser.this, MainActivity.class));
        }
    }

    private void Authenticate(String email, String password,String phoneNumber){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            db.storeNewUser(new User("","",usernameValue,emailValue,"","",user.getUid(),String.valueOf(latitude),String.valueOf(longitude)));
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(newUser.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }
}

