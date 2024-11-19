package com.example.parxondemo1;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview); // Ensure this matches your XML file name

        // Find the VideoView
        VideoView videoView = findViewById(R.id.videoView);

        // Set the video URI
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.exercise_2);
        videoView.setVideoURI(videoUri);

        // Add MediaController for play/pause/seek controls
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Start the video
        videoView.start();
    }
}
