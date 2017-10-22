package com.prepper.prepper;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

/**
 * Created by singh on 10/21/2017.
 */

@DynamoDBTable(tableName = "Orders")
public class Order {

    private int mOrderNumber;
    private List<Food> mFoodList;
    private List<String> mFoodNames;
    private Customer mCustomer;

    public Order(int num){
        this.mOrderNumber = num;
    }

    public void setOrderNumber(int num){
        this.mOrderNumber = num;
    }
    @DynamoDBHashKey(attributeName = "Order Number")
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

    @DynamoDBAttribute(attributeName = "UserName")
    public Customer getCustomer(){
        return mCustomer;
    }

    @DynamoDBAttribute(attributeName = "Meals")
    public List<String> getFoods(){
        return mFoodNames;
    }
}
