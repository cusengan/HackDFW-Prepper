package com.prepper.prepper;

import java.util.List;

/**
 * Created by singh on 10/21/2017.
 */

public class Order {

    private int mOrderNumber;
    private List<Food> mFoodList;
    private Customer mCustomer;

    public Order(int num){
        this.mOrderNumber = num;
    }

    public void setOrderNumber(int num){
        this.mOrderNumber = num;
    }

    public int getOrderNumber(){
        return mOrderNumber;
    }

    public void addFood(Food food){
        mFoodList.add(food);
    }

    public List<Food> getFoodList(){
        return mFoodList;
    }

    public void setCustomer(Customer customer){
        this.mCustomer = customer;
    }

    public Customer getCustomer(){
        return mCustomer;
    }
}
