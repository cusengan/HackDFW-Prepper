package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class ProfileActivity extends AppCompatActivity {

    private static final String PROFILENAME = "profileName";
    private String userName;

    private Customer mCustomer;

    private TextView mUserName;
    private TextView mAddress;
    private TextView mEmail;
    private TextView mOrders;

    public static Intent newIntent(Context packageContext, String foodName){
        Intent intent = new Intent(packageContext, ProfileActivity.class);
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
        mOrders = (TextView)findViewById(R.id.orders);
        mOrders = (TextView)findViewById(R.id.orders);

        userName = getIntent().getStringExtra(PROFILENAME);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_container_orders, new OrderListFragment());
        ft.commit();
        System.out.println(userName + "LIT");
        updateUI();
    }

    private void updateUI() {
        AsyncTask task = new getCustomer().execute();
        try{
            mCustomer = (Customer)task.get();

        }catch(Exception e){

        }
        mUserName.setText(mCustomer.getName());
        mAddress.setText(mCustomer.getBilling());
        mEmail.setText(mCustomer.getEmail());
    }


    private class getCustomer extends AsyncTask<Void, Void, Customer> {

        @Override
        protected Customer doInBackground(Void... params) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getApplicationContext(),
                    "us-east-1:dfcd259e-839e-4393-b80e-287af96c1e92", // Identity pool ID
                    Regions.US_EAST_1 // Region
            );
            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
            Customer result = mapper.load(Customer.class, userName);

            return result;
        }

        @Override
        protected void onPostExecute(Customer customer) {
            mCustomer = customer;
        }

    }
}
