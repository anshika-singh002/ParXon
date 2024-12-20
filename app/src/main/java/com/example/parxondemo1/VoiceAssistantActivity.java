package com.example.parxondemo1;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


import android.speech.SpeechRecognizer;
import android.speech.RecognitionListener;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class VoiceAssistantActivity extends AppCompatActivity {

    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Replace with your layout

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        // Create an Intent to listen for speech
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US"); // Change to desired language

        // Set up the listener for recognition results

        speechRecognizer.setRecognitionListener(new RecognitionListener() {



            @Override
            public void onReadyForSpeech(Bundle params) {
                // When ready to listen, you can add custom actions here
            }

            @Override
            public void onResults(Bundle results) {
                // Get the recognized speech results
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String voiceCommand = matches.get(0);
                    handleVoiceCommand(voiceCommand);  // Call a method to process the voice command
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onError(int error) {
                // Handle errors (e.g., no speech detected, user didn't speak)
            }

            // Other listener methods (optional):
            @Override public void onBeginningOfSpeech() {}
            @Override public void onEndOfSpeech() {}
            @Override public void onEvent(int eventType, Bundle params) {}
            @Override public void onBufferReceived(byte[] buffer) {}
            @Override public void onRmsChanged(float rmsdB) {}
        });

        // Start listening for speech
        speechRecognizer.startListening(intent);
    }

    private void handleVoiceCommand(String command) {
        if (command.toLowerCase().contains("exercise")) {
            // Start the exercise process
            startExercise();
        } else if (command.toLowerCase().contains("reminder")) {
            // Show a reminder
            showReminder();
        } else {
            // Handle unknown commands

            showUnknownCommandMessage();
        }
    }

    private void showReminder() {
        // Display a simple reminder message using Toast
        Toast.makeText(getApplicationContext(), "It's time to do your exercises!", Toast.LENGTH_LONG).show();
    }

    private void showUnknownCommandMessage() {
        // Display a message saying the command wasn't recognized
        Toast.makeText(getApplicationContext(), "Sorry, I didn't understand that command.", Toast.LENGTH_LONG).show();
    }

    private void startExercise() {
        // Your logic to start an exercise routine for the patient
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up the SpeechRecognizer when the activity is destroyed
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}