package com.example.lalo.sendmessages.APIs;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.lalo.sendmessages.Activities.StoreActivity;
import com.example.lalo.sendmessages.Fragments.StoreProductListTab;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());


        String token = remoteMessage.getData().get("token");
        String parentId = remoteMessage.getData().get("parentId");
        Intent intent = new Intent("com.example.lalo.tienda_FCM-MESSAGE");
        intent.putExtra("token",token);
        intent.putExtra("parentId",parentId);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(intent);
    }

}