package com.prepper.prepper;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by Quan on 10/21/2017.
 */

@DynamoDBTable(tableName = "Foods")
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

    public Food(){
        this.mFoodName = "food";
        this.mCalories = 0;
        this.mFats = 0;
        this.mCarbs = 0;
        this.mProtein = 0;
    }

    public Food(String mFoodName, double mCalories){
        this.mFoodName = mFoodName;
        this.mCalories = mCalories;
    }

    @DynamoDBHashKey(attributeName = "FoodName")
    public String getFoodName() {
        return mFoodName;
    }

    public void setFoodName(String mFoodName) {
        this.mFoodName = mFoodName;
    }

    @DynamoDBAttribute(attributeName = "Calories")
    public double getCalories() {
        return mCalories;
    }

    public void setCalories(double mCalories) {
        this.mCalories = mCalories;
    }
//
//    @DynamoDBAttribute(attributeName = "Fat")
//    public int getFats() { return mFats; }
//
//    public void setFats(int mFats) { this.mFats = mFats; }

    @DynamoDBAttribute(attributeName = "Carb")
    public int getCarbs() { return mCarbs; }

    public void setCarbs(int mCarbs) { this.mCarbs = mCarbs; }
//
//    @DynamoDBAttribute(attributeName = "Protein")
//    public int getProtein() { return mProtein; }
//
//    public void setProtein(int mProtein) { this.mProtein = mProtein; }
}
