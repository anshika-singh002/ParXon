
package com.example.parxondemo1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        SharedPreferences preferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        button1 = findViewById(R.id.startExercise1);
        button2 = findViewById(R.id.startExercise2);
        button3 = findViewById(R.id.startExercice3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity2.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity3.class);
                startActivity(intent);
            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_privacy){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://parxondemo1.blogspot.com/2024/12/parxondemo1-privacy-policy-page.html "));
            startActivity(intent);


            return true;
        }
        if (id == R.id.id_term){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://parxondemo1.blogspot.com/2024/12/parxondemo1-terms-conditions.html "));
            startActivity(intent);



            return true;
        }
        if (id == R.id.id_rate){

            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));


            }catch(Exception ex){

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

            }


            return true;
        }
        if (id == R.id.id_more){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Leap+Fitness+Group&hl=en"));
            startActivity(intent);



            return true;
        }
        if (id == R.id.id_share){


            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String sharebody = "The best app for Parkinson's Patients." + "https://play.google.com/store/apps/details?id=com.example.parxondemo1";
            String sharehub = "ParXon";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,sharehub);
            myIntent.putExtra(Intent.EXTRA_TEXT,sharebody);
            startActivity(Intent.createChooser(myIntent, "share using"));



            return true;
        }
        return true;
    }
    public void stage1(View view) {
        Intent intent=new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    public void stage2(View view) {
        Intent intent=new Intent(MainActivity.this, SecondActivity2.class);
        startActivity(intent);
    }

    public void stage3(View view) {
        Intent intent=new Intent(MainActivity.this, SecondActivity3.class);
        startActivity(intent);
    }

    public void Games(View view) {
        Intent intent=new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}