package com.example.lalo.sendmessages.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lalo.sendmessages.Adapters.RecyclerViewAdapterForNBSA;
import com.example.lalo.sendmessages.R;
import com.example.lalo.sendmessages.Models.Tienda;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

public class NearByStoreActivity extends AppCompatActivity {

    private RecyclerView recyclerViewForStores;
    private RecyclerView.LayoutManager layoutManagerForStores;
    private DatabaseReference mDatabase;
    private Gson gson;
    FirebaseRecyclerAdapter adapterForShowTheStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_list);
        showsStoreinFireBase();
    }

    public void showsStoreinFireBase() {
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
                return vh;
            }
        };

        // Boost Perfomance
        recyclerViewForStores.setHasFixedSize(true);
        recyclerViewForStores.setLayoutManager(layoutManagerForStores);
        recyclerViewForStores.setAdapter(adapterForShowTheStores);
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
