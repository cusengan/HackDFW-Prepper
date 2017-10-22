package com.prepper.prepper;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.io.File;
import java.util.List;

/**
 * Created by singh on 10/21/2017.
 */

public class OrderFragment extends android.support.v4.app.Fragment {

    private static final String ARG_MEAL = "meal";

    private Meal mMeal;
    private String mMealNameString;
    private File mPhotoFile;
    private TextView mMealName;
    private TextView mMealCals;
    private TextView mMealDiscription;
    private Button mSubmit;

    public static OrderFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString(ARG_MEAL, name);

        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMealNameString = getArguments().getString(ARG_MEAL);
        AsyncTask task = new getMeals().execute();
        try {
            mMeal = (Meal) task.get();
        } catch (Exception e) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_order, container, false);

        mMealName = (TextView) v.findViewById(R.id.mealName);

        mMealName.setText(mMeal.getMealName());

        mMealCals = (TextView) v.findViewById(R.id.mealCalories);

        mMealCals.setText(Double.toString(mMeal.getCal()));


        mMealDiscription = (TextView) v.findViewById(R.id.mealDescription);


        mSubmit = (Button) v.findViewById(R.id.createOrderButton);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
            }
        });

        return v;
    }

    private class getMeals extends AsyncTask<Void, Void, Meal> {

        @Override
        protected Meal doInBackground(Void... params) {

            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getContext(),
                    "us-east-1:eeb724cc-0fe5-4fa9-9282-023995ffd07e", // Identity pool ID
                    Regions.US_EAST_1 // Region
            );

            AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);
            Meal result = mapper.load(Meal.class, mMealNameString);
            return result;
        }

        @Override
        protected void onPostExecute(Meal items) {
            mMeal = items;
        }
    }

}
