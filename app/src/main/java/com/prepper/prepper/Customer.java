package com.prepper.prepper;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshaller;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by Quan on 10/21/2017.
 */

@DynamoDBTable(tableName = "Customers")
public class Customer{

    private String mName;
    private double mCreditCard;
    private String mBilling;
    private String mEmail;

    public Customer(String mName, int mCreditCard, String mBilling, String mEmail){
        this.mName = mName;
        this.mCreditCard = mCreditCard;
        this.mBilling =  mBilling;
        this.mEmail = mEmail;
    }

    public Customer(){
        this.mName = "name";
        this.mCreditCard = 1211.0;
        this.mBilling =  "address";
        this.mEmail = "email";
    }

    @DynamoDBHashKey(attributeName = "UserName")
    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    @DynamoDBAttribute(attributeName = "CreditCard")
    public double getCreditCard() {
        return mCreditCard;
    }

    public void setCreditCard(double mCreditCard) {
        this.mCreditCard = mCreditCard;
    }

    @DynamoDBAttribute(attributeName = "Address")
    public String getBilling() {
        return mBilling;
    }

    public void setBilling(String mBilling) {
        this.mBilling = mBilling;
    }

    @DynamoDBAttribute(attributeName = "Email")
    public String getEmail() {
        return mEmail;
    }
    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}