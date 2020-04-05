package com.example.storeapp.logic.data;

import com.example.storeapp.Helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class Product {

    public static Product[] test = new Product[]{
            new Product(0, "Teste 001 de nome longuissimo para um produto com pouco espaço de texto", null, 0, 0, 1, 0),
            new Product(1, "Teste 002", null, 100, 0, 1, 0),
            new Product(2, "Teste 003", null, 250, 0, 1, 0),
            new Product(3, "Teste 004", null, 1000, 0, 1, 50),
            new Product(4, "Teste 005", null, 10000, 0, 1, 0),
            new Product(5, "Teste 006", null, 10, 0, 1, 0),
            new Product(6, "Teste 007", null, 5, 0, 1, 10),
            new Product(7, "Teste 008", null, 0, 0, 1, 0),
            new Product(7, "Teste 008", null, 0, 0, 1, 0)};

    public int id;
    public String name;
    public String image;
    public float price;
    public float pricePar;
    public int maxPar;
    public int promo;

    private String priceText;
    private String parText;
    private String promoText;

    public Product(int id, String name, String image, float price, float pricePar, int maxPar, int promo) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.pricePar = pricePar;
        this.maxPar = maxPar;
        this.promo = promo;
    }

    public void set(int id, String name, String image, float price, float pricePar, int maxPar, int promo) {
        this.id = id;
        this.name = name;
        this.image = image;
        if (price != this.price) {
            this.price = price;
            priceText = null;
        }
        if (pricePar != this.pricePar || maxPar != this.maxPar) {
            this.pricePar = pricePar;
            this.maxPar = maxPar;
            parText = null;
        }
        if (promo != this.promo) {
            this.promo = promo;
            promoText = null;
        }
    }

    public String getPriceText() {
        if (priceText == null) {
            priceText = "R$ " + Helper.formatter.format(price);
        }
        return priceText;
    }

    public String getParText() {
        if (parText == null) {
            parText = maxPar <= 0 ? ("") : (maxPar + "x de R$ " + Helper.formatter.format(pricePar / maxPar));
        }
        return parText;
    }

    public String getPromoText() {
        if (promoText == null) {
            promoText = promo <= 0 ? ("") : (promo + "%");
        }
        return promoText;
    }
}
