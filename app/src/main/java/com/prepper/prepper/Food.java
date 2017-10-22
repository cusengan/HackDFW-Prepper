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
    private double mFats;
    private double mCarbs;
    private double mProtein;

    public Food(String mFoodName, double mCalories, double mFats, double mCarbs, double mProtein){
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

    @DynamoDBAttribute(attributeName = "Fat")
    public double getFats() { return mFats; }

    public void setFats(double mFats) { this.mFats = mFats; }

    @DynamoDBAttribute(attributeName = "Carb")
    public double getCarbs() { return mCarbs; }

    public void setCarbs(double mCarbs) { this.mCarbs = mCarbs; }

    @DynamoDBAttribute(attributeName = "Protein")
    public double getProtein() { return mProtein; }

    public void setProtein(double mProtein) { this.mProtein = mProtein; }
}
