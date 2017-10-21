package com.prepper.prepper;

import java.util.List;

/**
 * Created by singh on 10/21/2017.
 */

public class Order {

    private int mOrderNumber;
    private List<Food> mFoodList;


    public void setOrderNumber(int num){
        mOrderNumber = num;
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
}
