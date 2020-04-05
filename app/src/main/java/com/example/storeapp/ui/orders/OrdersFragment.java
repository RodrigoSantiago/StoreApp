package com.example.storeapp.ui.orders;

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
import com.example.storeapp.logic.data.Order;
import com.example.storeapp.logic.data.Product;
import com.example.storeapp.ui.OrderAdapter;
import com.example.storeapp.ui.ProductAdapter;
import com.example.storeapp.ui.home.HomeViewModel;

public class OrdersFragment extends Fragment {
    private OrdersViewModel ordersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);

        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        final RecyclerView ordersView = root.findViewById(R.id.orders_view);
        ordersView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        ordersView.setLayoutManager(layoutManager);

        OrderAdapter mAdapter = new OrderAdapter(Order.test);
        ordersView.setAdapter(mAdapter);
        return root;
    }
}
