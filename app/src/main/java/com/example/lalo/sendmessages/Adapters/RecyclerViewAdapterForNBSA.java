package com.example.lalo.sendmessages.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalo.sendmessages.APIs.GlideApp;
import com.example.lalo.sendmessages.Models.Tienda;
import com.example.lalo.sendmessages.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class RecyclerViewAdapterForNBSA extends RecyclerView.Adapter<RecyclerViewAdapterForNBSA.ViewHolder> {

    private OnItemClickListener itemClickListener;
    int layout;

    public RecyclerViewAdapterForNBSA() {

    }

    @Override
    public RecyclerViewAdapterForNBSA.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        private TextView textViewStore;
        private TextView textViewStoreTime;
        private TextView textViewtoreDistance;
        private ImageView imageViewStore;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewStore = (TextView) itemView.findViewById(R.id.textViewStoreName);
            this.textViewStoreTime = (TextView) itemView.findViewById(R.id.textViewStoreTime);
            this.textViewtoreDistance = (TextView) itemView.findViewById(R.id.textViewStoreDistance);
            this.imageViewStore = (ImageView) itemView.findViewById(R.id.imageViewStore);


        }

        public void bind(Tienda tienda, final OnItemClickListener listener) {
            this.textViewStore.setText(tienda.getNombre());
            this.textViewStoreTime.setText("Horario: "+tienda.getHorario());
            this.textViewtoreDistance.setText("Distancia: "+tienda.getDistancia());

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference spaceRef = storageRef.child("Tienda.jpg");

            GlideApp.with(itemView.getContext())
                    .load(spaceRef)
                    .into(imageViewStore);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick();
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick();
    }

}
