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
import com.example.storeapp.logic.data.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static int HEADER = 0;
    private static int CARD = 1;
    private static int FOOTER = 2;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public int type;
        public TextView cardName;
        public TextView cardPrice;
        public TextView cardPar;
        public LinearLayout cardPromoCircle;
        public TextView cardPromo;
        public Button[] buttons;

        public ViewHolder(View v, int type) {
            super(v);
            this.type = type;
            view = v;
            if (type == HEADER) {
                cardName = v.findViewById(R.id.text_title);
            } else if (type == CARD) {
                cardName = v.findViewById(R.id.card_name);
                cardPrice = v.findViewById(R.id.card_price);
                cardPar = v.findViewById(R.id.card_par);
                cardPromoCircle = v.findViewById(R.id.card_promo_circle);
                cardPromo = v.findViewById(R.id.card_promo);
            } else {
                buttons = new Button[7];
                buttons[0] = v.findViewById(R.id.btn_nav_prev);
                buttons[1] = v.findViewById(R.id.btn_nav1);
                buttons[2] = v.findViewById(R.id.btn_nav2);
                buttons[3] = v.findViewById(R.id.btn_nav3);
                buttons[4] = v.findViewById(R.id.btn_nav4);
                buttons[5] = v.findViewById(R.id.btn_nav5);
                buttons[6] = v.findViewById(R.id.btn_nav_next);
            }
        }
    }

    private Product[] list;
    private String title;
    private int pages;
    private int page;

    public ProductAdapter(String title, Product[] list, int pages, int page) {
        this.list = list;
        this.title = title;
        this.pages = pages;
        this.page = page;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : position < list.length + 1 ? CARD : FOOTER;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(viewType == HEADER ? R.layout.list_title :
                        viewType == CARD ? R.layout.card_product :
                        R.layout.list_navigation, parent, false);

        return new ViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder.type == HEADER) {
            holder.cardName.setText(title);
        } else if (holder.type == CARD) {
            position -= 1;

            holder.view.setTag(list[position].id);
            holder.cardName.setText(list[position].name);
            holder.cardPrice.setText(list[position].getPriceText());
            holder.cardPar.setText(list[position].getParText());
            if (list[position].promo > 0) {
                holder.cardPromoCircle.setVisibility(View.VISIBLE);
                holder.cardPromo.setText(list[position].getPromoText());
            } else {
                holder.cardPromoCircle.setVisibility(View.GONE);
            }
        } else {
            holder.buttons[0].setVisibility(page > 0 ? View.VISIBLE : View.GONE);
            holder.buttons[1].setVisibility(View.VISIBLE);
            holder.buttons[2].setVisibility(View.VISIBLE);
            holder.buttons[3].setVisibility(pages > 2 ? View.VISIBLE : View.GONE);
            holder.buttons[4].setVisibility(pages > 3 ? View.VISIBLE : View.GONE);
            holder.buttons[5].setVisibility(pages > 4 ? View.VISIBLE : View.GONE);
            holder.buttons[6].setVisibility(page < pages - 1 ? View.VISIBLE : View.GONE);

            Drawable d = holder.view.getResources().getDrawable(R.drawable.dw_button_circle_act);
            if (pages <= 5) {
                holder.buttons[1].setText("1");
                holder.buttons[2].setText("2");
                holder.buttons[3].setText("3");
                holder.buttons[4].setText("4");
                holder.buttons[5].setText("5");
                holder.buttons[page + 1].setBackground(d);
            } else {
                holder.buttons[1].setText("1");
                if (page < 3) {
                    holder.buttons[2].setText("2");
                    holder.buttons[3].setText("3");
                    holder.buttons[4].setText("4");
                    holder.buttons[page + 1].setBackground(d);
                } else if (page >= pages - 2) {
                    holder.buttons[2].setText(String.valueOf(pages - 3));
                    holder.buttons[3].setText(String.valueOf(pages - 2));
                    holder.buttons[4].setText(String.valueOf(pages - 1));
                    holder.buttons[5 + (page - pages + 1)].setBackground(d);
                } else {
                    holder.buttons[2].setText(String.valueOf(page));
                    holder.buttons[3].setText(String.valueOf(page + 1));
                    holder.buttons[4].setText(String.valueOf(page + 2));
                    holder.buttons[3].setBackground(d);
                }

                holder.buttons[5].setText(String.valueOf(pages));
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.length + (pages > 1 ? 2 : 1);
    }
}
