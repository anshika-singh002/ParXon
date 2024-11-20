package com.example.parxondemo1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textView; // To display data on the UI
    private WebView webView;  // To display the embedded content

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle system bar insets (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
    }
}
