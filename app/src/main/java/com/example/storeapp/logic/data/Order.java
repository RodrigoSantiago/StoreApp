package com.example.storeapp.logic.data;

import com.example.storeapp.Helper;

public class Order {

    public static Order[] test = new Order[]{
            new Order(0, "10/10/2020", "Validando", new String[]{"A","B", "Casdf"}, new float[]{1,2,3}, new int[]{1,1,1}, 10),
            new Order(0, "11/10/2020", "Aprovado", new String[]{"A","Basdfas", "C"}, new float[]{1,2,3}, new int[]{1,1,1}, 1),
            new Order(0, "12/10/2020", "Em Trânsito", new String[]{"Asadfas", "B", "C"}, new float[]{1,2,3}, new int[]{1,1,1}, 0),
            new Order(0, "0/10/2020", "Entregue", new String[]{"Aasdfas", "Basf", "Casdfas"}, new float[]{1,2,3}, new int[]{1,1,1}, 10),
            new Order(0, "10/12/2020", "Cancelado", new String[]{"Aasdf"}, new float[]{1}, new int[]{1}, 10),
    };
    public int id;

    public String date;
    public String status;
    public String[] products;
    public float[] productsValues;
    public int[] productsCout;
    public int par;

    private String title;
    private String description;
    private String priceDescription;

    public Order(int id, String date, String status, String[] products, float[] productsValues, int[] productsCout, int par) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.products = products;
        this.productsValues = productsValues;
        this.productsCout = productsCout;
        this.par = par;
    }

    public String getTitle() {
        if (title == null) {
            float t = 0;
            for (int i = 0; i < productsValues.length; i++) {
                t += productsValues[i] * productsCout[i];
            }
            title = "Total : " + Helper.format(t) + (par <= 1 ? " à vista" : (" em " + par + "x de " + Helper.format(t / par)));
        }
        return title;
    }

    public String getDescription() {
        if (description == null) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < products.length; i++) {
                if (i > 0) s.append("\n");

                s.append(products[i]);
            }
            description = s.toString();
        }
        return description;
    }

    public String getPriceDescription() {
        if (priceDescription == null) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < products.length; i++) {
                if (i > 0) s.append("\n");

                s.append(productsCout[i]).append(" x R$ ").append(Helper.format(productsValues[i]));
            }
            priceDescription = s.toString();
        }
        return priceDescription;
    }
}
