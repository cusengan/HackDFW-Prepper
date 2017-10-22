package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.List;

public class Profile extends AppCompatActivity {

    private static final String PROFILENAME = "profileName";
    private String userName;

    private Customer mCustomer;

    private TextView mUserName;
    private TextView mAddress;
    private TextView mEmail;

    public static Intent newIntent(Context packageContext, String foodName){
        Intent intent = new Intent(packageContext, Profile.class);
        intent.putExtra(PROFILENAME, foodName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mUserName = (TextView)findViewById(R.id.userName);
        mAddress = (TextView)findViewById(R.id.address);
        mEmail = (TextView)findViewById(R.id.email);

        userName = getIntent().getStringExtra(PROFILENAME);
        System.out.println(userName + "LIT");
        updateUI();
    }

    private void updateUI() {
        AsyncTask task = new getCustomer().execute();
        try{
            mCustomer = (Customer)task.get();

        }catch(Exception e){

        }
        mUserName.setText(mCustomer.getmName());
        System.out.println(mCustomer.getmName());
    }


    private class getCustomer extends AsyncTask<Void, Void, Customer> {

        @Override
        protected Customer doInBackground(Void... params) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getApplicationContext(),
                    "us-east-1:2a52890a-4b46-4922-9624-3419adad9e02", // Identity pool ID
                    Regions.US_EAST_1 // Region
            );

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            Customer result = mapper.load(Customer.class, userName);

            return result;
        }

        @Override
        protected void onPostExecute(Customer items) {

        }

    }
}
