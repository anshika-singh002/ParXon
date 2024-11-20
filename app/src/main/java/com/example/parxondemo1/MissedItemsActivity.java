package com.example.parxondemo1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MissedItemsActivity extends AppCompatActivity {

    private Button contactPatientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activvity_missed);

        // Initialize Contact Patient Button
        contactPatientButton = findViewById(R.id.contact_patient_button);

        // Set OnClickListener for the button
        contactPatientButton.setOnClickListener(v -> {
            // Open the phone dialer with a predefined phone number
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:1234567890")); // Replace with the patient's phone number
            startActivity(intent);
        });
    }
}