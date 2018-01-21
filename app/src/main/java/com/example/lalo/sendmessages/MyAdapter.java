package com.example.lalo.sendmessages;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> tiendas;
    private int layout;
    private OnItemClickListener itemClickListener;

    public MyAdapter (List<String> tiendas, int layout, OnItemClickListener listener ) {
        this.tiendas = tiendas;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(tiendas.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return tiendas.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        private TextView textViewTienda;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewTienda = (TextView) itemView.findViewById(R.id.textViewTienda);
        }

        public void bind(final String name, final OnItemClickListener listener) {
            this.textViewTienda.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(name, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String name, int position);
    }
}
