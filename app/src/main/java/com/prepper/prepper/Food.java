package com.prepper.prepper;

/**
 * Created by Quan on 10/21/2017.
 */

public class Food {

    String mFoodName;
    double mCalories;

    public Food(String mFoodName, double mCalories){
        this.mFoodName = mFoodName;
        this.mCalories = mCalories;
    }

    public String getmFoodName() {
        return mFoodName;
    }

    public void setmFoodName(String mFoodName) {
        this.mFoodName = mFoodName;
    }

    public double getmCalories() {
        return mCalories;
    }

    public void setmCalories(double mCalories) {
        this.mCalories = mCalories;
    }
}
