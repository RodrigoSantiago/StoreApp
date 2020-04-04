package com.example.storeapp.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapp.R;
import com.example.storeapp.logic.data.Item;
import com.example.storeapp.logic.data.Product;
import com.example.storeapp.ui.CartAdapter;
import com.example.storeapp.ui.ProductAdapter;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        final RecyclerView productsView = root.findViewById(R.id.product_view);
        productsView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        productsView.setLayoutManager(layoutManager);

        CartAdapter mAdapter = new CartAdapter(Item.test);
        productsView.setAdapter(mAdapter);
        return root;
    }
}
