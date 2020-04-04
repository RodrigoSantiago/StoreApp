package com.example.storeapp.ui;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapp.R;
import com.example.storeapp.logic.data.Item;
import com.example.storeapp.logic.data.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public int type;
        public TextView cardName;
        public TextView cardPrice;
        public TextView cardPar;
        public TextView cardCount;
        public View cardBtn;
        public View cardDivider;

        public ViewHolder(View v, int type) {
            super(v);
            this.type = type;
            view = v;
            cardName = v.findViewById(R.id.card_name);
            cardPrice = v.findViewById(R.id.card_price);
            cardPar = v.findViewById(R.id.card_par);
            cardCount = v.findViewById(R.id.card_count);
            cardBtn = v.findViewById(R.id.card_btn);
            cardDivider = v.findViewById(R.id.card_item_divider);
        }
    }

    private Item[] list;

    public CartAdapter(Item[] list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new ViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.view.setTag(list[position].productID);
        holder.cardBtn.setTag(list[position].productID);
        holder.cardName.setText(list[position].productName);
        holder.cardPrice.setText("R$ 250,00");
        holder.cardPar.setText("10x de R$25,00");
        holder.cardCount.setText(String.valueOf(list[position].count));

        holder.cardDivider.setVisibility(position < list.length - 1 ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }
}
