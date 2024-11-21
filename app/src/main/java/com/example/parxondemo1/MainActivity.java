package com.example.parxondemo1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import org.json.JSONObject;
import org.json.JSONException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class ExerciseService {
    public void startExercise() {
        // Implementation
    }

    public void pauseExercise() {
        // Implementation
    }

    public void stopExercise() {
        // Implementation
    }
}

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
    private static final String BASE_URL = "https://api.vectorshift.ai";
    private static final String API_KEY = "sk_8hMNcl9nulU1gXDd0KpFUu49BtvnodlwLByhE3pGBOS9QWHc"; // Replace with your API key
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private ExerciseService exerciseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);

        Retrofit retrofit = RetrofitClient.getClient(BASE_URL, API_KEY);
        VectorShiftApi api = retrofit.create(VectorShiftApi.class);

        RequestBody body = createRequestBody(); // Create a request body
        api.sendDataToVectorShift(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Handle success
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
            }
        });

        initializeSpeechRecognizer();

        exerciseService = new ExerciseService();

        Button startButton = findViewById(R.id.startButton);
        Button pauseButton = findViewById(R.id.pauseButton);
        Button stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(v -> exerciseService.startExercise());
        pauseButton.setOnClickListener(v -> exerciseService.pauseExercise());
        stopButton.setOnClickListener(v -> exerciseService.stopExercise());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private RequestBody createRequestBody() {
        JSONObject json = new JSONObject();
        try {
            json.put("key", "value"); // Replace with actual key-value pairs
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return RequestBody.create(MediaType.parse("application/json"), json.toString());
    }

    private void initializeSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String userCommand = matches.get(0);
                    handleVoiceCommand(userCommand);
                }
            }

            @Override
            public void onError(int error) {
                Toast.makeText(MainActivity.this, "Error recognizing speech: " + error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onEndOfSpeech() {}
        });
    }

    private void handleVoiceCommand(String userCommand) {
        if ("start exercise".equalsIgnoreCase(userCommand)) {
            exerciseService.startExercise();
        } else if ("pause exercise".equalsIgnoreCase(userCommand)) {
            exerciseService.pauseExercise();
        } else if ("stop exercise".equalsIgnoreCase(userCommand)) {
            exerciseService.stopExercise();
        } else {
            Toast.makeText(this, "Unknown command: " + userCommand, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}

public class RetrofitClient {
    public static Retrofit getClient(String baseUrl, String apiKey) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
    }
}
