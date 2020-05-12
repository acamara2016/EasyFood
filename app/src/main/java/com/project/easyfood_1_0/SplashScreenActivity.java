package com.project.easyfood_1_0;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.felipecsl.gifimageview.library.GifImageView;
import com.project.easyfood_1_0.registration.authentication;
import com.project.easyfood_1_0.registration.login;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    private GifImageView gifImageView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBar);
        gifImageView = findViewById(R.id.gifImageView);
        progressBar.setVisibility(progressBar.VISIBLE);

        try{
            InputStream inputStream = getAssets().open("splashGIF.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }catch (IOException ex){

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashScreenActivity.this, authentication.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
