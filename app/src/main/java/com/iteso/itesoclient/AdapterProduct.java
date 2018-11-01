package com.iteso.itesoclient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * @author Oscar Vargas
 * @since 26/02/18.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    private ArrayList<String> products;
    private Context context;

    AdapterProduct(Context context, ArrayList<String> products){
        this.products = products;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;

        ViewHolder(View v){
            super(v);
            mTitle = v.findViewById(R.id.item_product_title);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTitle.setText(products.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
