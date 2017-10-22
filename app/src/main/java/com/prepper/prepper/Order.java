package com.prepper.prepper;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshalling;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

/**
 * Created by singh on 10/21/2017.
 */

@DynamoDBTable(tableName = "Orders")
public class Order {

    private String mOrderNumber;
//    private List<Food> mFoodList;
    private List<String> mFoodNames;
    private Customer mCustomer;

    public Order(String num){
        this.mOrderNumber = num;
    }

    public Order(){
        mOrderNumber = "0";
    }

    @DynamoDBHashKey(attributeName = "Order Number")
    public String getOrderNumber(){
        return mOrderNumber;
    }
    public void setOrderNumber(String num){
        this.mOrderNumber = num;
    }

//    @DynamoDBMarshalling(marshallerClass = FoodMarshaller.class)
//    public void addFood(Food food){
//        mFoodList.add(food);
//    }
//
//    @DynamoDBMarshalling(marshallerClass = FoodMarshaller.class)
//    public List<Food> getFoodList(){
//        return mFoodList;
//    }
//    public void setFoods(List<Food> list){
//        mFoodList = list;
//    }

    @DynamoDBMarshalling(marshallerClass = CustomerMarshaller.class)
    public Customer getCustomer(){
        return mCustomer;
    }
    public void setCustomer(Customer customer){
        this.mCustomer = customer;
    }

    @DynamoDBAttribute(attributeName = "Meals")
    public List<String> getFoods(){
        return mFoodNames;
    }
    public void setFoods(List<String> list){
        mFoodNames = list;
    }

}
