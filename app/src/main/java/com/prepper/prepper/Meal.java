package com.prepper.prepper;

import android.os.AsyncTask;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.prepper.prepper.Food;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Quan on 10/21/2017.
 */

@DynamoDBTable(tableName = "Meals")
public class Meal {

    private String mName;
    private int mCal;
    private int mProtein;
    private int mCarbs;
    private int mFats;
    private List<Food> mFoods;
    private List<String> foodNames;

    public Meal(List<Food> foodList, String name){
        calculateCals(foodList);
        calculateCarbs(foodList);
        calculateProtein(foodList);
        calculateFats(foodList);
        this.mName = name;
    }

    public Meal() {
        this.mName = getName();
        this.foodNames = getFoodNames();
    }

    private void calculateCals(List<Food> foodList){
        this.mCal = 0;
        for(Food food: foodList) {
            this.mCal += food.getCalories();
        }
    }

    private void calculateCarbs(List<Food> foodList){
        this.mCarbs = 0;
        for(Food food: foodList){
            this.mCarbs += food.getCarbs();
        }
    }

    private void calculateFats(List<Food> foodList){
        this.mFats = 0;
        for(Food food: foodList){
            this.mFats += food.getFats();
        }
    }

    private void calculateProtein(List<Food> foodList){
        this.mProtein = 0;
        for(Food food: foodList){
            this.mProtein += food.getProtein();
        }
    }

    @DynamoDBAttribute (attributeName = "Food")
    public List<String> getFoodNames() { return foodNames; }

    @DynamoDBAttribute(attributeName = "MealName")
    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getCal() {
        return mCal;
    }

    public int getProtein() {
        return mProtein;
    }

    public int getCarbs() {
        return mCarbs;
    }

    public int getFats() {
        return mFats;
    }

    public List<Food> getFoods() {
        return mFoods;
    }

    public void setFoodNames(List<String> names){
        this.foodNames = names;
    }
}
