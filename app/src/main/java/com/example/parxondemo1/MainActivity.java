package com.example.parxondemo1;

import android.os.Bundle;
<<<<<<< HEAD
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
=======
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

>>>>>>> 1aea5d0d9bcc7b3793fc13dc0b187a27fa2bea7f
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
    private static final String BASE_URL = "https://api.vectorshift.ai";
    private static final String API_KEY = "sk_8hMNcl9nulU1gXDd0KpFUu49BtvnodlwLByhE3pGBOS9QWHc"; // Replace with your API key
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private ExerciseService exerciseService;
=======
    private TextView textView; // To display data on the UI
    private WebView webView;  // To display the embedded content
>>>>>>> 1aea5d0d9bcc7b3793fc13dc0b187a27fa2bea7f

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
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

=======
        // Handle system bar insets (optional)
>>>>>>> 1aea5d0d9bcc7b3793fc13dc0b187a27fa2bea7f
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
<<<<<<< HEAD
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
=======

        // Initialize TextView
        textView = findViewById(R.id.textView);

        // Initialize WebView
        webView = findViewById(R.id.webView);
        setupWebView();

        // Fetch data from the server
        fetchDataFromServer();
    }

    private void setupWebView() {
        // Enable JavaScript and configure WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript
        webSettings.setDomStorageEnabled(true); // Enable DOM storage for modern web content

        // Set WebView client
        webView.setWebChromeClient(new WebChromeClient());

        // Load embedded YouTube video using an HTML string
        String videoHtml = "<html>" +
                "<body style=\"margin:0;padding:0;\">" +
                "<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/qgyH1BtGO08?si=-_eozDsVoIXkoMkl\" " +
                "title=\"YouTube video player\" frameborder=\"0\" " +
                "allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" " +
                "referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>" +
                "</body>" +
                "</html>";

        webView.loadData(videoHtml, "text/html", "utf-8");
    }


    private void fetchDataFromServer() {
        String url = "http://10.0.2.2:8080/api/fetch_users.php"; // Use 10.0.2.2 for localhost in the emulator

        // Create a Volley RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Create a JSON Array Request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    StringBuilder builder = new StringBuilder();

                    // Parse the JSON response
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String name = object.getString("name");
                            String email = object.getString("email");

                            // Log the data
                            Log.d("User", "Name: " + name + ", Email: " + email);

                            // Append the data to display in the TextView
                            builder.append("Name: ").append(name).append("\n");
                            builder.append("Email: ").append(email).append("\n\n");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Set the data in the TextView
                    textView.setText(builder.toString());
                },
                error -> {
                    // Log any errors
                    Log.e("VolleyError", error.toString());
                    textView.setText("Error fetching data!");
                });

        // Add the request to the RequestQueue
        queue.add(jsonArrayRequest);
    }

    // Placeholder for creating a new account
    public void Create_New_Account(View view) {
        Log.d("Action", "Create New Account button clicked");
    }

    // Placeholder for handling user name
    public void User_Name(View view) {
        Log.d("Action", "User Name button clicked");
>>>>>>> 1aea5d0d9bcc7b3793fc13dc0b187a27fa2bea7f
    }

}
