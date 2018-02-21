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

import com.example.lalo.sendmessages.Adapters.RecyclerViewAdapter;
import com.example.lalo.sendmessages.R;
import com.example.lalo.sendmessages.Models.Tienda;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TiendaListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference mDatabase;
    FirebaseRecyclerAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_list);
        showsStoreinFireBase();
    }

    public void showsStoreinFireBase() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Tiendas")
                .limitToLast(50);

        final FirebaseRecyclerOptions<Tienda> options =
                new FirebaseRecyclerOptions.Builder<Tienda>()
                        .setQuery(query, Tienda.class)
                        .build();

        adapter2 = new FirebaseRecyclerAdapter<Tienda, RecyclerViewAdapter.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position, @NonNull Tienda model) {
                holder.bind(options.getSnapshots().get(position).getNombre(), new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String name, int position) {
                        Intent i = new Intent(getApplicationContext(), StoreActivity.class);
                        startActivity(i);
                    }
                });
            }

            @Override
            public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_view_item, parent, false);
                RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(view);

                return vh;
            }
        };

        // Boost Perfomance
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.startListening();
    }
}
