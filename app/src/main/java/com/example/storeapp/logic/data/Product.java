package com.example.storeapp.logic.data;

public class Product {
    public int id;
    public String name;
    public String image;
    public float price;
    public float pricePar;
    public int maxPar;

    public Product(int id, String name, String image, float price, float pricePar, int maxPar) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.pricePar = pricePar;
        this.maxPar = maxPar;
    }
}
