package com.notice.korail_market.ItemData;

import android.content.Context;

public class Food {
    String name;
    public int price;
    public static int count = 1;
    public int resId;
    public int totalPrice;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public int totalPrice() {
        totalPrice = price*count;
        return  totalPrice;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Food(String name, int price, int count, int resId){
        this.price = price;
        this.name = name;
        this.resId = resId;
        this.count = count;

    }
}
