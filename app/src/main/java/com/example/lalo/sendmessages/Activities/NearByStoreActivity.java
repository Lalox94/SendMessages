package com.example.lalo.sendmessages.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.lalo.sendmessages.Adapters.RecyclerViewAdapterForNBSA;
import com.example.lalo.sendmessages.Models.Tienda;
import com.example.lalo.sendmessages.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.Gson;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

public class NearByStoreActivity extends AppCompatActivity {

    private RecyclerView recyclerViewForStores;
    private RecyclerView.LayoutManager layoutManagerForStores;
    private DatabaseReference mDatabase;
    private Toolbar toolbarForMenuDrawer;
    private Gson gson;

    FirebaseRecyclerAdapter adapterForShowTheStores;
    MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_list);
        showMenuDrawerNavigation();
        showsStoreinFireBase();
    }

    public void showsStoreinFireBase() {
        showDialogForWaitingTheServer();
        showTheNearestStoresFromUSer();
    }

    public void showMenuDrawerNavigation() {
        toolbarForMenuDrawer = (Toolbar) findViewById(R.id.toolbarMainActivity);

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Test1");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Test2");

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
        item1.withName("Tienes un pedido en curso").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        //notify the drawer about the updated element. it will take care about everything else
        result.updateItem(item1);
    }

    public void showTheNearestStoresFromUSer(){
        recyclerViewForStores = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManagerForStores = new LinearLayoutManager(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Tiendas")
                .limitToLast(50);

        final FirebaseRecyclerOptions<Tienda> options =
                new FirebaseRecyclerOptions.Builder<Tienda>()
                        .setQuery(query, Tienda.class)
                        .build();

        adapterForShowTheStores = new FirebaseRecyclerAdapter<Tienda,
                RecyclerViewAdapterForNBSA.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerViewAdapterForNBSA.ViewHolder holder, final int position, @NonNull Tienda model) {
                holder.bind(options.getSnapshots().get(position), new RecyclerViewAdapterForNBSA.OnItemClickListener() {
                    @Override
                    public void onItemClick() {
                        Tienda tienda = getSnapshots().get(position);
                        gson = new Gson();
                        String tiendaDataObjectAsAString = gson.toJson(tienda);
                        Intent i = new Intent(getApplicationContext(), StoreActivity.class);
                        i.putExtra("TiendaAsString",tiendaDataObjectAsAString);
                        startActivity(i);
                    }
                });
            }
            @Override
            public RecyclerViewAdapterForNBSA.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_view_item, parent, false);
                RecyclerViewAdapterForNBSA.ViewHolder vh = new RecyclerViewAdapterForNBSA.ViewHolder(view);
                view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        materialDialog.cancel();
                    }
                });
                return vh;
            }
        };
        // Boost Perfomance
        recyclerViewForStores.setHasFixedSize(true);
        recyclerViewForStores.setLayoutManager(layoutManagerForStores);
        recyclerViewForStores.setAdapter(adapterForShowTheStores);
    }

    public void showDialogForWaitingTheServer() {
        materialDialog =  new MaterialDialog.Builder(this)
                .title("Cargando...")
                .content("Espere un momento")
                .progress(true, 0)
                .show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterForShowTheStores.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterForShowTheStores.startListening();
    }
}
