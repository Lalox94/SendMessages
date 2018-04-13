package com.example.lalo.sendmessages.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.example.lalo.sendmessages.R;
import com.firebase.ui.auth.AuthUI;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private Button mSendMessageButton;
    private Toolbar toolbarForMenuDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ShowsAuthenticationProviders();

        showMenuDrawerNavigation();
        mSendMessageButton = (Button) findViewById(R.id.buttonSearchStores);
        mSendMessageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NearByStoreActivity.class);
                startActivity(i);
            }
        });
    }

    private void ShowsAuthenticationProviders() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.GreenTheme)
                        .setLogo(R.drawable.domily_logo)
                        .build(),
                RC_SIGN_IN);
    }

    public void showMenuDrawerNavigation() {
        toolbarForMenuDrawer = (Toolbar) findViewById(R.id.toolbarMainActivity);

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Mis Listas");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Mis compras");

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbarForMenuDrawer)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("Test1")
                )
                .build();
        // It updates by itself automatically
        item1.withName("Tienes un pedido en curso").withBadge("1").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        //notify the drawer about the updated element. it will take care about everything else
        result.updateItem(item1);
    }
}
