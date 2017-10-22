package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.List;

public class MealActivity extends AppCompatActivity{
/*
    private RecyclerView mRecyclerView;
    private MealActivity.MealAdapter mAdapter;
    private List<Meal> mMeals = new ArrayList<>();
*/


    private static final String TAG = "mealactivity";
    private ImageView mImageView;
    private TextView mMealName;
    private TextView mMealCalories;
    private TextView mMealDescription;
    private Button mCreateOrderButton;
    private String mealName; // STRING
    private int mOrderNumber;

    public static Intent getIntent(Context packageContext, String mealName) {
        Intent i = new Intent(packageContext, MealActivity.class);
        System.out.println(mealName + " this is the meal name in getIntent");
        i.putExtra(TAG,mealName);
        return i;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mMealName = (TextView) findViewById(R.id.mealName);
        mMealCalories = (TextView) findViewById(R.id.mealCalories);
        mMealDescription = (TextView) findViewById(R.id.mealDescription);
        mCreateOrderButton = (Button) findViewById(R.id.createOrderButton);
        mealName = getIntent().getStringExtra(TAG);
        System.out.println(mealName + " This is my name before setText");
        mMealName.setText(mealName);
        mOrderNumber = 10;
        mCreateOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = PurchaseActivity.getIntent(MealActivity.this, mOrderNumber);
                startActivity(i);
            }
        });
    }
}