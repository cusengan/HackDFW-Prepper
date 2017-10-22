package com.prepper.prepper;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshaller;

/**
 * Created by truon on 10/22/2017.
 */

public class FoodMarshaller implements DynamoDBMarshaller<Food> {
    @Override
    public String marshall(Food getterReturnResult) {
        return null;
    }

    @Override
    public Food unmarshall(Class<Food> clazz, String obj) {
        return null;
    }
}
