package com.prepper.prepper;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by Quan on 10/21/2017.
 */

@DynamoDBTable(tableName = "Customer")
public class Customer {

    private String mName;
    private int mCreditCard;
    private String mBilling;
    private String mEmail;

    public Customer(String mName, int mCreditCard, String mBilling, String mEmail){
        this.mName = mName;
        this.mCreditCard = mCreditCard;
        this.mBilling =  mBilling;
        this.mEmail = mEmail;
    }

    @DynamoDBHashKey(attributeName = "")
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    @DynamoDBAttribute(attributeName = "CreditCard")
    public int getmCreditCard() {
        return mCreditCard;
    }

    public void setmCreditCard(int mCreditCard) {
        this.mCreditCard = mCreditCard;
    }

    @DynamoDBAttribute(attributeName = "Address")
    public String getmBilling() {
        return mBilling;
    }

    public void setmBilling(String mBilling) {
        this.mBilling = mBilling;
    }

    @DynamoDBAttribute(attributeName = "Email")
    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}