package com.example.lalo.sendmessages.Fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lalo.sendmessages.APIs.GlideApp;
import com.example.lalo.sendmessages.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreContactTab extends Fragment {

    ImageView imageViewForStore;

    public StoreContactTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_contact_tab, container, false);
        imageViewForStore = (ImageView) rootView.findViewById(R.id.imageViewStoreContact);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference spaceRef = storageRef.child("Tienda_test.jpg");

        GlideApp.with(rootView.getContext())
                .load(spaceRef)
                .into(imageViewForStore);

        // Inflate the layout for this fragment
        return rootView;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
