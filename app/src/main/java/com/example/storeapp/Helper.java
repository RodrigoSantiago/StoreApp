package com.example.storeapp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Helper {
    public static DecimalFormat formatter = new DecimalFormat("#,##0.00",new DecimalFormatSymbols());

    public static String format(float currency) {
        return formatter.format(currency);
    }
}
