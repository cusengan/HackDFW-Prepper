package com.prepper.prepper;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshaller;

/**
 * Created by truon on 10/22/2017.
 */

public class CustomerMarshaller implements DynamoDBMarshaller<Customer> {
    @Override
    public String marshall(Customer getterReturnResult) {
        return "lit";
    }

    @Override
    public Customer unmarshall(Class<Customer> clazz, String obj) {
        return new Customer();
    }
}
