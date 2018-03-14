package com.example.lalo.sendmessages.Activities;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.lalo.sendmessages.Fragments.StoreContactTab;
import com.example.lalo.sendmessages.Fragments.StoreProductListTab;
import com.example.lalo.sendmessages.R;

public class StoreActivity extends AppCompatActivity implements
        StoreProductListTab.OnFragmentInteractionListener, StoreContactTab.OnFragmentInteractionListener{

    private static String intentTiendaObjectDetails;
    private Toolbar toolbarForMenuDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //tabLayout.addTab(tabLayout.newTab().setText("Mi Lista"));
        tabLayout.addTab(tabLayout.newTab().setText("Productos de la Tienda"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacto"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        intentTiendaObjectDetails = getIntent().getStringExtra("TiendaAsString");

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final PagerAdapter adapter = new com.example.lalo.sendmessages.Adapters.PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public static String get_Message(){
        return intentTiendaObjectDetails;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
