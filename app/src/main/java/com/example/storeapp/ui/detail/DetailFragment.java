package com.example.storeapp.ui.detail;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.storeapp.R;
import com.example.storeapp.logic.data.Product;
import com.example.storeapp.ui.ProductAdapter;

public class DetailFragment extends Fragment {

    private DetailViewModel detailViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        View root = inflater.inflate(R.layout.fragment_detail, container, false);

        final RecyclerView productsView = root.findViewById(R.id.product_view);
        productsView.setHasFixedSize(true);

        int dp = getActivity().getResources().getConfiguration().screenWidthDp;
        final int colums = dp < 400 ? 2 : dp < 600 ? 3 : 4;

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), colums);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 || position == Product.test.length + 1 ? colums : 1;
            }
        });
        productsView.setLayoutManager(layoutManager);

        ProductAdapter mAdapter = new ProductAdapter("Recomendados", Product.test, 0, 0);
        productsView.setAdapter(mAdapter);
        return root;
    }
}
