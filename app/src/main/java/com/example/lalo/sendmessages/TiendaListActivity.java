package com.example.lalo.sendmessages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
                        DatabaseReference id = mDatabase.child("Pedidos").push();
                        WriteDataUserRequest(id);
                        WriteDataStoreRequest(id);
                    }
                });
        // Boost Perfomance
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void WriteDataStoreRequest(DatabaseReference id) {
        String Token = "eEtn5yxKF8I:APA91bF6di9pEYPxqZAscPhGREc0arO-l1ud6-a_SKlggOSPg9Xkj6nTmT-5IdFAozvUV84n5PtNu5pWDCmcpdDcNbft1AYz94zfo9TUzbZl_QoQpTtd0Ikh1H7PF_eI90Udh6Ia1SlN";
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Tienda tienda = new Tienda("MacOs",Token);
        id.child("Tienda").setValue(tienda);
    }

    private void WriteDataUserRequest(DatabaseReference id) {
        String Token = "cD3FrzxXidc:APA91bEj8Nz2fxbZeM8fGdROK1G5-vKkDeUZf9WFg5ivbDBD1QpmdXy4q-rTakFZn9cVMHs7OLbQaaqB8ffdT6mCXyGgbB5BQTBrarBZZuIqcTa7T0KX3xUKaz0isD54xYoYPHtKKjoK";
        Tienda tienda = new Tienda("Telefono",Token);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Change value for User Model
        id.child("Usuario").setValue(tienda);
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
