package com.example.lalo.sendmessages.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lalo.sendmessages.APIs.GlideApp;
import com.example.lalo.sendmessages.APIs.MyAppGlideModule;
import com.example.lalo.sendmessages.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private Button mSendMessageButton;
    private ImageView mImageViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference spaceRef = storageRef.child("Cocacola.jpg");

        mImageViewTest = (ImageView) findViewById(R.id.imageviewTest);

        GlideApp.with(this)
                .load(spaceRef)
                .into(mImageViewTest);

        mSendMessageButton = (Button) findViewById(R.id.buttonSearchStores);
        mSendMessageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TiendaListActivity.class);
                startActivity(i);
            }
        });

    }

    private void ShowsAuthenticationProviders() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }
}
