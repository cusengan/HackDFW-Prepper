package com.prepper.prepper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by singh on 10/21/2017.
 */

public class MealPagerActivity  extends AppCompatActivity {
    private static final String EXTRA_ORDER = "com.prepper.prepper.OrderActivity";

    private ViewPager mViewPager;
    private List<Meal> mMeals = new ArrayList<>();


    public static Intent newIntent(Context packageContext, String foodName){
        Intent intent = new Intent(packageContext, MealPagerActivity.class);
        intent.putExtra(EXTRA_ORDER, foodName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_pager);

        String name = getIntent().getStringExtra(EXTRA_ORDER);

        mViewPager = (ViewPager) findViewById(R.id.food_view_pager);

        AsyncTask task = new getMeals().execute();
        try{
            mMeals = (List<Meal>)task.get();
            System.out.println(mMeals.size() + "mealPager");
        }catch(Exception e){

        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Meal meal = mMeals.get(position);
                return OrderFragment.newInstance(meal.getMealName());
            }

            @Override
            public int getCount() {
                return mMeals.size();
            }
        });

        for(int i = 0; i < mMeals.size(); i++){
            if(mMeals.get(i).getMealName().equals(name)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    private class getMeals extends AsyncTask<Void, Void, List<Meal>> {

        @Override
        protected List<Meal> doInBackground(Void... params) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getApplicationContext(),
                    "us-east-1:eeb724cc-0fe5-4fa9-9282-023995ffd07e", // Identity pool ID
                    Regions.US_EAST_1 // Region
            );

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            PaginatedScanList<Meal> result = mapper.scan(Meal.class, scanExpression);
            return result;
        }

        @Override
        protected void onPostExecute(List<Meal> items) {
            mMeals = items;
        }

    }

}