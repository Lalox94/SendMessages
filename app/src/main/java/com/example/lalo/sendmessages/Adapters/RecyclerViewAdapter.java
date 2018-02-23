package com.example.lalo.sendmessages.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.lalo.sendmessages.R;
import com.example.lalo.sendmessages.Models.Tienda;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<String> tiendas;
    private OnItemClickListener itemClickListener;
    int layout;


    public RecyclerViewAdapter(Tienda tienda, int layout, OnItemClickListener listener) {
        this.tiendas =  tiendas;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(tiendas.get(position), itemClickListener);
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
