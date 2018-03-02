package com.example.lalo.sendmessages.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.lalo.sendmessages.Activities.StoreActivity;
import com.example.lalo.sendmessages.Adapters.RecyclerViewAdapterForProductsTab;
import com.example.lalo.sendmessages.Models.Productos;
import com.example.lalo.sendmessages.Models.Tienda;
import com.example.lalo.sendmessages.Models.User;
import com.example.lalo.sendmessages.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

public class StoreProductListTab extends Fragment{

    FirebaseRecyclerAdapter adapterForShowTheProducts;
    MaterialDialog materialDialog;
    Gson gson;

    private RecyclerView recyclerViewForProducts;
    private RecyclerView.LayoutManager layoutManagerForProducts;
    private DatabaseReference firebaseDataBaseReference;

    private Button orderButton;
    private TextView totalTextiView;

    public StoreProductListTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_product_list_tab, container,
                false);

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mHandler,
                new IntentFilter("com.example.lalo.tienda_FCM-MESSAGE"));

        showProductsInStore(rootView);

        totalTextiView = (TextView) rootView.findViewById(R.id.textViewTotal);

        orderButton = (Button) rootView.findViewById(R.id.buttonOrder);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushOrderButton();
                showDialogWaitingForResponse();
            }
        });

        return rootView;
    }

    private BroadcastReceiver mHandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            stopDialog();
            showDialogForResponseReceived();
        }
    };


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void showProductsInStore(View rootView) {
        recyclerViewForProducts = (RecyclerView) rootView.findViewById
                (R.id.recyclerViewProductsFragment);
        layoutManagerForProducts = new LinearLayoutManager(getActivity());
        firebaseDataBaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Productos")
                .limitToLast(50);

        final FirebaseRecyclerOptions<Productos> options =
                new FirebaseRecyclerOptions.Builder<Productos>()
                        .setQuery(query, Productos.class)
                        .build();

        adapterForShowTheProducts = new FirebaseRecyclerAdapter
                <Productos, RecyclerViewAdapterForProductsTab.ViewHolder>(options) {

            @Override
            protected void onBindViewHolder
                    (@NonNull RecyclerViewAdapterForProductsTab.ViewHolder holder,
                     int position, @NonNull Productos model) {
                holder.bind(options.getSnapshots().get(position), totalTextiView);
            }

            @Override
            public RecyclerViewAdapterForProductsTab.ViewHolder onCreateViewHolder
                    (ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_view_item_products, parent, false);
                RecyclerViewAdapterForProductsTab.ViewHolder vh =
                        new RecyclerViewAdapterForProductsTab.ViewHolder(view);
                return vh;
            }

        };
        recyclerViewForProducts.setHasFixedSize(true);
        recyclerViewForProducts.setLayoutManager(layoutManagerForProducts);
        recyclerViewForProducts.setAdapter(adapterForShowTheProducts);
    }

    public void pushOrderButton() {
        firebaseDataBaseReference = FirebaseDatabase.getInstance().getReference();

        String tiendaDataObjectAsString = StoreActivity.get_Message();
        String Token = FirebaseInstanceId.getInstance().getToken();

        Gson gson = new Gson();
        User user = new User("Usuario", Token);
        Tienda tienda = gson.fromJson(tiendaDataObjectAsString, Tienda.class);

        DatabaseReference id = firebaseDataBaseReference.child("Pedidos").push();

        id.child("User").setValue(user);
        id.child("Tienda").setValue(tienda);
    }

    public void showDialogWaitingForResponse() {
            materialDialog = new MaterialDialog.Builder(getContext())
                    .title("¡Gracias por su compra!")
                    .content("Esperando respuesta")
                    .progress(true, 0)
                    .show();
    }

    public void showDialogForResponseReceived(){
        new MaterialDialog.Builder(getContext())
                .title("Respuesta obtenida")
                .content("¡Tu pedido esta en camino!")
                .positiveText("Seguir repartidor")
                .negativeText("Regresar")
                .show();
    }

    public void stopDialog(){
        materialDialog.cancel();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterForShowTheProducts.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterForShowTheProducts.startListening();
    }

}
