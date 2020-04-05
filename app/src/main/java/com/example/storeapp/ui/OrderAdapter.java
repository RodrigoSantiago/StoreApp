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
import com.example.storeapp.logic.data.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public int type;
        public TextView txtDate;
        public TextView txtTitle;
        public View bgStatus;
        public TextView txtStatus;
        public TextView txtDescription;
        public TextView txtDescription2;

        public ViewHolder(View v, int type) {
            super(v);
            this.type = type;
            view = v;
            txtDate = v.findViewById(R.id.txt_date);
            txtTitle = v.findViewById(R.id.txt_title);
            bgStatus = v.findViewById(R.id.bg_status);
            txtStatus = v.findViewById(R.id.txt_status);
            txtDescription = v.findViewById(R.id.txt_description);
            txtDescription2 = v.findViewById(R.id.txt_description2);
        }
    }

    private Order[] list;
    private Drawable blueDrawable;
    private Drawable greenDrawable;
    private Drawable yellowDrawable;
    private Drawable redDrawable;

    public OrderAdapter(Order[] list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_order, parent, false);

        return new ViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.view.setTag(list[position].productID);
        holder.txtDate.setText(list[position].date);
        holder.txtTitle.setText(list[position].getTitle());
        holder.txtStatus.setText(list[position].status);
        holder.txtDescription.setText(list[position].getDescription());
        holder.txtDescription2.setText(list[position].getPriceDescription());

        Drawable status;
        switch (list[position].status) {
            case "Validando" : status = getYellowDrawable(holder.view) ; break;
            case "Aprovado" :
            case "Em Trânsito" : status = getBlueDrawable(holder.view) ; break;
            case "Entregue" : status = getGreenDrawable(holder.view) ; break;
            default : status = getRedDrawable(holder.view) ; break;
        }

        holder.bgStatus.setBackground(status);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    private Drawable getRedDrawable(View view) {
        if (redDrawable == null) {
            redDrawable = view.getResources().getDrawable(R.drawable.dw_status_red);
        }
        return redDrawable;
    }

    private Drawable getYellowDrawable(View view) {
        if (yellowDrawable == null) {
            yellowDrawable = view.getResources().getDrawable(R.drawable.dw_status_yellow);
        }
        return yellowDrawable;
    }

    private Drawable getBlueDrawable(View view) {
        if (blueDrawable == null) {
            blueDrawable = view.getResources().getDrawable(R.drawable.dw_status_blue);
        }
        return blueDrawable;
    }

    private Drawable getGreenDrawable(View view) {
        if (greenDrawable == null) {
            greenDrawable = view.getResources().getDrawable(R.drawable.dw_status_green);
        }
        return greenDrawable;
    }
}
