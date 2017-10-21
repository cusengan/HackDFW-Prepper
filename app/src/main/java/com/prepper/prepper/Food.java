package com.prepper.prepper;

/**
 * Created by Quan on 10/21/2017.
 */

public class Food {

    private String mFoodName;
    private double mCalories;
    private int mFats;
    private int mCarbs;
    private int mProtein;

    public Food(String mFoodName, double mCalories, int mFats, int mCarbs, int mProtein){
        this.mFoodName = mFoodName;
        this.mCalories = mCalories;
        this.mCarbs = mCarbs;
        this.mFats = mFats;
        this.mProtein = mProtein;
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

    public int getFats() {
        return mFats;
    }

    public void setFats(int mFats) {
        this.mFats = mFats;
    }

    public int getCarbs() {
        return mCarbs;
    }

    public void setCarbs(int mCarbs) {
        this.mCarbs = mCarbs;
    }

    public int getProtein() {
        return mProtein;
    }

    public void setProtein(int mProtein) {
        this.mProtein = mProtein;
    }
}
