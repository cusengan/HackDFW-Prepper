package com.prepper.prepper;

import com.prepper.prepper.Food;

import java.util.List;

/**
 * Created by Quan on 10/21/2017.
 */

public class Meal {

    private String mName;
    private String mDescription;
    private int mCal;
    private int mProtein;
    private int mCarbs;
    private int mFats;
    private List<Food> mFoods;

    public Meal(List<Food> foodList){
//        calculateCals(foodList);
//        calculateCarbs(foodList);
//        calculateProtein(foodList);
//        calculateFats(foodList);
    }

    private void calculateCals(List<Food> foodList){
        this.mCal = 0;
        for(Food food: foodList) {
            this.mCal += food.getCalories();
        }
    }

//    private void calculateCarbs(List<Food> foodList){
//        this.mCarbs = 0;
//        for(Food food: foodList){
//            this.mCarbs += food.getCarbs();
//        }
//    }
//
//    private void calculateFats(List<Food> foodList){
//        this.mFats = 0;
//        for(Food food: foodList){
//            this.mFats += food.getFats();
//        }
//    }
//
//    private void calculateProtein(List<Food> foodList){
//        this.mProtein = 0;
//        for(Food food: foodList){
//            this.mProtein += food.getProtein();
//        }
//    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmCal() {
        return mCal;
    }

    public int getmProtein() {
        return mProtein;
    }

    public int getmCarbs() {
        return mCarbs;
    }

    public int getmFats() {
        return mFats;
    }

    public List<Food> getmFoods() {
        return mFoods;
    }
}
