package com.example.parxondemo1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class GettingDeviceTokenService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {


        // Get the new token here
        String deviceToken = token;

        // Optional: Log or send token to your server
        Log.d("Token", "Refreshed token: " + deviceToken);


    }

}
