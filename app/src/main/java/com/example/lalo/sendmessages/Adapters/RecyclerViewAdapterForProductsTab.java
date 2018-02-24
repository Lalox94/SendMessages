package com.example.lalo.sendmessages.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.lalo.sendmessages.APIs.GlideApp;
import com.example.lalo.sendmessages.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RecyclerViewAdapterForProductsTab extends
        RecyclerView.Adapter<RecyclerViewAdapterForProductsTab.ViewHolder> {

    int layout;

    public RecyclerViewAdapterForProductsTab() {
    }

    @Override
    public RecyclerViewAdapterForProductsTab.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterForProductsTab.ViewHolder holder, int position) {
        // I think this is not calling directly
        holder.bind("Testing");
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewProduct;
        private NumberPicker numberPicker;
        private ImageView imageViewProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewProduct = (TextView) itemView.findViewById(R.id.textViewProduct);
            this.numberPicker = (NumberPicker) itemView.findViewById(R.id.numberPickerProducts);
            this.imageViewProduct = (ImageView) itemView.findViewById(R.id.imageViewForProducts);
        }

        public void bind(final String name) {
            this.textViewProduct.setText(name);

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference spaceRef = storageRef.child("Cocacola.jpg");

            GlideApp.with(itemView.getContext())
                    .load(spaceRef)
                    .into(imageViewProduct);

            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(10);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setValue(0);
        }



    }

}
