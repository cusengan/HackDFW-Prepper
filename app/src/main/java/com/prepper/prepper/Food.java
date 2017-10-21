package com.prepper.prepper;

/**
 * Created by Quan on 10/21/2017.
 */

public class Food {

    private String mFoodName;
    private double mCalories;

    public Food(String mFoodName, double mCalories){
        this.mFoodName = mFoodName;
        this.mCalories = mCalories;
    }

    public String getFoodName() {
        return mFoodName;
    }

    public void setFoodName(String mFoodName) {
        this.mFoodName = mFoodName;
    }

    public double getCalories() {
        return mCalories;
    }

    public void setCalories(double mCalories) {
        this.mCalories = mCalories;
    }
}
