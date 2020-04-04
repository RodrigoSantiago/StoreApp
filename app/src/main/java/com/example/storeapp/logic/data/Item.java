package com.example.storeapp.logic.data;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Item {

    private static DecimalFormat formatter = new DecimalFormat("#,##0.00",new DecimalFormatSymbols());

    public static Item[] test = new Item[]{
            new Item(1, 0, "Teste 001 de nome longuissimo para um produto com pouco espaço de texto", null),
            new Item(1, 1, "Teste 001 de nome longuissimo", null),
            new Item(1, 2, "Teste 001 de nome longuissimo", null),
            new Item(1, 3, "Teste 001 de nome longuissimo", null),
            new Item(1, 4, "Teste 001 de nome longuissimo", null),
            new Item(1, 5, "Teste 001 de nome longuissimo", null),
            new Item(1, 6, "Teste 001 de nome longuissimo", null),
            new Item(1, 7, "Teste 001 de nome longuissimo", null)};

    public int count;
    public int productID;
    public String productName;
    public String productImage;
    public float[] productPriceTable;

    public Item(int count, int productID, String productName, String productImage) {
        this.count = count;
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
    }
}
