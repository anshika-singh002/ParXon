package com.example.parxondemo1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreenOk extends AppCompatActivity {

    private Animation up, down; // Declare animations
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge content
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen_ok);

        // Handle window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ImageView and TextView
        imageView = findViewById(R.id.appsplash);
        textView = findViewById(R.id.appname);

        // Load animations
        up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up);
        down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down);

        // Set animations
        imageView.setAnimation(up);
        textView.setAnimation(down);

        // Delay for 3.5 seconds and navigate to MainActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenOk.this, MainActivity.class));
            finish(); // Close SplashScreenOk activity
        }, 3500); // 3.5-second delay
    }
}
