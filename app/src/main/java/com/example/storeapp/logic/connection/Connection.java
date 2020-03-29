package com.example.storeapp.logic.connection;

import com.example.storeapp.logic.data.Product;

public class Connection {
    private static int itemsPerPage = 12;

    public Product[] loadSearch(Product[] products) {
        if (products == null) {
            products = new Product[itemsPerPage];
        }



        return products;
    }
}
