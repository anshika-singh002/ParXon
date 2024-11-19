package com.example.parxondemo1;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientDetailsActivity extends AppCompatActivity {

    private EditText nameInput, ageInput, genderInput, stageInput;
    private Button analyzeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_details); // Ensure 'patient_details.xml' exists in res/layout

        // Initialize the views
        nameInput = findViewById(R.id.name_input); // Ensure these IDs exist in 'patient_details.xml'
        ageInput = findViewById(R.id.age_input);
        genderInput = findViewById(R.id.gender_input);
        stageInput = findViewById(R.id.stage_input);
        analyzeButton = findViewById(R.id.analyze_button);

        // Set the button click listener
        analyzeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String age = ageInput.getText().toString().trim();
                String gender = genderInput.getText().toString().trim();
                String stage = stageInput.getText().toString().trim();

                // Check if any field is empty
                if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || stage.isEmpty()) {
                    Toast.makeText(PatientDetailsActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    // Logic for analyzing or proceeding to the next step
                    Toast.makeText(PatientDetailsActivity.this, "Details submitted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

