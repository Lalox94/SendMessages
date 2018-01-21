package com.example.lalo.sendmessages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TiendaListActivity extends AppCompatActivity {

    private List<String> mTiendas;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_list);


        showsStoreinFireBase();

    }

    public void showsStoreinFireBase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mTiendas = this.getAllTiendas();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new MyAdapter(mTiendas, R.layout.recycler_view_item,
                new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String name, int position) {
                        Toast.makeText(TiendaListActivity.this, name + " - " + position,
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
        // Boost Perfomance
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<String> getAllTiendas() {
        return new ArrayList<String>() {{
            add("Lalo");
            add("Aldahir");
            add("Rosa");
            add("Josue");
            add("Arely");
        }};
    }

}
